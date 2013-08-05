 package com.xpsoft.oa.dao.hrm.impl;
 
 import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.hrm.JobDao;
import com.xpsoft.oa.model.hrm.Job;
 
 public class JobDaoImpl extends BaseDaoImpl<Job>
   implements JobDao
 {
   public JobDaoImpl()
   {
     super(Job.class);
   }
 
   public List<Job> findByDep(Long depId)
   {
     String hql = "from Job vo where vo.department.depId=?";
     Object[] objs = { depId };
     return findByHql(hql, objs);
   }
   
   @Override
	public List<String> getAllJobName() {
		String sql = "select distinct jobName from job";
		SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
		List<String> jobNameList = sqlQuery.addScalar("jobName", Hibernate.STRING).list();
		return jobNameList;
	}
 }

