package com.xpsoft.oa.dao.statistics.impl;


import java.math.BigDecimal;

import org.hibernate.Query;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.ProjectRepayDao;
import com.xpsoft.oa.model.statistics.ProjectRepay;

public class ProjectRepayDaoImpl extends BaseDaoImpl<ProjectRepay> implements ProjectRepayDao{

	public ProjectRepayDaoImpl() {
		super(ProjectRepay.class);
	}

	@Override
	public BigDecimal getRePayedByProNo(String proNo) {
		// TODO Auto-generated method stub
		String hql = "select sum(amount) from ProjectRepay where project.proNo=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0,proNo);
		BigDecimal temp = (BigDecimal)query.uniqueResult();
		if(temp==null){
			temp = new BigDecimal(0);
		}
		return temp;
	}

}