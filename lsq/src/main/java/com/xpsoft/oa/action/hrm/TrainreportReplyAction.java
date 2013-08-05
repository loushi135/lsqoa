package com.xpsoft.oa.action.hrm;


import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.hrm.TrainreportReply;
import com.xpsoft.oa.service.hrm.TrainreportReplyService;
/**
 * 
 * @author 
 *
 */
public class TrainreportReplyAction extends BaseAction{
	@Resource
	private TrainreportReplyService trainreportReplyService;
	private TrainreportReply trainreportReply;
	
	private Long replyId;

	public Long getReplyId() {
		return replyId;
	}

	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	public TrainreportReply getTrainreportReply() {
		return trainreportReply;
	}

	public void setTrainreportReply(TrainreportReply trainreportReply) {
		this.trainreportReply = trainreportReply;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<TrainreportReply> list= trainreportReplyService.getAll(filter);
		
		Type type=new TypeToken<List<TrainreportReply>>(){}.getType();
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
				trainreportReplyService.remove(new Long(id));
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
		TrainreportReply trainreportReply=trainreportReplyService.get(replyId);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(trainreportReply));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(trainreportReply.getReplyId() == null){
			trainreportReply.setCreateDate(new Date());
		}
		trainreportReplyService.save(trainreportReply);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
