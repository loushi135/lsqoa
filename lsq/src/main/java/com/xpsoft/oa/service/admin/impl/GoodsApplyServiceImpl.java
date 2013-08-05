package com.xpsoft.oa.service.admin.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts2.ServletActionContext;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.admin.GoodsApplyDao;
import com.xpsoft.oa.model.admin.GoodApplyDep;
import com.xpsoft.oa.model.admin.GoodApplyTotal;
import com.xpsoft.oa.model.admin.GoodsApply;
import com.xpsoft.oa.service.admin.GoodsApplyService;

public class GoodsApplyServiceImpl extends BaseServiceImpl<GoodsApply>
		implements GoodsApplyService {
	private GoodsApplyDao dao;

	public GoodsApplyServiceImpl(GoodsApplyDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<GoodApplyTotal> getGoodApplyTotals(String month) {
		// TODO Auto-generated method stub
		return dao.getGoodApplyTotals(month);
	}

	@Override
	public List<String> getDeptList(String month) {
		// TODO Auto-generated method stub
		return dao.getDeptList(month);
	}

	@Override
	public void export(Map<String, Object> dataMap)throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream os = response.getOutputStream();// 取得输出流   

        response.setHeader("Content-disposition", "attachment; filename="+new String("办公用品汇总统计".getBytes("GB2312"),"8859_1")+".xls");// 设定输出文件头   
        response.setContentType("application/vnd.ms-excel");// 定义输出类型 
        
		List<GoodApplyTotal> gaTotal = (List<GoodApplyTotal>)dataMap.get("gaTotal");
		List<String> deptList = (List<String>)dataMap.get("deptList");
		WritableWorkbook wwb = null;
		try {
			//首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(wwb!=null){
			//创建一个可写入的工作表
			//Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
			WritableSheet ws = wwb.createSheet("sheet1", 0);
//			CellView cellView = new CellView();  
//			cellView.set; //设置自动大小  
//			ws.setColumnView(1, cellView);//根据内容自动设置列宽  
			ws.setColumnView(1, 25); // 设置第二列宽
			Label label21 = new Label(0, 2, "序号");
			Label label22 = new Label(1, 2, "物品名称");
			Label label23 = new Label(2, 2, "入库数量");
			Label label24 = new Label(3, 2, "单价");
			try {
				ws.addCell(label21);
				ws.addCell(label22);
				ws.addCell(label23);
				ws.addCell(label24);
				int deptLen = deptList.size();
				int colStart = 3;
				for(int x=0;x<deptLen;x++){
					int spanStart = colStart+x*2+1;
					Label depNameLabel = new Label(spanStart, 1, deptList.get(x));
					Label numLabel = new Label(colStart+x*2+1,2,"数量");
					Label amountLabel = new Label(colStart+x*2+2,2,"金额");
					ws.addCell(depNameLabel);
					ws.addCell(numLabel);
					ws.addCell(amountLabel);
				}
				Label rowAmountLabel = new Label(colStart+deptLen*2+1, 2, "总金额");
				ws.addCell(rowAmountLabel);
				int length = gaTotal.size();
				int startRow = 3;
				BigDecimal allRowAmount = new BigDecimal(0);//最下面的所有行总金额
				List<BigDecimal> deptTotalAmountList = new ArrayList<BigDecimal>(deptLen);//定义所有部门对应的总金额
				for(int x=0;x<deptLen;x++){
					deptTotalAmountList.add(new BigDecimal(0));
				}
				for(int i=0;i<length;i++){
					GoodApplyTotal ga = gaTotal.get(i);
					int curRow = startRow+i;
					jxl.write.Number indexLabel = new jxl.write.Number(0, curRow, i+1);
					Label nameLabel = new Label(1, curRow, ga.getGoodsName());
					jxl.write.Number totalNumLabel = new jxl.write.Number(2, curRow, ga.getTotalNum());
					jxl.write.Number priceLabel = new jxl.write.Number(3, curRow, ga.getPrice().doubleValue());
					ws.addCell(indexLabel);
					ws.addCell(nameLabel);
					ws.addCell(totalNumLabel);
					ws.addCell(priceLabel);
					List<GoodApplyDep> gaDepList = ga.getGoodApplyDepList();
					for(int x=0;x<deptLen;x++){
						String depName = deptList.get(x);
						int curNum = 0;
						BigDecimal curAmount = new BigDecimal(0);
						for(GoodApplyDep gaDep:gaDepList){
							if(depName.equals(gaDep.getDepName())){
								curNum = gaDep.getNum();
								curAmount = gaDep.getAmount();
								deptTotalAmountList.set(x, deptTotalAmountList.get(x).add(curAmount));
							}
						}
						jxl.write.Number numDataLabel = new jxl.write.Number(colStart+x*2+1,curRow,curNum);
						jxl.write.Number amountDataLabel = new jxl.write.Number(colStart+x*2+2,curRow,curAmount.doubleValue());
						ws.addCell(numDataLabel);
						ws.addCell(amountDataLabel);
					}
					allRowAmount=allRowAmount.add(ga.getTotalAmount());
					jxl.write.Number rowDataAmountLabel = new jxl.write.Number(colStart+deptLen*2+1, curRow, ga.getTotalAmount().doubleValue());
					ws.addCell(rowDataAmountLabel);
				}
				Label totalCalcLabel = new Label(1, startRow+length, "总计：");
				ws.addCell(totalCalcLabel);
				for(int x=0;x<deptLen;x++){
//					Label deptNameLabel = new Label(colStart+x*2+1,startRow+length,deptList.get(x));
					jxl.write.Number deptAmountLabel = new jxl.write.Number(colStart+x*2+2,startRow+length,deptTotalAmountList.get(x).doubleValue());
//					ws.addCell(deptNameLabel);
					ws.addCell(deptAmountLabel);
				}
				jxl.write.Number allRowAmountLabel = new jxl.write.Number(colStart+deptLen*2+1, startRow+length, allRowAmount.doubleValue());
				ws.addCell(allRowAmountLabel);
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
			try {
				//从内存中写入文件中
				wwb.write();
				//关闭资源，释放内存
				wwb.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
