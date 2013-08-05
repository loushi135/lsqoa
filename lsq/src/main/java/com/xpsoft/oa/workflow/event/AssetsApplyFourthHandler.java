package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

public class AssetsApplyFourthHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		String result="";
		int flag=0;
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		
		String totalPrice=(String)pi.getVariable("prePrice");//总价
		if(new BigDecimal(totalPrice).compareTo(new BigDecimal(3000))!=-1){
			flag = 2;
		}else {
			flag = 1;
		}
		
		switch (flag){
		  	case 1 :result="金额小于3000";break;
		  	case 2 :result="金额大于等于3000";break;
		  	default :break;
		}
		return result;
	}

}
