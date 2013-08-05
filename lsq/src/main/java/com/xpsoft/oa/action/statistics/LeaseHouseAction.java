package com.xpsoft.oa.action.statistics;


import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.LeaseHouse;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.service.statistics.LeaseHouseService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
/**
 * 
 * @author 
 *
 */
public class LeaseHouseAction extends BaseAction{
	@Resource
	private LeaseHouseService leaseHouseService;
	@Resource
	private ProjectNewService projectNewService;
	private LeaseHouse leaseHouse;
	
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LeaseHouse getLeaseHouse() {
		return leaseHouse;
	}

	public void setLeaseHouse(LeaseHouse leaseHouse) {
		this.leaseHouse = leaseHouse;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<LeaseHouse> list= leaseHouseService.getAll(filter);
		
		Type type=new TypeToken<List<LeaseHouse>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM").create();
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
				leaseHouseService.remove(new Long(id));
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
		LeaseHouse leaseHouse=leaseHouseService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(leaseHouse));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		String leaseEnd=getRequest().getParameter("leaseEnd");
		String leaseStart=getRequest().getParameter("leaseStart");
		String projectId=getRequest().getParameter("projectId");
		leaseHouse.setLeaseEnd(DateUtil.parse(leaseEnd,"yyyy-MM"));
		leaseHouse.setLeaseStart(DateUtil.parse(leaseStart,"yyyy-MM"));
		if(StringUtils.isNotBlank(projectId)){
			ProjectNew project= projectNewService.get(Long.valueOf(projectId));
			leaseHouse.setProject(project);
		}
		leaseHouseService.save(leaseHouse);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
