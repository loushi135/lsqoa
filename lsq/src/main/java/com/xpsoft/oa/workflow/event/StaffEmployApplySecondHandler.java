package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

//判断
public class StaffEmployApplySecondHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "";
		OpenProcessInstance pi = execution.getProcessInstance();
        String staffType = (String)pi.getVariable("staffType");//普通员工 非普通员工
        result = staffType;
		return result;
	}

}
