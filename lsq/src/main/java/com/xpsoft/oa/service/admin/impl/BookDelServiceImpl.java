package com.xpsoft.oa.service.admin.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.admin.BookDelDao;
import com.xpsoft.oa.model.admin.BookDel;
import com.xpsoft.oa.service.admin.BookDelService;

public class BookDelServiceImpl extends BaseServiceImpl<BookDel> implements BookDelService{
	private BookDelDao dao;
	
	public BookDelServiceImpl(BookDelDao dao) {
		super(dao);
		this.dao=dao;
	}

}