package com.xpsoft.oa.workflow.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.statistics.StaffLeaveapply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.statistics.StaffLeaveapplyService;
import com.xpsoft.oa.service.system.AppUserService;


public class StaffLeaveApplyFinishListener implements EventListener {
	
	private Log logger=LogFactory.getLog(StaffLeaveApplyFinishListener.class);

	@Override
	public void notify(EventListenerExecution execution) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.info("enter the StaffActiveApplyFinishListener notify method...");
		}

		
//		ShortMessageService smService = (ShortMessageService) AppUtil.getBean("shortMessageService");
		StaffLeaveapplyService staffLeaveapplyService = (StaffLeaveapplyService) AppUtil.getBean("staffLeaveapplyService");
		AppUserService appUserService=(AppUserService)AppUtil.getBean("appUserService");
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		Object companyName=pi.getVariable("companyName");//所属公司
		Object applyName=pi.getVariable("applyName");//姓名
		Object sex=pi.getVariable("sex");//性别
		Object deptName=pi.getVariable("deptName");//部门
		Object workPosition=pi.getVariable("workPosition");//职位
		Object comeInDate=pi.getVariable("comeInDate");//进公司日期
		Object leaveReason=pi.getVariable("leaveReason");//离职原因
		Object applyDate=pi.getVariable("applyDate");//离职申请日期
		Object leaveDate=pi.getVariable("leaveDate");//正式离职日期
		StaffLeaveapply staffLeaveapply = new StaffLeaveapply();
		if(companyName!=null){
			staffLeaveapply.setCompanyName(companyName.toString());
		}
		if(applyName!=null){
			staffLeaveapply.setApplyName(applyName.toString());
			staffLeaveapply.setSignName(applyName.toString());
		}
		if(sex!=null){
			staffLeaveapply.setSex((String)sex);
		}
		if(workPosition!=null){
			staffLeaveapply.setWorkPosition(workPosition.toString());
		}
		if(leaveReason!=null){
			staffLeaveapply.setLeaveReason(leaveReason.toString());
		}
		if(deptName!=null){
			staffLeaveapply.setDeptName(deptName.toString());
		}
		if(comeInDate!=null){
			staffLeaveapply.setComeInDate((Date)comeInDate);
		}
		if(applyDate!=null){
			staffLeaveapply.setApplyDate((Date)applyDate);
			staffLeaveapply.setSignDate((Date)applyDate);
		}
		if(leaveDate!=null){
			staffLeaveapply.setLeaveDate((Date)leaveDate);
		}
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		staffLeaveapply.setProcessRunId(processRun.getRunId());
		
		staffLeaveapplyService.save(staffLeaveapply);
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");

		
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);

	}

}
