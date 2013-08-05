package com.xpsoft.oa.dao.admin.impl;



import java.util.List;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.admin.BookSnDao;
import com.xpsoft.oa.model.admin.BookSn;

public class BookSnDaoImpl extends BaseDaoImpl<BookSn> implements BookSnDao{

	public BookSnDaoImpl() {
		super(BookSn.class);
	}
	@Override
	public List<BookSn> findByBookId(final Long bookId) {
		// TODO Auto-generated method stub
		final String hql = "from BookSn b where b.book.bookId=?";
		Object[] params ={bookId};
		return findByHql(hql, params);
	}
	@Override
	public List<BookSn> findBorrowByBookId(final Long bookId) {
		// TODO Auto-generated method stub
		final String hql = "from BookSn b where b.book.bookId=? and b.status=0";
		Object[] params ={bookId};
		return findByHql(hql, params);
	}
	@Override
	public List<BookSn> findReturnByBookId(final Long bookId) {
		// TODO Auto-generated method stub
		final String hql = "from BookSn b where b.book.bookId=? and b.status=1";
		Object[] params ={bookId};
		return findByHql(hql, params);
	}
}