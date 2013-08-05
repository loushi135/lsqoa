package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.personal.Leaveapply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.personal.LeaveapplyService;
import com.xpsoft.oa.service.system.AppUserService;

/**
 * 当请假单由上级审批完成后，会调用该事件进行相关的处理
 * 
 * @juql
 * 
 */
public class LeaveapplyListener implements EventListener {

	private Log logger = LogFactory.getLog(LeaveapplyListener.class);

	/**
	 * 审批的选择
	 */
	Short choice;

	@Override
	public void notify(EventListenerExecution execution) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.info("enter the LeaveapplyListener notify method...");
		}
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");

		OpenProcessInstance pi = execution.getProcessInstance();
		
		Long deptId = Long.parseLong((String) pi.getVariable("deptId"));
		String deptName = (String) pi.getVariable("deptName");
		String userName = (String) pi.getVariable("userName");
		Long userId =  Long.parseLong((String) pi.getVariable("userId"));
		Date startTime = (Date)pi.getVariable("startTime");
		String startAmOrPm = (String) pi.getVariable("startAmOrPm");
		Date endTime = (Date)pi.getVariable("endTime");
		String endAmOrPm = (String) pi.getVariable("endAmOrPm");
		String type = (String) pi.getVariable("type");
		String other = (String) pi.getVariable("other");
		String totalDays = (String) pi.getVariable("totalDays");
		String applyDate = (String)pi.getVariable("applyTime");

		LeaveapplyService leaveapplyService = (LeaveapplyService) AppUtil.getBean("leaveapplyService");
		ShortMessageService smService = (ShortMessageService) AppUtil.getBean("shortMessageService");
		AppUserService appUserService=(AppUserService)AppUtil.getBean("appUserService");
		
		//申请通过之后，保存
		Leaveapply leaveapply = new Leaveapply();
		leaveapply.setDeptId(deptId);
		leaveapply.setDeptName(deptName);
		leaveapply.setUserId(userId);
		leaveapply.setUserName(userName);
		leaveapply.setStartTime(startTime);
		leaveapply.setStartAmOrPm(startAmOrPm);
		leaveapply.setEndTime(endTime);
		leaveapply.setEndAmOrPm(endAmOrPm);
		leaveapply.setType(type);
		leaveapply.setTotalDays(totalDays);
		leaveapply.setOther(other);
		leaveapply.setApplyTime(applyDate!=null?DateUtil.parseDate(applyDate):DateUtil.parseDate(DateUtil.format(new Date(), "yyyy-MM-dd")));
		leaveapply.setApplyUser(userId);
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		leaveapply.setProcessRunId(processRun.getRunId());
		
		//为了避免由于集入考勤信息中出错导致数据重复的现象，将保存放到最后
		leaveapplyService.save(leaveapply);
		
		
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
		
		//给人力资源部发消息
		List<AppUser> appUsers=appUserService.findByRoleAndDept("短消息-人事", "人力资源部");
		if(appUsers.isEmpty()){
			if(logger.isDebugEnabled()){
				logger.info("角色【短消息-人事】下无任何用户");
			}
		}else{
			
			for (int i = 0; i < appUsers.size(); i++) {				
				smService.save(AppUser.SYSTEM_USER, appUsers.get(i).getUserId().toString(), "【"+flowStartUser.getFullname()+"】的请假申请已经通过审批，请办理！时间为：【"+(String) pi.getVariable("leaveApply.startTime")+"至"+(String) pi.getVariable("leaveApply.endTime")+"】", ShortMessage.MSG_TYPE_SYS);
			}
			
		}
		
	}
	
	//干掉重复的
	public List<Date> fuckList(List<Date> list){
		int len = list.size();
	    for(int i=0; i<len-1; i++){
	    	Date temp = list.get(i);
	        for(int j=i+1; j<len; j++){
	            if(temp.equals(list.get(j))){
	                list.remove(j);
	                j-- ;
	                len-- ;
	            }
	        }
	    }
		return  list;
	}
}
