package com.xpsoft.oa.dao.statistics.impl;


import java.math.BigDecimal;

import org.hibernate.Query;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.ProjectPercentageDao;
import com.xpsoft.oa.model.statistics.ProjectPercentage;

public class ProjectPercentageDaoImpl extends BaseDaoImpl<ProjectPercentage> implements ProjectPercentageDao{

	public ProjectPercentageDaoImpl() {
		super(ProjectPercentage.class);
	}

	@Override
	public BigDecimal getPercentageByProNo(String proNo) {
		// TODO Auto-generated method stub
		String hql = "select percentage from ProjectPercentage where project.proNo=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0,proNo);
		BigDecimal temp = (BigDecimal)query.uniqueResult();
		if(temp==null){
			temp = new BigDecimal(0);
		}
		return temp;
	}

	
}