package com.xpsoft.oa.action.bbs;


import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.bbs.BbsUserProperty;
import com.xpsoft.oa.service.bbs.BbsUserPropertyService;
/**
 * 
 * @author 
 *
 */
public class BbsUserPropertyAction extends BaseAction{
	@Resource
	private BbsUserPropertyService bbsUserPropertyService;
	private BbsUserProperty bbsUserProperty;
	
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BbsUserProperty getBbsUserProperty() {
		return bbsUserProperty;
	}

	public void setBbsUserProperty(BbsUserProperty bbsUserProperty) {
		this.bbsUserProperty = bbsUserProperty;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<BbsUserProperty> list= bbsUserPropertyService.getAll(filter);
		
		Type type=new TypeToken<List<BbsUserProperty>>(){}.getType();
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
				bbsUserPropertyService.remove(new Long(id));
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
		BbsUserProperty bbsUserProperty=bbsUserPropertyService.get(userId);
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		if(bbsUserProperty!=null){
			sb.append(gson.toJson(bbsUserProperty));
		}else{
			sb.append("{}");
		}
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		bbsUserPropertyService.save(bbsUserProperty);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
