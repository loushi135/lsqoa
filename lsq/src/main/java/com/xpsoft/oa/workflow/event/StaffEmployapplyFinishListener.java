package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.StaffEmployapply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.hrm.JobService;
import com.xpsoft.oa.service.statistics.StaffEmployapplyService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;

public class StaffEmployapplyFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(StaffEmployapplyFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String staffName = (String)pi.getVariable("staffEmployapply@staffName");
		String depId = (String)pi.getVariable("staffEmployapply@dept@depId");
		String jobId = (String)pi.getVariable("staffEmployapply@job@jobId");
		String inviteWay = (String)pi.getVariable("staffEmployapply@inviteWay");
		String publicWay = (String)pi.getVariable("staffEmployapply@publicWay");
		String inviteUserId = (String)pi.getVariable("inviteUserId");
		String inviteReason = (String)pi.getVariable("staffEmployapply@inviteReason");
		String attachFile = (String)pi.getVariable("staffEmployapply@attachFile");
		String attachIDs = (String)pi.getVariable("staffEmployapply@attachIDs");
		String professional = (String)pi.getVariable("staffEmployapply@professional");
		String experience = (String)pi.getVariable("staffEmployapply@experience");
		String confident = (String)pi.getVariable("staffEmployapply@confident");
		String thinkability = (String)pi.getVariable("staffEmployapply@thinkability");
		String expressability = (String)pi.getVariable("staffEmployapply@expressability");
		String looks = (String)pi.getVariable("staffEmployapply@looks");
		String agreeEnterType = (String)pi.getVariable("staffEmployapply@agreeEnterType");
		BigDecimal inspect = (BigDecimal)pi.getVariable("staffEmployapply@inspect");
		BigDecimal inspectSalary = (BigDecimal)pi.getVariable("staffEmployapply@inspectSalary");
		BigDecimal probation = (BigDecimal)pi.getVariable("staffEmployapply@probation");
		BigDecimal probationSalary = (BigDecimal)pi.getVariable("staffEmployapply@probationSalary");
		
		String score =(String)pi.getVariable("staffEmployapply@score");		//分数
		String medicalCheck=(String)pi.getVariable("staffEmployapply@medicalCheck");//体检情况
		
		
		StaffEmployapply staffEmployapply = new StaffEmployapply();
		staffEmployapply.setScore(score);
		staffEmployapply.setMedicalCheck(medicalCheck);
		staffEmployapply.setStaffName(staffName);
		staffEmployapply.setInviteWay(inviteWay);
		staffEmployapply.setPublicWay(publicWay);
		staffEmployapply.setInviteReason(inviteReason);
		staffEmployapply.setAttachFile(attachFile);
		staffEmployapply.setAttachIDs(attachIDs);
		staffEmployapply.setProfessional(professional);
		staffEmployapply.setExperience(experience);
		staffEmployapply.setConfident(confident);
		staffEmployapply.setThinkability(thinkability);
		staffEmployapply.setExpressability(expressability);
		staffEmployapply.setLooks(looks);
		staffEmployapply.setAgreeEnterType(agreeEnterType);
		staffEmployapply.setInspect(inspect);
		staffEmployapply.setInspectSalary(inspectSalary);
		staffEmployapply.setProbation(probation);
		staffEmployapply.setProbationSalary(probationSalary);
		if(StringUtils.isNotBlank(depId)){
			DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
			staffEmployapply.setDept(departmentService.get(Long.valueOf(depId)));
		}
		if(StringUtils.isNotBlank(jobId)){
			JobService jobService = (JobService)AppUtil.getBean("jobService");
			staffEmployapply.setJob(jobService.get(Long.valueOf(jobId)));
		}
		if(StringUtils.isNotBlank(inviteUserId)){
			AppUserService appUserService=(AppUserService)AppUtil.getBean("appUserService");
			staffEmployapply.setInviteUser(appUserService.get(Long.valueOf(inviteUserId)));
		}
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		staffEmployapply.setProcessRunId(processRun.getRunId());
		
		StaffEmployapplyService staffEmployapplyService = (StaffEmployapplyService)AppUtil.getBean("staffEmployapplyService");
		staffEmployapplyService.save(staffEmployapply);
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
