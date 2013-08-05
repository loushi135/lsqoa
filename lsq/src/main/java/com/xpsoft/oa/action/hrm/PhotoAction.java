package com.xpsoft.oa.action.hrm;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.StringUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.hrm.Photo;
import com.xpsoft.oa.model.hrm.PhotoFolder;
import com.xpsoft.oa.service.hrm.PhotoFolderService;
import com.xpsoft.oa.service.hrm.PhotoService;
/**
 * 
 * @author 
 *
 */
public class PhotoAction extends BaseAction{
	@Resource
	private PhotoService photoService;
	@Autowired
	private PhotoFolderService photoFolderService;
	private Photo photo;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		
		String folderId = (String)getRequest().getParameter("folderId");
		if(StringUtils.isNotBlank(folderId)&&!folderId.startsWith("xnode")){
			List<Long> idList = new ArrayList<Long>();
			PhotoFolder photoFolder =  photoFolderService.get(Long.valueOf(folderId));
			if(photoFolder!=null){
				getAllFolderIds(photoFolder,idList);
				if(idList.size()>1){
					String idParams = "";
					int i = 0;
					for(Long fId:idList){
						if(i==0){
							idParams = fId.toString();
						}else{
							idParams+=","+fId.toString();
						}
						i++;
					}
					filter.addFilter("Q_photoFolder.id_S_INL", idParams);//Q_photoFolder.id_S_INL 这里的S用成了L的话，查 出来的不正确
				}else{
					filter.addFilter("Q_photoFolder.id_L_EQ", idList.get(0).toString());
				}
			}
		}
		List<Photo> list= photoService.getAll(filter);
		
		Type type=new TypeToken<List<Photo>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	
	private void getAllFolderIds(PhotoFolder photoFolder,List<Long> idList){
			idList.add(photoFolder.getId());
			if(photoFolder.getChildFolders()!=null&&photoFolder.getChildFolders().size()>0){
				for(PhotoFolder pf:photoFolder.getChildFolders()){
						getAllFolderIds(pf,idList);
				}
			}
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				photoService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	public String singleDel(){
		if(id!=null){
			photoService.remove(id);
		}
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		Photo photo=photoService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(photo));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	
	public String update(){
		if(photo!=null){
			Photo p = photoService.get(photo.getId());
			p.setPhotoDesc(photo.getPhotoDesc());
			p.setPhotoName(photo.getPhotoName());
			photoService.save(p);
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		String folderId = getRequest().getParameter("folderId");
		String fileIds = getRequest().getParameter("fileIds");
		photoService.saveMultiPhoto(folderId,fileIds);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	public String imageView(){
		return "imageView";
	}
}
