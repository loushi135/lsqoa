package com.xpsoft.oa.service.hrm;


import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.hrm.TrainCourse;

public interface TrainCourseService extends BaseService<TrainCourse>{
	public List<TrainCourse>findByNewList(PagingBean pagingBean);
	public String saveFile(List<TrainCourse> list);
}


