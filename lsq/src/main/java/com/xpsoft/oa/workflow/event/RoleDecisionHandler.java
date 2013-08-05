package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.oa.model.system.AppUser;

public class RoleDecisionHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		String result = "";
		int flag = 1;

		// 获取流程发起者对象
		AppUser flowStartUser = (AppUser) execution.getProcessInstance()
				.getVariable("flowStartUser");

		flag = ContextUtil.getHighestRole(flowStartUser);

		switch (flag) {
		case 2:
			result = "部门领导";
			break;
		case 3:
			result = "分管领导";
			break;
		default:
			result = "普通员工";
			break;

		}
		return result;
	}

}
