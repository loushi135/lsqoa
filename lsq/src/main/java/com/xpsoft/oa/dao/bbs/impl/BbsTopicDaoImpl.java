package com.xpsoft.oa.dao.bbs.impl;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.bbs.BbsTopicDao;
import com.xpsoft.oa.model.bbs.BbsTopic;

public class BbsTopicDaoImpl extends BaseDaoImpl<BbsTopic> implements BbsTopicDao{
 private BbsTopic bbsTopic;
 private BbsTopicDao bbsTopicDao;
	public BbsTopicDaoImpl() {
		super(BbsTopic.class);
	}

	@Override
	public Long getMaxId() {
		String hql=" select max(b.topId)from BbsTopic b";
		Query query=getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public void topList(String ids[]) {
	
	}
}