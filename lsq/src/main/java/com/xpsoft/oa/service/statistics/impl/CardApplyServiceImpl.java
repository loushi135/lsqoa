package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.CardApplyDao;
import com.xpsoft.oa.model.statistics.CardApply;
import com.xpsoft.oa.service.statistics.CardApplyService;

public class CardApplyServiceImpl extends BaseServiceImpl<CardApply> implements CardApplyService{
	private CardApplyDao dao;
	
	public CardApplyServiceImpl(CardApplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}