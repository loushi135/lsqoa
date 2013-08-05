package com.xpsoft.oa.dao.hrm.impl;


import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;
import java.util.List;
import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.hrm.TrainPlanDao;
import com.xpsoft.oa.model.hrm.TrainPlan;

public class TrainPlanDaoImpl extends BaseDaoImpl<TrainPlan> implements TrainPlanDao{

	public TrainPlanDaoImpl() {
		super(TrainPlan.class);
	}

	@Override
	public int queryCount(String year) {
		Criteria criteria = this.getSession().createCriteria(TrainPlan.class);
		criteria.add(Restrictions.like("sn", year+"%")).setProjection(Projections.rowCount());
		Integer rowCount = (Integer)criteria.uniqueResult();
		return rowCount;
	}

	@Override
	public List<TrainPlan> getListNew() {
		Object[]statusList={TrainPlan.SCHEDULED,TrainPlan.COMPLETE,TrainPlan.UNSCHEDULED};
		String hql="from TrainPlan where  trainStatus in(:statusList)";  
	    Query query = getSession().createQuery(hql);  
	    query.setParameterList("statusList",statusList); 
//	    query.setFirstResult(pagingBean.getStart());
//	    query.setMaxResults(pagingBean.getPageSize());
		List<TrainPlan> list=query.list();		
		return list;
	}

}