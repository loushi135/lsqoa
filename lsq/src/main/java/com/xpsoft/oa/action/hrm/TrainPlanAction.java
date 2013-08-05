package com.xpsoft.oa.action.hrm;


import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.SendSmsUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.hrm.TrainApply;
import com.xpsoft.oa.model.hrm.TrainPlan;
import com.xpsoft.oa.service.hrm.TrainApplyService;
import com.xpsoft.oa.service.hrm.TrainPlanService;
/**
 * 
 * @author 
 *
 */
public class TrainPlanAction extends BaseAction{
	@Resource
	private TrainPlanService trainPlanService;
	@Resource
	private TrainApplyService trainApplyService;
	private TrainPlan trainPlan;
	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrainPlan getTrainPlan() {
		return trainPlan;
	}

	public void setTrainPlan(TrainPlan trainPlan) {
		this.trainPlan = trainPlan;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<TrainPlan> list= trainPlanService.getAll(filter);
		Type type=new TypeToken<List<TrainPlan>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_FULL).create();
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
			trainPlanService.removeTrainPlan(ids);;
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		TrainPlan trainPlan=trainPlanService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_FULL).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(trainPlan));
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		String trainApplyId = getRequest().getParameter("trainApplyId");
		if(StringUtils.isNotBlank(trainApplyId)){
			TrainApply trainApply = trainApplyService.get(Long.parseLong(trainApplyId));
			trainApply.setPublish(TrainApply.PUBLISH);
			trainApplyService.merge(trainApply);
		}
		String sendMsgFlag = getRequest().getParameter("sendMsgFlag");
		String userId = getRequest().getParameter("userId");
		trainPlanService.saveTrainPlan(trainPlan,sendMsgFlag,userId);
		return SUCCESS;
	}
	/**更新参与状态
	 * /
	 */
	public String cancel(){
		String reason=getRequest().getParameter("reason");
		String id=getRequest().getParameter("id");
		if(!StringUtils.isEmpty(id)){
			TrainPlan trainPlan=trainPlanService.get(Long.valueOf(id));
            trainPlan.setTrainStatus(TrainPlan.CANCEL);	
            trainPlan.setCancelReason(reason);
            trainPlanService.save(trainPlan);
		}
		return SUCCESS;
	}
	public String sendMsg(){
		String courseName = getRequest().getParameter("courseName");
		String coursePlace = getRequest().getParameter("coursePlace");
		String courseTime = getRequest().getParameter("courseTime");
		String psContext = getRequest().getParameter("psContext");
		StringBuffer msgcontent = new StringBuffer("提醒：您报名参加的《").
		append(courseName).
		append("》培训，将于").
		append(courseTime).
		append("在").
		append(coursePlace).
		append("准时开课，请各位佩带胸卡，提前10分钟进入会场，并注意保持会场纪律，谢谢配合！").
		append(psContext).
		append("【人力资源部】 ");
		String[] userIds = getRequest().getParameterValues("userIds");
		StringBuffer userId = new StringBuffer();
		for (String string : userIds) {
			userId.append(string);
			userId.append(",");
		}
		System.out.println(msgcontent.toString());
		SendSmsUtil.sendMessage(userId.toString(), msgcontent.toString());
		return SUCCESS;
	}
}
