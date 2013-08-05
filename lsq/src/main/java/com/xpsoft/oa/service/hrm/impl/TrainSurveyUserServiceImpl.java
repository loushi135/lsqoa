package com.xpsoft.oa.service.hrm.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.hrm.TrainSurveyUserDao;
import com.xpsoft.oa.model.hrm.TrainSurveyUser;
import com.xpsoft.oa.service.hrm.TrainSurveyUserService;

public class TrainSurveyUserServiceImpl extends BaseServiceImpl<TrainSurveyUser> implements TrainSurveyUserService{
	private TrainSurveyUserDao dao;
	
	public TrainSurveyUserServiceImpl(TrainSurveyUserDao dao) {
		super(dao);
		this.dao=dao;
	}

}