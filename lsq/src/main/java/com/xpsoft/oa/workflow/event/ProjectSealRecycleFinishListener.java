package com.xpsoft.oa.workflow.event;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.ProjectSealRecycle;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.ProjectSealRecycleService;
import com.xpsoft.oa.service.statistics.ProjectSealService;
import com.xpsoft.oa.service.system.AppUserService;

public class ProjectSealRecycleFinishListener implements EventListener {
	private Log logger=LogFactory.getLog(ProjectSealRecycleFinishListener.class);
	
	@Override
	public void notify(EventListenerExecution  execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the ProjectSealRecycleFinishListener notify method...");
		}

	//获取流程发起者对象
	AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	ProjectSealRecycleService projectSealRecycleService = (ProjectSealRecycleService)AppUtil.getBean("projectSealRecycleService");
	ProjectSealService projectSealService=(ProjectSealService) AppUtil.getBean("projectSealService");
	AppUserService appUserService= (AppUserService) AppUtil.getBean("appUserService");
	
	//获取表单数据
	OpenProcessInstance pi=execution.getProcessInstance();
	
	String sealId=(String)pi.getVariable("flowSealRecycle.sealApplyId");//印章ID
	Date innerCheckTime=(Date)pi.getVariable("flowSealRecycle.innerCheckTime");//项目完工内检时间
	Date lastReturnTime=(Date)pi.getVariable("flowSealRecycle.lastReturnTime");//最晚归还时间
	String thisApplyUserId=(String)pi.getVariable("flowSealRecycle.thisApplyUserId");//经办人ID
	
	ProjectSealRecycle projectSealRecycle=new ProjectSealRecycle();
	
	
	ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
	ProcessRun processRun=processRunService.getByExeId(pi.getId());
	
	projectSealRecycle.setProcessRunId(processRun.getRunId());
	projectSealRecycle.setProjectSeal(projectSealService.get(Long.valueOf(sealId)));
	projectSealRecycle.setApplyUser(appUserService.get(Long.valueOf(thisApplyUserId)));
	projectSealRecycle.setInnerCheckTime(innerCheckTime);
	projectSealRecycle.setLastReturnTime(lastReturnTime);
	
	projectSealRecycleService.save(projectSealRecycle);
	
	
	//所有参与流程的都发消息
	AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
}
}