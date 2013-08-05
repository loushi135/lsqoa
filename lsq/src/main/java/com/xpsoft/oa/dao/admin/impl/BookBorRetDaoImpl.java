package com.xpsoft.oa.dao.admin.impl;


import java.util.List;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.admin.BookBorRetDao;
import com.xpsoft.oa.model.admin.BookBorRet;

public class BookBorRetDaoImpl extends BaseDaoImpl<BookBorRet> implements BookBorRetDao{

	public BookBorRetDaoImpl() {
		super(BookBorRet.class);
	}

	@Override
	public BookBorRet getByBookSnId(final Long bookSnId) {
		// TODO Auto-generated method stub
		String hql = "from BookBorRet bookBorRet where bookBorRet.bookSn.bookSnId=?";
		Object[] params ={bookSnId};
		return findByHql(hql, params).get(0);
	}

	@Override
	public List<BookBorRet> getBorrowInfo(PagingBean pb) {
		// TODO Auto-generated method stub
		//status=1表示借出状态
		String hql = "select bookBorRet from BookBorRet bookBorRet,BookSn bookSn where bookBorRet.bookSn.bookSnId=bookSn.bookSnId and bookSn.status=1";
		return findByHql(hql,null,pb);
	}

	@Override
	public List<BookBorRet> getReturnInfo(PagingBean pb) {
		// TODO Auto-generated method stub
		//stauts=0表示在库（也表示已归还）
		String hql = "select bookBorRet from BookBorRet bookBorRet,BookSn bookSn where bookBorRet.bookSn.bookSnId=bookSn.bookSnId and bookSn.status=0";
		return findByHql(hql,null,pb);
	}

	@Override
	public Long getBookBorRetId(Long snId) {
		String hql="from BookBorRet vo where vo.bookSn.bookSnId=?";
		Object[] objs={snId};
		List<BookBorRet> list=findByHql(hql, objs);
		if(list.size()==1){
		    return list.get(0).getRecordId();
		}else{
			return null;
		}
	}

}