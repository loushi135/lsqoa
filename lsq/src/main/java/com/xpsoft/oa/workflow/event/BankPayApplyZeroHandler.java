package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;


//判断
public class BankPayApplyZeroHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "";
		
		OpenProcessInstance pi=execution.getProcessInstance();
		String bpaPayType=(String)pi.getVariable("bpaPayType");//付款类型
		
		if("尾款结算".equals(bpaPayType)||"主材（无战略）".equals(bpaPayType)||"常规材料".equals(bpaPayType)||"零星材料".equals(bpaPayType)){
				result="主材（无战略）、常规材料、零星材料、尾款结算";
		}else if("主材（战略）".equals(bpaPayType)){
				result="主材（战略）";
		}else if("单包".equals(bpaPayType)||"小双包".equals(bpaPayType)||"大双包".equals(bpaPayType)){
				result="单包、小双包、大双包";
		}
		
		return result;
	}

}
