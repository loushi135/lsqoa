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
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.EndInnerCheck;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.statistics.EndInnerCheckService;
import com.xpsoft.oa.service.system.FileAttachService;
/**
 * 
 * @author 
 *
 */
public class EndInnerCheckAction extends BaseAction{
	@Resource
	private EndInnerCheckService endInnerCheckService;
	@Resource
	private FileAttachService fileAttachService;
	private EndInnerCheck endInnerCheck;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EndInnerCheck getEndInnerCheck() {
		return endInnerCheck;
	}

	public void setEndInnerCheck(EndInnerCheck endInnerCheck) {
		this.endInnerCheck = endInnerCheck;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<EndInnerCheck> list= endInnerCheckService.getAll(filter);
		
		Type type=new TypeToken<List<EndInnerCheck>>(){}.getType();
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
				endInnerCheckService.remove(new Long(id));
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
		EndInnerCheck endInnerCheck=endInnerCheckService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(endInnerCheck));
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
			endInnerCheck.getFileAttachs().clear();
			for(String fileId:fileIds){
				fileSet.add(fileAttachService.get(Long.valueOf(fileId)));
			}
			endInnerCheck.getFileAttachs().addAll(fileSet);
		}
		endInnerCheckService.save(endInnerCheck);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
