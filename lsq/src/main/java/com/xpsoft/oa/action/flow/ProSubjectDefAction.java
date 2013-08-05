package com.xpsoft.oa.action.flow;


import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.jbpm.pv.ParamField;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.flow.ProDefinition;
import com.xpsoft.oa.model.flow.ProOtherDef;
import com.xpsoft.oa.model.flow.ProSubjectDef;
import com.xpsoft.oa.service.flow.ProDefinitionService;
import com.xpsoft.oa.service.flow.ProOtherDefService;
import com.xpsoft.oa.service.flow.ProSubjectDefService;
/**
 * 
 * @author 
 *
 */
public class ProSubjectDefAction extends BaseAction{
	@Resource
	private ProSubjectDefService proSubjectDefService;
	private ProSubjectDef proSubjectDef;
	@Resource
	private ProDefinitionService definitionService;
	@Resource
	private ProOtherDefService proOtherDefService;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProSubjectDef getProSubjectDef() {
		return proSubjectDef;
	}

	public void setProSubjectDef(ProSubjectDef proSubjectDef) {
		this.proSubjectDef = proSubjectDef;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<ProSubjectDef> list= proSubjectDefService.getAll(filter);
		
		Type type=new TypeToken<List<ProSubjectDef>>(){}.getType();
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
				proSubjectDefService.remove(new Long(id));
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
		ProSubjectDef proSubjectDef=proSubjectDefService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(proSubjectDef));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	
	public String getDef(){
		ProSubjectDef proSubjectDef=proSubjectDefService.getByDefId(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(proSubjectDef));
		
		ProOtherDef  proOtherDef= proOtherDefService.get(id);
		
		if(null!=proOtherDef){
			sb.append(",printUserIds: '"+proOtherDef.getPrintUserIds()+"',printUserNames:'"+proOtherDef.getPrintUserNames()+"'");
		}
		
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	
	
	/**
	 * 添加及保存操作
	 */
	public String save(){
		proSubjectDefService.save(proSubjectDef);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	public String getFiled(){
		
		long defId=Long.valueOf(getRequest().getParameter("defId"));
		
		ProDefinition definition= definitionService.get(defId);
		
		Map<String, ParamField> map= ProcessActivityAssistant.constructFieldMap(definition.getName(), "开始");
		
		StringBuffer buf=new StringBuffer("{result:[");
		
		for(String key:map.keySet()){
			//去除不显示的
			if(map.get(key).getIsShowed()==1){
				buf.append("{name:'").append(key).append("',").append("label:'").append(map.get(key).getLabel()).append("'},");
			}
		}
		
		if (map.size() > 0) {
			buf.deleteCharAt(buf.length() - 1);
		}
		
		buf.append("]}");
		setJsonString(buf.toString());
		
		return SUCCESS;
	}
}
