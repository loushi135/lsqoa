package com.xpsoft.oa.action.statistics;


import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.SalesProject;
import com.xpsoft.oa.service.statistics.SalesProjectService;
/**
 * 
 * @author 
 *
 */
public class SalesProjectAction extends BaseAction{
	@Resource
	private SalesProjectService salesProjectService;
	private SalesProject salesProject;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SalesProject getSalesProject() {
		return salesProject;
	}

	public void setSalesProject(SalesProject salesProject) {
		this.salesProject = salesProject;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<SalesProject> list= salesProjectService.getAll(filter);
		
		Type type=new TypeToken<List<SalesProject>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
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
				salesProjectService.remove(new Long(id));
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
		SalesProject salesProject=salesProjectService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(salesProject));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(salesProject.getId()==null){
			salesProject.setProNo(salesProjectService.getNewProNo(salesProject.getTeamDep().getDepName()));
		}
		salesProjectService.save(salesProject);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	public String checkProName(){
		String proName = getRequest().getParameter("proName");
		SalesProject salesProject = salesProjectService.getByProName(proName.trim());
		StringBuffer sb = new StringBuffer();
		if(salesProject == null){
			sb.append("{success:true}");
		}else{
			sb.append("{success:false}");
		}
		jsonString = sb.toString();
		return SUCCESS;
	}
}
