package com.xpsoft.oa.workflow.event;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;
import com.xpsoft.core.Constants;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.system.AppUserService;


public class ContractLettingJSAssignmentableHandler implements AssignmentHandler {

	private Log logger=LogFactory.getLog(ContractLettingJSAssignmentableHandler.class);
	
	
	@Override
	public void assign(Assignable assignable, OpenExecution openExecution) throws Exception {
		
		String assignId=(String)openExecution.getVariable(Constants.FLOW_ASSIGN_ID);
		
		logger.info("assignId:===========>" + assignId);
		
		String deptName="";
		AppUser flowStartUser=(AppUser)openExecution.getProcessInstance().getVariable("flowStartUser");
		deptName=flowStartUser.getDepartment().getDepName();
		
		List<AppUser> appUsers=null;
		AppUserService appUserService=(AppUserService)AppUtil.getBean("appUserService");	
		
		
		
		if(deptName.contains("工程一区")||deptName.contains("工程二区")||deptName.contains("工程三区")){
			appUsers=appUserService.findByRoleName("核算专员1");	
		}else if(deptName.contains("工程六区")||deptName.contains("工程七区")||deptName.contains("直属部")){
			appUsers=appUserService.findByRoleName("核算专员2");
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
