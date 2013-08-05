package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.OutTaxApply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.OutTaxApplyService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.system.FileAttachService;

public class OutTaxApplyFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(OutTaxApplyFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the UnpunchListener notify method...");
		}
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)pi.getVariable("flowStartUser");
		ProjectNewService projectNewService = (ProjectNewService)AppUtil.getBean("projectNewService");
		
		String proId = (String)pi.getVariable("outTaxApply.proId");
		String attachIds = (String)pi.getVariable("outTaxApply.attachIds");
		String attathType = (String)pi.getVariable("outTaxApply.attathType");
		BigDecimal amountXX = (BigDecimal)pi.getVariable("outTaxApply.amountXX");
		String amountDX = (String)pi.getVariable("outTaxApply.amountDX");
		
		
		FileAttachService fileAttachService = (FileAttachService)AppUtil.getBean("fileAttachService");
		OutTaxApplyService outTaxApplyService = (OutTaxApplyService)AppUtil.getBean("outTaxApplyService");
		OutTaxApply outTaxApply = new OutTaxApply();
		
		outTaxApply.setProjectNew(projectNewService.get(Long.valueOf(proId)));
		outTaxApply.setAmountDX(amountDX);
		outTaxApply.setAmountXX(amountXX);
		outTaxApply.setAttathType(attathType);
		outTaxApply.setTimeCreate(new Date());
		outTaxApply.setUserCreate(flowStartUser);
		if(StringUtils.isNotBlank(attachIds)){
			Set<FileAttach> fileSet = new HashSet<FileAttach>();
			String fileIds[] = attachIds.split(",");
			outTaxApply.getFileAttachs().clear();
			for(String fileId:fileIds){
				fileSet.add(fileAttachService.get(Long.valueOf(fileId)));
			}
			outTaxApply.getFileAttachs().addAll(fileSet);
		}
		
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		
		outTaxApply.setProcessRunId(processRun.getRunId());
		
		outTaxApplyService.save(outTaxApply);
		
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
