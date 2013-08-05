package com.xpsoft.oa.action.statistics;


import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.statistics.Helmetsoverallsapply;
import com.xpsoft.oa.service.statistics.HelmetsoverallsapplyService;
/**
 * 
 * @author 
 *
 */
public class HelmetsoverallsapplyAction extends BaseAction{
	@Resource
	private HelmetsoverallsapplyService helmetsoverallsapplyService;
	private Helmetsoverallsapply helmetsoverallsapply;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Helmetsoverallsapply getHelmetsoverallsapply() {
		return helmetsoverallsapply;
	}

	public void setHelmetsoverallsapply(Helmetsoverallsapply helmetsoverallsapply) {
		this.helmetsoverallsapply = helmetsoverallsapply;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<Helmetsoverallsapply> list= helmetsoverallsapplyService.getAll(filter);
		
		Type type=new TypeToken<List<Helmetsoverallsapply>>(){}.getType();
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
				helmetsoverallsapplyService.remove(new Long(id));
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
		Helmetsoverallsapply helmetsoverallsapply=helmetsoverallsapplyService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(helmetsoverallsapply));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		helmetsoverallsapplyService.save(helmetsoverallsapply);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
