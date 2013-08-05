package com.xpsoft.oa.workflow.event;


import java.util.ArrayList;
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
import com.xpsoft.core.util.SendSmsUtil;
import com.xpsoft.oa.model.flow.TaskAgent;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.service.flow.TaskAgentService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;


public class RoleAssignmentableHandler implements AssignmentHandler {

	private Log logger=LogFactory.getLog(RoleAssignmentableHandler.class);
	
	private String roleName;
	
	
	@Override
	public void assign(Assignable assignable, OpenExecution openExecution) throws Exception {
		
		if(null==roleName){
			if(logger.isDebugEnabled()){
				logger.info("你必须设置角色名称");
			}
		}
		
		String assignId=(String)openExecution.getVariable(Constants.FLOW_ASSIGN_ID);
		
		logger.info("assignId:===========>" + assignId);
		String deptName="";
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)openExecution.getProcessInstance().getVariable("flowStartUser");
		deptName=flowStartUser.getDepartment().getDepName();
		OpenProcessInstance pi = openExecution.getProcessInstance();
		//查出指定角色名和部门的用户对象
		AppUserService appUserService=(AppUserService)AppUtil.getBean("appUserService");
		
		if(null!=pi.getVariable("innerFlowDeptName")){
			deptName = (String)pi.getVariable("innerFlowDeptName");
		}
		if(null!=pi.getVariable("staffPromoteapply@oldDepTreeSelector")){
			deptName = (String)pi.getVariable("staffPromoteapply@oldDepTreeSelector");
		}
		if(null!=pi.getVariable("applyUserId")){//员工转正 通过转正人找其分管副总
			String applyUserId = (String)pi.getVariable("applyUserId") ;
			AppUser appUser = appUserService.get(Long.valueOf(applyUserId));
			deptName = appUser.getDepartment().getDepName();
		}
		List<AppUser> appUsers= new ArrayList<AppUser>();
		if("流程-部门经理".equals(roleName)){
			appUsers=appUserService.findByRoleAndDept(roleName,deptName );
		}else if("流程-分管领导".equals(roleName)){
			appUsers=appUserService.findByRoleNameFZ(deptName);
		}else if("流程-项目经理".equals(roleName)){//通过指定项目经理
			String projectChargerUserId = (String)pi.getVariable("projectChargerUserId");
			projectChargerUserId = getNextIfBlank(projectChargerUserId,"projectManagerId",pi);
			projectChargerUserId = getNextIfBlank(projectChargerUserId,"useProjectManagerId",pi);
			if(StringUtils.isNotBlank(projectChargerUserId)){
				AppUser appUser = appUserService.get(Long.valueOf(projectChargerUserId));
				appUsers.add(appUser);
			}
		}else if("流程-预决算员".equals(roleName)){
			String preCalcUserId = (String)pi.getVariable("preCalcUserId");
			if(StringUtils.isNotBlank(preCalcUserId)){
				AppUser appUser = appUserService.get(Long.valueOf(preCalcUserId));
				appUsers.add(appUser);
			}else {
				String bpaPreCalcUserId = (String)pi.getVariable("bpaPreCalcUserId");
				bpaPreCalcUserId = getNextIfBlank(bpaPreCalcUserId,"constructionPreCalcUserId",pi);
				if(StringUtils.isNotBlank(bpaPreCalcUserId)){
					AppUser appUser = appUserService.get(Long.valueOf(bpaPreCalcUserId));
					appUsers.add(appUser);
				}
			}
		}else if("流程-区域经理".equals(roleName)){
			String deptId = (String)pi.getVariable("deptId");
			DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
			deptId = getNextIfBlank(deptId,"bpaDeptId",pi);
			deptId = getNextIfBlank(deptId,"flowHO.areaId",pi);
			deptId = getNextIfBlank(deptId,"materialDeptId",pi);
			deptId = getNextIfBlank(deptId,"signApplyDepartmentId",pi);
			deptId = getNextIfBlank(deptId,"oddEmployapplyDepartmentId",pi);
			deptId = getNextIfBlank(deptId,"constructionContract.deptId",pi);
			String newDeptId = getNextIfBlank(pi, "projectEndCheck.project.areaId");
			if(StringUtils.isNotBlank(newDeptId)){
				deptId = newDeptId;
			}
			if(StringUtils.isNotBlank(deptId)){
				Department department = departmentService.get(Long.valueOf(deptId));
				appUsers = appUserService.findByRoleAndDept("流程-部门经理", department.getDepName());
			}else{
				String chargerId = (String)pi.getVariable("materialDeptChargerId"); //区域经理id
				chargerId = getNextIfBlank(chargerId,"bpaDeptChargerId",pi);
				if(StringUtils.isNotBlank(chargerId)){
					AppUser appUser = appUserService.get(Long.valueOf(chargerId));
					appUsers.add(appUser);
				}
			}
		}else if("流程-项目负责人".equals(roleName)){
			String applyGoodProChargerId = (String)pi.getVariable("applyGoodProChargerId");
			appUsers.add(appUserService.get(Long.valueOf(applyGoodProChargerId)));
		}else if("流程-转正人".equals(roleName)){
			String applyUserId = (String)pi.getVariable("applyUserId");
			if(StringUtils.isNotBlank(applyUserId)){
				AppUser appUser = appUserService.get(Long.valueOf(applyUserId));
				appUsers.add(appUser);
			}
		}else if("流程-推荐人".equals(roleName)){
			String inviteUserId = (String)pi.getVariable("inviteUserId");
			if(StringUtils.isNotBlank(inviteUserId)){
				AppUser appUser = appUserService.get(Long.valueOf(inviteUserId));
				appUsers.add(appUser);
			}
		}else if("流程-审批人".equals(roleName)){
			String auditUserId = getNextIfBlank(pi, "flowApply.relatedUser.userId","projectEndCheck.engineeUser.userId");
			if(StringUtils.isNotBlank(auditUserId)){
				AppUser appUser = appUserService.get(Long.valueOf(auditUserId));
				appUsers.add(appUser);
			}
		}
		TaskAgentService taskAgentService=(TaskAgentService)AppUtil.getBean("taskAgentService");

