package com.xpsoft.oa.dao.hrm.impl;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.hrm.TrainCourseDao;
import com.xpsoft.oa.model.hrm.TrainCourse;

public class TrainCourseDaoImpl extends BaseDaoImpl<TrainCourse> implements TrainCourseDao{

	public TrainCourseDaoImpl() {
		super(TrainCourse.class);
	}
	public List<TrainCourse>findByNewList(PagingBean pagingBean){
	String sqlcount ="SELECT count(*) from TrainCourse tc WHERE tc.courseId NOT IN (SELECT trainCourse.courseId from TrainPlan)";
		Query sqlQueryCount = this.getSession().createQuery(sqlcount);
		Long c = (Long)sqlQueryCount.uniqueResult();
		String sql ="from TrainCourse tc WHERE tc.courseId NOT IN (SELECT trainCourse.courseId from TrainPlan)";
		Query sqlQuery = this.getSession().createQuery(sql);
		sqlQuery.setFirstResult(pagingBean.getStart());
		sqlQuery.setMaxResults(pagingBean.getPageSize());
//		sqlQuery.setResultTransformer(Transformers.aliasToBean(TrainCourse.class));	
		pagingBean.setTotalItems(Integer.parseInt(c.toString()));
		List list = sqlQuery.list();
		return list;
	}
}	