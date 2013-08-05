package com.xpsoft.oa.service.flow.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.ExecutionService;
import org.jbpm.pvm.internal.task.TaskImpl;

import com.xpsoft.core.jbpm.pv.TaskInfo;
import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.flow.TaskDao;
import com.xpsoft.oa.model.flow.HandleTask;
import com.xpsoft.oa.model.flow.JbpmTask;
import com.xpsoft.oa.model.flow.ProSubjectDef;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.FormDataService;
import com.xpsoft.oa.service.flow.HandleTaskService;
import com.xpsoft.oa.service.flow.ProSubjectDefService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.flow.TaskService;
import com.xpsoft.oa.service.system.AppUserService;

public class TaskServiceImpl extends BaseServiceImpl<TaskImpl> implements
		TaskService {
	@Resource
    private ProSubjectDefService proSubjectDefService;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private HandleTaskService handleTaskService;
	private TaskDao dao;

	@Resource
	private AppUserService appUserService;

	public TaskServiceImpl(TaskDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<TaskImpl> getTasksByUserId(String userId, PagingBean pb) {
		return this.dao.getTasksByUserId(userId, pb);
	}

	public List<TaskInfo> getTaskInfosByUserId(String userId, PagingBean pb) {
		//朗杰通本身的待办事项
		List<TaskImpl> list = getTasksByUserId(userId, pb);
		//怡和oa生成的待办事项
		List<HandleTask> yhoaTaskList= handleTaskService.getHandleTaskByAssigneeId(userId,(short)1);
		List<TaskInfo> taskInfoList = new ArrayList<TaskInfo>();
		
		//向展示列表中添加朗捷通任务
		for (TaskImpl taskImpl : list) {
			TaskInfo taskInfo = new TaskInfo(taskImpl);
			AppUser user=null;
			if (taskImpl.getAssignee() != null) {
				user = (AppUser) this.appUserService.get(new Long(taskImpl.getAssignee()));
				taskInfo.setAssignee(user.getFullname());
			}
			
			if (taskImpl.getSuperTask() != null){
				taskImpl = taskImpl.getSuperTask();
				taskInfo.setExecutionId(taskImpl.getExecutionId());
			}
			ProcessRun processRun = this.processRunService.getByPiId(taskImpl.getExecutionId());
			if (processRun != null) {
				ProSubjectDef proSubjectDef= proSubjectDefService.getByDefId(processRun.getProDefinition().getDefId());
			       if(null!=proSubjectDef){
					       taskInfo.setTaskName(processRun.getSubject());
			       }else{
			    	   taskInfo.setTaskName(processRun.getCreator()+"的"+processRun.getProDefinition().getName());
			       }
				
				taskInfo.setActivityName(taskImpl.getActivityName());
			}

			taskInfoList.add(taskInfo);
		}
		
		//向展示列表中添怡和任务
		for (HandleTask handleTask : yhoaTaskList) {
			TaskInfo taskInfo = new TaskInfo();
			taskInfo.setActivityName(handleTask.getUrl());
			taskInfo.setAssignee(handleTask.getAssignee());
			taskInfo.setCreateTime(handleTask.getTaskCreatetime());
			taskInfo.setTaskId(handleTask.getTaskId());
			taskInfo.setTaskName("("+handleTask.getAppsouce()+")"+handleTask.getSubject());
			
			taskInfoList.add(taskInfo);
		}
		return taskInfoList;
	}

	public Set<Long> getHastenByActivityNameVarKeyLongVal(String activityName,
			String varKey, Long value) {
		List<JbpmTask> jtasks = this.dao
				.getByActivityNameVarKeyLongVal(activityName, varKey, value);
		Set userIds = new HashSet();
		for (JbpmTask jtask : jtasks) {
			if (jtask.getAssignee() == null) {
				List userlist = this.dao.getUserIdByTask(jtask
						.getTaskId());
				userIds.addAll(userlist);
				List<Long> groupList = this.dao.getGroupByTask(jtask
						.getTaskId());
				for (Long l : groupList) {
					List<AppUser> uList = this.appUserService
							.findByRoleId(l);
					List idList = new ArrayList();
					for (AppUser appUser : uList) {
						idList.add(appUser.getUserId());
					}
					userIds.addAll(idList);
				}
			} else {
				userIds.add(new Long(jtask.getAssignee()));
			}
		}
		return userIds;
	}

}
