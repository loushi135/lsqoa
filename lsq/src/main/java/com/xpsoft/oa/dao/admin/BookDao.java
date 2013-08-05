package com.xpsoft.oa.dao.admin;


import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.admin.Book;

/**
 * 
 * @author 
 *
 */
public interface BookDao extends BaseDao<Book>{
	public List<Book> findByTypeId(Long typeId,PagingBean pb);

	public String getNextID(Long typeId);

	public void saveAll(List<Book> list);
}