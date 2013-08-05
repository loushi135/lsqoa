package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class BankPayOtherApplyFourHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		// 获取流程付款类别
		String bpaPayTypeOT = (String) execution.getProcessInstance()
				.getVariable("bpaPayTypeOT");

		if ("税金".equals(bpaPayTypeOT)) {
			return "类型为税金";
		} else {
			return "类型非税金";
		}
	}

}
