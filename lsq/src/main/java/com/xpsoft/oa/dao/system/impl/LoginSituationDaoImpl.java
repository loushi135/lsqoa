package com.xpsoft.oa.dao.system.impl;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Query;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.LoginSituationDao;
import com.xpsoft.oa.model.system.LoginSituation;

public class LoginSituationDaoImpl extends BaseDaoImpl<LoginSituation> implements LoginSituationDao{

	public LoginSituationDaoImpl() {
		super(LoginSituation.class);
	}

	@Override
	public Long getMessageCount(Long userId) {
		Calendar date = Calendar.getInstance();
		date.setTime(new Date());
		date.add(Calendar.HOUR,-1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String dateBefore = sdf.format(date.getTime());
		String dateNew = sdf.format(new Date());
		String sql="select count(0) from login_situation where userId ='"+userId+"' and actionTime " +
				   "between '"+dateBefore+"' and  '"+dateNew+"' and isClear=0 ";
		Query sqlQueryCount = this.getSession().createSQLQuery(sql);
		Long c = Long.parseLong(sqlQueryCount.uniqueResult().toString());
		return c;
	}

	@Override
	public void clearHourErr(Long userId) {
		
		Calendar date = Calendar.getInstance();
		date.setTime(new Date());
		date.add(Calendar.HOUR,-1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String dateBefore = sdf.format(date.getTime());
		String dateNew = sdf.format(new Date());
		String sql="update login_situation set isClear=1 where userId ='"+userId+"' and actionTime " +
				   "between '"+dateBefore+"' and  '"+dateNew+" ' ";
		
		this.getSession().createSQLQuery(sql).executeUpdate();
	}

}