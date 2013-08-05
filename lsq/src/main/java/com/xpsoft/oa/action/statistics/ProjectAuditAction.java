package com.xpsoft.oa.action.statistics;


import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.statistics.ProjectAudit;
import com.xpsoft.oa.service.statistics.ProjectAuditService;
/**
 * 
 * @author 
 *
 */
public class ProjectAuditAction extends BaseAction{
	@Resource
	private ProjectAuditService projectAuditService;
	private ProjectAudit projectAudit;
	
	private Long proId;

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}
	public ProjectAudit getProjectAudit() {
		return projectAudit;
	}

	public void setProjectAudit(ProjectAudit projectAudit) {
		this.projectAudit = projectAudit;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<ProjectAudit> list= projectAuditService.getAll(filter);
		
		Type type=new TypeToken<List<ProjectAudit>>(){}.getType();
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
				projectAuditService.remove(new Long(id));
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
		ProjectAudit projectAudit=projectAuditService.get(proId);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		if(projectAudit!=null){
			sb.append(gson.toJson(projectAudit));
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
		projectAudit.setCreatetime(new Date());
		projectAudit.setCreateUser(ContextUtil.getCurrentUser());
		projectAuditService.save(projectAudit);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
