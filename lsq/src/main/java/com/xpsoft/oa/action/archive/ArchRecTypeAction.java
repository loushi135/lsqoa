 package com.xpsoft.oa.action.archive;
 
 import java.util.List;

import javax.annotation.Resource;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.archive.ArchRecType;
import com.xpsoft.oa.service.archive.ArchRecTypeService;

import flexjson.JSONSerializer;
 
 public class ArchRecTypeAction extends BaseAction
 {
 
   @Resource
   private ArchRecTypeService archRecTypeService;
   private ArchRecType archRecType;
   private Long recTypeId;
 
   public Long getRecTypeId()
   {
     return this.recTypeId;
   }
 
   public void setRecTypeId(Long recTypeId) {
     this.recTypeId = recTypeId;
   }
 
   public ArchRecType getArchRecType() {
     return this.archRecType;
   }
 
   public void setArchRecType(ArchRecType archRecType) {
     this.archRecType = archRecType;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.archRecTypeService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     JSONSerializer serializer = new JSONSerializer();
     buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String combo()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List<ArchRecType> list = this.archRecTypeService.getAll(filter);
     StringBuffer sb = new StringBuffer("[");
     for (ArchRecType type : list) {
       sb.append("['").append(type.getRecTypeId()).append("','").append(type.getTypeName()).append("'],");
     }
     if (list.size() > 0) {
       sb.deleteCharAt(sb.length() - 1);
     }
     sb.append("]");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.archRecTypeService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     ArchRecType archRecType = (ArchRecType)this.archRecTypeService.get(this.recTypeId);
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
 
     JSONSerializer serializer = new JSONSerializer();
     sb.append(serializer.exclude(new String[] { "class", "department.class" }).serialize(archRecType));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.archRecTypeService.save(this.archRecType);
     setJsonString("{success:true}");
     return "success";
   }
 }

