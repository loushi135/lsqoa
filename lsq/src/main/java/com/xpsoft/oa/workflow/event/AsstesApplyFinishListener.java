package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.oa.model.admin.AssetsApply;
import com.xpsoft.oa.model.admin.AssetsApplycontent;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.admin.AssetsApplyService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.system.AppUserService;

public class AsstesApplyFinishListener implements EventListener {
	
	private Log logger=LogFactory.getLog(AsstesApplyFinishListener.class);

	@Override
	public void notify(EventListenerExecution execution) throws Exception {

		
		
		if (logger.isDebugEnabled()) {
			logger.info("enter the AsstesApplyFinishListener notify method...");
		}
		
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");

		
		ShortMessageService smService = (ShortMessageService) AppUtil.getBean("shortMessageService");
		AssetsApplyService aaService = (AssetsApplyService) AppUtil.getBean("assetsApplyService");
		AppUserService appUserService=(AppUserService)AppUtil.getBean("appUserService");
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		Object name=pi.getVariable("name");//姓名
		Object dept=pi.getVariable("dept");//部门
		String useProjectId=(String)pi.getVariable("useProjectId");//部门
		String useProjectManagerId=(String)pi.getVariable("useProjectManagerId");//部门
		Object applyType=pi.getVariable("applyType");//资产类型
		Object applyDate=pi.getVariable("applyDate");//申请日期
		Object resultData=pi.getVariable("resultGridData");//资产列表
		Object applyDescription=pi.getVariable("applyDescription");//申请日期
		Object isSubsidyOrNot=pi.getVariable("isSubsidyOrNot");//是否补贴
		
		//印刷品明细数据，从一维数组转换成矩阵
		String[] resultArray={};
		if(null!=resultData)
			resultArray=resultData.toString().split(",");
		
		Set<AssetsApplycontent> dataList=new HashSet<AssetsApplycontent>();
		AssetsApplycontent assetsApplycontent=null;
		
		int height=resultArray.length/7;
		int width=7;
		int index=0;
		
		lableA://行的循环的标签 
		for (int i = 0; i < height; i++) {
			assetsApplycontent=new AssetsApplycontent();
			for (int j = 0; j < width; j++) {	
				String temp=resultArray[index++];
				switch(j){
					case 0:
						if("".equals(temp.substring(temp.indexOf(":")+1))){
							break lableA;
						}else{
							assetsApplycontent.setName(temp.substring(temp.indexOf(":")+1));
							break;
						}
					case 1:assetsApplycontent.setModel(temp.substring(temp.indexOf(":")+1));break;
					case 2:assetsApplycontent.setBrand(temp.substring(temp.indexOf(":")+1));break;
					case 3:assetsApplycontent.setUnit(temp.substring(temp.indexOf(":")+1));break;
					case 4:assetsApplycontent.setArrivalDate(DateUtil.parseDate("".equals(temp.substring(temp.indexOf(":")+1))?DateUtil.format(DateUtil.now()):temp.substring(temp.indexOf(":")+1)));break;
					case 5:assetsApplycontent.setNum(new BigDecimal("".equals(temp.substring(temp.indexOf(":")+1))?"0":temp.substring(temp.indexOf(":")+1)));break;
					case 6:assetsApplycontent.setPrice(new BigDecimal("".equals(temp.substring(temp.indexOf(":")+1, temp.indexOf(";")))?"0":temp.substring(temp.indexOf(":")+1, temp.indexOf(";"))));break;
					default : break;
				}
			}
			dataList.add(assetsApplycontent);
		}
		
		
		String strName="";
		String strDept="";
		Date strAplyDate=null;
		Integer strApplyType=0;
		String strApplyDescription="";
		String strAppIsSubsidyOrNot="";
		
		if(null!=name)
			strName=name.toString();
		if(null!=dept)
			strDept=dept.toString();	
		if(null!=applyDate)
			strAplyDate=DateUtil.parseDate(applyDate.toString());
		if(null!=applyType){
			if("电子设备".equals(applyType.toString())){
				strApplyType=0;
			}else if("运输设备".equals(applyType.toString())){
				strApplyType=1;
			}else if("办公家具".equals(applyType.toString())){
				strApplyType=3;
			}else{
				strApplyType=2;//	其它
			}
		}
		if(null!=applyDescription)
			strApplyDescription=applyDescription.toString();	
		if(null!=isSubsidyOrNot){
			strAppIsSubsidyOrNot=isSubsidyOrNot.toString();
		}
		
		
		AssetsApply assetsApply=new AssetsApply();
		assetsApply.setName(strName);
		assetsApply.setDept(strDept);
		assetsApply.setApplyDate(strAplyDate);
		assetsApply.setApplyType(strApplyType);
		assetsApply.setApplyDescription(strApplyDescription);
		assetsApply.setAssetsApplycontents(dataList);
		assetsApply.setIsSubsidyOrNot(strAppIsSubsidyOrNot);
		
		if(StringUtils.isNotBlank(useProjectId)){
			ProjectNewService projectNewService = (ProjectNewService)AppUtil.getBean("projectNewService");
			ProjectNew project= projectNewService.get(Long.valueOf(useProjectId));
			assetsApply.setUseProject(project);
		}
		if(StringUtils.isNotBlank(useProjectManagerId)){
			AppUser projectManager= appUserService.get(Long.valueOf(useProjectManagerId));
			assetsApply.setUseProjectManager(projectManager);
		}
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		assetsApply.setProcessRunId(processRun.getRunId());
		
		aaService.save(assetsApply);
	
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
		
//		//给办公室发消息
//		List<AppUser> appUsers=appUserService.findByRoleAndDept("短消息-办公室资产请购", "办公室");
//		if(appUsers.isEmpty()){
//			if(logger.isDebugEnabled()){
//				logger.info("角色【短消息-办公室资产请购】下无任何用户");
//			}
//		}else{
//			
//			for (int i = 0; i < appUsers.size(); i++) {				
//				smService.save(AppUser.SYSTEM_USER, appUsers.get(i).getUserId().toString(), "【"+flowStartUser.getFullname()+"】的资产请购申请已经通过审批，请办理！", ShortMessage.MSG_TYPE_SYS);
//			}
//			
//		}
		
		//给采购部发消息
		List<AppUser> appUsers1=appUserService.findByRoleAndDept("短消息-采购部资产请购", "采购部");
		if(appUsers1.isEmpty()){
			if(logger.isDebugEnabled()){
				logger.info("角色【短消息-采购部资产请购】下无任何用户");
			}
		}else{
			
			for (int i = 0; i < appUsers1.size(); i++) {				
				smService.save(AppUser.SYSTEM_USER, appUsers1.get(i).getUserId().toString(), "【"+flowStartUser.getFullname()+"】的资产请购申请已经通过审批，请办理！", ShortMessage.MSG_TYPE_SYS);
			}
			
		}
		
		

	}

}
