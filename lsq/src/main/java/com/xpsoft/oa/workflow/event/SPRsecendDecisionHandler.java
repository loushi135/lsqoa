package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

public class SPRsecendDecisionHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		OpenProcessInstance pi = execution.getProcessInstance();
        
        BigDecimal buildPrice=(BigDecimal)pi.getVariable("buildPrice");//甲方造价预算(万元)
        
        if(buildPrice.compareTo(new BigDecimal(3000))==-1){
        	return "3000万以下";
        }else{
        	return "3000万以上";
        }
        
	}
}