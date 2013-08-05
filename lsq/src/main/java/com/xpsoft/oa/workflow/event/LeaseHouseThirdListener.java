package com.xpsoft.oa.workflow.event;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

//判断
public class LeaseHouseThirdListener implements DecisionHandler {
	@Override
	public String decide(OpenExecution execution) {
		String result = "";
		int flag = 0;
		// 从表单获取分类
		OpenProcessInstance pi = execution.getProcessInstance();
		String yearRent = (String) pi.getVariable("yearRent");// 总租金
		if (StringUtils.isNotBlank(yearRent)) {
			if (Float.valueOf(yearRent) >= 20000) {
				flag = 1;
			}
		}
		switch (flag) {
			case 1:
				result = "租金总额大于等于2万";
				break;
			default:
				result = "租金总额小于2万";
				break;
		}

		return result;
	}
}
