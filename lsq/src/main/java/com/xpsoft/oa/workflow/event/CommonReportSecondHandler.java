package com.xpsoft.oa.workflow.event;

import java.util.Set;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.xpsoft.oa.model.system.AppRole;
import com.xpsoft.oa.model.system.AppUser;

//判断
public class CommonReportSecondHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String roleName = "";
		String result = "";
		int flag = 0;

		// 获取流程发起者对象
		AppUser flowStartUser = (AppUser) execution.getProcessInstance()
				.getVariable("flowStartUser");
		// 获取流程发起者的角色
		Set<AppRole> AppRoleSet = flowStartUser.getRoles();

		for (AppRole appRole : AppRoleSet) {
			roleName = appRole.getRoleName();
			if ("流程-部门经理".equals(roleName)) {
				flag = 1;
			} else if (roleName.contains("分管领导")) {
				flag = 2;
				break;
			}
		}

		switch (flag) {
		case 1:
			result = "部门领导";
			break;
		case 2:
			result = "分管领导";
			break;
		default:
			result = "普通员工";
			break;

		}
		return result;
	}

}
