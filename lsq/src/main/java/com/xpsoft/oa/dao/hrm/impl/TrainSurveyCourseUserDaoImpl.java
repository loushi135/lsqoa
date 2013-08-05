package com.xpsoft.oa.dao.hrm.impl;


import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.hrm.TrainSurveyCourseUserDao;
import com.xpsoft.oa.model.hrm.TrainSurveyCourseUser;

public class TrainSurveyCourseUserDaoImpl extends BaseDaoImpl<TrainSurveyCourseUser> implements TrainSurveyCourseUserDao{

	public TrainSurveyCourseUserDaoImpl() {
		super(TrainSurveyCourseUser.class);
	}

	@Override
	public void delBySql(String sql,Object[] obj) {
		Session session = this.getSession();
		
		SQLQuery query = session.createSQLQuery(sql);
		if(obj != null && obj.length>0){
			for (int i = 0 ; i < obj.length ; i++) {
				query.setParameter(i, obj[i]);
			}
		}
		query.executeUpdate();
	}

	@Override
	public List<Object> queryBySql(String sql,Object[] obj) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		if(obj != null && obj.length>0){
			for (int i = 0 ; i < obj.length ; i++) {
				sqlQuery.setParameter(i, obj[i]);
			}
		}
		return sqlQuery.list();
	}

}