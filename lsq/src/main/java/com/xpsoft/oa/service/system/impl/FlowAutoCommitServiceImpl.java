package com.xpsoft.oa.service.system.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.model.Transition;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.model.HandleJob;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.xpsoft.core.jbpm.pv.ParamField;
import com.xpsoft.core.service.ProSendMsgService;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.oa.action.flow.FlowRunInfo;
import com.xpsoft.oa.action.flow.ProcessActivityAssistant;
import com.xpsoft.oa.dao.flow.ProcessFormDao;
import com.xpsoft.oa.model.flow.ProDefinition;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.flow.Transform;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.UserFlowconfig;
import com.xpsoft.oa.service.flow.JbpmService;
import com.xpsoft.oa.service.flow.ProcessFormService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.FlowAutoCommitService;
import com.xpsoft.oa.service.system.UserFlowconfigService;
import com.xpsoft.webservice.service.inner.GoldMantisService;

public class FlowAutoCommitServiceImpl implements FlowAutoCommitService {
	
	private Log logger=LogFactory.getLog(FlowAutoCommitServiceImpl.class);
	
	@Resource
	private TaskService taskService;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private JbpmService jbpmService;
	@Resource
	private AppUserService appUserService;
	@Resource
	private GoldMantisService goldMantisService;
	@Resource
	private UserFlowconfigService userFlowconfigService;
	@Resource
	private ProSendMsgService proSendMsgService;
	@Resource
	private RepositoryService repositoryService;
	
	
	/**
	 * 流程任务自动审批
	 */
	public boolean autoCommit(FlowRunInfo flowRunInfo){
		TaskImpl task=(TaskImpl)taskService.createTaskQuery().processInstanceId(flowRunInfo.getPiId()).uniqueResult();
		//添加金螳螂ERP代办事项
		if(task!=null){
			if(task.getSubTasks().size()>0){
				for (Task subTask : task.getSubTasks()) {
					UserFlowconfig userFlowconfig=userFlowconfigService.getByUserId(Long.parseLong(subTask.getAssignee()));
					if(null!=userFlowconfig){
						if(userFlowconfig.getIsToERP()){
							//流程名称
							String workflowName=(String)task.getProcessInstance().getVariable("processName");
							//流程发起人
							AppUser flowStartUser=(AppUser)task.getProcessInstance().getVariable("flowStartUser");
							String requestJobCode=flowStartUser.getStaffNo();
							//下一节点审批人
							AppUser assigneeUser=appUserService.get(Long.parseLong(subTask.getAssignee()));
							String handlerJobCode=assigneeUser.getStaffNo();
							//下一节点名称
							String workflowNodeName=task.getActivityName();
							//审批url
							String waitHandleJobUrl=flowRunInfo.getServerUrl()+"/flow/processRunDetailForLeader.do?taskId="+subTask.getId();
							//调用添加ERP待办事项的方法
							HandleJob handleJob=new HandleJob();
							handleJob.setWorkflowName(workflowName);
							handleJob.setRequestJobCode(requestJobCode);
							handleJob.setHandlerJobCode(handlerJobCode);
							handleJob.setFactHandleJobCode(handlerJobCode);
							handleJob.setWorkflowNodeName(workflowNodeName);
							
							if(StringUtils.isNotEmpty(flowRunInfo.getRunId())){
								handleJob.setRunId(flowRunInfo.getRunId());
							}else if(StringUtils.isNotEmpty(flowRunInfo.getPiId())){
								ProcessRun processRun=this.processRunService.getByExeId(flowRunInfo.getPiId());
								if(processRun!=null){
									handleJob.setRunId(processRun.getRunId().toString());
								}else{
									return false;
								}
							}else{
								return false;
							}
							
							waitHandleJobUrl+="&runId="+handleJob.getRunId();
							handleJob.setWaitHandleJobUrl(waitHandleJobUrl);
							goldMantisService.AddWaitHandleJob(handleJob);
						}
					}
					
					//检查是否需要自动审批
					String assigneeuserId =checkCommit(flowRunInfo,(TaskImpl)subTask);
					
					//自动审批
					if(!"".equals(assigneeuserId)&&assigneeuserId!=null){
						//系统自动批
						Map<String, ParamField> fieldMap = new HashMap<String, ParamField>();

						List<Transition> trans = jbpmService.getTransitionsForSignalProcess(flowRunInfo.getPiId());
						List<Transform> allTrans = new ArrayList<Transform>();
						flowRunInfo.setTaskId(subTask.getId());
						flowRunInfo.setUserId(assigneeuserId);

						for (Transition tran : trans) {
							Transform transform = new Transform(tran);
							allTrans.add(new Transform(tran));
							if (!transform.getName().contains("取消")&& !transform.getName().contains("驳回")) {
								ParamField paramField = new ParamField("leaderReply","varchar", "审批意见", Integer.parseInt("0"), Short.parseShort("1"));
								paramField.setValue("同意");
								fieldMap.put("leaderReply", paramField);

								ParamField paramField1 = new ParamField("leader", "varchar","审批人", Integer.parseInt("0"), Short.parseShort("0"));
								paramField1.setValue(appUserService.get(Long.parseLong(assigneeuserId)).getFullname());
								fieldMap.put("manager", paramField1);

								flowRunInfo.setDestName(transform.getDestination());
								flowRunInfo.setTransitionName(transform.getName());
								flowRunInfo.setActivityName(transform.getSource());
								flowRunInfo.setParamFields(fieldMap);
								flowRunInfo.setReturnBack(-1);//状态为通过
								
								processRunService.saveAndNextStep(flowRunInfo);
								
							    return true;
							}
						}
					}
				}
			}else{
				UserFlowconfig userFlowconfig=userFlowconfigService.getByUserId(Long.parseLong(task.getAssignee()));
				if(null!=userFlowconfig){
					if(userFlowconfig.getIsToERP()){
						//流程名称
						String workflowName=(String)task.getProcessInstance().getVariable("processName");
						//流程发起人
						AppUser flowStartUser=(AppUser)task.getProcessInstance().getVariable("flowStartUser");
						String requestJobCode=flowStartUser.getStaffNo();
						//下一节点审批人
						AppUser assigneeUser=appUserService.get(Long.parseLong(task.getAssignee()));
						String handlerJobCode=assigneeUser.getStaffNo();
						//下一节点名称
						String workflowNodeName=task.getActivityName();
						//审批url
						String waitHandleJobUrl=flowRunInfo.getServerUrl()+"/flow/processRunDetailForLeader.do?taskId="+task.getId();
						//调用添加ERP待办事项的方法
						HandleJob handleJob=new HandleJob();
						handleJob.setWorkflowName(workflowName);
						handleJob.setRequestJobCode(requestJobCode);
						handleJob.setHandlerJobCode(handlerJobCode);
						handleJob.setFactHandleJobCode(handlerJobCode);
						handleJob.setWorkflowNodeName(workflowNodeName);
						
						if(StringUtils.isNotEmpty(flowRunInfo.getRunId())){
							handleJob.setRunId(flowRunInfo.getRunId());
						}else if(StringUtils.isNotEmpty(flowRunInfo.getPiId())){
							ProcessRun processRun=this.processRunService.getByExeId(flowRunInfo.getPiId());
							if(processRun!=null){
								handleJob.setRunId(processRun.getRunId().toString());
							}else{
								return false;
							}
						}else{
							return false;
						}
						
						waitHandleJobUrl+="&runId="+handleJob.getRunId();
						handleJob.setWaitHandleJobUrl(waitHandleJobUrl);
						goldMantisService.AddWaitHandleJob(handleJob);
					}
				}
				
				//检查是否需要自动审批
				String assigneeuserId =checkCommit(flowRunInfo,task);
				
				//自动审批
				if(!"".equals(assigneeuserId)&&assigneeuserId!=null){
					//系统自动批
					Map<String, ParamField> fieldMap = new HashMap<String, ParamField>();

					List<Transition> trans = jbpmService.getTransitionsForSignalProcess(flowRunInfo.getPiId());
					List<Transform> allTrans = new ArrayList<Transform>();
					flowRunInfo.setTaskId(task.getId());
					flowRunInfo.setUserId(assigneeuserId);

					for (Transition tran : trans) {
						Transform transform = new Transform(tran);
						allTrans.add(new Transform(tran));
						if (!transform.getName().contains("取消")&& !transform.getName().contains("驳回")) {
							ParamField paramField = new ParamField("leaderReply","varchar", "审批意见", Integer.parseInt("0"), Short.parseShort("1"));
							paramField.setValue("同意");
							fieldMap.put("leaderReply", paramField);

							ParamField paramField1 = new ParamField("leader", "varchar","审批人", Integer.parseInt("0"), Short.parseShort("0"));
							paramField1.setValue(appUserService.get(Long.parseLong(assigneeuserId)).getFullname());
							fieldMap.put("manager", paramField1);

							flowRunInfo.setDestName(transform.getDestination());
							flowRunInfo.setTransitionName(transform.getName());
							flowRunInfo.setActivityName(transform.getSource());
							flowRunInfo.setParamFields(fieldMap);
							flowRunInfo.setReturnBack(-1);//状态为通过
							
							processRunService.saveAndNextStep(flowRunInfo);
							
						    return true;
						}
					}
				}
			}
			
		}else{
			logger.info("JBPM_TASK TASK IS NULL OR CPMPLATE");
		}
		
		return false;
	}
	
