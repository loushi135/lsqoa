package com.xpsoft.oa.action.flow;

import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.JbpmService;
import com.xpsoft.oa.service.flow.ProcessFormService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.system.AppUserService;

import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.springframework.security.AuthenticationManager;

public class ProcessRunDetailForLeaderAction extends BaseAction {

	@Resource
	private ProcessRunService processRunService;

	@Resource
	private ProcessFormService processFormService;

	@Resource
	private JbpmService jbpmService;


	@Resource
	private TaskService taskService;

	private Long runId;
	private Long taskId;
	private String piId;

	public Long getRunId() {
		return this.runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	public Long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getPiId() {
		return piId;
	}

	public void setPiId(String piId) {
		this.piId = piId;
	}

	public String execute() throws Exception {
		ProcessRun processRun = null;
		Task task = null;
		if (this.piId != null) {
			ProcessInstance pis = this.jbpmService.getProcessInstanceByExeId(this.piId);
			processRun = this.processRunService.getByPiId(pis.getId());
			getRequest().setAttribute("processRun", processRun);
			this.runId = processRun.getRunId();
			
		} else if (this.runId != null) {
			processRun = (ProcessRun) this.processRunService.get(this.runId);
			getRequest().setAttribute("processRun", processRun);
			if(this.taskId != null){
				task=taskService.getTask(this.taskId.toString());
			}
		} else if (this.taskId != null) {
			ProcessInstance pis = this.jbpmService.getProcessInstanceByTaskId(this.taskId.toString());
			if(pis!=null){
				processRun = this.processRunService.getByPiId(pis.getId());
				getRequest().setAttribute("processRun", processRun);
				this.runId = processRun.getRunId();				
			}
			task=taskService.getTask(this.taskId.toString());
		}
		//form
		List pfList = this.processFormService.getByRunId(this.runId);
		getRequest().setAttribute("pfList", pfList);
		getRequest().setAttribute("piId", processRun.getPiId());
		getRequest().setAttribute("task", task);
		

		return "success";
	}
}
