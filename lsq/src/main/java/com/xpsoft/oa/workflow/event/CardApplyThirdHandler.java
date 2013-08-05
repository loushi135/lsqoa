package com.xpsoft.oa.workflow.event;

import java.util.Set;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

public class CardApplyThirdHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		String result="";
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String isType=(String) pi.getVariable("isType");
		
		if("是".equals(isType)){
			result="高印";
		}else{
			result="其他";
		}
		
		return result;
	}

}
