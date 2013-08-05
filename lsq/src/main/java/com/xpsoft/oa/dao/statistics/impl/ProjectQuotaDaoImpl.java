package com.xpsoft.oa.dao.statistics.impl;


import java.math.BigDecimal;

import org.hibernate.Query;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.ProjectQuotaDao;
import com.xpsoft.oa.model.statistics.ProjectQuota;

public class ProjectQuotaDaoImpl extends BaseDaoImpl<ProjectQuota> implements ProjectQuotaDao{

	public ProjectQuotaDaoImpl() {
		super(ProjectQuota.class);
	}

	@Override
	public BigDecimal getQuotaByProNo(String proNo) {
		// TODO Auto-generated method stub
		String hql = "select quota from ProjectQuota where project.proNo=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0,proNo);
		BigDecimal temp = (BigDecimal)query.uniqueResult();
		if(temp==null){
			temp = new BigDecimal(0);
		}
		return temp;
	}

}