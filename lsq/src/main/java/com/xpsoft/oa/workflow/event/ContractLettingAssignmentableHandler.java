package com.xpsoft.oa.workflow.event;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;

import com.xpsoft.core.Constants;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.system.AppUserService;


public class ContractLettingAssignmentableHandler implements AssignmentHandler {

	private Log logger=LogFactory.getLog(ContractLettingAssignmentableHandler.class);
	
	
	@Override
	public void assign(Assignable assignable, OpenExecution openExecution) throws Exception {
		
		String assignId=(String)openExecution.getVariable(Constants.FLOW_ASSIGN_ID);
		
		logger.info("assignId:===========>" + assignId);
		
		List<AppUser> appUsers=null;
		AppUserService appUserService=(AppUserService)AppUtil.getBean("appUserService");	
		
		
		//获取表单参数
		OpenProcessInstance pi=openExecution.getProcessInstance();
		String sysType=(String)pi.getVariable("sysItem");//系统类别
		
		if(sysType.contains("01")||sysType.contains("06")||sysType.contains("07")||sysType.contains("11")||sysType.contains("12")||sysType.contains("13")||sysType.contains("14")){
			appUsers=appUserService.findByRoleName("材供专员1");	
		}else if(sysType.contains("02")||sysType.contains("03")||sysType.contains("04")||sysType.contains("05")||sysType.contains("08")||sysType.contains("09")||sysType.contains("10")||sysType.contains("15")){
			appUsers=appUserService.findByRoleName("材供专员2");
		}
		
		
		//判断查出的用户对象一个还是多个
		if(appUsers==null){
			if(logger.isDebugEnabled()){
				logger.info("没有找到任何用户");
			}
		}else{
			String[] userIds=new String[appUsers.size()];
			for (int i = 0; i < appUsers.size(); i++) {
				userIds[i]=String.valueOf(appUsers.get(i).getUserId());
			}
			if(userIds!=null && userIds.length>1){//多于一个人的
				 for(String uId:userIds){   
					 assignable.addCandidateUser(uId);   
	             } 
			}else if(userIds!=null && userIds.length==1){
				assignable.setAssignee(userIds[0]);   
			}
		}
	}
}
