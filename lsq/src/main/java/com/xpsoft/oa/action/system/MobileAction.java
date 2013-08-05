package com.xpsoft.oa.action.system;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.pvm.internal.model.Transition;
import org.jbpm.pvm.internal.task.TaskImpl;

import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.jbpm.pv.TaskInfo;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.action.flow.ProcessActivityAssistant;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.flow.Transform;
import com.xpsoft.oa.model.info.Notice;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.FormDataService;
import com.xpsoft.oa.service.flow.JbpmService;
import com.xpsoft.oa.service.flow.ProcessFormService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.flow.TaskService;
import com.xpsoft.oa.service.info.NoticeService;
import com.xpsoft.oa.service.system.AppUserService;

import flexjson.JSONSerializer;

public class MobileAction extends BaseAction {
	
	@Resource
	private NoticeService noticeService;
	@Resource
	private TaskService flowTaskService;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private ProcessFormService processFormService;
	@Resource
	private AppUserService appUserService;
	@Resource
	private JbpmService jbpmService;
	@Resource
	private org.jbpm.api.TaskService taskService;
	
	private Long taskId;
	public Long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	
	
	/**
	 * 公告
	 * @return
	 */
	public String noticeList(){
		String nextPage=getRequest().getParameter("retrieved");
		String pageSize=getRequest().getParameter("retrieve");
		
		if(StringUtils.isEmpty(pageSize)){
			pageSize="8";
		}
		if(StringUtils.isEmpty(nextPage)){
			nextPage="0";
		}
		
		QueryFilter filter=new QueryFilter(getRequest());
		//filter.addFilter("Q_state_SN_EQ","1");
		filter.addSorted("id", "desc");
		filter.getPagingBean().setPageSize(Integer.parseInt(pageSize));
		filter.getPagingBean().setStart(Integer.parseInt(nextPage));
		List<Notice> list= noticeService.getAll(filter);
		
		
		if("0".equals(nextPage)){
			getRequest().setAttribute("noticeList", list);
			getRequest().setAttribute("currentPage", nextPage);
			return "NoticeList";
		}else{
			StringBuffer buff = new StringBuffer("callback('{\"data\":[{\"count\":")
			.append(Integer.parseInt(pageSize)).append(",\"json\":");
			
			JSONSerializer serializer=JsonUtil.getJSONSerializer("effectiveDate","expirationDate");
			buff.append(serializer.exclude(new String[]{"class","noticeContent"}).serialize(list));
			buff.append("}]}')");
			jsonString=buff.toString();	
			return SUCCESS;
		}
		
	}
	
	/**
	 * 查询公告信息
	 * @return
	 */
	public String getNotice(){
		String noticeId=getRequest().getParameter("noticeId");
		if(StringUtils.isNotEmpty(noticeId)){
			Notice notice=noticeService.get(Long.parseLong(noticeId));			
			getRequest().setAttribute("notice", notice);
		}
			
		return "Notice";
	}
	
	/**
	 * 代办任务列表
	 * @return
	 */
	public String taskList(){
		String nextPage=getRequest().getParameter("retrieved");
		String pageSize=getRequest().getParameter("retrieve");
		
		if(StringUtils.isEmpty(pageSize)){
			pageSize="8";
		}
		if(StringUtils.isEmpty(nextPage)){
			nextPage="0";
		}
		
		PagingBean pb=new PagingBean((Integer.parseInt(nextPage)), Integer.parseInt(pageSize));
		List<TaskInfo> tasks=flowTaskService.getTaskInfosByUserId(ContextUtil.getCurrentUserId().toString(),pb);
		
		for ( int i = 0 ; i < tasks.size() - 1 ; i ++ ) {
			for ( int j = tasks.size() - 1 ; j > i; j -- ) {
				if (tasks.get(j).getCreateTime().compareTo(tasks.get(i).getCreateTime())==0) {
					tasks.remove(j);
				} 
			} 
		}
		
		if("0".equals(nextPage)){
			getRequest().setAttribute("taskList", tasks);
			getRequest().setAttribute("currentPage", nextPage);
			return "TaskList";
		}else{
			StringBuffer buff = new StringBuffer("callback('{\"data\":[{\"count\":")
			.append(Integer.parseInt(pageSize)).append(",\"json\":");
			
			JSONSerializer serializer=JsonUtil.getJSONSerializer("createTime","dueDate");
			buff.append(serializer.exclude(new String[]{"class"}).serialize(tasks));
			buff.append("}]}')");
			jsonString=buff.toString();	
			return SUCCESS;
		}
	}
	
