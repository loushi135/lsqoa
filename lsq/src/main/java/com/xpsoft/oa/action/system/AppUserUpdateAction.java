package com.xpsoft.oa.action.system;


import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.system.AppUserUpdate;
import com.xpsoft.oa.service.system.AppUserUpdateService;
/**
 * 
 * @author 
 *
 */
public class AppUserUpdateAction extends BaseAction{
	@Resource
	private AppUserUpdateService appUserUpdateService;
	private AppUserUpdate appUserUpdate;
	
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public AppUserUpdate getAppUserUpdate() {
		return appUserUpdate;
	}

	public void setAppUserUpdate(AppUserUpdate appUserUpdate) {
		this.appUserUpdate = appUserUpdate;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<AppUserUpdate> list= appUserUpdateService.getAll(filter);
		
		Type type=new TypeToken<List<AppUserUpdate>>(){}.getType();
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
				appUserUpdateService.remove(new Long(id));
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
		AppUserUpdate appUserUpdate=appUserUpdateService.get(userId);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(appUserUpdate));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		appUserUpdateService.save(appUserUpdate);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
