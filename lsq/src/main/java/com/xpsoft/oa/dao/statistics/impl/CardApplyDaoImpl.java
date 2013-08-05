package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.CardApplyDao;
import com.xpsoft.oa.model.statistics.CardApply;

public class CardApplyDaoImpl extends BaseDaoImpl<CardApply> implements CardApplyDao{

	public CardApplyDaoImpl() {
		super(CardApply.class);
	}

}