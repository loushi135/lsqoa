package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;


//判断
public class BankPayApplyFirstHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "";

		
		
		OpenProcessInstance pi=execution.getProcessInstance();
		String bpaPayType=(String)pi.getVariable("bpaPayType");//付款类型
		BigDecimal bpaApplyMoneyXX = (BigDecimal)pi.getVariable("bpaApplyMoneyXX");//付款金额
		
		if(bpaApplyMoneyXX.compareTo(new BigDecimal(100000))==-1&&!"尾款结算".equals(bpaPayType)){
				result="主材（无战略）、常规材料、零星材料 金额小于10万";
		}else{
				result="主材（无战略）、常规材料、零星材料 金额大于等于10万或尾款";
		}
		
		return result;
	}

}
