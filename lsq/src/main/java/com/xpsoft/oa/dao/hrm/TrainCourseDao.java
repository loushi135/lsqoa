package com.xpsoft.oa.dao.hrm;


import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.hrm.TrainCourse;

/**
 * 
 * @author 
 *
 */
public interface TrainCourseDao extends BaseDao<TrainCourse>{
	public List<TrainCourse>findByNewList(PagingBean pagingBean);
}