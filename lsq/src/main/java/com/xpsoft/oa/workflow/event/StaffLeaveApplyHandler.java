package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.oa.model.system.AppUser;

//判断
public class StaffLeaveApplyHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "";
//		OpenProcessInstance pi = execution.getProcessInstance();
//        String staffType = (String)pi.getVariable("staffType");//普通员工 非普通员工
        
        AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
        
//        result = staffType;
        
        switch(ContextUtil.getHighestRole(flowStartUser)){
        	case 1: result="普通员工";
        		break;
        	default: result="非普通员工";	
        }
        
		return result;
	}

}
