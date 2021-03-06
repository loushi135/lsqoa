 package com.xpsoft.oa.action.flow;
 
 import com.xpsoft.core.command.QueryFilter;
 import com.xpsoft.core.jbpm.jpdl.JpdlConverter;
 import com.xpsoft.core.util.BeanUtil;
 import com.xpsoft.core.util.JsonUtil;
 import com.xpsoft.core.web.action.BaseAction;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.model.flow.ProDefinition;
 import com.xpsoft.oa.model.flow.ProType;
 import com.xpsoft.oa.service.flow.JbpmService;
 import com.xpsoft.oa.service.flow.ProDefinitionService;
 import com.xpsoft.oa.service.flow.ProTypeService;
 import flexjson.JSONSerializer;
 import java.util.Date;
 import java.util.List;
 import java.util.UUID;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 
 public class ProDefinitionAction extends BaseAction
 {
 
   @Resource
   private ProDefinitionService proDefinitionService;
 
   @Resource
   private ProTypeService proTypeService;
 
   @Resource
   private JbpmService jbpmService;
   private ProDefinition proDefinition;
   private Long defId;
 
   public Long getDefId()
   {
     return this.defId;
   }
 
   public void setDefId(Long defId) {
     this.defId = defId;
   }
 
   public ProDefinition getProDefinition() {
     return this.proDefinition;
   }
 
   public void setProDefinition(ProDefinition proDefinition) {
     this.proDefinition = proDefinition;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_defId_L_NEQ", "110");
     filter.addFilter("Q_defId_L_NEQ", "109");
     filter.addFilter("Q_defId_L_NEQ", "85");
     String typeId = getRequest().getParameter("typeId");
 
     if ((StringUtils.isNotEmpty(typeId)) && (!"0".equals(typeId))) {
       filter.addFilter("Q_proType.typeId_L_EQ", typeId);
     }
 
     List list = this.proDefinitionService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "createtime" }).exclude(new String[] { "defXml" });
 
     buff.append(serializer.serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids)
       {
         this.jbpmService.doUnDeployProDefinition(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     if (this.defId != null) {
       this.proDefinition = ((ProDefinition)this.proDefinitionService.get(this.defId));
     } else {
       this.proDefinition = new ProDefinition();
       String proTypeId = getRequest().getParameter("proTypeId");
       if (StringUtils.isNotEmpty(proTypeId)) {
         ProType proType = (ProType)this.proTypeService.get(new Long(proTypeId));
         this.proDefinition.setProType(proType);
       }
 
     }
 
     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "createtime" });
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(serializer.serialize(this.proDefinition));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
   
   
   public String getByDefName(){
	   
	   String defName=getRequest().getParameter("defName");
	   if(StringUtils.isNotEmpty(defName)){
		   this.proDefinition =proDefinitionService.getByName(defName);
	   }
	   
	   JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "createtime" });
	   
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(serializer.serialize(this.proDefinition));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
	   
   }
 
   public String defSave() {
     this.logger.info("...eneter defSave......");
 
     if (StringUtils.isNotEmpty(this.proDefinition.getDrawDefXml()))
     {
       Long uuid = Long.valueOf(Math.abs(UUID.randomUUID().getLeastSignificantBits()));
 
       String defXml = JpdlConverter.JpdlGen(this.proDefinition.getDrawDefXml(), "pd" + uuid);
 
       defXml = defXml.replace("<process", "<process xmlns=\"http://jbpm.org/4.0/jpdl\"");
 
       if (this.logger.isDebugEnabled()) {
         this.logger.debug("jbpmXml:" + defXml);
       }
 
       this.proDefinition.setDefXml(defXml);
 
       save();
     }
 
     return "success";
   }
 
   public String save()
   {
     Long proTypeId = this.proDefinition.getProTypeId();
     if (proTypeId != null) {
       ProType proType = (ProType)this.proTypeService.get(proTypeId);
       this.proDefinition.setProType(proType);
     }
     if (this.proDefinition.getDefId() != null) {
       ProDefinition proDef = (ProDefinition)this.proDefinitionService.get(this.proDefinition.getDefId());
       try {
         BeanUtil.copyNotNullProperties(proDef, this.proDefinition);
         this.jbpmService.saveOrUpdateDeploy(proDef);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     } else {
       this.proDefinition.setCreatetime(new Date());
 
       if (this.logger.isDebugEnabled()) {
         this.logger.info("---start deploy---");
       }
 
       this.jbpmService.saveOrUpdateDeploy(this.proDefinition);
     }
     setJsonString("{success:true}");
     return "success";
   }
 }