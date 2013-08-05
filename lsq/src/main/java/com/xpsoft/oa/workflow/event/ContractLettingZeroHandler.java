package com.xpsoft.oa.workflow.event;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;


//判断
public class ContractLettingZeroHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		
		OpenProcessInstance pi=execution.getProcessInstance();
		String mainItem=(String)pi.getVariable("mainItem");//合同类型
		String result = "类别01、02、03";
		if(StringUtils.isNotBlank(mainItem)){
			if(mainItem.contains("01")||mainItem.contains("02")||mainItem.contains("03")){
				result = "类别01、02、03";
			}else{
				result="类别04、05、06";
			}
		}
		return result;
	}

}
