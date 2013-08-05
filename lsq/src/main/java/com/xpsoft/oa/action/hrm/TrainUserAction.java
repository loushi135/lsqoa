package com.xpsoft.oa.action.hrm;


import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.hrm.TrainPlan;
import com.xpsoft.oa.model.hrm.TrainUser;
import com.xpsoft.oa.service.hrm.TrainPlanService;
import com.xpsoft.oa.service.hrm.TrainUserService;
/**
 * 
 * @author 
 *
 */
public class TrainUserAction extends BaseAction{
	@Resource
	private TrainUserService trainUserService;
	private TrainUser trainUser;
	@Resource
	private TrainPlanService trainPlanService;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrainUser getTrainUser() {
		return trainUser;
	}

	public void setTrainUser(TrainUser trainUser) {
		this.trainUser = trainUser;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		QueryFilter filter=new QueryFilter(getRequest());
		Long userId = ContextUtil.getCurrentUser().getUserId();
		filter.addFilter("Q_appUser.userId_L_EQ", userId.toString());
		List<TrainUser> list= trainUserService.getAll(filter);
		Type type=new TypeToken<List<TrainUser>>(){}.getType();
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
				trainUserService.remove(new Long(id));
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
		TrainUser trainUser=trainUserService.get(id);
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(trainUser));
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		trainUserService.save(trainUser);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	public String updateAllAttend(){
		String[] attendArray = getRequest().getParameterValues("attendArray");
		String[] notAttendArray = getRequest().getParameterValues("notAttendArray");
		trainUserService.updateAllAttend(attendArray,notAttendArray);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	public String confirmByAttend(){
		Long userId = ContextUtil.getCurrentUser().getUserId();
		String trainPlanId=getRequest().getParameter("id");
		String attend=getRequest().getParameter("attend");
		
		if(TrainUser.ATTEND.equals(attend)){
			attend = TrainUser.UN_ATTEND;
			jsonString="{success:true,data:'成功取消该次培训！'}";
		}else{
			TrainPlan trainPlan=trainPlanService.get(new Long(trainPlanId));
			if(null!=trainPlan.getSumNum()&&trainPlan.getSumNum()<=trainPlan.getNowNum()){
				jsonString="{success:true,data:'该次培训报名已满！'}";
				return SUCCESS;
			}
			attend = TrainUser.ATTEND;
			jsonString="{success:true,data:'成功报名该次培训！'}";
		}
		trainUserService.updateByAttend(new Long(trainPlanId),userId,attend);
		
		return SUCCESS;
	}
	public String queryRegistUser(){
		String trainPlanId=getRequest().getParameter("trainPlanId");
		List<TrainUser> list= trainUserService.findByTrainPlanRegist(Long.parseLong(trainPlanId));
		Type type=new TypeToken<List<TrainUser>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true")
		.append(",result:");
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	public String addRegistUser(){
		String trainPlanId=getRequest().getParameter("trainPlanId");
		String userIds=getRequest().getParameter("userIds");
		trainUserService.updateByTrainPlanUser(Long.parseLong(trainPlanId),userIds);
		jsonString="{success:true}";
		return SUCCESS;
	}
}
