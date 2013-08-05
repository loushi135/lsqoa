 package com.xpsoft.oa.action.customer;
 
 import com.xpsoft.core.command.QueryFilter;
 import com.xpsoft.core.util.ContextUtil;
 import com.xpsoft.core.util.JsonUtil;
 import com.xpsoft.core.web.action.BaseAction;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.model.customer.CusConnection;
 import com.xpsoft.oa.model.system.AppUser;
 import com.xpsoft.oa.service.customer.CusConnectionService;
 import flexjson.JSONSerializer;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class CusConnectionAction extends BaseAction
 {
 
   @Resource
   private CusConnectionService cusConnectionService;
   private CusConnection cusConnection;
   private Long connId;
 
   public Long getConnId()
   {
     return this.connId;
   }
 
   public void setConnId(Long connId) {
     this.connId = connId;
   }
 
   public CusConnection getCusConnection() {
     return this.cusConnection;
   }
 
   public void setCusConnection(CusConnection cusConnection) {
     this.cusConnection = cusConnection;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.cusConnectionService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "startDate", "endDate" });
     buff.append(json.exclude(new String[] { "class" }).serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.cusConnectionService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     CusConnection cusConnection = (CusConnection)this.cusConnectionService.get(this.connId);
 
     JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "startDate", "endDate" });
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(json.exclude(new String[] { "class" }).serialize(cusConnection));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     boolean pass = false;
     StringBuffer buff = new StringBuffer("{");
     if (this.cusConnection.getStartDate().getTime() < this.cusConnection.getEndDate().getTime())
       pass = true;
     else buff.append("msg:'交往结束日期不能早于开始日期!',");
 
     if (pass) {
       this.cusConnection.setCreator(ContextUtil.getCurrentUser().getFullname());
       this.cusConnectionService.save(this.cusConnection);
       buff.append("success:true}");
     } else {
       buff.append("failure:true}");
     }
     setJsonString(buff.toString());
     return "success";
   }
 }

