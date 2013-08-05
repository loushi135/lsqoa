package com.xpsoft.oa.action.statistics;


import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.DesignProject;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.service.statistics.DesignProjectService;
import com.xpsoft.oa.service.system.DepartmentService;
/**
 * 
 * @author 
 *
 */
public class DesignProjectAction extends BaseAction{
	@Resource
	private DesignProjectService designProjectService;
	@Resource
	private DepartmentService departmentService;
	private DesignProject designProject;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DesignProject getDesignProject() {
		return designProject;
	}

	public void setDesignProject(DesignProject designProject) {
		this.designProject = designProject;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<DesignProject> list= designProjectService.getAll(filter);
		
		Type type=new TypeToken<List<DesignProject>>(){}.getType();
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
				designProjectService.remove(new Long(id));
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
		DesignProject designProject=designProjectService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(designProject));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(designProject.getId()==null){//新增
			designProject.setProNo(designProjectService.getNewProNo());
		}
		String depIds = getRequest().getParameter("depIds");
		if(StringUtils.isNotBlank(depIds)){
			String[] depId = depIds.split(",");
			designProject.getDepts().clear();
			Set<Department> depts = new HashSet<Department>();
			for(String deptId : depId){
				depts.add(departmentService.get(Long.valueOf(deptId)));
			}
			designProject.getDepts().addAll(depts);
		}
		designProjectService.save(designProject);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	public String checkProName(){
		String proName = getRequest().getParameter("proName");
		DesignProject designProject = designProjectService.getByProName(proName.trim());
		StringBuffer sb = new StringBuffer();
		if(designProject == null){
			sb.append("{success:true}");
		}else{
			sb.append("{success:false}");
		}
		jsonString = sb.toString();
		return SUCCESS;
	}
}
