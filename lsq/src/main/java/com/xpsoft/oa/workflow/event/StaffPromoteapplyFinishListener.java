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
import com.xpsoft.oa.model.statistics.StaffPromoteapply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.hrm.JobService;
import com.xpsoft.oa.service.statistics.StaffPromoteapplyService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;

public class StaffPromoteapplyFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(StaffPromoteapplyFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String promoteUserId = (String)pi.getVariable("staffPromoteapply@promoteUser@userId");
		String oldDeptId = (String)pi.getVariable("staffPromoteapply@oldDept@depId");
		String oldJobName = (String)pi.getVariable("staffPromoteapply@oldJobName");
		String newDeptId = (String)pi.getVariable("staffPromoteapply@newDept@depId");
		String newJobId = (String)pi.getVariable("staffPromoteapply@newJob@jobId");
		Date activeDate = (Date)pi.getVariable("staffPromoteapply@activeDate");
		String promoteReason = (String)pi.getVariable("staffPromoteapply@promoteReason");
		String advantageOrChange = (String)pi.getVariable("staffPromoteapply@advantageOrChange");
		String professional = (String)pi.getVariable("staffPromoteapply@professional");
		String mixedAbility = (String)pi.getVariable("staffPromoteapply@mixedAbility");
		
		StaffPromoteapply staffPromoteapply = new StaffPromoteapply();
		staffPromoteapply.setActiveDate(activeDate);
		staffPromoteapply.setPromoteReason(promoteReason);
		staffPromoteapply.setAdvantageOrChange(advantageOrChange);
		staffPromoteapply.setProfessional(professional);
		staffPromoteapply.setMixedAbility(mixedAbility);
		
		if(StringUtils.isNotBlank(promoteUserId)){
			AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
			staffPromoteapply.setPromoteUser(appUserService.get(Long.valueOf(promoteUserId)));
		}
		DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
		JobService jobService = (JobService)AppUtil.getBean("jobService");
		if(StringUtils.isNotBlank(oldDeptId)){
			staffPromoteapply.setOldDept(departmentService.get(Long.valueOf(oldDeptId)));
		}
		if(StringUtils.isNotBlank(oldJobName)){
			if(StringUtils.isNotBlank(oldDeptId)){
				Job oldJob = jobService.findByDepAndJobName(Long.valueOf(oldDeptId),oldJobName);
				staffPromoteapply.setOldJob(oldJob);
			}
		}
		if(StringUtils.isNotBlank(newJobId)){
			staffPromoteapply.setNewJob(jobService.get(Long.valueOf(newJobId)));
		}
		if(StringUtils.isNotBlank(newDeptId)){
			staffPromoteapply.setNewDept(departmentService.get(Long.valueOf(newDeptId)));
		}
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		staffPromoteapply.setProcessRunId(processRun.getRunId());
		
		StaffPromoteapplyService staffPromoteapplyService = (StaffPromoteapplyService)AppUtil.getBean("staffPromoteapplyService");
		staffPromoteapplyService.save(staffPromoteapply);
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
