package com.xpsoft.oa.dao.statistics.impl;


import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.BillDao;
import com.xpsoft.oa.model.statistics.Bill;

public class BillDaoImpl extends BaseDaoImpl<Bill> implements BillDao{

	public BillDaoImpl() {
		super(Bill.class);
	}

	@Override
	public BigDecimal getTotalByMaterialId(Long materialId) {
		// TODO Auto-generated method stub
		String hql = "select sum(amount) from Bill where materialContract.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0,materialId);
		BigDecimal temp = (BigDecimal)query.uniqueResult();
		if(temp==null){
			temp = new BigDecimal(0);
		}
		return temp;
	}

	@Override
	public BigDecimal getTotalByProAndSupp(Long proId, Long suppId) {
		String hql = "select sum(amount) from Bill where projectNew.id=? and suppliersAssess.suppliersId=?";
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
	public List<Bill> getByProAndSupp(Long id, Long suppliersId) {
		String hql = "from Bill where projectNew.id=? and suppliersAssess.suppliersId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0,id);
		query.setParameter(1,suppliersId);
		return query.list();
	}

}