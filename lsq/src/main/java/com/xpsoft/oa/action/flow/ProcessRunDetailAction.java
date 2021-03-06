 package com.xpsoft.oa.action.flow;
 
 import java.util.List;

import javax.annotation.Resource;

import org.jbpm.api.ProcessInstance;

import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.service.flow.JbpmService;
import com.xpsoft.oa.service.flow.ProcessFormService;
import com.xpsoft.oa.service.flow.ProcessRunService;
 
 public class ProcessRunDetailAction extends BaseAction
 {
 
   @Resource
   private ProcessRunService processRunService;
 
   @Resource
   private ProcessFormService processFormService;
 
   @Resource
   private JbpmService jbpmService;
   private Long runId;
   private Long taskId;
 
   public Long getRunId()
   {
     return this.runId;
   }
 
   public void setRunId(Long runId) {
     this.runId = runId;
   }
 
   public Long getTaskId()
   {
     return this.taskId;
   }
 
   public void setTaskId(Long taskId) {
     this.taskId = taskId;
   }
 
   public String execute() throws Exception
   {
     ProcessRun processRun = null;
     if (this.runId == null) {
    	 if(null!=this.taskId){
    		 ProcessInstance pis = this.jbpmService.getProcessInstanceByTaskId(this.taskId.toString());
    		 processRun = this.processRunService.getByPiId(pis.getId());
    		 getRequest().setAttribute("processRun", processRun);
    		 this.runId = processRun.getRunId();
    	 }
     } else {
       processRun = (ProcessRun)this.processRunService.get(this.runId);
     }
     List pfList = this.processFormService.getByRunId(this.runId);
 
     getRequest().setAttribute("pfList", pfList);
 
     return "success";
   }
 }

