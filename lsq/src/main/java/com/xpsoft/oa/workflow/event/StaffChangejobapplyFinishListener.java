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
import com.xpsoft.oa.model.hrm.Job;
import com.xpsoft.oa.model.statistics.StaffChangejobapply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.hrm.JobService;
import com.xpsoft.oa.service.statistics.StaffChangejobapplyService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.AppUserUpdateService;
import com.xpsoft.oa.service.system.DepartmentService;

public class StaffChangejobapplyFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(StaffChangejobapplyFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String companyName = (String)pi.getVariable("staffChangejobapply@companyName");
		String changeJobUserId = (String)pi.getVariable("staffChangejobapply@changeJobUser@userId");
		String oldDeptId = (String)pi.getVariable("staffChangejobapply@oldDept@depId");
		String oldJobName = (String)pi.getVariable("staffChangejobapply@oldJobName");
		String newDeptId = (String)pi.getVariable("staffChangejobapply@newDept@depId");
		String newJobId = (String)pi.getVariable("staffChangejobapply@newJob@jobId");
		Date changeJobDate = (Date)pi.getVariable("staffChangejobapply@changeJobDate");
		String changeJobReason = (String)pi.getVariable("staffChangejobapply@changeJobReason");
		
		StaffChangejobapply staffChangejobapply = new StaffChangejobapply();
		staffChangejobapply.setCompanyName(companyName);
		staffChangejobapply.setChangeJobDate(changeJobDate);
		staffChangejobapply.setChangeJobReason(changeJobReason);
		if(StringUtils.isNotBlank(changeJobUserId)){
			AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
			AppUser changeJobUser = appUserService.get(Long.valueOf(changeJobUserId));
			staffChangejobapply.setChangeJobUser(changeJobUser);
			AppUserUpdateService appUserUpdateService = (AppUserUpdateService)AppUtil.getBean("appUserUpdateService");
			appUserUpdateService.saveAppUserUpdate(changeJobUser);
		}
		DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
		JobService jobService = (JobService)AppUtil.getBean("jobService");
		if(StringUtils.isNotBlank(oldDeptId)){
			staffChangejobapply.setOldDept(departmentService.get(Long.valueOf(oldDeptId)));
		}
		if(StringUtils.isNotBlank(oldJobName)){
			if(StringUtils.isNotBlank(oldDeptId)){
				Job oldJob = jobService.findByDepAndJobName(Long.valueOf(oldDeptId),oldJobName);
				staffChangejobapply.setOldJob(oldJob);
			}
		}
		if(StringUtils.isNotBlank(newJobId)){
			staffChangejobapply.setNewJob(jobService.get(Long.valueOf(newJobId)));
		}
		if(StringUtils.isNotBlank(newDeptId)){
			staffChangejobapply.setNewDept(departmentService.get(Long.valueOf(newDeptId)));
		}
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		staffChangejobapply.setProcessRunId(processRun.getRunId());

		
		StaffChangejobapplyService staffChangejobapplyService = (StaffChangejobapplyService)AppUtil.getBean("staffChangejobapplyService");
		staffChangejobapplyService.save(staffChangejobapply);
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
