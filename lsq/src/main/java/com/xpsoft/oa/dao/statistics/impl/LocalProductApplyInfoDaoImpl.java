package com.xpsoft.oa.dao.statistics.impl;


import java.util.List;

import org.hibernate.Query;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.LocalProductApplyInfoDao;
import com.xpsoft.oa.model.statistics.LocalProductApplyInfo;

public class LocalProductApplyInfoDaoImpl extends BaseDaoImpl<LocalProductApplyInfo> implements LocalProductApplyInfoDao{

	public LocalProductApplyInfoDaoImpl() {
		super(LocalProductApplyInfo.class);
	}

	@Override
	public void deleteLocalProductApplyInfos(Long applyId, List<Long> idList) {
		String hql = "delete from LocalProductApplyInfo where id not in (:ids) and localProductApply.id=:applyId";
		Query query =  getSession().createQuery(hql);
		query.setParameterList("ids", idList).setParameter("applyId", applyId);
		query.executeUpdate();
	}

}