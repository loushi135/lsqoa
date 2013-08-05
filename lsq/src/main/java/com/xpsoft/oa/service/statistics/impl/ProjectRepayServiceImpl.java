package com.xpsoft.oa.service.statistics.impl;


import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.util.ExcelUtils;
import com.xpsoft.core.util.Trans2RMB;
import com.xpsoft.oa.dao.statistics.ProjectRepayDao;
import com.xpsoft.oa.model.statistics.DesignProject;
import com.xpsoft.oa.model.statistics.OtherProject;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectRepay;
import com.xpsoft.oa.model.statistics.SalesProject;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.statistics.DesignProjectService;
import com.xpsoft.oa.service.statistics.OtherProjectService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.statistics.ProjectRepayService;
import com.xpsoft.oa.service.statistics.SalesProjectService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.FileAttachService;

public class ProjectRepayServiceImpl extends BaseServiceImpl<ProjectRepay> implements ProjectRepayService{
	private ProjectRepayDao dao;
	private Logger log=Logger.getLogger(ProjectReceiveServiceImpl.class);
	@Resource
	private FileAttachService attachService;
	@Resource
	private ProjectNewService projectNewService;
	@Resource
	private OtherProjectService otherProjectService;
	@Resource
	private DesignProjectService designProjectService;
	@Resource
	private SalesProjectService salesProjectService;
	@Resource
	private AppUserService appUserService;
	public ProjectRepayServiceImpl(ProjectRepayDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public BigDecimal getRePayedByProNo(String proNo) {
		// TODO Auto-generated method stub
		return dao.getRePayedByProNo(proNo);
	}

	@Override
	public String imports(ProjectNew projectIn, String attachIds) {


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
						if(a.size()!=7){
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
						ProjectRepay projectRepay=new ProjectRepay();
						projectRepay.setRepayTime(DateUtil.parse(((String)a.get(0)).trim(), "yyyy-MM-dd")
									!=null?DateUtil.parse(((String)a.get(0)).trim(), "yyyy-MM-dd"):DateUtil.parse(((String)a.get(0)).trim(), "dd/MM/yyyy"));
						
						if(null==projectRepay.getRepayTime()){
							log.info(fileName+"文件,sheet:"+s+",行："+r+",现金报销日期格式错误");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",现金报销日期格式错误'}");
						}
						
						String money=(String)a.get(6);
						
						if(null==money){
							log.info(fileName+"文件,sheet:"+s+",行："+r+",现金报销金额为空");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",现金报销金额为空'}");
						}else {
							money=money.trim().replace(",", "");
						}
						
						try {
							projectRepay.setAmount(new BigDecimal(money));
							projectRepay.setAmountBig(trans2rmb.toRMB(money));
						} catch (Exception e) {
							log.info(fileName+"文件,sheet:"+s+",行："+r+",现金报销金额格式错误");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",现金报销金额格式错误'}");
						}
						
						if(null!=projectIn){
							if(!((String)a.get(3)).trim().equals(projectIn.getProName())){
								log.info(fileName+"文件,sheet:"+s+",行："+r+",项目名称错误");
								throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",请导入相同项目的现金报销'}");
							}
							
//							if(this.isHasImport(projectReceive.getPzzh(),projectReceive.getZy(),projectReceive.getAmountBig(),projectReceive.getReceiveTime(),projectIn.getId())){
//								log.info(fileName+"文件,sheet:"+s+",行："+r+",数据重复导入");
//								throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",数据重复导入'}");
//							
//							}
							projectRepay.setProject(projectIn);
						}else{
							ProjectNew projectNew=null;
							projectNew=projectNewService.getByProName(((String)a.get(3)).trim());
							if(null==projectNew){
								log.info(fileName+"文件,sheet:"+s+",行："+r+",项目名称错误2");
								throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",请导入存在的项目的现金报销'}");
							}
							if(!projectNew.getProNo().equals(((String)a.get(2)).trim())){
								log.info(fileName+"文件,sheet:"+s+",行："+r+",项目编号错误2");
								throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",导入"+((String)a.get(3)).trim()+"的项目编号不正确'}");
							}
							if(!projectNew.getArea().equals(((String)a.get(1)).trim())){
								log.info(fileName+"文件,sheet:"+s+",行："+r+",项目施工区域错误2");
								throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",导入"+((String)a.get(3)).trim()+"的施工区域不正确'}");
							}