	/**
	 * 查询待办任务信息
	 * @return
	 */
	public String getTask(){
		String taskId=getRequest().getParameter("taskId");
		String activityName=getRequest().getParameter("activityName");
		try {
			activityName = new String(activityName.getBytes("ISO-8859-1"),"UTF-8").replace("'", "");
		} catch (UnsupportedEncodingException e) {
			
		}
		
		Long runId=null;
		ProcessRun processRun=null;
		
		if(StringUtils.isNotEmpty(taskId)){		
			ProcessInstance pis = this.jbpmService.getProcessInstanceByTaskId(this.taskId.toString());
			if(pis==null){
				return taskList();
			}
	        processRun = this.processRunService.getByPiId(pis.getId());
	        getRequest().setAttribute("processRun", processRun);
			
			if(processRun!=null){
				runId=processRun.getRunId();
			}
			
			List pfList=processFormService.getByRunId(runId);
			getRequest().setAttribute("pfList", pfList);
			
			
			//取开始表单所有值
			Map processRunVars = this.processFormService.getVariables(processRun.getRunId());
			Iterator fieldNames = processRunVars.keySet().iterator();
			while (fieldNames.hasNext()) {
				String fieldName = (String) fieldNames.next();
				getRequest().setAttribute(fieldName, processRunVars.get(fieldName));
			}
		}
		
		/*****************************************************************/
		// 检查jsp表单是否特殊
		String vmPath = ProcessActivityAssistant.getJspFormAbsPath(processRun.getProDefinition().getName(), activityName);
		InputStream is=null;
		try{
			is=new FileInputStream(vmPath);
		}catch(Exception ex){
			logger.warn("error when read the file from " + activityName + ".jsp, the reason is not upload the ");
		}
		
		if (is!=null) {
			getRequest().setAttribute("flag", true);
			getRequest().setAttribute("formUrl","/WEB-INF/FlowForm/"+processRun.getProDefinition().getName()+"/"+activityName+".jsp");
		}else{
			getRequest().setAttribute("flag", false);
			getRequest().setAttribute("formUrl","/WEB-INF/FlowForm/通用/表单.jsp");
		}
		
		
		/*****************************************************************/
		
		
		getRequest().setAttribute("taskId", taskId);	
		getRequest().setAttribute("activityName", activityName);
		return "Task";
	}
	
	
	/**
	 * 代办任务列表
	 * @return
	 */
	public String contactList(){
		String nextPage=getRequest().getParameter("retrieved");
		String pageSize=getRequest().getParameter("retrieve");
		
		if(StringUtils.isEmpty(pageSize)){
			pageSize="8";
		}
		if(StringUtils.isEmpty(nextPage)){
			nextPage="0";
		}
		
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_delFlag_SN_EQ", Constants.FLAG_UNDELETED.toString());
		filter.addFilter("Q_status_SN_EQ", Constants.FLAG_ACTIVATION.toString());
		filter.getPagingBean().setPageSize(Integer.parseInt(pageSize));
		filter.getPagingBean().setStart(Integer.parseInt(nextPage));
		List<AppUser> list = appUserService.getAll(filter);
		
		
		if("0".equals(nextPage)){
			getRequest().setAttribute("contactList", list);
			getRequest().setAttribute("currentPage", nextPage);
			return "ContactList";
		}else{
			StringBuffer buff = new StringBuffer("callback('{\"data\":[{\"count\":")
			.append(Integer.parseInt(pageSize)).append(",\"json\":");
			
			JSONSerializer serializer=JsonUtil.getJSONSerializer();
			buff.append(serializer.exclude(new String[]{"class","department"}).serialize(list));
			buff.append("}]}')");
			jsonString=buff.toString();	
			return SUCCESS;
		}
	}
	
	/**
	 * 取得当前任务所有出口
	 * @return
	 */
	public String trans(){
		TaskImpl task = (TaskImpl) this.taskService.getTask(this.taskId.toString());
		List allTrans = new ArrayList();
		List<Transition> trans = this.jbpmService.getTransitionsByTaskId(this.taskId.toString());

		for (Transition tran : trans) {
			if (tran.getName() != null && tran.getSource() != null
					&& tran.getDestination() != null) {
				allTrans.add(new Transform(tran));
			}
		}
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[0]);
		String result = serializer.serialize(allTrans);

		setJsonString("{\"success\":true,\"data\":" + result + ",\"executionId\":\""+task.getExecutionId()+"\"}");
		return SUCCESS;
	}
	
	

}
