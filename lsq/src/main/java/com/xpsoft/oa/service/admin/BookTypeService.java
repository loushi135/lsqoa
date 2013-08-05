package com.xpsoft.oa.service.admin;




import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.admin.BookType;

public interface BookTypeService extends BaseService<BookType> {

	/**
	 * 根据当前的类型ID 获取从该类型向上的所有类型的 汤晓华 2011 5 11 20：50
	 */
	BookType[] path(Long typeId);

	String sPath(Long typeId);

	/**
	 * 获取直接子分类
	 * 
	 * @param typeId
	 * @return
	 */
	int directSubTypeCount(Long typeId);

	List<BookType> getByPtypeId(Long typeId);
	
	String getTree(Long typeId);

	String multiDel(String[] ids);
	
	
	

}
