package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

//判断
public class PaymentListThirdHandler implements DecisionHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String decide(OpenExecution execution) {

		String result = "";
		int flag = 0;
		//从表单获取数据
		OpenProcessInstance pi=execution.getProcessInstance();
		String paymentType=(String)pi.getVariable("paymentType");
		BigDecimal paymentSumSmall=(BigDecimal)pi.getVariable("paymentSumSmall");
		String deptName=(String)pi.getVariable("deptName");
		
		if((paymentType.toString().contains("备用"))&&(paymentSumSmall.floatValue()<5000000)||((paymentType.toString().equals("税金"))&&(paymentSumSmall.floatValue()<5000000))){
				flag = 1;
			}
											
		switch (flag) {
		case 1:
			result = "金额小于500万";
			break;
		default:
			result = "金额大于等于500万或个人借款";
			break;

		}
		return result;
	}

}
