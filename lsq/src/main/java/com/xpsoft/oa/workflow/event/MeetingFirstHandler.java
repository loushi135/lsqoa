package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.oa.model.system.AppUser;



//申请人权限判断
public class MeetingFirstHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		
		String result="";
		int flag=0;
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		
		flag = ContextUtil.getHighestRole(flowStartUser);
		if(flag!=1){
			flag = 2;
		}
		
		switch (flag){
		  	case 1 :result="至部门经理审批";break;
		  	case 2 :result="至行政企划部审批";break;
		  	
		}
		return result;
	}

}
