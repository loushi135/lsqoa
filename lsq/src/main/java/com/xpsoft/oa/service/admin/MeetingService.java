package com.xpsoft.oa.service.admin;

import java.util.Date;
import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.admin.Meeting;



public interface MeetingService extends BaseService<Meeting>{
	public List<Meeting> getAllByTime(Date startTime, Date endTime);
	public List<Meeting> getListByTime(Date startTime, Date endTime);
	public List<Meeting> getMeetingByMeetingRoom(String name,Date startTime, Date endTime);
}


