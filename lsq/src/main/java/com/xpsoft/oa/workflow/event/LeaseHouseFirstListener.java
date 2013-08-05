package com.xpsoft.oa.workflow.event;

import java.util.Set;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.xpsoft.oa.model.system.AppRole;
import com.xpsoft.oa.model.system.AppUser;

public class LeaseHouseFirstListener implements DecisionHandler {
	public static final String  WORK_DEPTS = "工程一区工程二区工程三区工程五区工程六区工程七区直属一部直属二部";
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
			if (roleName.contains("部门经理")) {
				flag = 1;
			} else if (roleName.contains("分管领导")) {
				flag = 2;
			}
		}
		if(WORK_DEPTS.contains(flowStartUser.getDepartment().getDepName())){//施工部门
			flag = 3;
		}
		switch (flag) {
		case 1:
			result = "部门领导";
			break;
		case 2:
			result = "分管领导";
			break;
		case 3:
			result = "施工部门";
			break;
		default:
			result = "普通员工";
			break;
		}
		return result;
	}

}

