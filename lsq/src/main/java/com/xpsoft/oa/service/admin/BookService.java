package com.xpsoft.oa.service.admin;


import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.admin.Book;

public interface BookService extends BaseService<Book>{
	public List<Book> findByTypeId(Long typeId,PagingBean pb);

	public String getNextID(Long typeId);
	
	public void readerExcel(String[] filePath) throws Exception;
	
	public void updateBookData();
}


