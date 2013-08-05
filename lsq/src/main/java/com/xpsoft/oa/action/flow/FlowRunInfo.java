 package com.xpsoft.oa.action.flow;
 
 import com.xpsoft.core.jbpm.pv.ParamField;
import com.xpsoft.core.util.AppUtil;

import java.io.IOException;
import java.io.InputStream;
 import java.util.HashMap;
 import java.util.Map;
import java.util.Properties;

 import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
 
 public class FlowRunInfo
 {
   private Map variables = new HashMap();
 
   private Map<String, ParamField> paramFields = new HashMap();
 
   private boolean isStartFlow = false;
   private HttpServletRequest request;
   private String processName = "通用";
   private String activityName = "开始";
   private String destName = null;
   private String transitionName;
   private String taskId;
   private String piId;
   private String runId;
   private String userId;
   private boolean isAuto;
   private int returnBack;//0退回审批人； 1退回发起人 
   private boolean isReStart=false;
   private String serverUrl=(String)AppUtil.getSysConfig().get("app.serverUrl");
 
   public String getUserId() {
	return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

public FlowRunInfo(HttpServletRequest req)
   {
	
     if ("true".equals(req.getParameter("startFlow"))) {
       this.isStartFlow = true;
       if ("true".equals(req.getParameter("reStart"))) {
           this.isReStart = true;
        }
     }
     else {
       String signUserIds = req.getParameter("signUserIds");
       if (StringUtils.isNotEmpty(signUserIds)) {
         this.variables.put("signUserIds", signUserIds);
       }
 
       String flowAssignId = req.getParameter("flowAssignId");
       if (StringUtils.isNotEmpty(flowAssignId)) {
         this.variables.put("flowAssignId", flowAssignId);
       }
 
       String signUserId = req.getParameter("");
 
       String pTaskId = req.getParameter("taskId");
       if (StringUtils.isNotEmpty(pTaskId)) {
         this.taskId = pTaskId;
       }
 
       String pPiId = req.getParameter("piId");
 
       if (StringUtils.isNotEmpty(pPiId)) {
         this.piId = pPiId;
       }
       
       String runId = req.getParameter("runId");
       
       if (StringUtils.isNotEmpty(runId)) {
         this.runId = runId;
       }
 
       String pActivityName = req.getParameter("activityName");
       if (StringUtils.isNotEmpty(pActivityName)) {
         this.activityName = pActivityName;
       }
 
       String pDestName = req.getParameter("destName");
 
       if (StringUtils.isNotEmpty(pDestName)) {
         this.destName = pDestName;
       }
 
       String pSignName = req.getParameter("signalName");
       if (StringUtils.isNotEmpty(pSignName))
         this.transitionName = pSignName;
       
       String returnBack = req.getParameter("returnBack");
       if ("0".equals(returnBack)) {
         this.returnBack = 0;
       }else if("1".equals(returnBack)){
    	   this.returnBack = 1;
       }else{
    	   this.returnBack = -1;
       }
       
      
     }
   }
 
   public FlowRunInfo()
   {
   }
 
   public Map getVariables()
   {
     return this.variables;
   }
 
   public void setVariables(Map variables) {
     this.variables = variables;
   }
 
   public boolean isStartFlow() {
     return this.isStartFlow;
   }
 
   public void setStartFlow(boolean isStartFlow) {
     this.isStartFlow = isStartFlow;
   }
 
   public HttpServletRequest getRequest() {
     return this.request;
   }
 
   public void setRequest(HttpServletRequest request) {
     this.request = request;
   }
 
   public String getProcessName() {
     return this.processName;
   }
 
   public void setProcessName(String processName) {
     this.processName = processName;
   }
 
   public String getActivityName() {
     return this.activityName;
   }
 
   public void setActivityName(String activityName) {
     this.activityName = activityName;
   }
 
   public Map<String, ParamField> getParamFields() {
     return this.paramFields;
   }
 
   public void setParamFields(Map<String, ParamField> paramFields) {
     this.paramFields = paramFields;
   }
 
   public String getTransitionName() {
     return this.transitionName;
   }
 
   public void setTransitionName(String transitionName) {
     this.transitionName = transitionName;
   }
 
   public String getTaskId() {
     return this.taskId;
   }
 
   public void setTaskId(String taskId) {
     this.taskId = taskId;
   }
 
   public String getPiId() {
     return this.piId;
   }
 
   public void setPiId(String piId) {
     this.piId = piId;
   }
 
   public String getDestName() {
     return this.destName;
   }
 
   public void setDestName(String destName) {
     this.destName = destName;
   }
 
   public void setdAssignId(String assignId)
   {
     this.variables.put("flowAssignId", assignId);
   }
 
   public void setMultipleTask(String userIds)
   {
     this.variables.put("signUserIds", userIds);
   }

	public boolean isAuto() {
		return isAuto;
	}
	
	public void setAuto(boolean isAuto) {
		this.isAuto = isAuto;
	}

	public int isReturnBack() {
		return returnBack;
	}

	public void setReturnBack(int returnBack) {
		this.returnBack = returnBack;
	}

	public boolean isReStart() {
		return isReStart;
	}

	public void setReStart(boolean isReStart) {
		this.isReStart = isReStart;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getRunId() {
		return runId;
	}

	public void setRunId(String runId) {
		this.runId = runId;
	}
   
   
   
   
 }

