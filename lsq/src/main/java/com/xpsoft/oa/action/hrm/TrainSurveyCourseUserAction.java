package com.xpsoft.oa.action.hrm;


import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.hrm.TrainSurveyCourseUser;
import com.xpsoft.oa.service.hrm.TrainSurveyCourseUserService;
/**
 * 
 * @author 
 *
 */
public class TrainSurveyCourseUserAction extends BaseAction{
	@Resource
	private TrainSurveyCourseUserService trainSurveyCourseUserService;
	private TrainSurveyCourseUser trainSurveyCourseUser;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrainSurveyCourseUser getTrainSurveyCourseUser() {
		return trainSurveyCourseUser;
	}

	public void setTrainSurveyCourseUser(TrainSurveyCourseUser trainSurveyCourseUser) {
		this.trainSurveyCourseUser = trainSurveyCourseUser;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<TrainSurveyCourseUser> list= trainSurveyCourseUserService.getAll(filter);
		
		Type type=new TypeToken<List<TrainSurveyCourseUser>>(){}.getType();
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
				trainSurveyCourseUserService.remove(new Long(id));
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
		TrainSurveyCourseUser trainSurveyCourseUser=trainSurveyCourseUserService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(trainSurveyCourseUser));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		trainSurveyCourseUserService.save(trainSurveyCourseUser);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
