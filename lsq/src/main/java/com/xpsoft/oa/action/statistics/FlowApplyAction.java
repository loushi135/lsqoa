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
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.FlowApply;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.statistics.FlowApplyService;
import com.xpsoft.oa.service.system.FileAttachService;
/**
 * 
 * @author 
 *
 */
public class FlowApplyAction extends BaseAction{
	@Resource
	private FlowApplyService flowApplyService;
	@Resource
	private FileAttachService fileAttachService;
	private FlowApply flowApply;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FlowApply getFlowApply() {
		return flowApply;
	}

	public void setFlowApply(FlowApply flowApply) {
		this.flowApply = flowApply;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<FlowApply> list= flowApplyService.getAll(filter);
		
		Type type=new TypeToken<List<FlowApply>>(){}.getType();
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
				flowApplyService.remove(new Long(id));
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
		FlowApply flowApply=flowApplyService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(flowApply));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		String attachIds = getRequest().getParameter("attachIds");
		if(StringUtils.isNotBlank(attachIds)){
			Set<FileAttach> fileSet = new HashSet<FileAttach>();
			String fileIds[] = attachIds.split(",");
			flowApply.getFileAttachs().clear();
			for(String fileId:fileIds){
				fileSet.add(fileAttachService.get(Long.valueOf(fileId)));
			}
			flowApply.getFileAttachs().addAll(fileSet);
		}
		flowApplyService.save(flowApply);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
