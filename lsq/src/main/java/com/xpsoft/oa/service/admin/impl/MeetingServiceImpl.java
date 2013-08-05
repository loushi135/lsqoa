package com.xpsoft.oa.service.admin.impl;

import java.util.Date;
import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.admin.MeetingDao;
import com.xpsoft.oa.model.admin.Meeting;
import com.xpsoft.oa.service.admin.MeetingService;



public class MeetingServiceImpl extends BaseServiceImpl<Meeting> implements
		MeetingService {
	private MeetingDao dao;

	public MeetingServiceImpl(MeetingDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Meeting> getAllByTime(Date startTime, Date endTime) {
		// 查询 一段时间内所有的会议申请       三种情况： 1 输入开始时间 在 库中开始时间和结束时间之间，2输入结束时间在 库中开始时间和结束时间之间
		// 3 输入开始时间小于库中开始时间并且输入结束时间大于库中结束时间
		String hql = "from Meeting m where (m.startTime<='" + startTime + "'"
				+ " and m.entTime>'" + startTime + "') or (m.startTime<'"
				+ endTime + "'" + " and m.entTime>='" + endTime
				+ "') or (m.startTime>='" + startTime + "'"
				+ " and m.entTime<='" + endTime + "')";
		
		return dao.findByHql(hql);
	}
	
	public List<Meeting> getListByTime(Date startTime, Date endTime) {
		String hql = "from Meeting m where (m.startTime>='" + startTime + "'"
				+ " and m.entTime<='" + endTime + "') or( m.startTime<='" + startTime + "'"
				+ " and m.entTime>='" + endTime + "')";

		return dao.findByHql(hql);
	}
	@Override
	public List<Meeting> getMeetingByMeetingRoom(String name,Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		String hql = "from Meeting m where m.meetingRoom=? and ( (m.startTime<='" + startTime + "'"
		+ " and m.entTime>'" + startTime + "') or (m.startTime<'"
		+ endTime + "'" + " and m.entTime>='" + endTime
		+ "') or (m.startTime>='" + startTime + "'))";
		return dao.findByHql(hql, new Object[]{name});
	}

}