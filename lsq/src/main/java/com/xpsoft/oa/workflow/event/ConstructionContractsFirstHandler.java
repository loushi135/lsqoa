package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;


//判断
public class ConstructionContractsFirstHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "";

		OpenProcessInstance pi=execution.getProcessInstance();
		BigDecimal sumPrice = (BigDecimal)pi.getVariable("sumPrice");
		if(sumPrice.compareTo(new BigDecimal(100000))==-1){
			result="小于10万";
		}else{
			result="大于等于10万";
		}
		return result;
	}

}
