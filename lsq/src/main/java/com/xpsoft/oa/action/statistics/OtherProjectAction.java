package com.xpsoft.oa.action.statistics;


import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.OtherProject;
import com.xpsoft.oa.service.statistics.OtherProjectService;
/**
 * 
 * @author 
 *
 */
public class OtherProjectAction extends BaseAction{
	@Resource
	private OtherProjectService otherProjectService;
	private OtherProject otherProject;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OtherProject getOtherProject() {
		return otherProject;
	}

	public void setOtherProject(OtherProject otherProject) {
		this.otherProject = otherProject;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<OtherProject> list= otherProjectService.getAll(filter);
		
		Type type=new TypeToken<List<OtherProject>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
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
				otherProjectService.remove(new Long(id));
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
		OtherProject otherProject=otherProjectService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(otherProject));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(otherProject.getId()==null){
			otherProject.setProNo(otherProjectService.getNewProNo());
		}
		otherProjectService.save(otherProject);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	public String checkProName(){
		String proName = getRequest().getParameter("proName");
		OtherProject otherProject = otherProjectService.getByProName(proName.trim());
		StringBuffer sb = new StringBuffer();
		if(otherProject == null){
			sb.append("{success:true}");
		}else{
			sb.append("{success:false}");
		}
		jsonString = sb.toString();
		return SUCCESS;
	}
}
