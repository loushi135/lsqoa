package com.xpsoft.oa.dao.statistics;


import java.util.List;
import java.util.Map;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectRelatedData;

/**
 * 
 * @author 
 *
 */
public interface ProjectNewDao extends BaseDao<ProjectNew>{
	
	public List<ProjectRelatedData> getAllData(PagingBean pagingBean,Map<String,String> dataMap);

	public ProjectNew getByProName(String trim);
	
	public ProjectNew getByProNo(String proNo);
}