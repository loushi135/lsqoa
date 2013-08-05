 package com.xpsoft.oa.action.system;
 
 import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.system.FileAttachService;
 
 public class FileAttachAction extends BaseAction
 {
 
   @Resource
   private FileAttachService fileAttachService;
   private FileAttach fileAttach;
   private Long fileId;
   private String filePath;
 
   public Long getFileId()
   {
     return this.fileId;
   }
 
   public void setFileId(Long fileId) {
     this.fileId = fileId;
   }
 
   public FileAttach getFileAttach() {
     return this.fileAttach;
   }
 
   public void setFileAttach(FileAttach fileAttach) {
     this.fileAttach = fileAttach;
   }
 
   public String getFilePath() {
     return this.filePath;
   }
 
   public void setFilePath(String filePath) {
     this.filePath = filePath;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List<FileAttach> list = this.fileAttachService.getAll(filter);
 
     Type type = new TypeToken<List<FileAttach>>() {}.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
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
         this.fileAttachService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     FileAttach fileAttach = (FileAttach)this.fileAttachService.get(this.fileId);
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(fileAttach));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.fileAttachService.save(this.fileAttach);
     setJsonString("{success:true}");
     return "success";
   }
 
   public String delete()
   {
     this.fileAttachService.removeByPath(this.filePath);
     return "success";
   }
   public String fileList(){
	   	String ids=getRequest().getParameter("fileIds");
	    List<FileAttach> list=fileAttachService.getFileAttachById(ids);
	    Type type = new TypeToken<List<FileAttach>>(){}.getType();
	     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
	     .append(list.size()).append(",result:");
	     Gson gson = new Gson();
	     buff.append(gson.toJson(list, type));
	     buff.append("}");
	     this.jsonString = buff.toString();
         return "success";
   }
   
   public String downloadTemplate() throws Exception{
	   String fileName = new String(getRequest().getParameter("fileName").getBytes("ISO-8859-1"), "UTF-8");
	// path是指欲下载的文件的路径。
	   ServletContext context = ServletActionContext.getServletContext(); 
	   String path = context.getRealPath(File.separator+"report"+File.separator)+File.separator+fileName;  //  报表根路径
       File file = new File(path);
       // 取得文件的后缀名。
       //String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
       // 以流的形式下载文件。
       InputStream fis = new BufferedInputStream(new FileInputStream(file));
       byte[] buffer = new byte[fis.available()];
       fis.read(buffer);
       fis.close();
       // 清空response
       getResponse().reset();
       // 设置response的Header
       getResponse().addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"),"ISO-8859-1"));
       getResponse().addHeader("Content-Length", "" + file.length());
       OutputStream toClient = new BufferedOutputStream(getResponse().getOutputStream());
       getResponse().setContentType("application/octet-stream");
       toClient.write(buffer);
       toClient.flush();
       toClient.close();
	   return SUCCESS;
   }
 }
