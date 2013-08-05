package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

public class AssetsApplyFivethHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		String result="";
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		
		String isSubsidyOrNot=(String)pi.getVariable("isSubsidyOrNot");//总价
		if(isSubsidyOrNot.equals("是")){
			result="补贴";
		}
		if(isSubsidyOrNot.equals("否")){
			result="不补贴";
		}
		return result;
	}

}
