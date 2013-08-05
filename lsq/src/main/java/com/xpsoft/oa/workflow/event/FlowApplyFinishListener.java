package com.xpsoft.oa.workflow.event;

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
import com.xpsoft.oa.model.statistics.FlowApply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.FlowApplyService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.FileAttachService;

public class FlowApplyFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(FlowApplyFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String flowName = (String)pi.getVariable("flowApply.flowName");
		String background = (String)pi.getVariable("flowApply.background");
		String flowDesc = (String)pi.getVariable("flowApply.flowDesc");
		String relatedUserId = (String)pi.getVariable("flowApply.relatedUser.userId");
		String flowNode = (String)pi.getVariable("flowApply.flowNode");
		String remark = (String)pi.getVariable("flowApply.remark");
		String attachIds = (String)pi.getVariable("flowApply.attachIds");
		String applyUserId = (String)pi.getVariable("flowApply.applyUser.userId");
		FlowApply flowApply = new FlowApply();
		FlowApplyService flowApplyService = (FlowApplyService)AppUtil.getBean("flowApplyService");
		
		flowApply.setFlowName(flowName);
		flowApply.setBackground(background);
		flowApply.setFlowDesc(flowDesc);
		flowApply.setFlowNode(flowNode);
		flowApply.setRemark(remark);
		
		AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
		if(StringUtils.isNotBlank(relatedUserId)){
			flowApply.setRelatedUser(appUserService.get(Long.valueOf(relatedUserId)));
		}
		if(StringUtils.isNotBlank(applyUserId)){
			flowApply.setApplyUser(appUserService.get(Long.valueOf(applyUserId)));
		}
		
		FileAttachService fileAttachService = (FileAttachService)AppUtil.getBean("fileAttachService");
		if(StringUtils.isNotBlank(attachIds)){
			Set<FileAttach> fileSet = new HashSet<FileAttach>();
			String fileIds[] = attachIds.split(",");
			flowApply.getFileAttachs().clear();
			for(String fileId:fileIds){
				fileSet.add(fileAttachService.get(Long.valueOf(fileId)));
			}
			flowApply.getFileAttachs().addAll(fileSet);
		}
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		flowApply.setProcessRunId(processRun.getRunId());
		
		flowApplyService.save(flowApply);
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
