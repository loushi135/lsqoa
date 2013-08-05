package com.xpsoft.oa.dao.system.impl;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Query;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.UserMessageDao;
import com.xpsoft.oa.model.system.UserMessage;

public class UserMessageDaoImpl extends BaseDaoImpl<UserMessage> implements UserMessageDao{

	public UserMessageDaoImpl() {
		super(UserMessage.class);
	}

	@Override
	public Long getMessageCount(Long userId) {
		Calendar date = Calendar.getInstance();
		date.setTime(new Date());
		date.add(Calendar.HOUR,-1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String dateBefore = sdf.format(date.getTime());
		String dateNew = sdf.format(new Date());
		String sql="select count(*) from UserMessage where userId ='"+userId+"' and actionTime " +
				   "between '"+dateBefore+"' and  '"+dateNew+"'";
		Query sqlQueryCount = this.getSession().createQuery(sql);
		Long c = (Long)sqlQueryCount.uniqueResult();
		return c;
	}

}