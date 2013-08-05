 package com.xpsoft.oa.action.flow;
 
 import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.task.ParticipationImpl;
import org.jbpm.pvm.internal.task.TaskImpl;

import com.google.gson.Gson;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.system.AppUserService;
 
 public class ProcessRunAction extends BaseAction
 {
 
   @Resource
   private ProcessRunService processRunService;
   @Resource
   private TaskService taskService;
   @Resource
	private AppUserService appUserService;
   
   private ProcessRun processRun;
   private Long runId;
 
   public Long getRunId()
   {
     return this.runId;
   }
 
   public void setRunId(Long runId) {
     this.runId = runId;
   }
 
   public ProcessRun getProcessRun() {
     return this.processRun;
   }
 
   public void setProcessRun(ProcessRun processRun) {
     this.processRun = processRun;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
 
     filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
 
     List<ProcessRun> list = this.processRunService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:[");
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
     for (ProcessRun run : list) {
	   String assignee="";
	   String userName="";
	   if(run.getPiId()!=null){
			Task task=taskService.createTaskQuery().processInstanceId(run.getPiId()).uniqueResult();
			if(task !=null){
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
				}else{
  					Set<ParticipationImpl> participations = ((TaskImpl)task).getParticipations();
  					for (Iterator iterator = participations.iterator(); iterator
							.hasNext();) {
  						if(!"".equals(userName)){
  							userName+=",";
  						}
						ParticipationImpl participationImpl = (ParticipationImpl) iterator
								.next();
						if(StringUtils.isNotBlank(participationImpl.getUserId())){
							userName+=appUserService.get(Long.parseLong(participationImpl.getUserId())).getFullname();
						}
					}
  				}
			}
		}
       buff.append("{runId:'").append(run.getRunId()).append("',subject:'")
         .append(run.getSubject()).append("',createtime:'").append(sdf.format(run.getCreatetime()))
         .append("',piId:'").append(run.getPiId()).append("',defId:'").append(run.getProDefinition().getDefId())
         .append("',runStatus:'").append(run.getRunStatus())
			.append("',assignee:'").append(userName).append(
			"'},");
     }
 
     if (list.size() > 0) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]");
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String my()
   {
     QueryFilter filter = new QueryFilter(getRequest());
 
     filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
 
     List<ProcessRun> processRunList = this.processRunService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:[");
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
     for (ProcessRun run : processRunList) {
    	 String assignee="";
  	   String userName="";
  	   if(run.getPiId()!=null){
  			Task task=taskService.createTaskQuery().processInstanceId(run.getPiId()).uniqueResult();
  			if(task !=null){
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
       buff.append("{runId:'").append(run.getRunId()).append("',subject:'")
         .append(run.getSubject()).append("',createtime:'").append(sdf.format(run.getCreatetime()))
         .append("',piId:'").append(run.getPiId()).append("',defId:'").append(run.getProDefinition().getDefId())
         .append("',runStatus:'").append(run.getRunStatus()).append("',assignee:'").append(userName).append("'},");
     }
 
     if (processRunList.size() > 0) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]");
     buff.append("}");
 
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String multiDel()
   {
     String id = getRequest().getParameter("id");
     String  reason = getRequest().getParameter("reason");
     if (StringUtils.isNotEmpty(id)) {       	 
    	 processRunService.multiDel(id, reason);       
     }
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String get()
   {
     ProcessRun processRun = (ProcessRun)this.processRunService.get(this.runId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(processRun));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.processRunService.save(this.processRun);
     setJsonString("{success:true}");
     return "success";
   }
 }

