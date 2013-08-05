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
import com.xpsoft.oa.model.statistics.LeaseHouse;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.LeaseHouseService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.system.AppUserService;
public class LeaseHouseFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(PersonLoanFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the UnpunchListener notify method...");
		}
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		ProjectNewService projectNewService = (ProjectNewService)AppUtil.getBean("projectNewService");
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String reporter = (String)pi.getVariable("reporter");
		String reporterDepatment =(String)pi.getVariable("reporterDepatment");
		String reporterCompanyName = (String)pi.getVariable("reporterCompanyName");
		String projectId = (String)pi.getVariable("projectId");
		String projectManagerId = (String)pi.getVariable("projectManagerId");
		Date leaseStart = (Date)pi.getVariable("leaseStart");
		Date leaseEnd = (Date)pi.getVariable("leaseEnd");
		String monthlyRent = (String)pi.getVariable("monthlyRent");
		String yearRent=(String)pi.getVariable("yearRent");
		String sort=(String)pi.getVariable("sort");
		String cause=(String)pi.getVariable("cause");
		String leaseLen = (String)pi.getVariable("leaseLen");
		String attachIds = (String)pi.getVariable("leaseHouse.attachIds");
		String attachFiles = (String)pi.getVariable("leaseHouse.attachFiles");
		
		LeaseHouseService leaseHouseService = (LeaseHouseService)AppUtil.getBean("leaseHouseService");
		LeaseHouse leaseHouse = new LeaseHouse();
		leaseHouse.setCause(cause);
		if(StringUtils.isNotBlank(projectId)){
			ProjectNew project= projectNewService.get(Long.valueOf(projectId));
			leaseHouse.setProject(project);
		}
		if(StringUtils.isNotBlank(projectManagerId)){
			AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
			AppUser projectManager= appUserService.get(Long.valueOf(projectManagerId));
			leaseHouse.setProjectManager(projectManager);
		}
		leaseHouse.setSort(sort);
		leaseHouse.setLeaseEnd(leaseEnd);
		leaseHouse.setLeaseStart(leaseStart);
		leaseHouse.setMonthlyRent(monthlyRent);
		leaseHouse.setReporter(reporter);
		leaseHouse.setReporterCompanyName(reporterCompanyName);
		leaseHouse.setYearRent(yearRent);
		leaseHouse.setReporterDepatment(reporterDepatment);
		leaseHouse.setLeaseLen(leaseLen);
		leaseHouse.setAttachIds(attachIds);
		leaseHouse.setAttachFiles(attachFiles);
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		leaseHouse.setProcessRunId(processRun.getRunId());
		
		leaseHouseService.save(leaseHouse);
		
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
