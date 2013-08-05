package com.xpsoft.oa.dao.admin;


import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.admin.BookSn;

/**
 * 
 * @author 
 *
 */
public interface BookSnDao extends BaseDao<BookSn>{
	//通过bookId找到图书的boonSn
	public List<BookSn> findByBookId(Long bookId);
	//通过bookId找到还没有借出去的图书的boonSn
	public List<BookSn> findBorrowByBookId(Long bookId);
	//通过bookId找到已借出去还未归还的图书的boonSn
	public List<BookSn> findReturnByBookId(Long bookId);
}