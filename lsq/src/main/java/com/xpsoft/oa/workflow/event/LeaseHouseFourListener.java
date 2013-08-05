package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

//判断
public class LeaseHouseFourListener implements DecisionHandler {
	@Override
	public String decide(OpenExecution execution) {
		// 从表单获取分类
		OpenProcessInstance pi = execution.getProcessInstance();
		String sort = (String) pi.getVariable("sort");// 类别：项目，其它

		return "类别为"+sort;
	}
}
