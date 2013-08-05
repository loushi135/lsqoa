package com.xpsoft.oa.action.hrm;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.hrm.PhotoFolder;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.hrm.PhotoFolderService;
/**
 * 
 * @author 
 *
 */
public class PhotoFolderAction extends BaseAction{
	@Resource
	private PhotoFolderService photoFolderService;
	private PhotoFolder photoFolder;
	@Expose
	protected AppUser user;
	private Long id;

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PhotoFolder getPhotoFolder() {
		return photoFolder;
	}

	public void setPhotoFolder(PhotoFolder photoFolder) {
		this.photoFolder = photoFolder;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		String opt=getRequest().getParameter("opt");
		StringBuffer buff = new StringBuffer();
		if(StringUtils.isNotEmpty(opt)){
			buff.append("[");
		}else{
			buff.append("[{id:'-1',text:'"+AppUtil.getCompanyName()+"',userId:'102',expanded:true,children:[");
		}
		List<PhotoFolder> firstLevelList = photoFolderService.findByParentId(0L);
		for(PhotoFolder photoFolder:firstLevelList){
			buff.append("{id:'"+photoFolder.getId()+"',text:'"+photoFolder.getFolderName()+"',userId:'"+photoFolder.getUser().getUserId()+"',");
		    buff.append(findChild(photoFolder.getId()));
		}
		if (!firstLevelList.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
	    }
		if(StringUtils.isNotEmpty(opt)){
		   buff.append("]");
		}else{
			buff.append("]}]");
		}
		setJsonString(buff.toString());
		return SUCCESS;
	}
	/**
	 * 寻找根节点
	 * @param depId
	 * @return
	 */
	public String findChild(Long depId){
		StringBuffer buff1=new StringBuffer("");
		List<PhotoFolder> list=photoFolderService.findByParentId(depId);
		if(list.size()==0){
			buff1.append("leaf:true},");
			return buff1.toString(); 
		}else {
			buff1.append("children:[");
			for(PhotoFolder photoFolder:list){
				buff1.append("{id:'"+photoFolder.getId()+"',text:'"+photoFolder.getFolderName()+"',userId:'"+photoFolder.getUser().getUserId()+"',");
				buff1.append(findChild(photoFolder.getId()));
			}
			buff1.deleteCharAt(buff1.length() - 1);
			buff1.append("]},");
			return buff1.toString();
		}
	}
	public String singleDel(){
		if(id!=null){
			PhotoFolder photoFolder = photoFolderService.get(id);
			photoFolderService.remove(photoFolder);
		}
		jsonString="{success:true}";
		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				photoFolderService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		PhotoFolder photoFolder=photoFolderService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(photoFolder));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(photoFolder.getId()==null){
			photoFolder.setUser(ContextUtil.getCurrentUser());
		}
		photoFolderService.save(photoFolder);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
