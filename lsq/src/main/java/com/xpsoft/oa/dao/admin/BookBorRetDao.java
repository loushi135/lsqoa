package com.xpsoft.oa.dao.admin;

import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.admin.BookBorRet;

/**
 * 
 * @author 
 *
 */
public interface BookBorRetDao extends BaseDao<BookBorRet>{
	public BookBorRet getByBookSnId(Long bookSnId);
	//根据图书状态得到已借出图书列表
	public List getBorrowInfo(PagingBean Pb);
	//根据图书状态得到已归还图书列表
	public List getReturnInfo(PagingBean Pb);
	/**
	 * 根据SNID来查找借阅的ID
	 */
	public Long getBookBorRetId(Long snId);
}