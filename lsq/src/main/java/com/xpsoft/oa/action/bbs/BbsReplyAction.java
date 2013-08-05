package com.xpsoft.oa.action.bbs;


import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.bbs.BbsModel;
import com.xpsoft.oa.model.bbs.BbsReply;
import com.xpsoft.oa.model.bbs.BbsTopic;
import com.xpsoft.oa.model.bbs.BbsUserProperty;
import com.xpsoft.oa.service.bbs.BbsReplyService;
import com.xpsoft.oa.service.bbs.BbsTopicService;
import com.xpsoft.oa.service.bbs.BbsUserPropertyService;
/**
 * 
 * @author 
 *
 */
public class BbsReplyAction extends BaseAction{
	@Resource
	private BbsReplyService bbsReplyService;
	private BbsReply bbsReply;
	private BbsTopic topic;
	@Resource
	private BbsTopicService bbsTopicService;
	private BbsUserProperty bbsUserProperty;
	@Resource
	private BbsUserPropertyService bbsUserPropertyService;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BbsReply getBbsReply() {
		return bbsReply;
	}

	public void setBbsReply(BbsReply bbsReply) {
		this.bbsReply = bbsReply;
	}
	

	/**
	 * 显示列表
	 */
	public String list(){
		String id=getRequest().getParameter("topicId");
		topic = bbsTopicService.get(new Long(id));
		QueryFilter filter=new QueryFilter(getRequest());
		List<BbsModel> modelList = new ArrayList<BbsModel>();
		Integer start = Integer.valueOf(getRequest().getParameter("start"));
		if(start.equals(0)){//start=0表示第一页， 那么首条记录为 主题内容， start!=0表示 全为回复记录
			BbsModel bbsModel = new BbsModel();
			bbsModel.setButtonType("display:none");
			bbsModel.setId(topic.getId().toString());
			bbsModel.setContent(topic.getContent());
			bbsModel.setFullname(topic.getUser().getFullname());
			bbsModel.setUsername(topic.getUser().getUsername());
			bbsModel.setAccessionTime(topic.getUser().getAccessionTime()==null?"":new SimpleDateFormat("yyyy-MM-dd").format(topic.getUser().getAccessionTime()));
			bbsModel.setPublishTime(topic.getPublishTime()==null?"":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(topic.getPublishTime()));
			if(topic.getUser().getPhoto()==null||topic.getUser().getPhoto()==""){
			 bbsModel.setPhoto("/images/default_image_male.jpg");
			}else{
				 bbsModel.setPhoto("/attachFiles/"+topic.getUser().getPhoto());
			}
			Long userId=topic.getUser().getUserId();
			bbsUserProperty=bbsUserPropertyService.get(userId);
			if(bbsUserProperty==null){
				bbsModel.setDescription(" ");
			}else{
				bbsModel.setDescription(bbsUserProperty.getDescription());
			}
			PagingBean pagingBean = filter.getPagingBean();
			pagingBean.setPageSize(pagingBean.getPageSize()-1);//查询剩下的回复记录
			modelList.add(bbsModel);
		}
		filter.addFilter("Q_topic.id_L_EQ",id);
		List<BbsReply> list= bbsReplyService.getAll(filter);
		for(BbsReply reply:list){
			BbsModel bbsModel = new BbsModel();
			bbsModel.setButtonType("");
			bbsModel.setId(reply.getId().toString());
			bbsModel.setContent(reply.getReplyContent());
			bbsModel.setFullname(reply.getReplyUser().getFullname());
			bbsModel.setUsername(reply.getReplyUser().getUsername());
			bbsModel.setAccessionTime(reply.getReplyUser().getAccessionTime()==null?"":new SimpleDateFormat("yyyy-MM-dd").format(reply.getReplyUser().getAccessionTime()));
			bbsModel.setPublishTime(reply.getReplyTime()==null?"":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(reply.getReplyTime()));
			if(reply.getReplyUser().getPhoto()==null||reply.getReplyUser().getPhoto()==""){
				 bbsModel.setPhoto("/images/default_image_male.jpg");
				}else{
					bbsModel.setPhoto("/attachFiles/"+reply.getReplyUser().getPhoto());
				}
			Long userId=reply.getReplyUser().getUserId();
			bbsUserProperty=bbsUserPropertyService.get(userId);
			if(bbsUserProperty==null){
				bbsModel.setDescription(" ");
			}else{
				bbsModel.setDescription(bbsUserProperty.getDescription());
			}
			
			modelList.add(bbsModel);
		}
		Type type=new TypeToken<List<BbsModel>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(modelList, type));
		buff.append("}");
		
		jsonString=buff.toString();
		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		String id=getRequest().getParameter("id");
		bbsReply=bbsReplyService.get(new Long(id));
	    Long userId=bbsReply.getReplyUser().getUserId();
	    Long CurrentUserId=ContextUtil.getCurrentUserId();
		if(CurrentUserId.equals(userId)){
			bbsReplyService.remove(new Long(id));
			jsonString="{success:true}";
		}else{
			jsonString="{success:false}";
		}
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		BbsReply bbsReply=bbsReplyService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bbsReply));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(bbsReply.getTopic().getId()!=null){
			topic=bbsTopicService.get(bbsReply.getTopic().getId());
			Integer reply=topic.getIsReply();
			reply+=1;
			topic.setIsReply(reply);
			topic.setLastUpdateTime(new Date());
			bbsTopicService.save(topic);
			bbsReply.setTopic(topic);
			bbsReply.setReplyTime(new Date());
			bbsReplyService.save(bbsReply);
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}

}
