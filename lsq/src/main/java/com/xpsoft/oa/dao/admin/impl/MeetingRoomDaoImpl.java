package com.xpsoft.oa.dao.admin.impl;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.admin.MeetingRoomDao;
import com.xpsoft.oa.model.admin.MeetingRoom;




public class MeetingRoomDaoImpl extends BaseDaoImpl<MeetingRoom> implements MeetingRoomDao{

	public MeetingRoomDaoImpl() {
		super(MeetingRoom.class);
	}

}