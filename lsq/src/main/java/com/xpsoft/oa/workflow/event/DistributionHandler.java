package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class DistributionHandler implements DecisionHandler {
	String className;
	@Override
	public String decide(OpenExecution execution) {
		return "";
	}
}
