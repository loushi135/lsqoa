package com.xpsoft.oa.service.admin.impl;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.admin.MeetingRoomDao;
import com.xpsoft.oa.model.admin.MeetingRoom;
import com.xpsoft.oa.service.admin.MeetingRoomService;



public class MeetingRoomServiceImpl extends BaseServiceImpl<MeetingRoom> implements MeetingRoomService{
	private MeetingRoomDao dao;
	
	public MeetingRoomServiceImpl(MeetingRoomDao dao) {
		super(dao);
		this.dao=dao;
	}

}