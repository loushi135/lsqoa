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


import com.xpsoft.oa.model.statistics.LocalProductApplyInfo;
import com.xpsoft.oa.service.statistics.LocalProductApplyInfoService;
/**
 * 
 * @author 
 *
 */
public class LocalProductApplyInfoAction extends BaseAction{
	@Resource
	private LocalProductApplyInfoService localProductApplyInfoService;
	private LocalProductApplyInfo localProductApplyInfo;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalProductApplyInfo getLocalProductApplyInfo() {
		return localProductApplyInfo;
	}

	public void setLocalProductApplyInfo(LocalProductApplyInfo localProductApplyInfo) {
		this.localProductApplyInfo = localProductApplyInfo;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<LocalProductApplyInfo> list= localProductApplyInfoService.getAll(filter);
		
		Type type=new TypeToken<List<LocalProductApplyInfo>>(){}.getType();
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
				localProductApplyInfoService.remove(new Long(id));
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
		LocalProductApplyInfo localProductApplyInfo=localProductApplyInfoService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(localProductApplyInfo));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		localProductApplyInfoService.save(localProductApplyInfo);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
