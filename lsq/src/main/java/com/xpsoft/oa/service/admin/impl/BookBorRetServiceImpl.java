package com.xpsoft.oa.service.admin.impl;


import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.admin.BookBorRetDao;
import com.xpsoft.oa.model.admin.BookBorRet;
import com.xpsoft.oa.service.admin.BookBorRetService;

public class BookBorRetServiceImpl extends BaseServiceImpl<BookBorRet> implements BookBorRetService{
	private BookBorRetDao dao;
	
	public BookBorRetServiceImpl(BookBorRetDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public BookBorRet getByBookSnId(Long bookSnId) {
		// TODO Auto-generated method stub
		return dao.getByBookSnId(bookSnId);
	}

	@Override
	public List getBorrowInfo(PagingBean pb) {
		// TODO Auto-generated method stub
		return dao.getBorrowInfo(pb);
	}

	@Override
	public List getReturnInfo(PagingBean pb) {
		// TODO Auto-generated method stub
		return dao.getReturnInfo(pb);
	}

	@Override
	public Long getBookBorRetId(Long snId) {
		return dao.getBookBorRetId(snId);
	}

}