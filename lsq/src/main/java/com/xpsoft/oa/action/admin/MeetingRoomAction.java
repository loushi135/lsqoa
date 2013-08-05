package com.xpsoft.oa.action.admin;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.admin.Meeting;
import com.xpsoft.oa.model.admin.MeetingRoom;
import com.xpsoft.oa.service.admin.MeetingRoomService;
import com.xpsoft.oa.service.admin.MeetingService;


/**
 * 
 * @juql
 * 
 */
public class MeetingRoomAction extends BaseAction {
	@Resource
	private MeetingRoomService meetingRoomService;
	private MeetingRoom meetingRoom;
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

	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(MeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
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
	public String list() {
		Timestamp startTime = null;
		Timestamp endTime = null;
		boolean ifCheck = true;
		Enumeration paramEnu = getRequest().getParameterNames();
		while (paramEnu.hasMoreElements()) {
			String paramName = (String) paramEnu.nextElement();
			if (paramName.equals("SEARCH_startTime")) {
				String paramValue = (String) getRequest().getParameter(
						paramName);
				if (!"---请选择日期---".equals(paramValue)) {
					startTime = new Timestamp(DateUtil.parse(paramValue, "yyyy-MM-dd HH:mm").getTime());
				}
			} else if (paramName.equals("SEARCH_endTime")) {
				String paramValue = (String) getRequest().getParameter(
						paramName);
				if (!"---请选择日期---".equals(paramValue)) {
					endTime = new Timestamp(DateUtil.parse(paramValue, "yyyy-MM-dd HH:mm").getTime());
					ifCheck = false;
				}
			}
		}
		
		List<Meeting> li = new ArrayList();
		if (startTime != null && endTime != null) {
			li = meetingService.getAllByTime(startTime, endTime);
		}

		QueryFilter filter = new QueryFilter(getRequest());
		List<MeetingRoom> list = meetingRoomService.getAll(filter);
//		if(ifCheck){
//			for(MeetingRoom meettingroom : list){
//				meettingroom.setStatus((short)0);
//			}
//		}
		//首先将所有的会议室都当作是可用，然后根据存在的会议名称对应，存在的设为占用
		for(MeetingRoom mr : list){
			mr.setStatus((short)0);
		}
		if(li.size()>0){
			for(int i=0;i<li.size();i++){
				String mrName = li.get(i).getMeetingRoom();
				for(MeetingRoom mr : list){
					if(mr.getName().equals(mrName)){
						mr.setStatus((short)1);
					}
				}
			}
		}
		List<MeetingRoom> newList = new ArrayList<MeetingRoom>();
		if(startTime==null){
			startTime = new Timestamp(new Date().getTime());
//			getRequest().setAttribute("SEARCH_startTime", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm"));
		}
		if(endTime==null){
			endTime = new Timestamp(new Date().getTime());
//			getRequest().setAttribute("SEARCH_endTime", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm"));
		}
		for(MeetingRoom mr : list){
			mr.setMeetingList(meetingService.getMeetingByMeetingRoom(mr.getName(),startTime,endTime));
			newList.add(mr);
		}
		
		Type type = new TypeToken<List<MeetingRoom>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		Gson gson = new Gson();
		buff.append(gson.toJson(newList, type));
		buff.append("}");
		jsonString = buff.toString();
		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				meetingRoomService.remove(new Long(id));
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		MeetingRoom meetingRoom = meetingRoomService.get(id);

		Gson gson = new Gson();
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(meetingRoom));
		sb.append("}");
		setJsonString(sb.toString());

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				MeetingRoom mRoom = meetingRoomService.get(new Long(id));
				if(mRoom.getStatus().intValue() == 0){
					mRoom.setStatus(Short.valueOf("1"));
				}else{
					mRoom.setStatus(Short.valueOf("0"));
				}
				meetingRoomService.save(mRoom);
			}
		}else{
			meetingRoomService.save(meetingRoom);
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
