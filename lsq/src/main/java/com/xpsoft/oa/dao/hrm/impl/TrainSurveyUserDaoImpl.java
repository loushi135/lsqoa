package com.xpsoft.oa.dao.hrm.impl;


import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.hrm.TrainSurveyUserDao;
import com.xpsoft.oa.model.hrm.TrainSurveyUser;

public class TrainSurveyUserDaoImpl extends BaseDaoImpl<TrainSurveyUser> implements TrainSurveyUserDao{

	public TrainSurveyUserDaoImpl() {
		super(TrainSurveyUser.class);
	}

	@Override
	public void delBySql(String sql, Object[] obj) {
		Session session = this.getSession();
		
		SQLQuery query = session.createSQLQuery(sql);
		if(obj != null && obj.length>0){
			for (int i = 0 ; i < obj.length ; i++) {
				query.setParameter(i, obj[i]);
			}
		}
		query.executeUpdate();
		
	}

}