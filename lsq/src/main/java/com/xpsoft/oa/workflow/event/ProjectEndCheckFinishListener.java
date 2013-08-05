package com.xpsoft.oa.workflow.event;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.ProjectEndCheck;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.ProjectEndCheckService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.system.AppUserService;

public class ProjectEndCheckFinishListener implements EventListener {
	private Log logger=LogFactory.getLog(ProjectEndCheckFinishListener.class);
	
	@Override
	public void notify(EventListenerExecution  execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the PaymentListApplyFinishListener notify method...");
		}

	//获取流程发起者对象
	AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	ProjectEndCheckService projectEndCheckService = (ProjectEndCheckService) AppUtil.getBean("projectEndCheckService");
	ProjectNewService projectNewService = (ProjectNewService)AppUtil.getBean("projectNewService");
	AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
	
	//获取表单数据
	OpenProcessInstance pi=execution.getProcessInstance();
	String proId=(String)pi.getVariable("projectEndCheck.project.id");//项目主键
	String qualityUserId=(String)pi.getVariable("projectEndCheck.qualityUser.userId");
	String workUserId=(String)pi.getVariable("projectEndCheck.workUser.userId");
	String safeUserId=(String)pi.getVariable("projectEndCheck.safeUser.userId");
	String preCalUserId=(String)pi.getVariable("projectEndCheck.preCalUser.userId");
	String engineeUserId=(String)pi.getVariable("projectEndCheck.engineeUser.userId");
	String conclusion=(String)pi.getVariable("projectEndCheck.conclusion");
	Date actualWorkDate=(Date)pi.getVariable("projectEndCheck.actualWorkDate");
	Date actualFinishDate=(Date)pi.getVariable("projectEndCheck.actualFinishDate");
	String applyUserId=(String)pi.getVariable("projectEndCheck.applyUser.userId");
	ProjectEndCheck projectEndCheck = new ProjectEndCheck();
	projectEndCheck.setConclusion(conclusion);
	projectEndCheck.setActualWorkDate(actualWorkDate);
	projectEndCheck.setActualFinishDate(actualFinishDate);
	if(StringUtils.isNotBlank(proId)){
		ProjectNew projectNew = projectNewService.get(Long.valueOf(proId));
		projectEndCheck.setProject(projectNew);
	}
	if(StringUtils.isNotBlank(qualityUserId)){
		projectEndCheck.setQualityUser(appUserService.get(Long.valueOf(qualityUserId)));
	}
	if(StringUtils.isNotBlank(workUserId)){
		projectEndCheck.setWorkUser(appUserService.get(Long.valueOf(workUserId)));
	}
	if(StringUtils.isNotBlank(safeUserId)){
		projectEndCheck.setSafeUser(appUserService.get(Long.valueOf(safeUserId)));
	}
	if(StringUtils.isNotBlank(preCalUserId)){
		projectEndCheck.setPreCalUser(appUserService.get(Long.valueOf(preCalUserId)));
	}
	if(StringUtils.isNotBlank(engineeUserId)){
		projectEndCheck.setEngineeUser(appUserService.get(Long.valueOf(engineeUserId)));
	}
	if(StringUtils.isNotBlank(applyUserId)){
		projectEndCheck.setApplyUser(appUserService.get(Long.valueOf(applyUserId)));
	}
	
	ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
	ProcessRun processRun=processRunService.getByExeId(pi.getId());
	projectEndCheck.setProcessRunId(processRun.getRunId());
	
	projectEndCheckService.save(projectEndCheck);
	
	
	//所有参与流程的都发消息
	AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
}
}