package com.xpsoft.oa.workflow.event;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

//判断
public class LeaseHouseSecondListener implements DecisionHandler {
	@Override
	public String decide(OpenExecution execution) {
		String result = "";
		int flag = 0;
		// 从表单获取分类
		OpenProcessInstance pi = execution.getProcessInstance();
		String monthlyRent = (String) pi.getVariable("monthlyRent");// 总租金
		String yearRent = (String) pi.getVariable("yearRent");// 总租金
		if (StringUtils.isNotBlank(yearRent)&& StringUtils.isNotBlank(monthlyRent)) {
			if (Float.valueOf(yearRent) < 20000 && Float.valueOf(monthlyRent) < 3000) {
				flag = 1;
			}
		}
		switch (flag) {
			case 1:
				result = "月租金小于3千并且租金总额小于2万";
				break;
			default:
				result = "月租金大于等于3千或租金总额大于等于2万";
				break;
		}
		return result;
	}
}
