package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.OddEmployapply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.MaterialContractService;
import com.xpsoft.oa.service.statistics.OddEmployapplyService;

public class OddEmployapplyFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(OddEmployapplyFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String contractId = (String)pi.getVariable("oddEmployapply.contract.id");
		String employType = (String)pi.getVariable("oddEmployapply.employType");
		String num = (String)pi.getVariable("oddEmployapply.num");
		String contact = (String)pi.getVariable("oddEmployapply.contact");
		String employTime = (String)pi.getVariable("oddEmployapply.employTime");
		String employFee = (String)pi.getVariable("oddEmployapply.employFee");
		String employReason = (String)pi.getVariable("oddEmployapply.employReason");
		String employContent = (String)pi.getVariable("oddEmployapply.employContent");
		String attachFiles = (String)pi.getVariable("oddEmployapply.attachFiles");
		String attachIDs = (String)pi.getVariable("oddEmployapply.attachIDs");

		OddEmployapply oddEmployapply = new OddEmployapply();

		oddEmployapply.setEmployType(employType);
		if(StringUtils.isNotBlank(num)){
			oddEmployapply.setNum(Integer.valueOf(num));
		}
		oddEmployapply.setContact(contact);
		oddEmployapply.setEmployTime(employTime);
		if(StringUtils.isNotBlank(employFee)){
			oddEmployapply.setEmployFee(new BigDecimal(employFee));
		}
		oddEmployapply.setEmployReason(employReason);
		oddEmployapply.setEmployContent(employContent);
		oddEmployapply.setAttachFiles(attachFiles);
		oddEmployapply.setAttachIds(attachIDs);
		
		if(StringUtils.isNotBlank(contractId)){
			MaterialContractService materialContractService = (MaterialContractService)AppUtil.getBean("materialContractService");
			oddEmployapply.setContract(materialContractService.get(Long.valueOf(contractId)));
		}
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		oddEmployapply.setProcessRunId(processRun.getRunId());
		
		OddEmployapplyService oddEmployapplyService = (OddEmployapplyService)AppUtil.getBean("oddEmployapplyService");
		oddEmployapplyService.save(oddEmployapply);
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
