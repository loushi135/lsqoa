package com.xpsoft.oa.action.statistics;


import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.ProjectPercentage;
import com.xpsoft.oa.service.statistics.ProjectPercentageService;
/**
 * 
 * @author 
 *
 */
public class ProjectPercentageAction extends BaseAction{
	@Resource
	private ProjectPercentageService projectPercentageService;
	private ProjectPercentage projectPercentage;
	
	private Long id;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProjectPercentage getProjectPercentage() {
		return projectPercentage;
	}

	public void setProjectPercentage(ProjectPercentage projectPercentage) {
		this.projectPercentage = projectPercentage;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<ProjectPercentage> list= projectPercentageService.getAll(filter);
		
		Type type=new TypeToken<List<ProjectPercentage>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
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
				projectPercentageService.remove(new Long(id));
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
		ProjectPercentage projectPercentage=projectPercentageService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		if(projectPercentage!=null){
			sb.append(gson.toJson(projectPercentage));
		}else{
			sb.append("''");
		}
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		projectPercentage.setCreatetime(new Date());
		projectPercentage.setCreateUser(ContextUtil.getCurrentUser());
		projectPercentageService.save(projectPercentage);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
