package com.xpsoft.oa.service.admin;


import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.admin.BookBorRet;

public interface BookBorRetService extends BaseService<BookBorRet>{
	public BookBorRet getByBookSnId(Long bookSnId);
	//根据图书状态得到已借出图书列表
	public List getBorrowInfo(PagingBean pb);
	//根据图书状态得到已归还图书列表
	public List getReturnInfo(PagingBean pb);
	/**
	 * 根据SN来查找记录ID
	 * @param snId
	 * @return
	 */
	public Long getBookBorRetId(Long snId);
}


