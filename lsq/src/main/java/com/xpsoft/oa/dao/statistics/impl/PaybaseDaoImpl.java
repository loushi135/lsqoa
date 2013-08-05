package com.xpsoft.oa.dao.statistics.impl;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.PaybaseDao;
import com.xpsoft.oa.model.statistics.Paybase;

public class PaybaseDaoImpl extends BaseDaoImpl<Paybase> implements PaybaseDao{

	public PaybaseDaoImpl() {
		super(Paybase.class);
	}

	@Override
	@Deprecated
	public BigDecimal getTotalByMaterialId(Long materialId) {
		// TODO Auto-generated method stub
		String hql = "select sum(paymentBase) from Paybase where materialContract.id=?";
	    Query query = getSession().createQuery(hql);
	    query.setParameter(0, materialId);
	    BigDecimal temp = (BigDecimal)query.uniqueResult();
	    if(temp==null){
	    	temp = new BigDecimal(0);
	    }
		return temp;
	}
	
	@Override
	public BigDecimal getTotalByProNo(String proNo){
		String hql = "select sum(paymentBase) from Paybase where projectNew.proNo=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0,proNo);
		BigDecimal temp = (BigDecimal)query.uniqueResult();
		if(temp==null){
			temp = new BigDecimal(0);
		}
		return temp;
	}

	@Override
	public BigDecimal getTotalByProAndSupp(Long proId, Long suppId) {
		String hql = "select sum(paymentBase) from Paybase where projectNew.id=? and suppliersAssess.suppliersId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0,proId);
		query.setParameter(1,suppId);
		BigDecimal temp = (BigDecimal)query.uniqueResult();
		if(temp==null){
			temp = new BigDecimal(0);
		}
		return temp;
	}

	@Override
	public List<Paybase> getByProAndSupp(Long proId, Long suppliersId) {
		String hql = "from Paybase where projectNew.id=? and suppliersAssess.suppliersId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0,proId);
		query.setParameter(1,suppliersId);
		return query.list();
	}

	@Override
	public List<Paybase> getByProName(String proName) {
		String hql = "from Paybase where projectNew.proName=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0,proName);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paybase> getAll(final DetachedCriteria criteria) {
		return (List)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria c = null;
					c = criteria.getExecutableCriteria(session);
				return c.list();
			}	
		});
	}

	@Override
	public List<Paybase> getByProName(String proName, int pageFirst,
			int pageSize) {
		String hql = "from Paybase where projectNew.proName=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0,proName);
		query.setFirstResult(pageFirst);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public int getCountByProName(String proName) {
		String hql = "select count(*) from Paybase where projectNew.proName=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0,proName);
		return ((Long)query.uniqueResult()).intValue();
	}
	
}