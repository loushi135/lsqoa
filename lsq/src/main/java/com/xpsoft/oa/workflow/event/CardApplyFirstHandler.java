package com.xpsoft.oa.workflow.event;

import java.util.Set;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.oa.model.system.AppRole;
import com.xpsoft.oa.model.system.AppUser;

public class CardApplyFirstHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		String result="";
		int flag=0;
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String type=(String) pi.getVariable("type");
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		//获取流程发起者的角色
		Set<AppRole> AppRoleSet=  flowStartUser.getRoles();
		
		for (AppRole appRole : AppRoleSet) {
			if("新印".equals(type.toString())){
				flag=1;
				break;
			}else if("重印".equals(type.toString())){
				flag=2;
				break;
			}
		}
		
		switch (flag){
		  	case 1 :result="新印";break;
		  	case 2 :result="重印";break;
		  	default :break;
		  	
		}
		return result;
	}

}
