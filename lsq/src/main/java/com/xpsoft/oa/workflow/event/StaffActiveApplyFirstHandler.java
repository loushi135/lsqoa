package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.system.AppUserService;

//判断
public class StaffActiveApplyFirstHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "";
		OpenProcessInstance pi = execution.getProcessInstance();
		// String staffType = (String)pi.getVariable("staffType");//普通员工 非普通员工
		String userIdStr = (String) pi.getVariable("applyUserId");// 转正人

		AppUserService appUserService = (AppUserService) AppUtil.getBean("appUserService");

		AppUser appUser = appUserService.get(Long.valueOf(userIdStr));

		switch (ContextUtil.getHighestRole(appUser)) {
		case 1:
			result = "普通员工";
			break;
		default:
			result = "非普通员工";
		}

		return result;
	}

}
