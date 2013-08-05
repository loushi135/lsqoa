package com.xpsoft.oa.service.admin.impl;


import java.io.IOException;
import java.io.OutputStream;
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
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.oa.dao.admin.ExpressApplyDao;
import com.xpsoft.oa.model.admin.ExpressApply;
import com.xpsoft.oa.model.admin.ExpressApplyExport;
import com.xpsoft.oa.service.admin.ExpressApplyService;

public class ExpressApplyServiceImpl extends BaseServiceImpl<ExpressApply> implements ExpressApplyService{
	private ExpressApplyDao dao;
	
	public ExpressApplyServiceImpl(ExpressApplyDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public void export(Map<String, Object> dataMap)throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream os = response.getOutputStream();// 取得输出流   

        response.setHeader("Content-disposition", "attachment; filename="+new String("快递分摊统计".getBytes("GB2312"),"8859_1")+".xls");// 设定输出文件头   
        response.setContentType("application/vnd.ms-excel");// 定义输出类型 

        List<ExpressApplyExport> eaExportList = (List<ExpressApplyExport>)dataMap.get("eaExportList");
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
			ws.setColumnView(1, 20); // 设置第二列宽
			ws.setColumnView(3, 40); // 设置第二列宽
			Label label1 = new Label(2, 0, "快递分摊统计");
			Label label20 = new Label(0, 1, "序号");
			Label label21 = new Label(1, 1, "快递单号");
			Label label22 = new Label(2, 1, "部门/项目");
			Label label23 = new Label(3, 1, "部门名/项目名");
			Label label24 = new Label(4, 1, "经办人");
			Label label25 = new Label(5, 1, "时间");
			try {
				ws.addCell(label1);
				ws.addCell(label20);
				ws.addCell(label21);
				ws.addCell(label22);
				ws.addCell(label23);
				ws.addCell(label24);
				ws.addCell(label25);
				int length = eaExportList.size();
				int startRow = 2;
				//下面开始添加单元格
				for(int i=0;i<length;i++){
					ExpressApplyExport expressApplyExport = eaExportList.get(i);
					int curRow = startRow+i;
					int index = i+1;
					jxl.write.Number indexNumberLabel = new jxl.write.Number(0, curRow, Double.parseDouble(index+""));
					Label expressNoLabel = new Label(1, curRow, expressApplyExport.getExpressNo());
					Label typeLabel = new Label(2, curRow, expressApplyExport.getDispatchType());
					Label deptOrPro = null;
					if(expressApplyExport.getProjectNew()!=null){
						deptOrPro = new Label(3, curRow, expressApplyExport.getProjectNew().getProName()+"");
					}else if(expressApplyExport.getDept()!=null){
						deptOrPro = new Label(3, curRow, expressApplyExport.getDept().getDepName()+"");
					}else{
						deptOrPro = new Label(3, curRow, "");
					}
					Label applyerLabel = new Label(4, curRow, expressApplyExport.getApplyer());
					Label timeLabel = new Label(5, curRow, DateUtil.format(expressApplyExport.getApplyDate(), "yyyy-MM-dd"));
					ws.addCell(indexNumberLabel);
					ws.addCell(expressNoLabel);
					ws.addCell(typeLabel);
					ws.addCell(deptOrPro);
					ws.addCell(applyerLabel);
					ws.addCell(timeLabel);
				}
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