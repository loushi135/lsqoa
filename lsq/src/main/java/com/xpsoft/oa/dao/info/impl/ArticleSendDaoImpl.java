package com.xpsoft.oa.dao.info.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.info.ArticleSendDao;
import com.xpsoft.oa.model.info.ArticleSend;

public class ArticleSendDaoImpl extends BaseDaoImpl<ArticleSend> implements ArticleSendDao{

	public ArticleSendDaoImpl() {
		super(ArticleSend.class);
	}

}