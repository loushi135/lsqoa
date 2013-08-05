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
import com.xpsoft.oa.model.statistics.StaffEntryapply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.hrm.JobService;
import com.xpsoft.oa.service.statistics.StaffEntryapplyService;
import com.xpsoft.oa.service.system.DepartmentService;

public class StaffEntryapplyFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(StaffEntryapplyFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String companyName = (String)pi.getVariable("staffEntryapply@companyName");
		String depId = (String)pi.getVariable("staffEntryapply@dept@depId");
		String entryUserName = (String)pi.getVariable("staffEntryapply@entryUserName");
		String jobId = (String)pi.getVariable("staffEntryapply@job@jobId");
		String contact = (String)pi.getVariable("staffEntryapply@contact");
		Date entryDate = (Date)pi.getVariable("staffEntryapply@entryDate");
		StaffEntryapply staffEntryapply = new StaffEntryapply();
		staffEntryapply.setCompanyName(companyName);
		staffEntryapply.setEntryUserName(entryUserName);
		staffEntryapply.setContact(contact);
		staffEntryapply.setEntryDate(entryDate);
		if(StringUtils.isNotBlank(depId)){
			DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
			staffEntryapply.setDept(departmentService.get(Long.valueOf(depId)));
		}
		if(StringUtils.isNotBlank(jobId)){
			JobService jobService = (JobService)AppUtil.getBean("jobService");
			staffEntryapply.setJob(jobService.get(Long.valueOf(jobId)));
		}
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		staffEntryapply.setProcessRunId(processRun.getRunId());
		
		StaffEntryapplyService staffEntryapplyService = (StaffEntryapplyService)AppUtil.getBean("staffEntryapplyService");
		staffEntryapplyService.save(staffEntryapply);
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
