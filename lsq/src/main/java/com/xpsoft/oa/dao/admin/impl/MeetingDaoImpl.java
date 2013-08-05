package com.xpsoft.oa.dao.admin.impl;

import java.util.List;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.admin.MeetingDao;
import com.xpsoft.oa.model.admin.Meeting;



public class MeetingDaoImpl extends BaseDaoImpl<Meeting> implements MeetingDao{

	public MeetingDaoImpl() {
		super(Meeting.class);
	}
	
	public List<Meeting> findByHql(final String hql){
		return findByHql(hql,null);
	}

}