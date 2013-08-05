package com.xpsoft.oa.workflow.event;

import java.util.Set;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.oa.model.system.AppRole;
import com.xpsoft.oa.model.system.AppUser;

public class CommonReportFirstHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		
		String roleName="";
		String result="";
	//从表单获取分类
		OpenProcessInstance pi=execution.getProcessInstance();
		Object state=pi.getVariable("state");//类型
		if(state.toString().equals("采购类")){
			result="采购类";
		}else if(state.toString().equals("其它")){
				result="其它";
	   }
		return result;
	   }
	

}

