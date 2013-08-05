 package com.xpsoft.oa.service.flow.impl;
 
 import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.task.TaskImpl;

import com.xpsoft.core.jbpm.pv.ParamField;
import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.action.flow.FlowRunInfo;
import com.xpsoft.oa.dao.flow.FormDataDao;
import com.xpsoft.oa.dao.flow.ProcessFormDao;
import com.xpsoft.oa.dao.flow.ProcessRunDao;
import com.xpsoft.oa.dao.system.AppUserDao;
import com.xpsoft.oa.model.flow.FormData;
import com.xpsoft.oa.model.flow.ProDefinition;
import com.xpsoft.oa.model.flow.ProSubjectDef;
import com.xpsoft.oa.model.flow.ProcessDelReason;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.flow.TaskSign;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.JbpmService;
import com.xpsoft.oa.service.flow.ProSubjectDefService;
import com.xpsoft.oa.service.flow.ProcessDelReasonService;
import com.xpsoft.oa.service.flow.ProcessRunService;
 
 public class ProcessRunServiceImpl extends BaseServiceImpl<ProcessRun>
   implements ProcessRunService
 {
   private ProcessRunDao dao;
   @Resource
   private ProSubjectDefService proSubjectDefService;
   @Resource
   private ProcessFormDao processFormDao;
   @Resource
   private FormDataDao formDataDao;
   
   @Resource
	private AppUserDao appUserDao;
   @Resource
	private TaskService taskService;
   @Resource
   private JbpmService jbpmService;
   @Resource
	private ProcessDelReasonService processDelReasonService;
   @Resource
   private ExecutionService executionService;
   
   private Integer version=0;
 
   public ProcessRunServiceImpl(ProcessRunDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public ProcessRun getByExeId(String exeId)
   {
     ProcessInstance pi = this.jbpmService.getProcessInstanceByExeId(exeId);
     if (pi != null) {
       return getByPiId(pi.getId());
     }
     return null;
   }
 
   public ProcessRun getByTaskId(String taskId) {
     ProcessInstance pi = this.jbpmService.getProcessInstanceByTaskId(taskId);
     if (pi != null) {
       return getByPiId(pi.getId());
     }
     return null;
   }
 
   public ProcessRun getByPiId(String piId) {
     return this.dao.getByPiId(piId);
   }
 
   public ProcessRun initNewProcessRun(ProDefinition proDefinition)
   {
     ProcessRun processRun = new ProcessRun();
     AppUser curUser = ContextUtil.getCurrentUser();
 
     Date curDate = new Date();
     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
 
     processRun.setSubject(proDefinition.getName() + sdf.format(curDate) + "(" + curUser.getFullname() + ")");
     processRun.setCreator(curUser.getFullname());
     processRun.setAppUser(curUser);
     processRun.setCreatetime(curDate);
     processRun.setProDefinition(proDefinition);
 
     return processRun;
   }
 
   public void saveProcessRun(ProcessRun processRun, ProcessForm processForm, FlowRunInfo runInfo)
   {
	 Map variables = new HashMap();
	   
     variables.putAll(runInfo.getVariables());
 
     save(processRun);
 
     boolean isNewsForm = processForm.getFormId() == null;
 
     AppUser curUser = ContextUtil.getCurrentUser();
     if (isNewsForm) {
 
       processForm.setCreatorId(curUser.getUserId());
       processForm.setCreatorName(curUser.getFullname());
     }
     
     ///
     Iterator it = runInfo.getParamFields().keySet().iterator();
     
     String selfDefName = "";//自定义名称 取自vm
     while (it.hasNext()) {
       String key = (String)it.next();
       ParamField paramField = (ParamField)runInfo.getParamFields().get(key);
       FormData fd = new FormData(paramField);
       if (!isNewsForm) {
    	 key = key.replaceAll("\\.", "_");//formdata里的参数.都改成_了;然后查询formdata, 查询完成之后要改回来，以便修改保存进jbpm的数据
         fd = this.formDataDao.getByFormIdFieldName(processForm.getFormId(), key);
         fd.copyValue(paramField);
         key = key.replaceAll("_", "\\.");
       } else {
         fd = new FormData(paramField);
       }
       if("selfDefName".equals(fd.getFieldName())){
    	   selfDefName = fd.getVal();
       }
       fd.setProcessForm(processForm);
 
       variables.put(key, fd.getValue());
       processForm.getFormDatas().add(fd);
     }
     
     ///
     if(runInfo.isReStart()){
    	 Integer maxVersion = Integer.valueOf(this.processFormDao.getMaxVersion(processRun.getRunId()).intValue());
    	 version=maxVersion+1;
     }else{
    	 version=0; 
     }
     processForm.setVersion(version);
     this.processFormDao.save(processForm);
 
 
     if (runInfo.isStartFlow())
     {
       variables.put("flowStartUser", ContextUtil.getCurrentUser());
 
       variables.put("processName", processRun.getProDefinition().getName());
 
       String piId = this.jbpmService.startProcess(processRun.getProDefinition().getDeployId(), variables);
       processRun.setRunStatus(ProcessRun.RUN_STATUS_RUNNING);
       processRun.setPiId(piId);
      
       ProSubjectDef proSubjectDef= proSubjectDefService.getByDefId(processRun.getProDefinition().getDefId());
       
       if(null!=proSubjectDef){
    	   if(StringUtils.isEmpty(proSubjectDef.getRefField())){
        	   selfDefName=proSubjectDef.getDefaultVal();
           }else{
        	   selfDefName= (String)variables.get(proSubjectDef.getRefField());
           }
       }
       if(StringUtils.isNotBlank(selfDefName)){
    	   //重新设置名称
    	   processRun.setSubject(processRun.getProDefinition().getName() +"-"+ selfDefName + "(" + curUser.getFullname() + ")");
       }
       
       save(processRun);
     }
   }
 
   public void saveAndNextStep(FlowRunInfo runInfo)
   {
     ProcessInstance pi;
     if (StringUtils.isNotEmpty(runInfo.getTaskId()))
       pi = this.jbpmService.getProcessInstanceByTaskId(runInfo.getTaskId());
     else {
       pi = this.jbpmService.getProcessInstance(runInfo.getPiId());
     }
 
     String xml = this.jbpmService.getDefinitionXmlByPiId(pi.getId());
     String nodeType = this.jbpmService.getNodeType(xml, runInfo.getActivityName());
     Map variables = runInfo.getVariables();
 
     ProcessRun processRun = getByPiId(pi.getId());
 
     //获取当前节点直行次数，防止重复提交
     Integer maxSn = Integer.valueOf(this.processFormDao.getActvityExeTimes(processRun.getRunId(), runInfo.getActivityName()).intValue());
     //保存表单数据
     ProcessForm processForm = new ProcessForm();
     processForm.setActivityName(runInfo.getActivityName());
     processForm.setSn(Integer.valueOf(maxSn.intValue() + 1));
     if(runInfo.isReturnBack()>=0){
    	 if(runInfo.isReturnBack()==0){
   			processForm.setStatus("2");//退回审批人
   		}else{
   			processForm.setStatus("3");//退回发起人
   		}
    	 processForm.setVersion(version++) ;
    	 variables.put("signVoteType",TaskSign.DECIDE_TYPE_REFUSE);
	}else{
		processForm.setStatus("1");
		processForm.setVersion(version) ;
		variables.put("signVoteType",TaskSign.DECIDE_TYPE_PASS);
	}
 
     AppUser curUser=null;
		if(null!=runInfo.getUserId()){
			curUser=appUserDao.get(Long.parseLong(runInfo.getUserId()));
		}else{
			curUser=ContextUtil.getCurrentUser();
		}
 
     processForm.setCreatorId(curUser.getUserId());
     processForm.setCreatorName(curUser.getFullname());
     processForm.setProcessRun(processRun);
 
     
 
     Iterator it = runInfo.getParamFields().keySet().iterator();
     while (it.hasNext()) {
       String key = (String)it.next();
       ParamField paramField = (ParamField)runInfo.getParamFields().get(key);
       FormData fd = new FormData(paramField);
       fd.setProcessForm(processForm);
 
       variables.put(key, fd.getValue());
       processForm.getFormDatas().add(fd);
     }
 
     this.processFormDao.save(processForm);
 
     if ("task".equals(nodeType))
     {
    	 Task task=taskService.getTask(runInfo.getTaskId());
		 if(runInfo.getActivityName().equals(task.getActivityName())){
			 try{
				 //完成此任务，同时为下一任务指定执行人
				 TaskImpl taskImpl=(TaskImpl)task;
					if(taskImpl.getSuperTask()!=null){
						jbpmService.completeSignSubTask(taskImpl.getSuperTask(), taskImpl, runInfo.getTransitionName(),runInfo.getDestName(), variables,curUser);
					}else{
						jbpmService.completeTask(task.getId(),runInfo.getTransitionName(),runInfo.getDestName(),variables);
					}
				 
			 }catch(Exception ex){
				 ex.printStackTrace();
			 }
		 }else{
			 this.jbpmService.completeTask(runInfo.getTaskId(), runInfo.getTransitionName(), runInfo.getDestName(), runInfo.getVariables());
		 }
       
     }else{
    	 this.jbpmService.signalProcess(pi.getId(), runInfo.getTransitionName(), variables);
     }
   }
   
   
   /**
	 * 完成任务，同时把数据保存至form_data表，记录该任务填写的表单数据
	 * @param piId
	 * @param transitionName
	 * @param variables
	 */
	public void saveAndNextstep(String piId,String destName, String transitionName,Map<String, ParamField> fieldMap,String taskId,Long userId){
		
		String xml=jbpmService.getProcessDefintionXMLByPiId(piId);
		if(destName.contains("-")){
			destName = destName.substring(0,destName.lastIndexOf("-"));
		}
		String nodeType=jbpmService.getNodeType(xml, destName);
		//保存这些数据至流程运行的环境中
		Map<String,Object>variables=new HashMap<String, Object>();
		
		ProcessRun processRun=getByPiId(piId);
		//ProcessInstance pi=jbpmService.getProcessInstance(piId);
		Iterator it=fieldMap.keySet().iterator();
		
		//取得最大的sn号，也则某一任务被重复驳回时，可以由此查看
		Integer maxSn=processFormDao.getActvityExeTimes(processRun.getRunId(), destName).intValue();
		ProcessForm processForm=new ProcessForm();
		processForm.setActivityName(destName);
		processForm.setSn(maxSn+1);
		
		 if(transitionName.contains("不通过")||transitionName.contains("取消")||transitionName.contains("驳回")){
				processForm.setStatus("3");
				processForm.setVersion(version++) ;
				variables.put("signVoteType",TaskSign.DECIDE_TYPE_REFUSE);
			}else{
				processForm.setStatus("1");
				processForm.setVersion(version) ;
				variables.put("signVoteType",TaskSign.DECIDE_TYPE_PASS);
			}
		
		
		AppUser curUser=null;
		if(null!=userId&&userId!=0){
			curUser=appUserDao.get(userId);
		}else{
			curUser=ContextUtil.getCurrentUser();
		}
		
		//设置执行人ID及人名，方便后面查询参与用户
		processForm.setCreatorId(curUser.getUserId());
		processForm.setCreatorName(curUser.getFullname());
		
		processForm.setProcessRun(processRun);
		
		
		while(it.hasNext()){
			String key=(String)it.next();
			ParamField paramField=fieldMap.get(key);
			FormData fd=new FormData(paramField);
			fd.setProcessForm(processForm);
			//把数据存储在variables
			variables.put(key, fd.getValue());
			processForm.getFormDatas().add(fd);
		}
		//保存数据至表单中，方便后面显示
		processFormDao.save(processForm);
		
		//设置当前任务为完成状态，并且为下一任务设置新的执行人或候选人
		 if ("task".equals(nodeType))
	     {
			 Task task=taskService.getTask(taskId);
			 if(destName.equals(task.getActivityName())){
				 try{
					 //完成此任务，同时为下一任务指定执行人
					 TaskImpl taskImpl=(TaskImpl)task;
						if(taskImpl.getSuperTask()!=null){
							jbpmService.completeSignSubTask(taskImpl.getSuperTask(), taskImpl, transitionName,destName, variables,curUser);
						}else{
							this.jbpmService.completeTask(taskId, transitionName, destName, variables);
						}
					 
				 }catch(Exception ex){
					 ex.printStackTrace();
				 }
			 }else{
				 this.jbpmService.completeTask(taskId, transitionName, destName, variables);
			 }
			 
	      
	     }
	     else this.jbpmService.signalProcess(piId, transitionName, variables);
	}
 
   public void remove(Long runId)
   {
     ProcessRun processRun = (ProcessRun)this.dao.get(runId);
     if (ProcessRun.RUN_STATUS_INIT.equals(processRun.getRunStatus())) {
       List<ProcessForm> processForms = this.processFormDao.getByRunId(runId);
       for (ProcessForm processForm : processForms) {
         this.processFormDao.remove(processForm);
       }
     }
     this.dao.remove(processRun);
   }
 
   public void removeByDefId(Long defId)
   {
     List processRunList = this.dao.getByDefId(defId, new PagingBean(0, 25));
     for (int i = 0; i < processRunList.size(); i++) {
       this.dao.remove((ProcessRun)processRunList.get(i));
     }
 
     if (processRunList.size() == 25)
       removeByDefId(defId);
   }
 
   public List<ProcessRun> getByUserIdSubject(Long userId, String subject, PagingBean pb)
   {
     return this.dao.getByUserIdSubject(userId, subject, pb);
   }
   
   
   public void multiDel(String id,String reason){
	   //查询流程
	   ProcessRun processRun =  dao.get(new Long(id));
		if(processRun!=null){
			if(processRun.getPiId()!=null){
				//根据流程实例ID查询任务
				List<Task> taskList=taskService.createTaskQuery().processInstanceId(processRun.getPiId()).list();
				for (Task task : taskList) {
					 TaskImpl taskImpl = (TaskImpl)task;  
					 //删除任务
					 taskImpl.setExecution((Execution)null);
					 taskService.deleteTask(task.getId());
				}
				//删除实例
				executionService.deleteProcessInstanceCascade(processRun.getPiId());
				
				 //保存删除的流程基本信息，原因
				 ProcessDelReason processDelReason =new ProcessDelReason();
				 processDelReason.setFlowName(processRun.getSubject());
				 processDelReason.setFlowCreater(processRun.getCreator());
				 processDelReason.setFlowCreaterId(processRun.getUserId());
				 processDelReason.setFlowCreaterDate(processRun.getCreatetime());
				 processDelReason.setReason(reason);
				 processDelReasonService.save(processDelReason);
				 
				 dao.remove(new Long(id));
			}
		}
	   
   }
 }

