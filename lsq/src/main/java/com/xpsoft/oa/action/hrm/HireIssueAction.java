 package com.xpsoft.oa.action.hrm;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.xpsoft.core.command.QueryFilter;
 import com.xpsoft.core.util.ContextUtil;
 import com.xpsoft.core.web.action.BaseAction;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.model.hrm.HireIssue;
 import com.xpsoft.oa.model.system.AppUser;
 import com.xpsoft.oa.service.hrm.HireIssueService;
 import java.lang.reflect.Type;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 
 public class HireIssueAction extends BaseAction
 {
 
   @Resource
   private HireIssueService hireIssueService;
   private HireIssue hireIssue;
   private Long hireId;
 
   public Long getHireId()
   {
     return this.hireId;
   }
 
   public void setHireId(Long hireId) {
     this.hireId = hireId;
   }
 
   public HireIssue getHireIssue() {
     return this.hireIssue;
   }
 
   public void setHireIssue(HireIssue hireIssue) {
     this.hireIssue = hireIssue;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List<HireIssue> list = this.hireIssueService.getAll(filter);
 
     Type type = new TypeToken<List<HireIssue>>() {}.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
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
         this.hireIssueService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     HireIssue hireIssue = (HireIssue)this.hireIssueService.get(this.hireId);
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:[");
     sb.append(gson.toJson(hireIssue));
     sb.append("]}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     AppUser user = ContextUtil.getCurrentUser();
     if (this.hireIssue.getHireId() == null) {
       this.hireIssue.setRegFullname(user.getFullname());
       this.hireIssue.setRegDate(new Date());
     } else {
       this.hireIssue.setModifyFullname(user.getFullname());
       this.hireIssue.setModifyDate(new Date());
     }
     this.hireIssue.setStatus(HireIssue.NOTYETPASS_CHECK);
     this.hireIssueService.save(this.hireIssue);
     setJsonString("{success:true}");
     return "success";
   }
 
   public String load()
   {
     String strHireId = getRequest().getParameter("hireId");
     if (StringUtils.isNotEmpty(strHireId)) {
       this.hireIssue = ((HireIssue)this.hireIssueService.get(new Long(strHireId)));
     }
     return "load";
   }
 
   public String check()
   {
     String status = getRequest().getParameter("status");
     String strHireId = getRequest().getParameter("hireId");
     String checkOpinion = getRequest().getParameter("checkOpinion");
     if (StringUtils.isNotEmpty(strHireId)) {
       AppUser appUser = ContextUtil.getCurrentUser();
       this.hireIssue = ((HireIssue)this.hireIssueService.get(new Long(strHireId)));
       this.hireIssue.setCheckFullname(appUser.getFullname());
       this.hireIssue.setCheckDate(new Date());
       this.hireIssue.setCheckOpinion(checkOpinion);
       if (StringUtils.isNotEmpty(status)) {
         this.hireIssue.setStatus(Short.valueOf(status));
         this.hireIssueService.save(this.hireIssue);
         setJsonString("{success:true}");
       } else {
         setJsonString("{success:false}");
       }
     } else {
       setJsonString("{success:false}");
     }
     return "success";
   }
 }

