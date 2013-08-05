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
import com.xpsoft.oa.model.statistics.Prequalificareport;
import com.xpsoft.oa.service.statistics.PrequalificareportService;
/**
 * 
 * @author 
 *
 */
public class PrequalificareportAction extends BaseAction{
	@Resource
	private PrequalificareportService prequalificareportService;
	private Prequalificareport prequalificareport;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Prequalificareport getPrequalificareport() {
		return prequalificareport;
	}

	public void setPrequalificareport(Prequalificareport prequalificareport) {
		this.prequalificareport = prequalificareport;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<Prequalificareport> list= prequalificareportService.getAll(filter);
		
		Type type=new TypeToken<List<Prequalificareport>>(){}.getType();
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
				prequalificareportService.remove(new Long(id));
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
		Prequalificareport prequalificareport=prequalificareportService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(prequalificareport));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(prequalificareport.getMaterialFitDept().getDepId()==0){
			prequalificareport.setMaterialFitDept(null);
		}
		prequalificareportService.save(prequalificareport);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
