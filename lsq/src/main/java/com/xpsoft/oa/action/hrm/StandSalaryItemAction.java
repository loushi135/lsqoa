 package com.xpsoft.oa.action.hrm;
 
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;
 import com.xpsoft.core.command.QueryFilter;
 import com.xpsoft.core.web.action.BaseAction;
 import com.xpsoft.oa.model.hrm.StandSalaryItem;
 import com.xpsoft.oa.service.hrm.StandSalaryItemService;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class StandSalaryItemAction extends BaseAction
 {
 
   @Resource
   private StandSalaryItemService standSalaryItemService;
   private StandSalaryItem standSalaryItem;
   private Long itemId;
   private Long standardId;
 
   public Long getStandardId()
   {
     return this.standardId;
   }
 
   public void setStandardId(Long standardId) {
     this.standardId = standardId;
   }
 
   public Long getItemId() {
     return this.itemId;
   }
 
   public void setItemId(Long itemId) {
     this.itemId = itemId;
   }
 
   public StandSalaryItem getStandSalaryItem() {
     return this.standSalaryItem;
   }
 
   public void setStandSalaryItem(StandSalaryItem standSalaryItem) {
     this.standSalaryItem = standSalaryItem;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List<StandSalaryItem> list = null;
     if (this.standardId != null) {
       list = this.standSalaryItemService.getAllByStandardId(this.standardId);
     }
     Type type = new TypeToken<List<StandSalaryItem>>() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(list.size()).append(",result:");
 
     Gson gson = new Gson();
     buff.append(gson.toJson(list, type));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.standSalaryItemService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     StandSalaryItem standSalaryItem = (StandSalaryItem)this.standSalaryItemService.get(this.itemId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(standSalaryItem));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.standSalaryItemService.save(this.standSalaryItem);
     setJsonString("{success:true}");
     return "success";
   }
 }

