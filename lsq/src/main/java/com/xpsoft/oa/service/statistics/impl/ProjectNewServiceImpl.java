package com.xpsoft.oa.service.statistics.impl;


import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.BeanUtil;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.util.ExcelUtils;
import com.xpsoft.core.util.Trans2RMB;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.statistics.BankpayapplyDao;
import com.xpsoft.oa.dao.statistics.ProjectNewDao;
import com.xpsoft.oa.dao.statistics.ProjectPercentageDao;
import com.xpsoft.oa.dao.statistics.ProjectReceiveDao;
import com.xpsoft.oa.dao.statistics.ProjectRepayDao;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectReceive;
import com.xpsoft.oa.model.statistics.ProjectRelatedData;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.system.FileAttachService;

public class ProjectNewServiceImpl extends BaseServiceImpl<ProjectNew> implements ProjectNewService{
	private ProjectNewDao dao;
	@Autowired
	private ProjectReceiveDao projectReceiveDao;
	@Autowired
	private ProjectPercentageDao projectPercentageDao;
	@Autowired
	private ProjectRepayDao projectRepayDao;
	@Autowired
	private BankpayapplyDao bankpayapplyDao;
	@Resource
	private FileAttachService attachService;
	private Logger log=Logger.getLogger(ProjectNewServiceImpl.class);
	public ProjectNewServiceImpl(ProjectNewDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public BigDecimal getProLeftMoney(String proNo) {
		// TODO Auto-generated method stub
		//得到此项目付款数
		BigDecimal received = projectReceiveDao.getTotalReceiveByProNo(proNo);
		//得到此项目管理费率
		BigDecimal percentage = projectPercentageDao.getPercentageByProNo(proNo);
		//得到此项目报销费用
		BigDecimal rePayed = projectRepayDao.getRePayedByProNo(proNo);
		//得到此项目下的所有的材料发包合同的累计已付款
		BigDecimal payed = bankpayapplyDao.getPayedByProNo(proNo);
		//计算资金结余
		BigDecimal leftMoney = received.subtract(percentage.divide(new BigDecimal(100),4).multiply(received))
								.subtract(rePayed).subtract(payed);
		return leftMoney;
	}

	@Override
	public List<ProjectRelatedData> getAllData(PagingBean pagingBean,Map<String,String> dataMap) {
		return dao.getAllData(pagingBean,dataMap);
	}

	@Override
	public ProjectNew getByProName(String trim) {
		return dao.getByProName(trim);
	}

	@Override
	public ProjectNew getByProNo(String proNo) {
		return dao.getByProNo(proNo);
	}

	@Override
	public String importsUpdate(String attachIds) {

		String[] attachArr=attachIds.trim().split(",");
		
		String filePath="";//文件路径
		String fileName="";//文件名
		long id=-1;
		
		Trans2RMB trans2rmb=new Trans2RMB();
		
		int count=0;
		for(String attachId:attachArr){
			try {
				id=Long.valueOf(attachId);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				log.error("数据异常1",e);
				throw new RuntimeException("{success:false,data:'数据异常'}");
			}
			//filePath=((FileAttach)attachService.get(id)).getFilePath();
			
			FileAttach fileAttach=(FileAttach)attachService.get(id);
			
			if(null==fileAttach){
				log.error("数据异常2");
				throw new RuntimeException("{success:false,data:'数据异常'}");
			}else{
				filePath=fileAttach.getFilePath();
				fileName=fileAttach.getFileName();
				List<ArrayList> list=ExcelUtils.readExcel(
						ServletActionContext.getServletContext().getRealPath("attachFiles/"+filePath));
				
				if(null==list){
					log.error("数据异常3");
					throw new RuntimeException("{success:false,data:'数据异常'}");
				}
				//excle的所有sheet
				int s,r,c;
				s=c=0;
				r=1;
				for(ArrayList<ArrayList> arr:list){
					s++;
					//excle的所有行
					for(ArrayList a:arr){
						r++;
						if(null==a.get(0)||((String)a.get(0)).trim().equals("")){
							continue;
						}
						ProjectNew projectNew=new ProjectNew();
//						projectNew.setStartDate((DateUtil.parse(((String)a.get(21)).trim(), "yyyy-MM-dd")
//									!=null?DateUtil.parse(((String)a.get(21)).trim(), "yyyy-MM-dd"):DateUtil.parse(((String)a.get(21)).trim(), "dd/MM/yyyy")));
//						
//						if(null==projectNew.getStartDate()){
//							log.info(fileName+"文件,sheet:"+s+",行："+r+",开工日期格式错误");
//							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",开工日期格式错误'}");
//						}
//						
//						projectNew.setEndDate((DateUtil.parse(((String)a.get(22)).trim(), "yyyy-MM-dd")
//								!=null?DateUtil.parse(((String)a.get(22)).trim(), "yyyy-MM-dd"):DateUtil.parse(((String)a.get(22)).trim(), "dd/MM/yyyy")));
//						
//						if(null==projectNew.getEndDate()){
//							log.info(fileName+"文件,sheet:"+s+",行："+r+",竣工日期格式错误");
//							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",竣工日期格式错误'}");
//						}
//						
//						projectNew.setEnterDate((DateUtil.parse(((String)a.get(24)).trim(), "yyyy-MM-dd")
//								!=null?DateUtil.parse(((String)a.get(24)).trim(), "yyyy-MM-dd"):DateUtil.parse(((String)a.get(24)).trim(), "dd/MM/yyyy")));
//						
//						if(null==projectNew.getEnterDate()){
//							log.info(fileName+"文件,sheet:"+s+",行："+r+",进场日期格式错误");
//							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",进场日期格式错误'}");
//						}
								
						projectNew.setProCharger(((String)a.get(3)).trim());//负责人
						projectNew.setProChargerTel(((String)a.get(4)).trim());//负责人电话
						projectNew.setProFollower(((String)a.get(5)).trim());//跟踪预算人
						projectNew.setProFollowerTel(((String)a.get(6)).trim());//跟踪预算人电话
						
						ProjectNew	projectNewOld=dao.getByProName(((String)a.get(0)).trim());
						if(null==projectNewOld){
							log.info(fileName+"文件,sheet:"+s+",行："+r+",项目名称不存在");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",项目名称不存在'}");
						}
						
						try {
							BeanUtil.copyNotNullProperties(projectNewOld, projectNew);
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("{success:false,data:'数据异常'}");
						}
						dao.save(projectNewOld);
						count++;
					}
				}
				
			}
			
			
		}
		
	
		
		return "{success:true,data:'数据更新成功'}";
	}
	
	

}