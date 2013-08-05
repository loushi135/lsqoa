package com.xpsoft.oa.action.statistics;


import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.statistics.ProjectSeal;
import com.xpsoft.oa.service.statistics.ProjectSealService;
/**
 * 
 * @author 
 *
 */
public class ProjectSealAction extends BaseAction{
	@Resource
	private ProjectSealService projectSealService;
	private ProjectSeal projectSeal;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProjectSeal getProjectSeal() {
		return projectSeal;
	}

	public void setProjectSeal(ProjectSeal projectSeal) {
		this.projectSeal = projectSeal;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<ProjectSeal> list= projectSealService.getAll(filter);
		
		Type type=new TypeToken<List<ProjectSeal>>(){}.getType();
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
				projectSealService.remove(new Long(id));
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
		ProjectSeal projectSeal=projectSealService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(projectSeal));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		projectSealService.save(projectSeal);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
