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
import com.xpsoft.oa.model.statistics.StaffRecruitapply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Dictionary;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.hrm.JobService;
import com.xpsoft.oa.service.statistics.StaffRecruitapplyService;
import com.xpsoft.oa.service.system.DepartmentService;
import com.xpsoft.oa.service.system.DictionaryService;

public class StaffRecruitapplyFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(StaffRecruitapplyFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String companyName = (String)pi.getVariable("staffRecruitapply@companyName");
		String depId = (String)pi.getVariable("staffRecruitapply@dept@depId");
		String jobId = (String)pi.getVariable("staffRecruitapply@job@jobId");
		Integer currentNum = (Integer)pi.getVariable("staffRecruitapply@currentNum");
		Integer applyNum = (Integer)pi.getVariable("staffRecruitapply@applyNum");
		String applyReason = (String)pi.getVariable("staffRecruitapply@applyReason");
		String age = (String)pi.getVariable("staffRecruitapply@age");
		String sex = (String)pi.getVariable("staffRecruitapply@sex");
		String majorDicId = (String)pi.getVariable("staffRecruitapply@majorDic@dicId");
		String educationDicId = (String)pi.getVariable("staffRecruitapply@educationDic@dicId");
		String workYear = (String)pi.getVariable("staffRecruitapply@workYear");
		String skillRequirement = (String)pi.getVariable("staffRecruitapply@skillRequirement");
		String positionDuty = (String)pi.getVariable("staffRecruitapply@positionDuty");
		String workExperience = (String)pi.getVariable("staffRecruitapply@workExperience");
		String personality = (String)pi.getVariable("staffRecruitapply@personality");
		String mainPositionIDs = (String)pi.getVariable("staffRecruitapply@mainPositionIDs");
		String mainPositionNames = (String)pi.getVariable("staffRecruitapply@mainPositionNames");
		String otherRequirement = (String)pi.getVariable("staffRecruitapply@otherRequirement");
		Date deadline = (Date)pi.getVariable("staffRecruitapply@deadline");
		String personalCharacter = (String)pi.getVariable("staffRecruitapply@personalCharacter");
		
		StaffRecruitapply staffRecruitapply = new StaffRecruitapply();
		staffRecruitapply.setCompanyName(companyName);
		staffRecruitapply.setCurrentNum(currentNum);
		staffRecruitapply.setApplyNum(applyNum);
		staffRecruitapply.setApplyReason(applyReason);
		staffRecruitapply.setAge(age);
		staffRecruitapply.setSex(sex);
		staffRecruitapply.setWorkYear(workYear);
		staffRecruitapply.setWorkExperience(workExperience);
		staffRecruitapply.setPersonality(personality);
		staffRecruitapply.setMainPositions(mainPositionNames);
		staffRecruitapply.setPositionDuty(positionDuty);
		staffRecruitapply.setSkillRequirement(skillRequirement);
		staffRecruitapply.setOtherRequirement(otherRequirement);
		staffRecruitapply.setDeadline(deadline);
		staffRecruitapply.setPersonalCharacter(personalCharacter);
		if(StringUtils.isNotBlank(depId)){
			DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
			staffRecruitapply.setDept(departmentService.get(Long.valueOf(depId)));
		}
		if(StringUtils.isNotBlank(jobId)){
			JobService jobService = (JobService)AppUtil.getBean("jobService");
			staffRecruitapply.setJob(jobService.get(Long.valueOf(jobId)));
		}
		if(StringUtils.isNotBlank(majorDicId)){
			DictionaryService dictionaryService = (DictionaryService)AppUtil.getBean("dictionaryService");
			staffRecruitapply.setMajorDic(dictionaryService.get(Long.valueOf(majorDicId)));
		}
		if(StringUtils.isNotBlank(educationDicId)){
			DictionaryService dictionaryService = (DictionaryService)AppUtil.getBean("dictionaryService");
			staffRecruitapply.setEducationDic(dictionaryService.get(Long.valueOf(educationDicId)));
		}
		if(StringUtils.isNotBlank(mainPositionIDs)){
			String[] dicIds = mainPositionIDs.split(",");
			DictionaryService dictionaryService = (DictionaryService)AppUtil.getBean("dictionaryService");
			Set<Dictionary> mainPositionDics = new HashSet<Dictionary>();
			for(String dicId:dicIds){
				Dictionary dictionary = dictionaryService.get(Long.valueOf(dicId));
				mainPositionDics.add(dictionary);
			}
			staffRecruitapply.setMainPositionDics(mainPositionDics);
		}
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		staffRecruitapply.setProcessRunId(processRun.getRunId());
		
		StaffRecruitapplyService staffRecruitapplyService = (StaffRecruitapplyService)AppUtil.getBean("staffRecruitapplyService");
		staffRecruitapplyService.save(staffRecruitapply);
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
