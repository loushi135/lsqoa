package com.xpsoft.oa.service.flow.impl;


import com.xpsoft.oa.service.flow.ProcessFormService;
import com.xpsoft.oa.service.flow.TaskAgentService;
import com.xpsoft.oa.service.flow.TaskSignDataService;
import com.xpsoft.oa.service.flow.TaskSignService;
import com.xpsoft.oa.action.flow.FlowRunInfo;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.TaskAgent;
import com.xpsoft.oa.model.flow.TaskSign;
import com.xpsoft.oa.model.flow.Transform;
import com.xpsoft.core.Constants;
import com.xpsoft.core.jbpm.jpdl.Node;
import com.xpsoft.core.jbpm.pv.ParamField;

import com.xpsoft.core.service.ProSendMsgService;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.ContextUtil;

import com.xpsoft.oa.model.flow.ProDefinition;

import com.xpsoft.oa.model.flow.ProUserAssign;

import com.xpsoft.oa.model.flow.ProcessRun;

import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;

import com.xpsoft.oa.service.flow.JbpmService;

import com.xpsoft.oa.service.flow.ProDefinitionService;

import com.xpsoft.oa.service.flow.ProUserAssignService;

import com.xpsoft.oa.service.flow.ProcessRunService;

import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;
import com.xpsoft.oa.service.system.UserSubService;

import java.io.File;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import java.util.Iterator;

import java.util.List;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;

import org.dom4j.Attribute;


import org.dom4j.DocumentHelper;

import org.dom4j.Element;


import org.hibernate.Session;

import org.jbpm.api.Execution;

import org.jbpm.api.ExecutionService;

import org.jbpm.api.HistoryService;


import org.jbpm.api.ProcessDefinition;


import org.jbpm.api.ProcessEngine;

import org.jbpm.api.ProcessInstance;


import org.jbpm.api.RepositoryService;


import org.jbpm.api.TaskService;

import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.model.OpenProcessInstance;


import org.jbpm.api.task.Task;

import org.jbpm.pvm.internal.env.Environment;

import org.jbpm.pvm.internal.env.EnvironmentFactory;

import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;

import org.jbpm.pvm.internal.model.Activity;

import org.jbpm.pvm.internal.model.ActivityImpl;

import org.jbpm.pvm.internal.model.ExecutionImpl;

import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;

import org.jbpm.pvm.internal.model.Transition;

import org.jbpm.pvm.internal.model.TransitionImpl;

import org.jbpm.pvm.internal.svc.TaskServiceImpl;

import org.jbpm.pvm.internal.task.TaskImpl;

public class JbpmServiceImpl implements JbpmService {
	private static final Log logger = LogFactory
			.getLog(JbpmServiceImpl.class);

	@Resource
	private ProcessEngine processEngine;

	@Resource
	private RepositoryService repositoryService;

	@Resource
	private ExecutionService executionService;

	@Resource
	private ProDefinitionService proDefinitionService;

	@Resource
	private TaskService taskService;
	@Resource
	private TaskSignService taskSignService;
	
	@Resource
	private TaskSignDataService taskSignDataService;
	@Resource
	private HistoryService historyService;

	@Resource
	private ProUserAssignService proUserAssignService;

	@Resource
	private UserSubService userSubService;

	@Resource
	private ProcessRunService processRunService;
	@Resource
	private ProcessFormService processFormService;
	@Resource
	private ProSendMsgService proSendMsgService;

	@Resource
	private TaskAgentService taskAgentService;
	public Task getTaskById(String taskId) {
		Task task = this.taskService.getTask(taskId);

		return task;
	}

	public void assignTask(String taskId, String userId) {
		this.taskService.assignTask(taskId, userId);
	}

	public void doUnDeployProDefinition(Long defId) {
		this.processRunService.removeByDefId(defId);

		ProDefinition pd = (ProDefinition) this.proDefinitionService
				.get(defId);
		if (pd != null) {
			this.repositoryService.deleteDeploymentCascade(pd
					.getDeployId());

			this.proDefinitionService.remove(pd);
		}
	}

