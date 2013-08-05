package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

//判断
public class BankPayOtherApplyThirdHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "";

		OpenProcessInstance pi = execution.getProcessInstance();

		String bpaPayTypeOT = (String) execution.getProcessInstance().getVariable("bpaPayTypeOT");

		if ("税金".equals(bpaPayTypeOT)) {
			return "金额小于500万或类型为税金";
		} else {
			// String bpaPayTypeOT=(String)pi.getVariable("bpaPayTypeOT");//付款类型
			BigDecimal bpaApplyMoneyOTXX = (BigDecimal) pi.getVariable("bpaApplyMoneyOTXX");// 付款金额

			if (bpaApplyMoneyOTXX.compareTo(new BigDecimal(5000000)) == -1) {
				result = "金额小于500万或类型为税金";
			} else {
				result = "金额大于等于500万";
			}
			return result;
		}
	}

}