	/**
	 * 检查当前审批人是否已经审批过
	 * @param executionId
	 * @param task
	 * @return
	 */
	public String checkCommit(FlowRunInfo flowRunInfo,TaskImpl task){
		Map<String, String> map = new HashMap<String, String>();

		ProcessRun processRun=processRunService.getByExeId(flowRunInfo.getPiId());
		ProcessDefinition pd= this.repositoryService.createProcessDefinitionQuery()
							.processDefinitionId(jbpmService.getProcessInstanceByExeId(flowRunInfo.getPiId()).getProcessDefinitionId())
							.uniqueResult();
		if(processRun!=null){	
			
			if(task!=null&&!"".equals(task.getAssignee())&&task.getAssignee()!=null){
				
				if(task.getAssignee().equals(flowRunInfo.getUserId())){
					// 检查表单是否特殊，特殊则不自动，通用则自动批
					String vmPath = ProcessActivityAssistant.getFormAbsPath(processRun.getProDefinition().getName(), task.getActivityName());
					InputStream is=null;
					try{
						is=new FileInputStream(vmPath);
					}catch(Exception ex){
						logger.warn("error when read the file from " + task.getActivityName() + "-fields.xml, the reason is not upload the ");
					}
					
					if (is!=null) {
						logger.info("审批表单特殊，停止自动审批,请用户登录系统审批");
						return null;
					}
					
					return task.getAssignee();
				}else{
					// 检查表单是否特殊，特殊则不自动，通用则自动批
					String vmPath = ProcessActivityAssistant.getFormAbsPath(processRun.getProDefinition().getName(), task.getActivityName());
					InputStream is=null;
					try{
						is=new FileInputStream(vmPath);
					}catch(Exception ex){
						logger.warn("error when read the file from " + task.getActivityName() + "-fields.xml, the reason is not upload the ");
					}
					
					if (is!=null) {
						map.put("isReceived", "0");
						logger.info("审批表单特殊，停止自动审批,请用户登录系统审批");
					}else{
						map.put("isReceived", "1");
						logger.info("发送正常短信列表");
					}
					//不自动批的需要发短信
					//发短信
					List<String> assigneeList=new ArrayList<String>();
					assigneeList.add(task.getAssignee());
					this.proSendMsgService.SendMsg(assigneeList,flowRunInfo.getPiId(), task.getId(), task.getName(),pd.getName(),map);
				}
			}
		}
		return null;
	}
	
	
	
	
	

}
