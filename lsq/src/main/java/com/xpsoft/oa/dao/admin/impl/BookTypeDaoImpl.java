package com.xpsoft.oa.dao.admin.impl;



import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.admin.BookTypeDao;
import com.xpsoft.oa.model.admin.BookType;

public class BookTypeDaoImpl extends BaseDaoImpl<BookType> implements BookTypeDao{

	public BookTypeDaoImpl() {
		super(BookType.class);
	}

}