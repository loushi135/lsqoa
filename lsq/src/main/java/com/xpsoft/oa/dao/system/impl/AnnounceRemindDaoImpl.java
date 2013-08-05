package com.xpsoft.oa.dao.system.impl;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.AnnounceRemindDao;
import com.xpsoft.oa.model.system.AnnounceRemind;

public class AnnounceRemindDaoImpl extends BaseDaoImpl<AnnounceRemind> implements AnnounceRemindDao{

	public AnnounceRemindDaoImpl() {
		super(AnnounceRemind.class);
	}

	@Override
	public void deleteByAnnounceId(long announceId) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		String sql = "delete from announce_remind where announceId = "+announceId;
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List findBySql(String sql) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql);
		return query.list(); 
		
	}

}