//							if(this.isHasImport(projectReceive.getPzzh(),projectReceive.getZy(),
//									projectReceive.getAmountBig(),projectReceive.getReceiveTime(),projectNews.getId())){
//								log.info(fileName+"文件,sheet:"+s+",行："+r+",数据重复导入");
//								throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",数据重复导入'}");
//							
//							}
							projectRepay.setProject(projectNew);
						}
						
						AppUser appUser=appUserService.findByUserName(((String)a.get(4)).trim());
						
						if(null==appUser){
							log.info(fileName+"文件,sheet:"+s+",行："+r+",用户名错误");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",报销人的用户名不存在'}");
						}
						
						if(!appUser.getFullname().equals(((String)a.get(5)).trim())){
							log.info(fileName+"文件,sheet:"+s+",行："+r+",用报销人错误");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",用户名"+((String)a.get(4)).trim()+"的报销人姓名不正确'}");	
						}
						projectRepay.setRepayUser(appUser);
						projectRepay.setCreatetime(new Date());
						projectRepay.setCreateUser(ContextUtil.getCurrentUser());
						dao.save(projectRepay);
						count++;
					}
				}
				
			}
			
			
		}
		
	
		
		return "{success:true,data:'共上传"+count+"条数据'}";
	
	}
	
	public String imports(String attachIds){
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
						if(null==a.get(0)||((String)a.get(0)).trim().equals("")){
							break;
						}
						ProjectRepay projectRepay=new ProjectRepay();
						Date repayTime = null;
						try {
							repayTime = DateUtils.parseDate(a.get(0).toString().trim(), new String[]{"d/M/yyyy","yyyy-MM-dd","dd/MM/yyyy"} );
						} catch (ParseException e1) {
							log.info(fileName+"文件,sheet:"+s+",行："+r+",登记日期格式错误");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",登记日期格式错误'}");
						}
						if(null==a.get(1)||((String)a.get(1)).trim().equals("")){
							log.info(fileName+"文件,sheet:"+s+",行："+r+",项目编号为空");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",项目编号为空'}");
						}
						if(null==a.get(2)||((String)a.get(2)).trim().equals("")){
							log.info(fileName+"文件,sheet:"+s+",行："+r+",项目名称为空");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",项目名称为空'}");
						}
						if(null==a.get(3)||((String)a.get(3)).trim().equals("")){
							log.info(fileName+"文件,sheet:"+s+",行："+r+",报销人为空");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",报销人为空'}");
						}
						if(null==a.get(4)||((String)a.get(4)).trim().equals("")){
							log.info(fileName+"文件,sheet:"+s+",行："+r+",部门为空");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",部门为空'}");
						}
						if(null==a.get(5)||((String)a.get(5)).trim().equals("")){
							log.info(fileName+"文件,sheet:"+s+",行："+r+",金额为空");
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",金额为空'}");
						}
						String proNo = a.get(1).toString().trim();
						
						String proName = a.get(2).toString().trim();
						String repayUserName = a.get(3).toString().trim();
						String depName = a.get(4).toString().trim();
						
						String amount = a.get(5).toString().trim().replace(",", "");
						if (amount.startsWith("(")) {
							amount = "-" + amount.substring(1, amount.length() - 1);
						}
						String amountBig = new Trans2RMB().toRMB(amount);
						String proId = "";
						String repayType = "1";
						projectRepay.setAmount(new BigDecimal(amount));
						projectRepay.setAmountBig(amountBig);
						projectRepay.setRepayTime(repayTime);
						
						if(proNo.startsWith("Z.")){//其他项目
							repayType = "4";
							OtherProject otherProject = otherProjectService.getByProName(proName);
							if(otherProject!=null){
								proId = otherProject.getId().toString();
							}else{
								log.info(fileName+"文件,sheet:"+s+",行："+r+",其他项目：未找到项目名为"+proName+"的项目'}");
								throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",其他项目：未找到项目名为"+proName+"的项目'}");
							}
						}else if(proNo.startsWith("SJ.")){//设计项目
							repayType = "2";
							DesignProject designProject = designProjectService.getByProName(proName);
							if(designProject!=null){
								proId = designProject.getId().toString();
							}else{
								log.info(fileName+"文件,sheet:"+s+",行："+r+",设计项目：未找到项目名为"+proName+"的项目'}");
								throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",设计项目：未找到项目名为"+proName+"的项目'}");
							}
						}else if(proNo.split("\\.")[0].length() == 1){//工程项目
							repayType = "1";
							ProjectNew projectNews = projectNewService.getByProName(proName);
							if(projectNews==null)projectNews = projectNewService.getByProNo(proNo);
							if(projectNews!=null){
								proId = projectNews.getId().toString();
							}else{
								log.info(fileName+"文件,sheet:"+s+",行："+r+",工程项目：未找到项目名为"+proName+"的项目'}");
								throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",工程项目：未找到项目名为"+proName+"的项目'}");
							}
						}else{//营销项目
							repayType = "3";
							SalesProject salesProject = salesProjectService.getByProName(proName);
							if(salesProject!=null){
								proId = salesProject.getId().toString();
							}else{
								log.info(fileName+"文件,sheet:"+s+",行："+r+",营销项目：未找到项目名为"+proName+"的项目'}");
								throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",营销项目：未找到项目名为"+proName+"的项目'}");
							}
						}
						projectRepay.setProId(Long.valueOf(proId));
						projectRepay.setRepayType(Integer.valueOf(repayType));
						AppUser appUser = null;
						List<AppUser> appUserList = appUserService.findByFullnames("'"+repayUserName+"'");
						if(appUserList!=null&&appUserList.size()>0){
							if(appUserList.size()>1){
								appUser=appUserService.findByFullnameAndDepName(repayUserName, depName);
							}else{
								appUser = appUserList.get(0);
							}
						}
						if(null==appUser){
							log.info(fileName+"文件,sheet:"+s+",行："+r+",未找到报销人："+repayUserName+",部门："+depName);
							throw new RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",未找到报销人："+repayUserName+",部门："+depName+"'}");
						}
						projectRepay.setRepayUser(appUser);
						projectRepay.setCreatetime(new Date());
						projectRepay.setCreateUser(ContextUtil.getCurrentUser());
						dao.save(projectRepay);
						count++;
					}
				}
			}
		}
		return "{success:true,data:'共上传"+count+"条数据'}";
	}
}