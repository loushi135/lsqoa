package com.xpsoft.oa.workflow.event;

import java.util.List;
import java.util.Set;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.oa.model.system.AppRole;
import com.xpsoft.oa.model.system.AppUser;



//申请人权限判断
public class LeaveDecisionSecondHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		
		String roleName="";
		String result="";
		int flag=1;
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		String totalDays=(String)pi.getVariable("totalDays");//请假天数
		
		//获取流程发起者的角色
		Set<AppRole> AppRoleSet=  flowStartUser.getRoles();
		
		for (AppRole appRole : AppRoleSet) {
			roleName=appRole.getRoleName();
			if("流程-普通员工".equals(roleName)){
				flag = 1;
				break;
			}else if("流程-部门经理".equals(roleName)||"流程-分管领导".equals(roleName)){
				if(Float.parseFloat(totalDays)>2){
					flag = 2;
					break;
				}else {
					flag = 1;
				}
			}else{
				flag = 1;
			}
		}
		
		switch (flag){
		  	case 1 :result="至结束";break;
		  	case 2 :result="2天以上";break;
		  	
		}
		return result;
	}

}
