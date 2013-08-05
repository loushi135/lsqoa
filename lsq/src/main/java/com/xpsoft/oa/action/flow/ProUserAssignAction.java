 package com.xpsoft.oa.action.flow;
 
 import com.google.gson.Gson;
 import com.xpsoft.core.jbpm.jpdl.Node;
import com.xpsoft.core.util.StringUtil;
 import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.archive.ArchivesDoc;
 import com.xpsoft.oa.model.flow.ProDefinition;
import com.xpsoft.oa.model.flow.ProOtherDef;
import com.xpsoft.oa.model.flow.ProSubjectDef;
 import com.xpsoft.oa.model.flow.ProUserAssign;
 import com.xpsoft.oa.service.flow.JbpmService;
 import com.xpsoft.oa.service.flow.ProDefinitionService;
import com.xpsoft.oa.service.flow.ProOtherDefService;
import com.xpsoft.oa.service.flow.ProSubjectDefService;
 import com.xpsoft.oa.service.flow.ProUserAssignService;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
 
 public class ProUserAssignAction extends BaseAction
 {
 
   @Resource
   private ProUserAssignService proUserAssignService;
   @Resource
   private ProSubjectDefService proSubjectDefService;
   @Resource
   private JbpmService jbpmService;
   @Resource
   private ProOtherDefService proOtherDefService;
   @Resource
   private ProDefinitionService proDefinitionService;
   private ProUserAssign proUserAssign;
   private Long assignId;
   
 
   public void setJbpmService(JbpmService jbpmService)
   {
     this.jbpmService = jbpmService;
   }
 
   public Long getAssignId()
   {
     return this.assignId;
   }
 
   public void setAssignId(Long assignId) {
     this.assignId = assignId;
   }
 
   public ProUserAssign getProUserAssign() {
     return this.proUserAssign;
   }
 
   public void setProUserAssign(ProUserAssign proUserAssign) {
     this.proUserAssign = proUserAssign;
   }
 
   public String list()
   {
     String defId = getRequest().getParameter("defId");
 
     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(new Long(defId));
 
     List nodes = this.jbpmService.getTaskNodesByDefId(new Long(defId));
 
     List nodesAssignList = this.proUserAssignService.getByDeployId(proDefinition.getDeployId());
 
     StringBuffer buff = new StringBuffer("{result:[");
 
     for (int i = 0; i < nodes.size(); i++) {
       String nodeName = ((Node)nodes.get(i)).getName();
       buff.append("{activityName:'").append(nodeName).append("',deployId:'" + proDefinition.getDeployId()).append("'");
       for (int j = 0; j < nodesAssignList.size(); j++) {
         ProUserAssign assign = (ProUserAssign)nodesAssignList.get(j);
         if (!nodeName.equals(assign.getActivityName())) continue;
         buff.append(",assignId:'").append(assign.getAssignId())
           .append("',userId:'").append(assign.getUserId())
           .append("',username:'").append(assign.getUsername())
           .append("',roleId:'").append(assign.getRoleId())
           .append("',roleName:'").append(assign.getRoleName())
           .append("',isSigned:'").append(assign.getIsSigned())
           .append("'");
         break;
       }
 
       buff.append("},");
     }
 
     if (!nodes.isEmpty()) {
       buff.deleteCharAt(buff.length() - 1);
     }
 
     buff.append("]}");
 
     setJsonString(buff.toString());
 
     return "success";
   }
 
   public String get()
   {
     ProUserAssign proUserAssign = (ProUserAssign)this.proUserAssignService.get(this.assignId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(proUserAssign));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     String data = getRequest().getParameter("data");
     
     
//     System.out.println(getRequest().getParameter("subRefLabel"));
//     System.out.println(getRequest().getParameter("subRefFieName"));
//     System.out.println(getRequest().getParameter("defId"));
//     System.out.println(getRequest().getParameter("pId"));
     Long defId=Long.valueOf(getRequest().getParameter("defId"));
     
     ProSubjectDef proSubjectDef=new ProSubjectDef(
    		 defId,
    		 getRequest().getParameter("subRefFieName"),getRequest().getParameter("subRefLabel"));
     
     Long pid=null;
     
     if(StringUtils.isNotEmpty(getRequest().getParameter("pId"))){
    	 pid=Long.valueOf(getRequest().getParameter("pId"));
    	 proSubjectDef.setId(pid);
     }
     
     String printUserIds=getRequest().getParameter("printUserIds");
     String printUserNames=getRequest().getParameter("printUserNames");
     
     if(defId!=null&&StringUtils.isNotEmpty(printUserIds)&&StringUtils.isNotEmpty(printUserNames)){
    	 
    	 ProOtherDef proOtherDef=new ProOtherDef(defId);
    	 proOtherDef.setPrintUserIds(printUserIds);
    	 proOtherDef.setPrintUserNames(printUserNames);
    	 
    	 proOtherDefService.save(proOtherDef);
     }
     
     
     proSubjectDefService.save(proSubjectDef);
     
 
     if (StringUtils.isNotEmpty(data)) {
       Gson gson = new Gson();
       ProUserAssign[] assigns = gson.fromJson(data, ProUserAssign[].class);
       for (ProUserAssign assign : assigns) {
         if (assign.getAssignId().longValue() == -1L) {
           assign.setAssignId(null);
         }
         this.proUserAssignService.save(assign);
       }
     }
 
     return "success";
   }

 }

