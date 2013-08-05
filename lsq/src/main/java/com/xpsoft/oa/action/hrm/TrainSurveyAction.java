package com.xpsoft.oa.action.hrm;


import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.hrm.TrainSurvey;
import com.xpsoft.oa.service.hrm.TrainSurveyCourseUserService;
import com.xpsoft.oa.service.hrm.TrainSurveyService;
/**
 * 
 * @author 
 *
 */
public class TrainSurveyAction extends BaseAction{
	@Resource
	private TrainSurveyService trainSurveyService;
	@Resource
	private TrainSurveyCourseUserService trainSurveyCourseUserService;
	private TrainSurvey trainSurvey;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrainSurvey getTrainSurvey() {
		return trainSurvey;
	}

	public void setTrainSurvey(TrainSurvey trainSurvey) {
		this.trainSurvey = trainSurvey;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<TrainSurvey> list= trainSurveyService.getAll(filter);
		
		Type type=new TypeToken<List<TrainSurvey>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_FULL).excludeFieldsWithoutExposeAnnotation().create();
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
		trainSurveyService.removeTrainSurvey(ids);
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		TrainSurvey trainSurvey=trainSurveyService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_FULL).excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(trainSurvey));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		String courseIds = getRequest().getParameter("courseIds");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("trainSurvey",trainSurvey);
		map.put("courseIds",courseIds);
		trainSurveyService.saveTrainSurvey(map);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	public String queryTrainSurCo(){
		String trainSurveyId = getRequest().getParameter("trainSurveyId");
		List<Object> list = trainSurveyCourseUserService.queryTrainSurveyCourse(Long.parseLong(trainSurveyId),ContextUtil.getCurrentUserId());
		StringBuffer buff = new StringBuffer("{success:true,'result':[");
		for (int i = 0; i < list.size(); i++) {
			if(i>0){
				buff.append(",");
			}
			Object[] objs = (Object[])list.get(i);
			buff.append("{\"courseId\":\""+objs[0]+"\",");
			buff.append("\"courseNo\":\""+objs[1]+"\",");
			buff.append("\"courseName\":\""+objs[2]+"\",");
			buff.append("\"interest\":\""+objs[3]+"\"}");
		}
		buff.append("]}");
		jsonString=buff.toString();
		return SUCCESS;
	}
	public String updateVote(){
		String trainSurveyId = getRequest().getParameter("trainSurveyId");
		String trainSurveyUserId = getRequest().getParameter("trainSurveyUserId");
		String[] trainCourseIds = getRequest().getParameterValues("trainCourseIds");
		String userId = getRequest().getParameter("userId");
		trainSurveyCourseUserService.updateVote(Long.parseLong(trainSurveyId),Long.parseLong(trainSurveyUserId), trainCourseIds, Long.parseLong(userId));
		return SUCCESS;
	}
	public String viewVoteResult(){
		String trainSurveyId = getRequest().getParameter("trainSurveyId");
		List<Object> list = trainSurveyCourseUserService.viewVoteResult(Long.parseLong(trainSurveyId));
		StringBuffer buff = new StringBuffer("{success:true,'result':[");
		for (int i = 0; i < list.size(); i++) {
			if(i>0){
				buff.append(",");
			}
			Object[] objs = (Object[])list.get(i);
			buff.append("{\"courseId\":\""+objs[0]+"\",");
			buff.append("\"courseNo\":\""+objs[1]+"\",");
			buff.append("\"courseName\":\""+objs[2]+"\",");
			buff.append("\"interestYes\":\""+objs[3]+"\",");
			buff.append("\"interestNo\":\""+objs[4]+"\"}");
		}
		buff.append("]}");
		jsonString=buff.toString();
		return SUCCESS;
	}
	public String batchGen(){
		Map<String, Object> map = new HashMap<String, Object>();
		String trainSurveyId = getRequest().getParameter("trainSurveyId");
		String year = getRequest().getParameter("year");
		String[] courseIds = getRequest().getParameterValues("courseIds");
		map.put("trainSurveyId", trainSurveyId);
		map.put("courseIds", courseIds);
		map.put("year", year);
		trainSurveyCourseUserService.saveTrainPlanByTrainSurveyCourseUser(map);
		return SUCCESS;
	}
	public String check(){
		String msg = "yes";
		String trainSurveyId = getRequest().getParameter("trainSurveyId");
		trainSurvey = trainSurveyService.get(Long.parseLong(trainSurveyId));
		if(trainSurvey.getEndTime() != null){
			if(trainSurvey.getEndTime().compareTo(new Date())<0){
				msg = "no";
			}
		}
		setJsonString("{success:true,msg:'"+msg+"'}");
		return SUCCESS;
	}
}
