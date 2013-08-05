package com.xpsoft.oa.action.admin;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.admin.Meeting;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.admin.MeetingService;


/**
 * 
 * @juql 
 *
 */
public class MeetingAction extends BaseAction{
	@Resource
	private MeetingService meetingService;
	private Meeting meeting;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<Meeting> list= meetingService.getAll(filter);
		
		Type type=new TypeToken<List<Meeting>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	
	/**
	 * 显示登录人申请列表
	 */
	public String queryForMe(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		
		filter.addFilter("Q_userId_L_EQ", AppUser.SYSTEM_USER.toString());
		
		List<Meeting> list= meetingService.getAll(filter);
		Type type=new TypeToken<List<Meeting>>(){}.getType();
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
				meetingService.remove(new Long(id));
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
		Meeting meeting=meetingService.get(id);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(meeting));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		meetingService.save(meeting);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	public String validatorDate() throws ParseException{
		String startTime=getRequest().getParameter("startTime");
		String endTime=getRequest().getParameter("endTime");
		Date startT=DateUtil.parse(startTime,"yyyy-MM-dd HH:mm:ss");
		Date endT=DateUtil.parse(endTime,"yyyy-MM-dd HH:mm:ss");
		List<Meeting> list=meetingService.getListByTime(startT, endT);
		if(!list.isEmpty()){
			 setJsonString("{success:false}"); 
		}else{
			 setJsonString("{success:true}");
		}
		return SUCCESS;
	}
}
