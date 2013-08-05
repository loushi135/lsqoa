package com.xpsoft.oa.workflow.event;
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
import com.xpsoft.oa.model.statistics.EndInnerCheck;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.EndInnerCheckService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.FileAttachService;

public class EndInnerCheckFinishListener implements EventListener {
	private Log logger=LogFactory.getLog(EndInnerCheckFinishListener.class);
	
	@Override
	public void notify(EventListenerExecution  execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the PaymentListApplyFinishListener notify method...");
		}

	//获取流程发起者对象
	AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	EndInnerCheckService endInnerCheckService = (EndInnerCheckService) AppUtil.getBean("endInnerCheckService");
	ProjectNewService projectNewService = (ProjectNewService)AppUtil.getBean("projectNewService");
	AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
	
	//获取表单数据
	OpenProcessInstance pi=execution.getProcessInstance();
	String proId=(String)pi.getVariable("endInnerCheck.project.id");//项目主键
	String checkUserId=(String)pi.getVariable("endInnerCheck.checkUser.userId");
	Date checkTime=(Date)pi.getVariable("endInnerCheck.checkTime");
	String score=(String)pi.getVariable("endInnerCheck.score");
	String customerSatisfy=(String)pi.getVariable("endInnerCheck.customerSatisfy");
	String conclusion=(String)pi.getVariable("endInnerCheck.conclusion");
	String rewardOrPunish=(String)pi.getVariable("endInnerCheck.rewardOrPunish");
	String applyUserId=(String)pi.getVariable("endInnerCheck.applyUser.userId");
	String attachIds=(String)pi.getVariable("endInnerCheck.attachIds");
	
	EndInnerCheck endInnerCheck = new EndInnerCheck();
	endInnerCheck.setCheckTime(checkTime);
	endInnerCheck.setScore(score);
	endInnerCheck.setCustomerSatisfy(customerSatisfy);
	endInnerCheck.setConclusion(conclusion);
	endInnerCheck.setRewardOrPunish(rewardOrPunish);
	if(StringUtils.isNotBlank(proId)){
		ProjectNew projectNew = projectNewService.get(Long.valueOf(proId));
		endInnerCheck.setProject(projectNew);
	}
	
	if(StringUtils.isNotBlank(checkUserId)){
		endInnerCheck.setCheckUser(appUserService.get(Long.valueOf(checkUserId)));
	}
	if(StringUtils.isNotBlank(applyUserId)){
		endInnerCheck.setApplyUser(appUserService.get(Long.valueOf(applyUserId)));
	}
	
	FileAttachService fileAttachService = (FileAttachService)AppUtil.getBean("fileAttachService");
	if(StringUtils.isNotBlank(attachIds)){
		Set<FileAttach> fileSet = new HashSet<FileAttach>();
		String fileIds[] = attachIds.split(",");
		endInnerCheck.getFileAttachs().clear();
		for(String fileId:fileIds){
			fileSet.add(fileAttachService.get(Long.valueOf(fileId)));
		}
		endInnerCheck.getFileAttachs().addAll(fileSet);
	}
	
	ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
	ProcessRun processRun=processRunService.getByExeId(pi.getId());
	endInnerCheck.setProcessRunId(processRun.getRunId());
	
	endInnerCheckService.save(endInnerCheck);
	
	
	//所有参与流程的都发消息
	AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
}
}