package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

//判断
public class StaffEmployApplyFirstHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "";
		int flag = 0;
		OpenProcessInstance pi = execution.getProcessInstance();
        String inviteWay = (String)pi.getVariable("staffEmployapply@inviteWay");//普通员工 非普通员工
      	if("推荐".equals(inviteWay)){
      		flag = 0;
      	}else if("公开招聘".equals(inviteWay)){
      		flag = 1;
      	}
      	switch(flag){
      		case 0: result="推荐";break;
      		case 1: result="公开招聘";break;
      	}
		return result;
	}

}
