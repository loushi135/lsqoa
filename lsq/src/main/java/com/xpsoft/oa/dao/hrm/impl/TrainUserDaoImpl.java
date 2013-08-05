package com.xpsoft.oa.dao.hrm.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.hrm.TrainUserDao;
import com.xpsoft.oa.model.hrm.TrainPlan;
import com.xpsoft.oa.model.hrm.TrainUser;

public class TrainUserDaoImpl extends BaseDaoImpl<TrainUser> implements TrainUserDao{

	public TrainUserDaoImpl() {
		super(TrainUser.class);
	}
	@Override
	public void updateByAttend(Long id, Long userId,String attend) {
		String regist = TrainUser.UN_REGIST;
		if(TrainUser.ATTEND.equals(attend)){
			regist = TrainUser.REGIST;
		}
		String updateSql = "UPDATE train_user set attend = :attend,regist = :regist where trainPlanId = :trainPlanId and userId =:userId";
		SQLQuery sqlQuery = this.getSession().createSQLQuery(updateSql);
		sqlQuery.setParameter("trainPlanId", id);
		sqlQuery.setParameter("regist", regist);
		sqlQuery.setParameter("userId", userId);
		sqlQuery.setParameter("attend", attend);
		sqlQuery.executeUpdate();
	}
	@Override
	public List<TrainUser> findAttend(Long id, Long userId) { 
		String hql="from TrainUser where trainPlan.id = ? and appUser.userId =?";
		return findByHql(hql,new Object[]{id,userId});
		
	}
	@Override
	public List<TrainUser> findByTrainPlanRegist(Long planId) {
		String hql="select tu from TrainUser tu JOIN fetch tu.appUser tuau JOIN fetch tuau.department tuandept where tu.trainPlan.id = ? and tu.regist = ?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, planId);
		query.setParameter(1, TrainUser.REGIST);
		List list = query.list();
		return list;
	}
	@Override
	public void updateByTrainPlanUser(Long trainPlanId, String[] userIds) {
		String hql="select tu from TrainUser tu JOIN fetch tu.appUser tuau where tu.trainPlan.id = ? and tuau.userId = ?";
		for (int i = 0; i < userIds.length; i++) {
			Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
			query.setParameter(0, trainPlanId);
			query.setParameter(1, Long.parseLong(userIds[i]));
			TrainUser trainUser = (TrainUser)query.uniqueResult();
			trainUser.setRegist(TrainUser.REGIST);
			trainUser.setAttend(TrainUser.ATTEND);
		}
	}
	@Override
	public Integer countRegist(TrainPlan trainPlan) {
		Integer count=0;
		String hql="select count(*) from TrainUser tu where tu.trainPlan=? and regist=0";
		
		count=((Long)this.findUnique(hql, new Object[]{trainPlan})).intValue();
		
		return count;
	}
	@Override
	public Integer countRegist(long trainPlanId) {
		Integer count=0;
		String hql="select count(*) from TrainUser tu where tu.trainPlan.id=? and regist=0";
		
		count=((Long)this.findUnique(hql, new Object[]{trainPlanId})).intValue();
		
		return count;
	}
}