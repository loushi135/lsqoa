package com.xpsoft.oa.workflow.event;
import org.apache.commons.lang.StringUtils;
import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

//判断
public class SuppliersAssessApplyFirstHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "非战略";

		// 获取流程发起者对象
		String cooperateType = (String)execution.getProcessInstance()
				.getVariable("suppliersAssess.cooperateType");
		
		if(StringUtils.isNotBlank(cooperateType)){
			if(cooperateType.equals("战略")){
				result = cooperateType;
			}
		}
		return result;
	}

}
