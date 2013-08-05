package com.xpsoft.oa.workflow.event;

import java.util.Set;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.oa.model.system.AppRole;
import com.xpsoft.oa.model.system.AppUser;

public class InvoiceRegisterHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String invoiceType = (String)pi.getVariable("bill.invoiceType");
		
		if("工程发票".equals(invoiceType)){
			return "发票类型为 工程发票";
		}
		
		return "发票类型为 普通发票、增值税发票";
	}

}
