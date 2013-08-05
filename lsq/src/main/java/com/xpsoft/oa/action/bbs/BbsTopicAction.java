package com.xpsoft.oa.action.bbs;


import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.StringUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.bbs.BbsTopic;
import com.xpsoft.oa.service.bbs.BbsTopicService;
/**
 * 
 * @author 
 *
 */
public class BbsTopicAction extends BaseAction{
	@Resource
	private BbsTopicService bbsTopicService;
	private BbsTopic bbsTopic;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BbsTopic getBbsTopic() {
		return bbsTopic;
	}

	public void setBbsTopic(BbsTopic bbsTopic) {
		this.bbsTopic = bbsTopic;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		QueryFilter filter=new QueryFilter(getRequest());
		filter.addSorted("topId", QueryFilter.ORDER_DESC);
		filter.addSorted("lastUpdateTime", QueryFilter.ORDER_DESC);
		List<BbsTopic> list= bbsTopicService.getAll(filter);
		Type type=new TypeToken<List<BbsTopic>>(){}.getType();
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
				bbsTopicService.remove(new Long(id));
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
		BbsTopic bbsTopic=bbsTopicService.get(id);
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bbsTopic));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(bbsTopic.getId()==null){
			  bbsTopic.setPublishTime(new Date());
			  bbsTopic.setLastUpdateTime(new Date());
			  bbsTopic.setViewCount(0);
			  bbsTopic.setTopId(new Long(0));
			  bbsTopic.setIsTop(0);
			  bbsTopic.setIsReply(0);
		}
		
		bbsTopicService.save(bbsTopic);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	public void viewCount(){
		BbsTopic bbsTopic=bbsTopicService.get(id);
		if(bbsTopic.getViewCount()!=null){
			bbsTopic.setViewCount(bbsTopic.getViewCount()+1);
		}else{
			bbsTopic.setViewCount(1);
		}
		bbsTopicService.save(bbsTopic);
	}
	public String top(){
		String ids[]=getRequest().getParameterValues("ids");
		bbsTopicService.topList(ids);
	    return SUCCESS;
	}
	public String indexView(){
		QueryFilter filter=new QueryFilter(getRequest());
		filter.addSorted("lastUpdateTime", QueryFilter.ORDER_DESC);
		List list= bbsTopicService.getAll(filter);
	    getRequest().setAttribute("topicList", list);
	    return "BbsView";
	}
}
