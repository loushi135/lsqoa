package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;


//判断
public class BankPayApplySecondHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "";

		
		
		OpenProcessInstance pi=execution.getProcessInstance();
		BigDecimal bpaApplyMoneyXX = (BigDecimal)pi.getVariable("bpaApplyMoneyXX");//付款金额
		
		if(bpaApplyMoneyXX.compareTo(new BigDecimal(5000000))==-1){
			result="金额小于500万";
		}else{
			result="金额大于等于500万";
		}
		
		return result;
	}

}
