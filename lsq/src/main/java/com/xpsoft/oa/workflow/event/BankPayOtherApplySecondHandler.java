package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;


//判断
public class BankPayOtherApplySecondHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "";

				
		OpenProcessInstance pi=execution.getProcessInstance();
//		String bpaPayTypeOT=(String)pi.getVariable("bpaPayTypeOT");//付款类型
		BigDecimal bpaApplyMoneyOTXX = (BigDecimal)pi.getVariable("bpaApplyMoneyOTXX");//付款金额
		
		if(bpaApplyMoneyOTXX.compareTo(new BigDecimal(100000))==-1){
			result="金额小于10万";
		}else{
			result="金额大于等于10万";
		}
		
		return result;
	}

}
