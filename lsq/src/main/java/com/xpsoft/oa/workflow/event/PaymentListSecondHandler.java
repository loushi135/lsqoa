package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

//判断
public class PaymentListSecondHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "";
		int flag = 0;
		//从表单获取数据
		OpenProcessInstance pi=execution.getProcessInstance();
		String paymentType=(String)pi.getVariable("paymentType");
		BigDecimal paymentSumSmall=(BigDecimal)pi.getVariable("paymentSumSmall");
		
		if((paymentType.toString().contains("备用"))&&(paymentSumSmall.compareTo(new BigDecimal(20000))!=-1)){
				flag = 1;
		}else if((paymentType.toString().equals("税金"))&&(paymentSumSmall.compareTo(new BigDecimal(50000))!=-1)){
			    flag = 2;
		}
		switch (flag) {
			case 1:
				result = "备用金大于等于2万";
				break;
			case 2:
				result = "税金大于等于5万";
				break;
			default:
				result = "备用金小于2万或税金小于5万";
				break;

		}
		return result;
	}

}
