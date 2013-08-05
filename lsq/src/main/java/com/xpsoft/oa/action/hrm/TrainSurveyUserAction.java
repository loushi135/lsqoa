package com.xpsoft.oa.action.hrm;


import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.hrm.TrainSurveyUser;
import com.xpsoft.oa.service.hrm.TrainSurveyUserService;
/**
 * 
 * @author 
 *
 */
public class TrainSurveyUserAction extends BaseAction{
	@Resource
	private TrainSurveyUserService trainSurveyUserService;
	private TrainSurveyUser trainSurveyUser;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrainSurveyUser getTrainSurveyUser() {
		return trainSurveyUser;
	}

	public void setTrainSurveyUser(TrainSurveyUser trainSurveyUser) {
		this.trainSurveyUser = trainSurveyUser;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()+"");
		List<TrainSurveyUser> list= trainSurveyUserService.getAll(filter);
		
		Type type=new TypeToken<List<TrainSurveyUser>>(){}.getType();
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
				trainSurveyUserService.remove(new Long(id));
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
		TrainSurveyUser trainSurveyUser=trainSurveyUserService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(trainSurveyUser));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		trainSurveyUserService.save(trainSurveyUser);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