		//判断查出的用户对象一个还是多个
		if(appUsers==null||appUsers.size()==0){
			if(logger.isDebugEnabled()){
				logger.info("角色【"+roleName+"】下无任何用户");
			}
			//发给系统管理员
			assignable.setAssignee("1");  
			//给异常处理角色发短信 
			appUsers=appUserService.findByRoleName("流程-异常处理");
			for (AppUser appUser : appUsers) {
				SendSmsUtil.sendMessage(appUser.getUserId().toString(), 
						flowStartUser.getFullname()+"的"+"["+(String)openExecution.getVariable("processName")+"]异常，未能找到审批人,实例ID："+openExecution.getProcessInstance().getId());
			}
		}else{
			String[] userIds=new String[appUsers.size()];
			for (int i = 0; i < appUsers.size(); i++) {
				userIds[i]=String.valueOf(appUsers.get(i).getUserId());
			}
			if(userIds!=null && userIds.length>1){//多于一个人的
				 for(String uId:userIds){
							//assignable.addCandidateUser(uId);  
							TaskAgent taskAgent=taskAgentService.isExist(uId);
							if(taskAgent!=null){
								assignable.addCandidateUser(taskAgent.getAgentAssignId().toString());
							}else{
								assignable.addCandidateUser(uId);   
							}	
	             }
			}else if(userIds!=null && userIds.length==1){
					//assignable.setAssignee(userIds[0]);
					TaskAgent taskAgent=taskAgentService.isExist(userIds[0]);
					if(taskAgent!=null){
						assignable.setAssignee(taskAgent.getAgentAssignId().toString());
					}else{
						assignable.setAssignee(userIds[0]);   
					}	
			}
		}
	}
	private String getNextIfBlank(String deptId,String varName,OpenProcessInstance pi){
		if(StringUtils.isBlank(deptId)){
			deptId = (String)pi.getVariable(varName);
		}
		return deptId;
	}
	
	private String getNextIfBlank(OpenProcessInstance pi,String...varNames){
		for(String var:varNames){
			String value = (String)pi.getVariable(var);
			if(StringUtils.isNotBlank(value)){
				return value;
			}
		}
		return null;
	}
}
