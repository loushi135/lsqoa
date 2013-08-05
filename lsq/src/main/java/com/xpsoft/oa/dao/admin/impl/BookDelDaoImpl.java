package com.xpsoft.oa.dao.admin.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.admin.BookDelDao;
import com.xpsoft.oa.model.admin.BookDel;

public class BookDelDaoImpl extends BaseDaoImpl<BookDel> implements BookDelDao{

	public BookDelDaoImpl() {
		super(BookDel.class);
	}

}