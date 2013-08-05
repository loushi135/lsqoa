package com.xpsoft.oa.service.admin.impl;


import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.admin.BookSnDao;
import com.xpsoft.oa.model.admin.BookSn;
import com.xpsoft.oa.service.admin.BookSnService;

public class BookSnServiceImpl extends BaseServiceImpl<BookSn> implements BookSnService{
	private BookSnDao dao;
	
	public BookSnServiceImpl(BookSnDao dao) {
		super(dao);
		this.dao=dao;
	}
	@Override
	public List<BookSn> findByBookId(Long bookId) {
		// TODO Auto-generated method stub
		return dao.findByBookId(bookId);
	}
	@Override
	public List<BookSn> findBorrowByBookId(Long bookId) {
		// TODO Auto-generated method stub
		return dao.findBorrowByBookId(bookId);
	}
	@Override
	public List<BookSn> findReturnByBookId(Long bookId) {
		// TODO Auto-generated method stub
		return dao.findReturnByBookId(bookId);
	}
}