package com.xpsoft.oa.service.hrm.impl;


import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.hrm.TrainCourseDao;
import com.xpsoft.oa.model.hrm.TrainCourse;
import com.xpsoft.oa.service.hrm.TrainCourseService;

public class TrainCourseServiceImpl extends BaseServiceImpl<TrainCourse> implements TrainCourseService{
	private TrainCourseDao dao;
	
	public TrainCourseServiceImpl(TrainCourseDao dao) {
		super(dao);
		this.dao=dao;
	}
	public List<TrainCourse>findByNewList(PagingBean pagingBean){
		return dao.findByNewList(pagingBean);
	};
	public String saveFile(List<TrainCourse> list){
		int size = list.size();
		for(TrainCourse ts : list){
			if(ts == null){
				dao.remove(ts);
			}
		}
		String res = "";
		if (list.size() == 0) {
			return res;
		} else {
			if (size != list.size()) {
				res = (size - list.size()) + "";
			} else {
				res = "finish";
			}
			for(TrainCourse ts : list){
				dao.save(ts);
			}
			return res;
		}
	}

}