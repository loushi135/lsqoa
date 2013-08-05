package com.xpsoft.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.model.Transition;

import com.xpsoft.core.Constants;
import com.xpsoft.core.jbpm.pv.ParamField;
import com.xpsoft.core.model.ProMsgDetail;
import com.xpsoft.core.model.ProMsgReceived;
import com.xpsoft.core.model.ProcessProperty;
import com.xpsoft.core.service.ProMsgDetailService;
import com.xpsoft.core.service.ProSendMsgService;
import com.xpsoft.oa.action.flow.ProcessActivityAssistant;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.flow.Transform;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.UserFlowconfig;
import com.xpsoft.oa.service.flow.FormDataService;
import com.xpsoft.oa.service.flow.JbpmService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.flow.TaskService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.FlowAutoCommitService;
import com.xpsoft.oa.service.system.UserFlowconfigService;

public class ProSendMsgServiceImpl implements ProSendMsgService {

	private Log logger = LogFactory.getLog(ProSendMsgServiceImpl.class);

	@Resource
	private ProMsgDetailService proMsgDetailService;
	@Resource
	private AppUserService appUserService;
	@Resource
	private JbpmService jbpmService;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private UserFlowconfigService userFlowconfigService;
	@Resource
	private TaskService flowTaskService;
	@Resource
	private org.jbpm.api.TaskService taskService;
	@Resource
	private FormDataService formDataService;
	@Resource
	private FlowAutoCommitService flowAutoCommitService;

	/**
	 * 发送代办任务信息，短信或者邮件 分配任务时调用
	 */
	public void SendMsg(List<String> userList, String processId, String taskId,
			String taskName, String deployName, Map variables) {
		boolean flag = false;
		String userNamesList="";

		// 取任务申请的表单数据
		ProcessRun processRun = processRunService.getByPiId(processId);
		Map<String,Object> dataMap = new HashMap<String,Object>();
		if (processRun != null) {
			variables.putAll(formDataService.getFromDataMap(processRun.getRunId(),"开始"));
			Map<String, ParamField> paramMap = ProcessActivityAssistant.constructFieldMap(processRun.getProDefinition().getName(), "开始");
			dataMap.put("proDefName", processRun.getProDefinition().getName());
			dataMap.put("paramMap", paramMap);
		}
		List<AppUser> userMsgList = new ArrayList<AppUser>();
		List<AppUser> userEmailList = new ArrayList<AppUser>();
		Map<String, String> subTask = new HashMap<String, String>();
		String isReceived="";
		
		isReceived=(String)variables.get("isReceived");
		

		// 邮件和短信发送列表
		if (userList.size() > 0) {
			for (String userId : userList) {
				UserFlowconfig userFlowconfig = userFlowconfigService
						.getByUserId(Long.parseLong(userId));
				if (null != userFlowconfig) {
					if (userFlowconfig.getIsMsg()) {
						userMsgList.add(userFlowconfig.getAppUser());
					}
					if (userFlowconfig.getIsEmail()) {
						userEmailList.add(userFlowconfig.getAppUser());
					}
				}
			}

			// 查看是否有子任务，即会签
			List<Task> taskSubList = taskService.getSubTasks(taskId);

			if (taskSubList.size() > 0) {
				for (Task task : taskSubList) {
					subTask.put(task.getAssignee(), task.getId());
				}
			}
			if (userMsgList.size() > 0) {
				for (AppUser appUser : userMsgList) {
					if (taskSubList.size() > 0) {// 会签发送子任务
						flag = proMsgDetailService.insertMsgDetail(appUser,isReceived, processId, subTask.get(appUser.getId()), taskName, variables,dataMap);						
					} else {
						flag = proMsgDetailService.insertMsgDetail(appUser,isReceived, processId, taskId, taskName,variables,dataMap);
					}
					userNamesList+=appUser.getFullname()+",";
				}
			}else {
				for (AppUser appUser : userMsgList) {
					userNamesList+=appUser.getFullname()+",";
				}
				logger.info("无发送短信用户列表");
			}
		}

		if (!flag) {
			logger.info("实例ID为：【"+processId+"】的流程短信未能正常发出！接收者："+userNamesList);
		}
	}

	/**
	 * 短信回复接口
	 * 
	 * @param pmr
	 *            回复信息内容实体
	 * @param pmd
	 *            回复内容对应的流程信息
	 * @return true/fasle
	 */
	public boolean msgReplyDoTask(ProMsgReceived pmr, ProMsgDetail pmd) {

		return doTask(pmr.getResult(), pmr.getResNote(), pmd.getProcessId(),
				pmd.getTaskId(), pmd.getTaskName(), pmd.getUserId(), false);
	}

	// 判断任务实例ID是否存在
	public boolean isTask(String strId) {
		boolean flag = false;

		if (null != strId && !"".equals(strId)) {
			flag = flowTaskService.get(Long.parseLong(strId)) != null ? true
					: false;
		}

		return flag;
	}

	/**
	 * 短信回复处理待办事项 result:短信回复结果，同意或不同意 piId：流程实例ID taskId：任务实例ID taskName：任务名称
	 * 
	 * return ：true or false
	 */
	public boolean doTask(String result, String resNote, String piId,
			String taskId, String taskName, Long userId, boolean isAuto) {

		try {
			Map<String, ParamField> fieldMap = new HashMap<String, ParamField>();

			String activityName = taskName;

			List<Transition> trans = jbpmService
					.getTransitionsForSignalProcess(piId);
			List<Transform> allTrans = new ArrayList<Transform>();

			for (Transition tran : trans) {
				Transform transform = new Transform(tran);
				allTrans.add(new Transform(tran));
				if ("1".equals(result) && !transform.getName().contains("取消")
						&& !transform.getName().contains("驳回")) {
					ParamField paramField = new ParamField("msgReply",
							"varchar", "审批意见", Integer.parseInt("0"), Short
									.parseShort("1"));
					paramField.setValue("同意" + ("".equals(resNote) ? "" : ",")
							+ resNote);
					fieldMap.put("msgReply", paramField);

					ParamField paramField1 = new ParamField("manager",
							"varchar", "审批人", Integer.parseInt("0"), Short
									.parseShort("0"));
					paramField1.setValue(appUserService.get(userId)
							.getFullname());
					fieldMap.put("manager", paramField1);
					processRunService.saveAndNextstep(piId, activityName,
							transform.getName(), fieldMap, taskId, userId);

					break;
				} else if ("0".equals(result)
						&& (transform.getName().contains("取消") || transform
								.getName().contains("驳回"))) {
					ParamField paramField = new ParamField("msgReply",
							"varchar", "审批意见", Integer.parseInt("0"), Short
									.parseShort("1"));
					paramField.setValue("不同意" + ("".equals(resNote) ? "" : ",")
							+ resNote);
					fieldMap.put("msgReply", paramField);
					processRunService.saveAndNextstep(piId, activityName,
							transform.getName(), fieldMap, taskId, userId);

					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}



}