	public ProDefinition saveOrUpdateDeploy(ProDefinition proDefinition) {
		if (proDefinition.getDeployId() == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("deploy now===========");
			}
			String deployId = this.repositoryService
					.createDeployment()
					.addResourceFromString("process.jpdl.xml",
							proDefinition.getDefXml()).deploy();

			proDefinition.setDeployId(deployId);

			this.proDefinitionService.save(proDefinition);
		} else {
			this.proDefinitionService.evict(proDefinition);

			ProDefinition proDef = (ProDefinition) this.proDefinitionService
					.get(proDefinition.getDefId());

			if (!proDef.getDefXml().equals(proDefinition.getDefXml())) {
				if (proDef.getDeployId() != null) {
					this.repositoryService.deleteDeployment(proDef
							.getDeployId());
				}
				String deployId = this.repositoryService
						.createDeployment()
						.addResourceFromString("process.jpdl.xml",
								proDefinition.getDefXml()).deploy();
				proDefinition.setDeployId(deployId);
			}

			this.proDefinitionService.merge(proDefinition);
		}

		return proDefinition;
	}

	public ProcessDefinition getProcessDefinitionByKey(String processKey) {
		List list = this.repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey(processKey)
				.orderDesc("versionProperty.longValue").list();
		if ((list != null) && (list.size() > 0)) {
			return (ProcessDefinition) list.get(0);
		}
		return null;
	}

	public ProDefinition getProDefinitionByKey(String processKey) {
		ProcessDefinition processDefinition = getProcessDefinitionByKey(processKey);
		if (processDefinition != null) {
			ProDefinition proDef = this.proDefinitionService
					.getByDeployId(processDefinition.getDeploymentId());
			return proDef;
		}
		return null;
	}

	public String getDefinitionXmlByDefId(Long defId) {
		ProDefinition proDefinition = (ProDefinition) this.proDefinitionService
				.get(defId);
		return proDefinition.getDefXml();
	}

	public String getDefinitionXmlByDpId(String deployId) {
		ProDefinition proDefintion = this.proDefinitionService
				.getByDeployId(deployId);
		return proDefintion.getDefXml();
	}

	public String getDefinitionXmlByExeId(String exeId) {
		String pdId = this.executionService.findExecutionById(exeId)
				.getProcessDefinitionId();
		String deployId = this.repositoryService
				.createProcessDefinitionQuery().processDefinitionId(pdId)
				.uniqueResult().getDeploymentId();
		return getDefinitionXmlByDpId(deployId);
	}

	public String getDefinitionXmlByPiId(String piId) {
		ProcessInstance pi = this.executionService
				.createProcessInstanceQuery().processInstanceId(piId)
				.uniqueResult();
		ProcessDefinition pd = this.repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(pi.getProcessDefinitionId())
				.uniqueResult();
		return getDefinitionXmlByDpId(pd.getDeploymentId());
	}

	public ProcessDefinition getProcessDefinitionByTaskId(String taskId) {
		TaskImpl task = (TaskImpl) this.taskService.getTask(taskId);
		ProcessInstance pi = null;
		if (task.getSuperTask() != null)
			pi = task.getSuperTask().getProcessInstance();
		else {
			pi = task.getProcessInstance();
		}
		ProcessDefinition pd = this.repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(pi.getProcessDefinitionId())
				.uniqueResult();
		return pd;
	}

	public ProcessInstance getProcessInstance(String piId) {
		ProcessInstance pi = this.executionService
				.createProcessInstanceQuery().processInstanceId(piId)
				.uniqueResult();
		return pi;
	}

	public List<Node> getTaskNodesByDefId(Long defId) {
		ProDefinition proDefinition = (ProDefinition) this.proDefinitionService
				.get(defId);
		return getTaskNodesFromXml(proDefinition.getDefXml(), false,
				false);
	}

	public List<Node> getJumpNodesByDeployId(String deployId) {
		ProDefinition proDefinition = this.proDefinitionService
				.getByDeployId(deployId);
		return getTaskNodesFromXml(proDefinition.getDefXml(), false,
				true);
	}

	public List<Node> getFormNodes(Long defId) {
		ProDefinition proDefinition = (ProDefinition) this.proDefinitionService
				.get(defId);
		return getTaskNodesFromXml(proDefinition.getDefXml(), true,
				false);
	}

	public String getStartNodeName(ProDefinition proDefinition) {
		String filePath = AppUtil.getAppAbsolutePath()
				+ "/WEB-INF/FlowForm/" + proDefinition.getName() + "/开始.vm";

		File file = new File(filePath);

		if (file.exists())
			return "开始";
		try {
			Element root = DocumentHelper.parseText(
					proDefinition.getDefXml()).getRootElement();
			List<Element> el = root.elements();
			for (Element elem : el) {
				String tagName = elem.getName();
				if ("start".equals(tagName)) {
					Attribute nameAttr = elem.attribute("name");
					if (nameAttr == null)
						break;
					return nameAttr.getValue();
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return "开始";
	}

	private List<Node> getTaskNodesFromXml(String xml, boolean includeStart,
			boolean includeEnd) {
		List nodes = new ArrayList();
		try {
			Element root = DocumentHelper.parseText(xml)
					.getRootElement();
			List<Element> el = root.elements();
			for (Element elem : el) {
				String type = elem.getQName().getName();
				if ("task".equalsIgnoreCase(type)) {
					if (elem.attribute("name") != null) {
						Node node = new Node(elem.attribute("name")
								.getValue(), "任务节点");
						nodes.add(node);
					}
					} else if ((includeStart)
						&& ("start".equalsIgnoreCase(type))) {
					if (elem.attribute("name") != null) {
						Node node = new Node(elem.attribute("name")
								.getValue(), "开始节点");
						nodes.add(node);
					}
					} else if ((includeEnd)
						&& (type.startsWith("end"))) {
					Node node = new Node(elem.attribute("name")
							.getValue(), "结束节点");
					nodes.add(node);
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return nodes;
	}

	public String startProcess(String deployId, Map variables) {
		ProcessDefinition pd = this.repositoryService
				.createProcessDefinitionQuery().deploymentId(deployId)
				.uniqueResult();
		clearSession();

		ProcessInstance pi = this.executionService
				.startProcessInstanceById(pd.getId(), variables);
		String assignId = (String) variables.get("flowAssignId");

		String signUserIds = (String) variables.get("signUserIds");

		if (StringUtils.isNotEmpty(signUserIds)) {
			List newTasks = getTasksByPiId(pi.getId());
			Iterator localIterator = newTasks.iterator();
			if (localIterator.hasNext()) {
				Task nTask = (Task) localIterator.next();
				newTask(nTask.getId(), signUserIds);
			}
		} else {
			assignTask(pi, pd, assignId, null,variables);
		}

		return pi.getId();
	}

	public ProcessInstance getProcessInstanceByExeId(String executionId) {
		Execution execution = this.executionService
				.findExecutionById(executionId);
		return execution!=null?(ProcessInstance) execution.getProcessInstance():null;
	}

	public ProcessInstance getProcessInstanceByTaskId(String taskId) {
		TaskImpl taskImpl = (TaskImpl) this.taskService.getTask(taskId
				.toString());
		if(taskImpl!=null){
			if (taskImpl.getSuperTask() != null) {
				taskImpl = taskImpl.getSuperTask();
			}
			return taskImpl.getProcessInstance();
		}else{
			return null;
		}
	}

	
	/**
	 * 任务完成后分配下一个节点的人
	 * @param pi
	 * @param pd
	 * @param assignId
	 * @param taskName
	 * @param variables
	 */
	public void assignTask(ProcessInstance pi, ProcessDefinition pd,
			String assignId, String taskName , Map variables) {
		if (pd == null) {
			pd = this.repositoryService.createProcessDefinitionQuery()
					.processDefinitionId(pi.getProcessDefinitionId())
					.uniqueResult();
		}

		List<Task> taskList = null;

		if (StringUtils.isNotEmpty(taskName)) {
			taskList = this.taskService.createTaskQuery()
					.processInstanceId(pi.getId()).activityName(taskName)
					.list();
		}

		if ((taskList == null) || (taskList.size() == 0)) {
			taskList = getTasksByPiId(pi.getId());
		}

		for (Task task : taskList) {
			
			if (StringUtils.isNotEmpty(assignId)) {
				TaskAgent taskAgent=taskAgentService.isExist(assignId);
				if(taskAgent!=null){
					taskService.assignTask(task.getId(), taskAgent.getAgentAssignId().toString());
				}else{
					taskService.assignTask(task.getId(), assignId);
				}
				//this.taskService.assignTask(task.getId(), assignId);
			} else {
				ProUserAssign assign = this.proUserAssignService
						.getByDeployIdActivityName(pd.getDeploymentId(),
								task.getActivityName());

				if (assign != null) {
					if ("__start".equals(assign.getUserId())) {
						AppUser flowStartUser = (AppUser) this.executionService
								.getVariable(pi.getId(), "flowStartUser");
						if (flowStartUser != null)
							this.taskService.assignTask(task.getId(),
									flowStartUser.getUserId().toString());
						
					} else {
						StringBuffer upIds;
						Object localObject;
						Long userId;
						if ("__super".equals(assign.getUserId())) {
							AppUser flowStartUser = (AppUser) this.executionService
									.getVariable(pi.getId(), "flowStartUser");

							if (flowStartUser != null) {
								List superUserIds = this.userSubService.upUser(flowStartUser.getUserId());
								
								
								upIds = new StringBuffer();
								for (localObject = superUserIds
										.iterator(); ((Iterator) localObject)
										.hasNext();) {
									userId = (Long) ((Iterator) localObject)
											.next();
									upIds.append(userId).append(",");
									
								}
								if (superUserIds.size() > 0)
									upIds.deleteCharAt(upIds.length() - 1);
								else {
									upIds.append(flowStartUser.getUserId());
								}
								this.taskService.addTaskParticipatingUser(task.getId(),upIds.toString(), "candidate");
							}
					    } else if (StringUtils.isNotEmpty(assign.getUserId())) {
							String[] userIds = assign.getUserId().split("[,]");

							if ((userIds != null)&& (userIds.length > 1)) {
								
								if (assign != null && assign.IS_SIGNED_TASK.equals(assign.getIsSigned()) && userIds.length > 1)
								{//会签
									String strUserIds="";
									for (int i = 0; i < userIds.length; i++)
									{
										TaskAgent taskAgent=taskAgentService.isExist(userIds[i]);
										if(taskAgent!=null){
											strUserIds+=taskAgent.getAgentAssignId().toString()+",";
										}else{
											strUserIds+=userIds[i].toString()+",";
										}
										//strUserIds+=userIds[i].toString()+",";
									}
									
									newTask(task.getId(), strUserIds);
									
								}else{
									for (int upIds1 = 0; upIds1 < userIds.length; upIds1++) {//此处为非会签任务比较合适?
										String uId = userIds[upIds1];
										//this.taskService.addTaskParticipatingUser(task.getId(), uId,"candidate");
										if(StringUtils.isNotEmpty(uId)){//若在流程执行过程中，用户在表单指定了下一步的执行人员，则流程会自动指派至该人来执行
											TaskAgent taskAgent=taskAgentService.isExist(uId);
											if(taskAgent!=null){
												this.taskService.assignTask(task.getId(), taskAgent.getAgentAssignId().toString());
											}else{
												this.taskService.assignTask(task.getId(), uId);
											}
										}
									}
								}
								
							} else {
								//this.taskService.assignTask(task.getId(), assign.getUserId());			
								if(StringUtils.isNotEmpty(userIds[0])){//若在流程执行过程中，用户在表单指定了下一步的执行人员，则流程会自动指派至该人来执行
									TaskAgent taskAgent=taskAgentService.isExist(userIds[0]);
									if(taskAgent!=null){
										taskService.assignTask(task.getId(), taskAgent.getAgentAssignId().toString());
									}else{
										taskService.assignTask(task.getId(), assign.getUserId());
									}
								}else {
									taskService.assignTask(task.getId(), assign.getUserId());
								}
							}
							
						}
					}
					if (StringUtils.isNotEmpty(assign.getRoleId())){
						this.taskService.addTaskParticipatingGroup(task.getId(), assign.getRoleId(), "candidate");

					}
				} else {
					AppUser flowStartUser = (AppUser) this.executionService.getVariable(pi.getId(), "flowStartUser");
					if (flowStartUser != null)
						this.taskService.assignTask(task.getId(),flowStartUser.getUserId().toString());
				}
			}
			
			//this.proSendMsgService.SendMsg(userList,pi.getId(), task.getId(), task.getName(),pd.getName(),variables);
		}
		
	}
	
	private String getNextIfBlank(String deptId,String varName,Map variables){
		if(StringUtils.isBlank(deptId)){
			deptId = (String)variables.get(varName);
		}
		return deptId;
	}

	public List<Transition> getTransitionsForSignalProcess(String piId) {
		ProcessInstance pi = this.executionService.findProcessInstanceById(piId);
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		Environment env = environmentFactory.openEnvironment();
		try {
			ExecutionImpl executionImpl = (ExecutionImpl) pi;
			Activity activity = executionImpl.getActivity();

			List<Transition> localList = activity.getOutgoingTransitions();
			return localList;
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			env.close();
		}
		return null;
	}

	public List<Transition> getTransitionsByTaskId(String taskId) {
		
		TaskImpl task = (TaskImpl) this.taskService.getTask(taskId);
		if (task.getSuperTask() != null) {
			task = task.getSuperTask();
		}
		
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		Environment env = environmentFactory.openEnvironment();
		try {
			
			ProcessDefinitionImpl pd = task.getProcessInstance().getProcessDefinition();
			ActivityImpl activityFind = pd.findActivity(task.getActivityName());

			if (activityFind != null)
				return activityFind.getOutgoingTransitions();
		} finally {
			env.close();
		}
		return new ArrayList();
	}

	public void addOutTransition(ProcessDefinitionImpl pd, String sourceName,
			String destName) {
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		Environment env = null;
		try {
			env = environmentFactory.openEnvironment();

			ActivityImpl sourceActivity = pd.findActivity(sourceName);

			ActivityImpl destActivity = pd.findActivity(destName);

			TransitionImpl transition = sourceActivity
					.createOutgoingTransition();
			transition.setName("to" + destName);
			transition.setDestination(destActivity);

			sourceActivity.addOutgoingTransition(transition);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			if (env != null)
				env.close();
		}
	}

	public void removeOutTransition(ProcessDefinitionImpl pd,
			String sourceName, String destName) {
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		Environment env = null;
		try {
			env = environmentFactory.openEnvironment();

			ActivityImpl sourceActivity = pd.findActivity(sourceName);

			List<Transition> trans = sourceActivity.getOutgoingTransitions();
			for (Transition tran : trans)
				if (destName.equals(tran.getDestination().getName())) {
					trans.remove(tran);
					break;
				}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			if (env != null)
				env.close();
		}
	}

	public List<Transition> getFreeTransitionsByTaskId(String taskId) {
		TaskImpl task = (TaskImpl) this.taskService.getTask(taskId);

		List outTrans = new ArrayList();

		if (task.getSuperTask() != null) {
			task = task.getSuperTask();
		}
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		Environment env = null;
		try {
			env = environmentFactory.openEnvironment();
			ProcessDefinitionImpl pd = task.getProcessInstance()
					.getProcessDefinition();
			ActivityImpl curActivity = pd.findActivity(task
					.getActivityName());

			List<Node> allTaskNodes = getJumpNodesByDeployId(pd
					.getDeploymentId());

			for (Node taskNode : allTaskNodes) {
				if (taskNode.getName().equals(task.getActivityName()))
					continue;
				TransitionImpl transition = curActivity.createOutgoingTransition();

				transition.setName("to" + taskNode.getName());
				transition.setDestination(pd.findActivity(taskNode
						.getName()));

				curActivity.getOutgoingTransitions().remove(transition);

				outTrans.add(transition);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			if (env != null)
				env.close();
		}

		return outTrans;
	}
	
	
	public List<Transition> getBackTransitionsByTaskId(String taskId) {
		TaskImpl task = (TaskImpl) this.taskService.getTask(taskId);
		
		List outTrans = new ArrayList();

		if (task.getSuperTask() != null) {
			task = task.getSuperTask();
		}
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		Environment env = null;
		try {
			env = environmentFactory.openEnvironment();
			ProcessDefinitionImpl pd = task.getProcessInstance()
					.getProcessDefinition();
			ActivityImpl curActivity = pd.findActivity(task
					.getActivityName());

			List<Node> allTaskNodes = getJumpNodesByDeployId(pd
					.getDeploymentId());
			ProcessRun processRun=this.processRunService.getByPiId(task.getExecutionId());
//			Set<ProcessForm> backActivityNameList=processRun.getProcessForms();
			List<ProcessForm> backActivityNameList = this.processFormService.getBeforeProcessForm(processRun.getRunId(), task
					.getActivityName());
			for (Node taskNode : allTaskNodes) {
				if (taskNode.getName().equals(task.getActivityName()))
					continue;
				
				for (ProcessForm processForm : backActivityNameList) {
					if(processForm.getActivityName().equals(taskNode.getName())){
						TransitionImpl transition = curActivity.createOutgoingTransition();

						transition.setName("to" + taskNode.getName());
						transition.setDestination(pd.findActivity(taskNode
								.getName()));

						curActivity.getOutgoingTransitions().remove(transition);

						outTrans.add(transition);
					}
				} 
				
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			if (env != null)
				env.close();
		}

		return outTrans;
	}

	public String getProcessDefintionXMLByPiId(String piId) {
		ProcessRun processRun = this.processRunService.getByPiId(piId);
		return processRun.getProDefinition().getDefXml();
	}

	public List<Task> getTasksByPiId(String piId) {
		List taskList = this.taskService.createTaskQuery()
				.processInstanceId(piId).list();
		return taskList;
	}

	public String getNodeType(String xml, String nodeName) {
		String type = "";
		try {
			Element root = DocumentHelper.parseText(xml).getRootElement();
			List<Element> el = root.elements();
			for (Element elem : el)
				if (elem.attribute("name") != null) {
					String value = elem.attributeValue("name");
					if (value.equals(nodeName)) {
						type = elem.getQName().getName();
						return type;
					}
				}
		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}
		return type;
	}

	protected void clearSession() {
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		Environment env = environmentFactory.openEnvironment();
		try {
			Session session = (Session) env.get(Session.class);
			session.clear();
		} finally {
			env.close();
		}
	}

	protected void flush() {
		EnvironmentFactory environmentFactory = (EnvironmentFactory) this.processEngine;
		Environment env = environmentFactory.openEnvironment();
		try {
			Session session = (Session) env.get(Session.class);
			session.flush();
		} finally {
			env.close();
		}
	}
	
	//清除hibernate缓存
	protected void evict(Object obj)
	{
		Environment environmentimpl;
		EnvironmentFactory environmentfactory = (EnvironmentFactory)processEngine;
		environmentimpl = environmentfactory.openEnvironment();
		Session session = (Session)environmentimpl.get(Session.class);
		session.evict(obj);
		environmentimpl.close();
	}
	
	/**
	 * 完成任务
	 */
	public void completeTask(String taskId, String transitionName,
			String destName, Map variables) {
		TaskImpl taskImpl = (TaskImpl) this.taskService
				.getTask(taskId);

		String sourceName = taskImpl.getName();

		TaskImpl superTask = taskImpl.getSuperTask();

		ProcessDefinitionImpl pd = (ProcessDefinitionImpl) getProcessDefinitionByTaskId(taskId);
		ProcessInstance pi = null;
		String executionId = null;

		boolean isTransitionExist = false;

		List<Transition> trans = getTransitionsByTaskId(taskId);
		for (Transition tran : trans) {
			if (tran.getDestination()!=null&&tran.getDestination().getName().equals(destName)) {
				isTransitionExist = true;
				break;
			}
		}

		if (!isTransitionExist) {
			addOutTransition(pd, sourceName, destName);
		}

		if (superTask != null) {
			pi = superTask.getProcessInstance();
			executionId = superTask.getExecutionId();
			if (logger.isDebugEnabled()) {
				logger.debug("Super task is not null, task name is:"
						+ superTask.getActivityName());
			}

			if (superTask.getSubTasks() != null) {
				if (superTask.getSubTasks().size() == 1) {
					this.taskService.setVariables(taskId, variables);
					clearSession();

					this.taskService.completeTask(taskId);

					this.taskService.completeTask(superTask.getId(),
							transitionName);
				} else {
					this.taskService.setVariables(taskId, variables);
					clearSession();
					this.taskService.completeTask(taskId);

					return;
				}
			}
		} else {
			pi = taskImpl.getProcessInstance();
			executionId = taskImpl.getExecutionId();
			this.taskService.setVariables(taskId, variables);
			flush();
			this.taskService.completeTask(taskId, transitionName);
		}

		if (!isTransitionExist) {
			removeOutTransition(pd, sourceName, destName);
		}

		boolean isEndProcess = isProcessInstanceEnd(executionId);
		if (isEndProcess) {
			ProcessRun processRun = this.processRunService.getByPiId(executionId);
			if (processRun != null) {
				
				if(transitionName.contains("不通过")||transitionName.contains("取消")||transitionName.contains("驳回")){
					processRun.setRunStatus(ProcessRun.RUN_STATUS_UNPASSED);
				}else{
					processRun.setRunStatus(ProcessRun.RUN_STATUS_FINISHED);
				}
				processRun.setPiId(null);
				this.processRunService.save(processRun);
			}
			return;
		}

		String signUserIds = (String) variables.get("signUserIds");

		if ((destName != null)
				&& (StringUtils.isNotEmpty(signUserIds))) {
			List<Task> newTasks = getTasksByPiId(pi.getId());
			for (Task nTask : newTasks) {
				if (destName.equals(nTask.getName())) {
					newTask(nTask.getId(), signUserIds);
					break;
				}
			}
			return;
		}
		destName = null;

		String assignId = (String) variables.get("flowAssignId");

		assignTask(pi, null, assignId, destName, variables);
	}
	
	
	
	/**  
     * 完成会签子任务  
     * @param parentTask 父任务  
     * @param subTask 子任务  
     * @param variables 任务中的流程变量  
     */    
    public void completeSignSubTask(TaskImpl parentTask,TaskImpl subTask,String transitionName,String destName,Map variables,AppUser curUser){    
            
        //看目前还有多少子任务    
        int subTasksSize=parentTask.getSubTasks().size();    
            
        //检查会签的配置情况    
        ProcessInstance pi=((TaskImpl)parentTask).getProcessInstance();    
        ProcessDefinition pd=repositoryService.createProcessDefinitionQuery().processDefinitionId(pi.getProcessDefinitionId()).uniqueResult();    
        //取得该任务的后台人员配置    
        ProUserAssign assignSetting=proUserAssignService.getByDeployIdActivityName(pd.getDeploymentId(), parentTask.getActivityName());    
            
        evict(subTask);    
        evict(parentTask);   
            
        if(assignSetting!=null){    
            //取得会签配置    
            TaskSign taskSign=taskSignService.findByAssignId(assignSetting.getAssignId());    
                
            if(taskSign!=null){//按照配置执行任务跳转    
                //是否完成父任务    
                boolean isFinishSupTask=false;    
    
                //查看用户投的是哪一种票（同意还是不同意还是弃权）    
                Short isAgree=(Short)variables.get("signVoteType");    
                    
                if(isAgree==null){//若为空，则认为是投通过票    
                    isAgree=TaskSign.DECIDE_TYPE_PASS;    
                }    
                    
                //1.保存投票信息    
                taskSignDataService.addVote(parentTask.getId(),isAgree,curUser);    
                    
                //加上子任务的流程变量    
                taskService.setVariables(subTask.getId(),variables);    
                //2.完成子任务    
                taskService.completeTask(subTask.getId());    
                    
                //3.检查其投票数是否已满足后台会签配置条件    
                //3.1根据后台配置的投票类型，取得投票的总数    
                Long voteCounts=taskSignDataService.getVoteCounts(parentTask.getId(),taskSign.getDecideType());    
                    
                if(taskSign.getVoteCounts()!=null){//按绝对投票数来进行    
                    if(voteCounts>=taskSign.getVoteCounts()){    
                        isFinishSupTask=true;    
                    }    
                }else if(taskSign.getVotePercents()!=null){//按投票百分比来进行    
                    //取到动态有多少子任务    
                    Integer taskSignCounts=(Integer)taskService.getVariable(parentTask.getId(), "taskSignCounts");    
                    if(taskSignCounts==null || taskSignCounts==0){    
                        taskSignCounts=1;    
                    }    
                    BigDecimal totalSubTasks=new BigDecimal(taskSignCounts);    
                    //当前投票后占的百分比    
                    BigDecimal tempPercent=new BigDecimal(voteCounts).divide(totalSubTasks);    
                    Integer curPercent=new Integer(tempPercent.multiply(new BigDecimal(100)).intValue());    
                        
                    if(curPercent>=taskSign.getVotePercents()){    
                        isFinishSupTask=true;    
                    }    
                }    
                //若投票完成后，把投票结果存在decisionType变量里，方便在后台通过脚本根据投票的结果进行跳转    
                Map varsMap=new HashMap();    
                    
                //当前会签子任务完成后，若投票的情况已经满足后台的会签设置条件    
                //或没有满足会签设置的情况，并且会签所有子任务均已经完成    
                if(isFinishSupTask || (!isFinishSupTask && subTasksSize==1) ){    
                    String passRefuse=null;    
                    if(isFinishSupTask){//所有子任务完成，满足会签条件设置    
                        passRefuse=TaskSign.DECIDE_TYPE_PASS.compareTo(taskSign.getDecideType())==0?"pass":"refuse";    
                    }else{//所有子任务完成，不满足会签条件设置    
                        passRefuse=TaskSign.DECIDE_TYPE_PASS.compareTo(taskSign.getDecideType())==0?"refuse":"pass";    
                    }    
                    logger.debug("会签投票结果："+ passRefuse);    
                    varsMap.put("decisionType",passRefuse);  
                    varsMap.putAll(variables);//在会签中途会修改流程数据
                    taskService.setVariables(parentTask.getId(),varsMap);  
                    
                    if("refuse".equals(passRefuse)){
                    	transitionName="取消";
                    }
                    
                    //完成父任务    
                    taskService.completeTask(parentTask.getId(),transitionName);   
                   
                    
                  //检查当前的父流程是否已经结束
                  isFinishSupTask(parentTask,pi,variables,destName);
                }    
                    
            }else{//没有设置对应的会签配置，则认为会签是全部完成后才能往下执行    
                logger.error("Task "+parentTask.getActivityName()+" is not config right sign config in process admin console.");    
                    
                if(parentTask.getSubTasks().size()==1){//若只有当前子任务，则表示可以结束目前这个任务    
                    taskService.setVariables(subTask.getId(),variables);    
                    //完成子任务    
                    taskService.completeTask(subTask.getId());    
                    //完成父任务    
                    taskService.completeTask(parentTask.getId(),transitionName);  
                  //检查当前的父流程是否已经结束
                    isFinishSupTask(parentTask,pi,variables,destName);
                }else{    
                    taskService.setVariables(subTask.getId(), variables);    
                    //完成子任务后，直接返回则可    
                    taskService.completeTask(subTask.getId());    
                    return ;    
                }    
            }
            
            
        }else{    
            //TODO    
            logger.error("Task "+parentTask.getActivityName()+"is not config the setting in process admin console.");    
        }    
    }  
    
    
  //检查当前的流程是否已经结束
    private void isFinishSupTask(TaskImpl parentTask,ProcessInstance pi,Map variables,String destName){
    	
        String executionId=parentTask.getExecutionId();
	    HistoryProcessInstance hpi = historyService.createHistoryProcessInstanceQuery().processInstanceId(executionId).uniqueResult();  
	    String endActivityName = ((HistoryProcessInstanceImpl) hpi).getEndActivityName();  
	    
	    if (endActivityName != null) { // 流程实例已经结束了 
	    	ProcessRun processRun=processRunService.getByPiId(executionId);
	    	if(processRun!=null){
	    		
	    		if(endActivityName.contains("不通过")||endActivityName.contains("取消")||endActivityName.contains("驳回")){
					processRun.setRunStatus(ProcessRun.RUN_STATUS_UNPASSED);
				}else{
					processRun.setRunStatus(ProcessRun.RUN_STATUS_FINISHED);
				}
	    		processRun.setPiId(null);
	    		processRunService.save(processRun);
	    	}
	    }else{
	    	String assignId=(String)variables.get(Constants.FLOW_ASSIGN_ID);
		    //为下一任务授权
		    assignTask(pi, null,assignId,parentTask.getName(),variables);
	    }
    }

	protected boolean isProcessInstanceEnd(String executionId) {
		HistoryProcessInstance hpi = this.historyService
				.createHistoryProcessInstanceQuery()
				.processInstanceId(executionId).uniqueResult();
		if (hpi != null) {
			String endActivityName = ((HistoryProcessInstanceImpl) hpi)
					.getEndActivityName();
			if (endActivityName != null) {
				return true;
			}
		}
		return false;
	}

	public void newTask(String parentTaskId, String assignIds) {
		TaskServiceImpl taskServiceImpl = (TaskServiceImpl) this.taskService;
		Task parentTask = taskServiceImpl.getTask(parentTaskId);

		if (assignIds != null) {
			String[] userIds = assignIds.split("[,]");
			for (int i = 0; i < userIds.length; i++) {
				TaskImpl task = (TaskImpl) taskServiceImpl
						.newTask(parentTaskId);
				task.setAssignee(userIds[i]);
				task.setName(parentTask.getName() + "-" + (i + 1));
				task.setActivityName(parentTask.getName());
				task.setDescription(parentTask.getDescription());

				taskServiceImpl.saveTask(task);
			}
		}
	}

	public void signalProcess(String executionId, String transitionName,
			Map<String, Object> variables) {
		this.executionService.setVariables(executionId, variables);
		this.executionService.signalExecutionById(executionId,
				transitionName);
	}

	public void endProcessInstance(String piId) {
		ExecutionService executionService = this.processEngine
				.getExecutionService();
		executionService.endProcessInstance(piId, "ended");
	}

}

