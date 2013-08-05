package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;


//判断
public class ContractLettingFristHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "";
		
		OpenProcessInstance pi=execution.getProcessInstance();
		String mainItem=(String)pi.getVariable("mainItem");//合同类型
		String contractAmount = (String)pi.getVariable("contractAmount");//合同金额
		
		if(mainItem.contains("01")||mainItem.contains("02")||mainItem.contains("03")){
			if(new BigDecimal(contractAmount).compareTo(new BigDecimal(100000))==-1){
				result="类别01、02、03 + 金额小于10W";
			}else{
				result="类别01、02、03 + 金额大于等于10W 或 类别04、05、06";
			}
		}else{
			result="类别01、02、03 + 金额大于等于10W 或 类别04、05、06";
		}
		
		return result;
	}

}
