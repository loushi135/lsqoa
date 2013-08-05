package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;
import java.util.Set;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.oa.model.system.AppRole;
import com.xpsoft.oa.model.system.AppUser;

public class AssetsApplySecondHandler implements DecisionHandler {
	@Override
	public String decide(OpenExecution execution) {
		String roleName="";
		String result="";
		int flag=0;
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		String totalPrice=(String)pi.getVariable("prePrice");//总价
		String position = flowStartUser.getPosition();
		//获取流程发起者的角色
		Set<AppRole> AppRoleSet=  flowStartUser.getRoles();
		for (AppRole appRole : AppRoleSet) {
			String curRoleName=appRole.getRoleName();
			if("流程-普通员工".equals(curRoleName)){
				if(!roleName.equals("流程-部门经理")&&!roleName.contains("分管领导")){
					roleName = curRoleName;
				}
			}else if("流程-部门经理".equals(curRoleName)){
				if(!roleName.contains("分管领导")){
					roleName = curRoleName;
				}
			}else if(curRoleName.contains("分管领导")){
				roleName = "流程-分管领导";
			}
		}
		if("流程-分管领导".equals(roleName)&&new BigDecimal(totalPrice).compareTo(new BigDecimal(3000))==-1){
			flag = 1;
		}else if("流程-分管领导".equals(roleName)&&new BigDecimal(totalPrice).compareTo(new BigDecimal(3000))!=-1){
			flag = 2;
		}else if("流程-普通员工".equals(roleName)||"流程-部门经理".equals(roleName)){
			flag = 3;
		}
		
		switch (flag){
		  	case 1 :result="分管领导金额小于3000";break;
		  	case 2 :result="分管领导金额大于等于3000";break;
		  	case 3 :result="普通员工或部门经理";break;
		  	default :result="普通员工或部门经理";break;
		}
		return result;
	}

}
