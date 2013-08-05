package com.xpsoft.oa.action.statistics;


import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.TeamInStock;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.statistics.TeamInStockService;
import com.xpsoft.oa.service.system.FileAttachService;
/**
 * 
 * @author 
 *
 */
public class TeamInStockAction extends BaseAction{
	@Resource
	private TeamInStockService teamInStockService;
	private TeamInStock teamInStock;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TeamInStock getTeamInStock() {
		return teamInStock;
	}

	public void setTeamInStock(TeamInStock teamInStock) {
		this.teamInStock = teamInStock;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<TeamInStock> list= teamInStockService.getAll(filter);
		
		Type type=new TypeToken<List<TeamInStock>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();;
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
				teamInStockService.remove(new Long(id));
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
		TeamInStock teamInStock=teamInStockService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(teamInStock));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		FileAttachService fileAttachService = (FileAttachService)AppUtil.getBean("fileAttachService");
		String attachIds=getRequest().getParameter("teamInStock.attachIds");
		if(StringUtils.isNotBlank(attachIds)){
			Set<FileAttach> fileSet = new HashSet<FileAttach>();
			String fileIds[] = attachIds.split(",");
			teamInStock.getFileAttachs().clear();
			for(String fileId:fileIds){
				fileSet.add(fileAttachService.get(Long.valueOf(fileId)));
			}
			teamInStock.getFileAttachs().addAll(fileSet);
		}
		
		teamInStockService.save(teamInStock);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
