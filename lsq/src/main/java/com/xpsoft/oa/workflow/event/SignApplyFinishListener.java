package com.xpsoft.oa.workflow.event;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.SignApply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.customer.SuppliersAssessService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.MaterialContractService;
import com.xpsoft.oa.service.statistics.SignApplyService;

public class SignApplyFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(SignApplyFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String contractId = (String)pi.getVariable("signApply.contract.id");
		String suppliersId = (String)pi.getVariable("signApply.feeSupplier.suppliersId");
		String signReason = (String)pi.getVariable("signApply.signReason");
		String signContent = (String)pi.getVariable("signApply.signContent");
		String remark = (String)pi.getVariable("signApply.remark");
		String attachFiles = (String)pi.getVariable("signApply.attachFiles");
		String attachIDs = (String)pi.getVariable("signApply.attachIDs");
		SignApplyService signApplyService = (SignApplyService)AppUtil.getBean("signApplyService");
		
		SignApply signApply = new SignApply();
		signApply.setSignReason(signReason);
		signApply.setSignContent(signContent);
		signApply.setRemark(remark);
		signApply.setAttachFiles(attachFiles);
		signApply.setAttachIds(attachIDs);
		if(StringUtils.isNotBlank(suppliersId)){
			SuppliersAssessService suppliersAssessService = (SuppliersAssessService)AppUtil.getBean("suppliersAssessService");
			signApply.setFeeSupplier(suppliersAssessService.get(Long.valueOf(suppliersId)));
		}
		if(StringUtils.isNotBlank(contractId)){
			MaterialContractService materialContractService = (MaterialContractService)AppUtil.getBean("materialContractService");
			signApply.setContract(materialContractService.get(Long.valueOf(contractId)));
		}
		signApply.setSignNo(signApplyService.getMaxSignNo());
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		signApply.setProcessRunId(processRun.getRunId());
		
		signApplyService.save(signApply);
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
