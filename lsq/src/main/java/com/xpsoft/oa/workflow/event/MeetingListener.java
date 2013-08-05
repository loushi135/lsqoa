package com.xpsoft.oa.workflow.event;

import java.text.SimpleDateFormat;
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
import com.xpsoft.oa.model.admin.Meeting;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.admin.MeetingService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.system.AppUserService;


/**
 * 当请假单由上级审批完成后，会调用该事件进行相关的处理
 * 
 * @author
 * 
 */
public class MeetingListener implements EventListener {

	private Log logger = LogFactory.getLog(MeetingListener.class);

	/**
	 * 审批的选择
	 */
	Short choice;

	@Override
	public void notify(EventListenerExecution execution) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.info("enter the MeetingListener notify method...");
		}

		OpenProcessInstance pi = execution.getProcessInstance();
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		// 申请表单参数
		
		
		String department = (String) pi.getVariable("department");
		String applyUser = (String) pi.getVariable("applyUser");
		Long userId =  Long.parseLong((String) pi.getVariable("userId"));
		Date startTime = (Date)pi.getVariable("startTime");
		Date endTime = (Date)pi.getVariable("endTime");
		String meetingTitle = (String) pi.getVariable("meetingTitle");
		int personNum = Integer.valueOf((String) pi.getVariable("personNum"));
		int meetingType = getType((String) pi.getVariable("meetingType"));
		String meetingRequire = (String) pi.getVariable("meetingRequire");
		// 上司的意见
		//String opinion = (String) pi.getVariable("opinion");
		String meetingRoom = (String) pi.getVariable("meetingRoom");

		MeetingService meetingService = (MeetingService) AppUtil.getBean("meetingService");
		
		//申请通过之后，保存会议信息
		Meeting meeting = new Meeting();
		meeting.setApplyUser(applyUser);
		meeting.setDepartment(department);
		meeting.setMeetingNo(meetingTitle.toUpperCase()+applyUser.toUpperCase());
		meeting.setMeetingTitle(meetingTitle);
		meeting.setMeetingType((short)meetingType);
		meeting.setMeetingRoom(meetingRoom);
		meeting.setMeetingRequire(meetingRequire);
		meeting.setPersonNum(personNum);
		meeting.setStartTime(startTime);
		meeting.setEntTime(endTime);
		meeting.setUserId(userId);
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		meeting.setProcessRunId(processRun.getRunId());
		
		meetingService.save(meeting);

		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);

		
		
//		//给办公室发消息
//		List<AppUser> appUsers=appUserService.findByRoleAndDept("短消息-会务", "办公室");
//		if(appUsers.isEmpty()){
//			if(logger.isDebugEnabled()){
//				logger.info("角色【短消息-会务】下无任何用户");
//			}
//		}else{
//			
//			for (int i = 0; i < appUsers.size(); i++) {				
//				smService.save(AppUser.SYSTEM_USER, appUsers.get(i).getUserId().toString(), "【"+flowStartUser.getFullname()+"】的会议室申请已经通过审批，请办理！申请使用时间：【"+(String)pi.getVariable("startTime")+"至" + (String)pi.getVariable("endTime")+"】 地点：【"+meetingRoom+"】", ShortMessage.MSG_TYPE_SYS);
//			}
//			
//		}
		
		
		
	}
	
	private int getType(String typeName){
		if("部门会议".equals(typeName)){
			return 1;
		} else if("公司会议".equals(typeName)){
			return 2;
		} else if("股东会".equals(typeName)){
			return 3;
		} else if("董事会".equals(typeName)){
			return 4;
		} else if("外部接待".equals(typeName)){
			return 5;
		} else if("专项会议".equals(typeName)){
			return 6;
		} else {
			return 7;
		}
	}
}
