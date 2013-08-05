 package com.xpsoft.oa.action.flow;
 
 import com.xpsoft.core.web.action.BaseAction;
 import com.xpsoft.oa.model.flow.ProDefinition;
 import com.xpsoft.oa.service.flow.ProDefinitionService;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class ProcessDetailAction extends BaseAction
 {
 
   @Resource
   private ProDefinitionService proDefinitionService;
   private ProDefinition proDefinition;
 
   public ProDefinition getProDefinition()
   {
     return this.proDefinition;
   }
 
   public void setProDefinition(ProDefinition proDefinition) {
     this.proDefinition = proDefinition;
   }
 
   public String execute() throws Exception
   {
     String defId = getRequest().getParameter("defId");
     this.proDefinition = ((ProDefinition)this.proDefinitionService.get(new Long(defId)));
     return "success";
   }
 }

