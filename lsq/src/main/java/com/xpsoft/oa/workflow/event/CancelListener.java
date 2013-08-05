package com.xpsoft.oa.workflow.event;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskService;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.task.Task;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.info.ShortMessageService;

public class CancelListener implements EventListener {

	private Log logger = LogFactory.getLog(CancelListener.class);
	private String roleName;
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.info("enter the CancelListener notify method...");
		}
		ProcessInstance pi = execution.getProcessInstance();
//		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		if(StringUtils.isBlank(roleName)){
			TaskService taskService = (TaskService)AppUtil.getBean("taskService");
			List<Task> taskList = taskService.createTaskQuery()
					.processInstanceId(pi.getId()).list();
			if(taskList!=null&&taskList.size()>0){
				roleName = taskList.get(0).getActivityName();
			}
		}
		// 获取表单参数
		ShortMessageService smService = (ShortMessageService) AppUtil.getBean("shortMessageService");
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		
		ProcessRun processRun=processRunService.getByPiId(execution.getProcessInstance().getId());
				
		//不重复的发送userId列表
		List<String> userIdList = SetUniqueList.decorate(new ArrayList<String>());
		for (Object obj : processRun.getProcessForms()) {			
			ProcessForm processForm=(ProcessForm)obj;
			
			userIdList.add(processForm.getCreatorId().toString());			
		}
		
		
		//发系统短消息
		for (String userId : userIdList) {
			if("系统".equals(roleName)){
				smService.save(AppUser.SYSTEM_USER, userId, flowStartUser.getFullname()+"不需要该申请，系统已取消！", ShortMessage.MSG_TYPE_SYS);
			}else{
				if(userId.equals(flowStartUser.getUserId().toString())){
					smService.save(AppUser.SYSTEM_USER, userId, "您的【"+processRun.getSubject()+"】审批单在【"+roleName+"】环节上未通过！", ShortMessage.MSG_TYPE_SYS);
				}else{
					smService.save(AppUser.SYSTEM_USER, userId, flowStartUser.getFullname()+"的【"+processRun.getSubject()+"】审批单在【"+roleName+"】环节上未通过！", ShortMessage.MSG_TYPE_SYS);
				}
				
			}
		}

	}	
	
}
