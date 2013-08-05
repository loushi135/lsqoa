 package com.xpsoft.oa.action.hrm;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.xpsoft.core.command.QueryFilter;
 import com.xpsoft.core.util.ContextUtil;
 import com.xpsoft.core.web.action.BaseAction;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.model.hrm.Resume;
 import com.xpsoft.oa.model.system.AppUser;
 import com.xpsoft.oa.model.system.FileAttach;
 import com.xpsoft.oa.service.hrm.ResumeService;
 import com.xpsoft.oa.service.system.FileAttachService;
 import java.lang.reflect.Type;
 import java.util.Date;
 import java.util.List;
 import java.util.Set;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 
 public class ResumeAction extends BaseAction
 {
 
   @Resource
   private ResumeService resumeService;
   private Resume resume;
 
   @Resource
   private FileAttachService fileAttachService;
   private Long resumeId;
 
   public Long getResumeId()
   {
     return this.resumeId;
   }
 
   public void setResumeId(Long resumeId) {
     this.resumeId = resumeId;
   }
 
   public Resume getResume() {
     return this.resume;
   }
 
   public void setResume(Resume resume) {
     this.resume = resume;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List<Resume> list = this.resumeService.getAll(filter);
 
     Type type = new TypeToken<List<Resume>>() {}.getType();
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
         this.resumeService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     Resume resume = (Resume)this.resumeService.get(this.resumeId);
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:[");
     sb.append(gson.toJson(resume));
     sb.append("]}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     String fileIds = getRequest().getParameter("fileIds");
     if (this.resume.getResumeId() == null) {
       AppUser appUser = ContextUtil.getCurrentUser();
       this.resume.setRegistor(appUser.getFullname());
       this.resume.setRegTime(new Date());
     }
     if (StringUtils.isNotEmpty(fileIds)) {
       this.resume.getResumeFiles().clear();
       String[] ids = fileIds.split(",");
       for (int i = 0; i < ids.length; i++) {
         FileAttach fileAttach = (FileAttach)this.fileAttachService.get(new Long(ids[i]));
         this.resume.getResumeFiles().add(fileAttach);
       }
     }
     this.resumeService.save(this.resume);
     setJsonString("{success:true}");
     return "success";
   }
 
   public String delphoto()
   {
     String strResumeId = getRequest().getParameter("resumeId");
     if (StringUtils.isNotEmpty(strResumeId)) {
       this.resume = ((Resume)this.resumeService.get(new Long(strResumeId)));
       this.resume.setPhoto("");
       this.resumeService.save(this.resume);
       setJsonString("{success:true}");
     }
     return "success";
   }
 }

