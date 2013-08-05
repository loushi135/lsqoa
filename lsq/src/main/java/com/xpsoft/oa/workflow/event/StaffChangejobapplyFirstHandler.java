package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

//判断
public class StaffChangejobapplyFirstHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "";
		int flag = 0;
		OpenProcessInstance pi = execution.getProcessInstance();
        String oldDepId = (String)pi.getVariable("staffChangejobapply@oldDept@depId");//原部门
        String newDepId = (String)pi.getVariable("staffChangejobapply@newDept@depId");//原部门
      	if(oldDepId.equals(newDepId)){
      		flag = 0;
      	}else{
      		flag = 1;
      	}
      	switch(flag){
      		case 0: result="部门内";break;
      		case 1: result="部门外";break;
      	}
		return result;
	}

}
