package com.xpsoft.oa.action.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.model.Transition;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.google.gson.Gson;
import com.xpsoft.core.jbpm.pv.ParamField;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.flow.ProDefinition;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.flow.Transform;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.FormDataService;
import com.xpsoft.oa.service.flow.JbpmService;
import com.xpsoft.oa.service.flow.ProDefinitionService;
import com.xpsoft.oa.service.flow.ProcessFormService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.FlowAutoCommitService;
import com.xpsoft.webservice.service.inner.GoldMantisService;

import flexjson.JSONSerializer;

public class ProcessActivityAction extends BaseAction {

	@Resource
	private ProDefinitionService proDefinitionService;

	@Resource
	private ProcessRunService processRunService;

	@Resource
	private ProcessFormService processFormService;

	@Resource
	private JbpmService jbpmService;
	@Resource
	private AppUserService appUserService;

	@Resource
	private FormDataService formDataService;

	@Resource
	VelocityEngine flowVelocityEngine;
	
	@Resource
	private TaskService taskService;

	@Resource
	private FlowAutoCommitService  flowAutoCommitService;
	
	@Resource
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Resource
	private GoldMantisService goldMantisService;

	private String activityName;
	private Long runId;
	private Long taskId;
	private Long defId;

	public Long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getRunId() {
		return this.runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Long getDefId() {
		return this.defId;
	}

	public void setDefId(Long defId) {
		this.defId = defId;
	}

	public String get() throws Exception {
		ProDefinition proDefinition = getProDefinition();
		String processName = proDefinition.getName();

		if (StringUtils.isEmpty(this.activityName)) {
			this.activityName = this.jbpmService.getStartNodeName(proDefinition);
		}

		String tempLocation = ProcessActivityAssistant.getFormPath(processName,this.activityName);

		Map model = new HashMap();
		Map formDataMap = null;
		if (this.runId != null) {
			formDataMap = this.formDataService.getFromDataMap(this.runId,this.activityName);
		}

		Map fieldsMap = ProcessActivityAssistant.constructFieldMap(processName,this.activityName);

		Iterator fieldNames = fieldsMap.keySet().iterator();
		while (fieldNames.hasNext()) {
			String fieldName = (String) fieldNames.next();
			if (formDataMap != null) {
				Object fieldVal = formDataMap.get(fieldName);
				model.put(fieldName, fieldVal);
			}
			if (!model.containsKey(fieldName)) {
				model.put(fieldName, "");
			}

		}

		if (this.taskId != null) {
			ProcessRun processRun = this.processRunService.getByTaskId(this.taskId.toString());
			Map processRunVars = this.processFormService.getVariables(processRun.getRunId());

			List<Transition> trans = this.jbpmService.getTransitionsByTaskId(this.taskId.toString());

			List allTrans = new ArrayList();
			for (Transition tran : trans) {
				if (tran.getName() != null && tran.getSource() != null
						&& tran.getDestination() != null) {
					allTrans.add(new Transform(tran));
				}
			}

			model.putAll(processRunVars);
			model.put("nextTrans", allTrans);
			model.put("runId", processRun.getRunId());
		}

		model.put("currentUser", ContextUtil.getCurrentUser());
		model.put("dateTool", new DateTool());
		String formUiJs = "";
		try {
			formUiJs = VelocityEngineUtils.mergeTemplateIntoString(this.flowVelocityEngine, tempLocation, "UTF-8", model);
		} catch (Exception ex) {
			formUiJs = VelocityEngineUtils.mergeTemplateIntoString(this.flowVelocityEngine, ProcessActivityAssistant
							.getCommonFormPath(this.activityName), "UTF-8",model);
		}

		if (StringUtils.isEmpty(formUiJs)) {
			formUiJs = "[]";
		}
		setJsonString(formUiJs);

		return "success";
	}

	public String check() {
		Task task = this.jbpmService.getTaskById(String.valueOf(this.taskId));

		if (task != null) {
			String assignId = task.getAssignee();
			Long curUserId = ContextUtil.getCurrentUserId();

			if (curUserId.toString().equals(assignId)) {
				this.jsonString = "{success:true,isValid:true,msg:''}";
			} else if (StringUtils.isNotEmpty(assignId)) {
				this.jsonString = "{success:true,isValid:false,msg:'该任务已经被其他成员锁定执行！'}";
			} else {
				this.jbpmService.assignTask(task.getId(), curUserId.toString());
				this.jsonString = "{success:true,isValid:true,msg:'该任务已经被您锁定执行!'}";
			}
		} else {
			this.jsonString = "{success:true,isValid:false,msg:'该任务已经完成了'}";
		}

		return "success";
	}

	public String save() {
		FlowRunInfo flowRunInfo = getFlowRunInfo();
		ProcessRun processRun=new ProcessRun();
		ProcessForm processForm=new ProcessForm();

		if (this.runId != null) {
			processRun = (ProcessRun) this.processRunService
					.get(this.runId);
			processForm = this.processFormService
					.getByRunIdActivityName(this.runId, this.activityName);
			if (processForm != null) {
				this.processRunService.saveProcessRun(processRun, processForm,flowRunInfo);
			}
		} else if (this.defId != null) {
			processRun = initNewProcessRun();
			processForm = initNewProcessForm(processRun);
			this.processRunService.saveProcessRun(processRun, processForm,flowRunInfo);
		}
		
		flowRunInfo.setPiId(processRun.getPiId());

		setJsonString("{success:true}");
		return checkAssignee(flowRunInfo);
	}

	protected ProcessRun initNewProcessRun() {
		ProDefinition proDefinition = (ProDefinition) this.proDefinitionService
				.get(this.defId);

		return this.processRunService.initNewProcessRun(proDefinition);
	}

	protected ProcessForm initNewProcessForm(ProcessRun processRun) {
		ProcessForm processForm = new ProcessForm();
		processForm.setActivityName(this.activityName);
		processForm.setProcessRun(processRun);

		return processForm;
	}

	public String next() {
		FlowRunInfo flowRunInfo = getFlowRunInfo();

		this.processRunService.saveAndNextStep(flowRunInfo);

		setJsonString("{success:true}");

		return checkAssignee(flowRunInfo);
	}

	public String nextForLeader() {
		String result = getRequest().getParameter("result");
		String superOption = getRequest().getParameter("superOption");
		String userId = getRequest().getParameter("assignee");

		FlowRunInfo flowRunInfo = getFlowRunInfo();
		flowRunInfo.setUserId(userId);
		
		//验证是否已经被批过
		Task task = this.jbpmService.getTaskById(String.valueOf(this.taskId));
		if(task==null){
			setJsonString("{success:true}");
			return checkAssignee(flowRunInfo);
		}

		Map<String, ParamField> fieldMap = new HashMap<String, ParamField>();

		List<Transition> trans = jbpmService
				.getTransitionsForSignalProcess(flowRunInfo.getPiId());
		List<Transform> allTrans = new ArrayList<Transform>();

		for (Transition tran : trans) {
			Transform transform = new Transform(tran);
			allTrans.add(new Transform(tran));
			if ("同意".equals(result) && !transform.getName().contains("取消")
					&& !transform.getName().contains("驳回")) {
				ParamField paramField = new ParamField("leaderReply",
						"varchar", "审批意见", Integer.parseInt("0"), Short
								.parseShort("1"));
				paramField.setValue(superOption);
				fieldMap.put("leaderReply", paramField);

				ParamField paramField1 = new ParamField("leader", "varchar",
						"审批人", Integer.parseInt("0"), Short.parseShort("0"));
				paramField1.setValue(appUserService.get(Long.parseLong(userId))
						.getFullname());
				fieldMap.put("manager", paramField1);

				flowRunInfo.setDestName(transform.getDestination());
				flowRunInfo.setTransitionName(transform.getName());
				flowRunInfo.setParamFields(fieldMap);
				
				processRunService.saveAndNextStep(flowRunInfo);
				break;
			} else if ("不同意".equals(result)
					&& (transform.getName().contains("取消") || transform
							.getName().contains("驳回"))) {
				ParamField paramField = new ParamField("leaderReply",
						"varchar", "审批意见", Integer.parseInt("0"), Short
								.parseShort("1"));
				paramField.setValue(superOption);
				fieldMap.put("leaderReply", paramField);

				flowRunInfo.setDestName(transform.getDestination());
				flowRunInfo.setTransitionName(transform.getName());
				flowRunInfo.setParamFields(fieldMap);
				
				processRunService.saveAndNextStep(flowRunInfo);
				break;
			}
		}
		if(StringUtils.isNotEmpty(flowRunInfo.getRunId())){
			//删除ERP代办事项
			goldMantisService.processWaitHandleJob(flowRunInfo.getRunId());
		}else if(StringUtils.isNotEmpty(flowRunInfo.getPiId())){
			ProcessRun processRun=processRunService.getByExeId(flowRunInfo.getPiId());
			if(processRun!=null){
				//删除ERP代办事项
				goldMantisService.processWaitHandleJob(processRun.getRunId().toString());
			}
		}

		setJsonString("{success:true}");

		return checkAssignee(flowRunInfo);
	}

	public String freeTrans() {
		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer();

		sb.append("[");

		List<Transition> trans = this.jbpmService
				.getBackTransitionsByTaskId(this.taskId.toString());
		//删除重复
		removeDuplicate(trans);

		for (Transition tran : trans) {
			sb.append("[").append(gson.toJson(tran.getName())).append(",")
					.append(gson.toJson(tran.getDestination().getName()))
					.append("],");
		}

		if (trans.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		sb.append("]");

		setJsonString(sb.toString());

		return "success";
	}

	public String trans() {
		TaskImpl task = (TaskImpl) this.taskService.getTask(this.taskId.toString());
		
		List allTrans = new ArrayList();

		List<Transition> trans = this.jbpmService
				.getTransitionsByTaskId(this.taskId.toString());

		for (Transition tran : trans) {
			if (tran.getName() != null && tran.getSource() != null
					&& tran.getDestination() != null) {
				allTrans.add(new Transform(tran));
			}
		}
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[0]);
		String result = serializer.serialize(allTrans);
		
		String executionId=task.getSuperTask()==null?task.getExecutionId():task.getSuperTask().getExecutionId();

		setJsonString("{success:true,data:" + result + ",executionId:'"+executionId+"'}");
		return "success";
	}

	protected Map<String, ParamField> constructFieldMap() {
		HttpServletRequest request = getRequest();
		ProDefinition proDefinition = getProDefinition();

		if (StringUtils.isEmpty(this.activityName)) {
			this.activityName = this.jbpmService
					.getStartNodeName(proDefinition);
		}

		Map map = ProcessActivityAssistant.constructFieldMap(proDefinition
				.getName(), this.activityName);

		Iterator fieldNames = map.keySet().iterator();
		while (fieldNames.hasNext()) {
			String name = (String) fieldNames.next();
			ParamField pf = (ParamField) map.get(name);

			pf.setName(pf.getName().replace(".", "_"));
			pf.setValue(request.getParameter(name));
		}
		return map;
	}

	protected ProDefinition getProDefinition() {
		ProDefinition proDefinition = null;
		if (this.runId != null) {
			ProcessRun processRun = (ProcessRun) this.processRunService
					.get(this.runId);
			proDefinition = processRun.getProDefinition();
		} else if (this.defId != null) {
			proDefinition = (ProDefinition) this.proDefinitionService
					.get(this.defId);
		} else {
			ProcessRun processRun = this.processRunService
					.getByTaskId(this.taskId.toString());
			proDefinition = processRun.getProDefinition();
		}
		return proDefinition;
	}

	protected FlowRunInfo getFlowRunInfo() {
		FlowRunInfo info = new FlowRunInfo(getRequest());
		Map fieldMap = constructFieldMap();
		info.setParamFields(fieldMap);
		return info;
	}
	
	
	/**
	 * 启动流程审批后自动任务线程
	 * @return
	 */
	 public String checkAssignee(FlowRunInfo info) {
		 	
		 	//FlowRunInfo info = new FlowRunInfo(getRequest());
		 	
		 	if("".equals(info.getUserId())||info.getUserId()==null){
		 		info.setUserId(ContextUtil.getCurrentUserId().toString());
		 	}
			if(info.getPiId()!=null&&!"".equals(info.getPiId())){
				taskExecutor.execute(new JbpmTaskAutoRun(info));
				int count = taskExecutor.getActiveCount();  
			    logger.info(" 自动审批执行线程 数 : " + count);
				
			}else{
				logger.info(" 未进入自动审批线程，piId  is NULL");
			}
			return SUCCESS;
		}
	 
	 
	//去除重复数据
		private  void removeDuplicate(List<Transition> list) {
			   for ( int i = 0 ; i < list.size() - 1 ; i ++ ) {
			    for ( int j = list.size() - 1 ; j > i; j -- ) {
			      if (list.get(j).getDestination().equals(list.get(i).getDestination())) {
			        list.remove(j);
			      } 
			     } 
			   } 
			}
	 
	 
	 
	 /**
	 * 自动审批执行线程 
	 * @author Administrator
	 *
	 */
		private  class  JbpmTaskAutoRun implements Runnable {
			private FlowRunInfo flowRunInfo;

			public  JbpmTaskAutoRun(FlowRunInfo flowRunInfo) {
				this.flowRunInfo = flowRunInfo;	
			}

			public  void run() {
				boolean flag=true;
				while(flag){
					flag=flowAutoCommitService.autoCommit(flowRunInfo);
					flowRunInfo.setStartFlow(false);
				}
			}
		}

	 
	

	

}
