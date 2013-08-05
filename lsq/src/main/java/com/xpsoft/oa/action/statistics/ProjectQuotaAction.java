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
import com.xpsoft.oa.model.statistics.ProjectQuota;
import com.xpsoft.oa.service.statistics.ProjectQuotaService;
/**
 * 
 * @author 
 *
 */
public class ProjectQuotaAction extends BaseAction{
	@Resource
	private ProjectQuotaService projectQuotaService;
	private ProjectQuota projectQuota;
	
	private Long proId;

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public ProjectQuota getProjectQuota() {
		return projectQuota;
	}

	public void setProjectQuota(ProjectQuota projectQuota) {
		this.projectQuota = projectQuota;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<ProjectQuota> list= projectQuotaService.getAll(filter);
		
		Type type=new TypeToken<List<ProjectQuota>>(){}.getType();
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
				projectQuotaService.remove(new Long(id));
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
		ProjectQuota projectQuota=projectQuotaService.get(proId);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		if(projectQuota!=null){
			sb.append(gson.toJson(projectQuota));
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
		projectQuota.setCreatetime(new Date());
		projectQuota.setCreateUser(ContextUtil.getCurrentUser());
		projectQuotaService.save(projectQuota);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
