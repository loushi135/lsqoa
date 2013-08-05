package com.xpsoft.oa.service.statistics.impl;


import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.util.ExcelImportUtil;
import com.xpsoft.core.util.ExcelUtils;
import com.xpsoft.core.util.Trans2RMB;
import com.xpsoft.oa.dao.customer.ProjectDao;
import com.xpsoft.oa.dao.statistics.ProjectReceiveDao;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectReceive;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.statistics.ProjectReceiveService;
import com.xpsoft.oa.service.system.FileAttachService;

public class ProjectReceiveServiceImpl extends BaseServiceImpl<ProjectReceive> implements ProjectReceiveService{
	private ProjectReceiveDao dao;
	@Resource
	private FileAttachService attachService;
	@Resource
	private ProjectNewService projectNewService;
	
	private Logger log=Logger.getLogger(ProjectReceiveServiceImpl.class);
	
	public ProjectReceiveServiceImpl(ProjectReceiveDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public BigDecimal getTotalReceiveByProNo(String proNo) {
		// TODO Auto-generated method stub
		return dao.getTotalReceiveByProNo(proNo);
	}

	@Override
	public String imports(ProjectNew projectIn, String attachIds) {

		String[] attachArr=attachIds.trim().split(",");
		
		String filePath="";//文件路径
		String fileName="";//文件名
		long id=-1;
		
		Trans2RMB trans2rmb=new Trans2RMB();
		ProjectNew projectNews=null;
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
//				System.out.println(list);
				
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
						if(a.size()!=5){
							log.info(fileName+"文件,sheet:"+s+",行："+r+",数据列数异常3");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",数据列数异常'}");
						}
						//excle的所有列
						//test
//						{
//							for(Object o:a){
//								c++;
//								System.out.print((String)o);
//								System.out.print("||");
//							}
//							System.out.println();
//						}
						if(null==a.get(0)||((String)a.get(0)).trim().equals("")){
							continue;
							//log.info(fileName+"文件,sheet:"+s+",行："+r+",项目名称为空");
							//throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",项目名称为空'}");
						}
						ProjectReceive projectReceive=new ProjectReceive();
						projectReceive.setReceiveTime(DateUtil.parse(((String)a.get(1)).trim(), "yyyy-MM-dd")
									!=null?DateUtil.parse(((String)a.get(1)).trim(), "yyyy-MM-dd"):DateUtil.parse(((String)a.get(1)).trim(), "dd/MM/yyyy"));
						
						if(null==projectReceive.getReceiveTime()){
							log.info(fileName+"文件,sheet:"+s+",行："+r+",收款时间格式错误");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",收款时间格式错误'}");
						}
						
						String money=(String)a.get(4);
						
						if(null==money){
							log.info(fileName+"文件,sheet:"+s+",行："+r+",收款金额为空");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",收款金额为空'}");
						}else {
							money=money.trim().replace(",", "");
							if(money.startsWith("(")){
								money="-"+money.substring(1,money.length()-1);
							}
						}
						
						try {
							projectReceive.setAmount(new BigDecimal(money));
							projectReceive.setAmountBig(trans2rmb.toRMB(money));
						} catch (Exception e) {
							log.info(fileName+"文件,sheet:"+s+",行："+r+",收款金额格式错误");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",收款金额格式错误'}");
						}
						
						projectReceive.setPzzh(((String)a.get(2)).trim());
						projectReceive.setZy(((String)a.get(3)).trim());
						
						if(null!=projectIn){
							if(!((String)a.get(0)).trim().equals(projectIn.getProName())){
								//TODO 导入所有的
								log.info(fileName+"文件,sheet:"+s+",行："+r+",项目名称错误");
								throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",请导入相同项目的收款'}");
							}
							
//							if(this.isHasImport(projectReceive.getPzzh(),projectReceive.getZy(),projectReceive.getAmountBig(),projectReceive.getReceiveTime(),projectIn.getId())){
//								log.info(fileName+"文件,sheet:"+s+",行："+r+",数据重复导入");
//								throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",数据重复导入'}");
//							
//							}
							projectReceive.setProject(projectIn);
						}else{
							projectNews=projectNewService.getByProName(((String)a.get(0)).trim());
							if(null==projectNews){
								log.info(fileName+"文件,sheet:"+s+",行："+r+",项目名称错误2");
								throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",请导入存在的项目的收款'}");
							}
//							if(this.isHasImport(projectReceive.getPzzh(),projectReceive.getZy(),
//									projectReceive.getAmountBig(),projectReceive.getReceiveTime(),projectNews.getId())){
//								log.info(fileName+"文件,sheet:"+s+",行："+r+",数据重复导入");
//								throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",数据重复导入'}");
//							
//							}
							projectReceive.setProject(projectNews);
						}
						
						projectReceive.setCreatetime(new Date());
						projectReceive.setCreateUser(ContextUtil.getCurrentUser());
					//	projectReceive.setId(null);
						dao.save(projectReceive);
						count++;
					}
				}
				
			}
			
			
		}
		
	
		
		return "{success:true,data:'共上传"+count+"条数据'}";
	}

	public boolean isHasImport(String amountBig, Date receiveTime, Long id) {
		
		
		return dao.checkHasImport(amountBig,receiveTime, id);
	}
	
	public boolean isHasImport(String pzzh,String zy,String amountBig, Date receiveTime, Long id) {
		
		
		return dao.checkHasImport(pzzh,zy,amountBig,receiveTime, id);
	}

	@Override
	public ProjectReceive getByPro(ProjectNew projectNews) {
		
		return dao.getByPro(projectNews);
	}

	@Override
	public List<ProjectReceive> getMyData(QueryFilter filter,
			AppUser currentUser) {
		
		
		return dao.getMyData(filter,currentUser);
	}

}