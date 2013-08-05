 package com.xpsoft.oa.action.flow;
 
 import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.task.TaskImpl;

import com.google.gson.Gson;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.StringUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.flow.FormData;
import com.xpsoft.oa.model.flow.HandleTask;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.service.flow.HandleTaskService;
import com.xpsoft.oa.service.flow.ProcessFormService;
import com.xpsoft.oa.service.system.AppUserService;
 
 public class ProcessFormAction extends BaseAction
 {
 
   @Resource
   private ProcessFormService processFormService;
   @Resource
   private AppUserService appUserService;
   @Resource
   private HandleTaskService handleTaskService;
   @Resource
   private TaskService taskService;
   private ProcessForm processForm;
   private Long formId;
   private Long runId;
   
 
   public Long getRunId() {
	return runId;
	}
	
	public void setRunId(Long runId) {
		this.runId = runId;
	}
	
	public Long getFormId()
   {
     return this.formId;
   }
 
   public void setFormId(Long formId) {
     this.formId = formId;
   }
 
   public ProcessForm getProcessForm() {
     return this.processForm;
   }
 
   public void setProcessForm(ProcessForm processForm) {
     this.processForm = processForm;
   }
 
   public String list()
   {
	   short runStatus=0;
	   String type =getRequest().getParameter("type");
		QueryFilter filter=new QueryFilter(getRequest());
		
		if("ALL".equals(type)){			
			filter.addFilter("Q_activityName_S_EQ", "开始");
			runStatus=-1;
		}else{
			filter.addFilter("Q_creatorId_L_EQ", ContextUtil.getCurrentUserId().toString());
			filter.addFilter("Q_activityName_S_NEQ", "开始");
			runStatus=2;
		}
		
		List<ProcessForm> list= processFormService.getAll(filter);
		//去除重复，一个人两次审批时会出现两条同样的数据
		removeDuplicate(list);
		
		List<HandleTask> yhList=handleTaskService.getHandleTaskByAssigneeId(ContextUtil.getCurrentUserId().toString(), runStatus);
		
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:[");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for(ProcessForm run:list){
			String assignee="";
			String userName="";
			if(run.getProcessRun().getPiId()!=null){
				Task task=taskService.createTaskQuery().processInstanceId(run.getProcessRun().getPiId()).uniqueResult();
				if(task!=null){
					Set<Task> subTasks= ((TaskImpl)task).getSubTasks();
					assignee=task.getAssignee();
					if(!"".equals(assignee)&&assignee!=null){
						userName=appUserService.get(Long.parseLong(assignee)).getFullname();
					}else if(subTasks.size()>0){
						for (Task task2 : subTasks) {
							if(!"".equals(userName)){
								userName+=",";
							}
							userName+=appUserService.get(Long.parseLong(task2.getAssignee())).getFullname();
						}
					}
				}
			}
			buff.append("{runId:'").append(run.getProcessRun().getRunId()).append("',subject:'")
			.append(run.getProcessRun().getSubject()).append("',createtime:'").append(sdf.format(run.getProcessRun().getCreatetime()))
			.append("',piId:'").append(run.getProcessRun().getPiId()).append("',defId:'").append(run.getProcessRun().getProDefinition().getDefId())
			.append("',runStatus:'").append(run.getProcessRun().getRunStatus()).append("',assignee:'").append(userName).append("'},");
		}
		
		for(HandleTask handleTask:yhList){
			buff.append("{runId:'").append(handleTask.getRunId()).append("',subject:'")
			.append("("+handleTask.getAppsouce()+")"+handleTask.getSubject()).append("',createtime:'")
			.append(sdf.format(handleTask.getProcessrunCreatetime()))
			.append("',piId:'").append("").append("',defId:'").append("")
			.append("',runStatus:'").append(handleTask.getRunStatus()).append("',assignee:'")
			.append(handleTask.getRunStatus()==1?handleTask.getAssignee():"").append("',appsouce:'")
			.append(handleTask.getAppsouce())
			.append("',taskId:'").append(handleTask.getTaskId())
			.append("',taskName:'").append(handleTask.getSubject())
			.append("',url:'").append(handleTask.getUrl())
			.append("'},");
		}
		
		if(list.size()>0){
			buff.deleteCharAt(buff.length()-1);
		}
		buff.append("]");
		buff.append("}");
		
		jsonString=buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.processFormService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     ProcessForm processForm = (ProcessForm)this.processFormService.get(this.formId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(processForm));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
   
   public String getByRunId()
   {
	   List pfList =this.processFormService.getByRunId(this.runId);
	   
	   Gson gson = new Gson();
	 
	   StringBuffer sb = new StringBuffer("{");
	   
	   for (Object object : pfList) {
		  ProcessForm processForm=(ProcessForm)object;
		  if("开始".equals(processForm.getActivityName())){
			  Set<FormData> formDataSet=processForm.getFormDatas();
			  for (FormData formData : formDataSet) {
				  sb.append("'"+formData.getFieldName().replaceAll("\\_", ".")+"'");
				  sb.append(":'");
				  
				  if("varchar".equals(formData.getFieldType())){
					  sb.append(formData.getStrValue());
				  }else if("int".equals(formData.getFieldType())){
					  sb.append(formData.getIntValue());
				  }else if("long".equals(formData.getFieldType())){
					  sb.append(formData.getLongValue());
				  }else if("decimal".equals(formData.getFieldType())){
					  sb.append(formData.getDecValue());
				  }else if("date".equals(formData.getFieldType())||"datetime".equals(formData.getFieldType())){
					  sb.append(formData.getDateValue());
				  }else if("bool".equals(formData.getFieldType())){
					  sb.append(formData.getBoolValue());
				  }else if("text".equals(formData.getFieldType())){
					  sb.append(StringUtil.replaceEnter(formData.getTextValue()));
				  }else if("file".equals(formData.getFieldType())){//处理上传文件
					  sb.append(formData.getStrValue());
				  }
				 
				  sb.append("',");
			  }
			  if(formDataSet.size()>0){
					 sb.deleteCharAt(sb.length()-1);
			  }
		  }
	   }
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   
   public String getByRunIdLast()
   {
	   List pfList =this.processFormService.getByRunId(this.runId);
	   
	   Gson gson = new Gson();
	 
	   StringBuffer sb = new StringBuffer("{");
	   Object object=pfList.get(pfList.size()-1);
		  ProcessForm processForm=(ProcessForm)object;
		  if(!"采购部询价".equals(processForm.getActivityName())){
			  processForm=(ProcessForm)pfList.get(0);
		  }
			  Set<FormData> formDataSet=processForm.getFormDatas();
			  for (FormData formData : formDataSet) {
				  sb.append("'"+formData.getFieldName().replaceAll("\\_", ".")+"'");
				  sb.append(":'");
				  
				  if("varchar".equals(formData.getFieldType())){
					  sb.append(formData.getStrValue());
				  }else if("int".equals(formData.getFieldType())){
					  sb.append(formData.getIntValue());
				  }else if("long".equals(formData.getFieldType())){
					  sb.append(formData.getLongValue());
				  }else if("decimal".equals(formData.getFieldType())){
					  sb.append(formData.getDecValue());
				  }else if("date".equals(formData.getFieldType())||"datetime".equals(formData.getFieldType())){
					  sb.append(formData.getDateValue());
				  }else if("bool".equals(formData.getFieldType())){
					  sb.append(formData.getBoolValue());
				  }else if("text".equals(formData.getFieldType())){
					  sb.append(formData.getTextValue());
				  }else if("file".equals(formData.getFieldType())){//处理上传文件
					  sb.append(formData.getStrValue());
				  }
				 
				  sb.append("',");
			  }
			  if(formDataSet.size()>0){
					 sb.deleteCharAt(sb.length()-1);
			  }
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
   
   public String save()
   {
     this.processFormService.save(this.processForm);
     setJsonString("{success:true}");
     return "success";
   }
   
 //去除重复数据
	private  void removeDuplicate(List<ProcessForm> list) {
		   for ( int i = 0 ; i < list.size() - 1 ; i ++ ) {
		    for ( int j = list.size() - 1 ; j > i; j -- ) {
		      if (list.get(j).getProcessRun().getCreatetime().compareTo(list.get(i).getProcessRun().getCreatetime())==0) {
		        list.remove(j);
		      } 
		     } 
		   } 
		   //System.out.println(list);
		}
 }

