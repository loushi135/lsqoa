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
import com.xpsoft.oa.model.statistics.StaffActiveapply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.StaffActiveapplyService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.FileAttachService;


public class StaffActiveApplyFinishListener implements EventListener {
	
	private Log logger=LogFactory.getLog(StaffActiveApplyFinishListener.class);

	@Override
	public void notify(EventListenerExecution execution) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.info("enter the StaffActiveApplyFinishListener notify method...");
		}
		
		StaffActiveapplyService staffActiveapplyService = (StaffActiveapplyService) AppUtil.getBean("staffActiveapplyService");
		AppUserService appUserService=(AppUserService)AppUtil.getBean("appUserService");
		FileAttachService fileAttachService = (FileAttachService)AppUtil.getBean("fileAttachService");
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		Object applyName=pi.getVariable("applyName");//姓名
		String applyUserId = (String)pi.getVariable("applyUserId");
		Object deptName=pi.getVariable("deptName");//公司/部门
		Object workPosition=pi.getVariable("workPosition");//职位
//		Object examProject=pi.getVariable("examProject");//考核项目
		Object workAchieve=pi.getVariable("workAchieve");//工作成果
		Object workEfficiency=pi.getVariable("workEfficiency");//工作效率
		Object teamSpirit=pi.getVariable("teamSpirit");//团队精神
		Object businessLevel=pi.getVariable("businessLevel");//业务水平
		Object consciousness=pi.getVariable("consciousness");//成本意识
		Object innovationAbility=pi.getVariable("innovationAbility");//创新能力
		Object developAbility=pi.getVariable("developAbility");//发展潜力
		Object workAttitude=pi.getVariable("workAttitude");//工作态度
		Object character=pi.getVariable("character");//品德言行
		Object salaryOption=pi.getVariable("salaryOption");//工资方式选择
		Object salaryMonthA=pi.getVariable("salaryMonthA");//考核期满后月薪
		Object salaryBusinessA=pi.getVariable("salaryBusinessA");//按业绩考核定
		Object contractOption=pi.getVariable("contractOption");//合同方式选择
		Object contractTime=pi.getVariable("contractTime");//合同年限选择
		Object salaryMonthB=pi.getVariable("salaryMonthB");//转正后月薪
		Object salaryBusinessB=pi.getVariable("salaryBusinessB");//基本年薪
		Object deptLeaderName=pi.getVariable("deptLeaderName");//部门领导
		String staffActiveAttachIDs=(String)pi.getVariable("staffActiveAttachIDs");//上传文件id
		String staffActiveAttachFiles=(String)pi.getVariable("staffActiveAttachFiles");//上传文件id
		
		StaffActiveapply staffActiveapply = new StaffActiveapply();
		
		staffActiveapply.setProbation((String)pi.getVariable("probation"));//试用期
		staffActiveapply.setFirstAttachIds((String)pi.getVariable("staffActiveAttachmentAttachIDs"));//上传附件id
		staffActiveapply.setFirstAttachFiles((String)pi.getVariable("staffActiveAttachmentAttachFiles"));//上传附件
		staffActiveapply.setScore((String)pi.getVariable("score"));//分数
		
		if(applyName!=null){
			staffActiveapply.setApplyName(applyName.toString());
		}
		if(StringUtils.isNotBlank(applyUserId)){
			staffActiveapply.setApplyUser(appUserService.get(Long.valueOf(applyUserId)));
		}
		if(deptName!=null){
			staffActiveapply.setDeptName(deptName.toString());
		}
		if(workPosition!=null){
			staffActiveapply.setWorkPosition(workPosition.toString());
		}
//		if(examProject!=null){
//			staffActiveapply.setExamProject(examProject.toString());
//		}
		if(workAchieve!=null){
			staffActiveapply.setWorkAchieve(workAchieve.toString());
		}
		if(workEfficiency!=null){
			staffActiveapply.setWorkEfficiency(workEfficiency.toString());
		}
		if(teamSpirit!=null){
			staffActiveapply.setTeamSpirit(teamSpirit.toString());
		}
		if(businessLevel!=null){
			staffActiveapply.setBusinessLevel(businessLevel.toString());
		}
		if(consciousness!=null){
			staffActiveapply.setConsciousness(consciousness.toString());
		}
		if(innovationAbility!=null){
			staffActiveapply.setInnovationAbility(innovationAbility.toString());
		}
		if(developAbility!=null){
			staffActiveapply.setDevelopAbility(developAbility.toString());
		}
		if(workAttitude!=null){
			staffActiveapply.setWorkAttitude(workAttitude.toString());
		}
		if(character!=null){
			staffActiveapply.setCharacter(character.toString());
		}
		if(salaryOption!=null){
			staffActiveapply.setSalaryOption((String)salaryOption);
		}
		if(salaryMonthA!=null){
			if(StringUtils.isNotBlank(salaryMonthA.toString())){
				staffActiveapply.setSalaryMonthA(new BigDecimal(salaryMonthA.toString()));
			}
		}
		if(salaryBusinessA!=null){
			if(StringUtils.isNotBlank(salaryBusinessA.toString())){
				staffActiveapply.setSalaryBusinessA(new BigDecimal((String)salaryBusinessA));
			}
		}
		if(contractOption!=null){
			staffActiveapply.setContractOption((String)contractOption);
		}
		if(contractTime!=null){
			staffActiveapply.setContractTime((String)contractTime);
		}
		if(salaryMonthB!=null){
			if(StringUtils.isNotBlank(salaryMonthB.toString())){
				staffActiveapply.setSalaryMonthB(new BigDecimal((String)salaryMonthB));
			}
		}
		if(salaryBusinessB!=null){
			if(StringUtils.isNotBlank(salaryBusinessB.toString())){
				staffActiveapply.setSalaryBusinessB(new BigDecimal((String)salaryBusinessB));
			}
		}
		if(deptLeaderName!=null){
			staffActiveapply.setDeptLeaderName(deptLeaderName.toString());
		}
		staffActiveapply.setAttachIds(staffActiveAttachIDs);
		staffActiveapply.setAttachFiles(staffActiveAttachFiles);
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		staffActiveapply.setProcessRunId(processRun.getRunId());
		
	    staffActiveapplyService.save(staffActiveapply);
	  //获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");

			
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
//		smService.save(AppUser.SYSTEM_USER, flowStartUser.getId(), "您的申请已通过最终审批！", ShortMessage.MSG_TYPE_SYS);

	}
}
