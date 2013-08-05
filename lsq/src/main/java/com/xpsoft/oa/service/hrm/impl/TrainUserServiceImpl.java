package com.xpsoft.oa.service.hrm.impl;


import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.StringUtil;
import com.xpsoft.oa.dao.hrm.TrainUserDao;
import com.xpsoft.oa.model.hrm.TrainPlan;
import com.xpsoft.oa.model.hrm.TrainUser;
import com.xpsoft.oa.service.hrm.TrainUserService;

public class TrainUserServiceImpl extends BaseServiceImpl<TrainUser> implements TrainUserService{
	private TrainUserDao dao;
	public TrainUserServiceImpl(TrainUserDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public void updateByAttend(Long trainPlanId, Long userId,String attend) {
		dao.updateByAttend(trainPlanId, userId, attend);
		if(trainPlanId != null){
			List<TrainUser> list=dao.findAttend(trainPlanId,userId);
		 	for(TrainUser tu:list){
		 		if(tu.getTrainPlan().getTrainTime() != null){
		 			Calendar calendar = Calendar.getInstance();
		 			calendar.setTime(tu.getTrainPlan().getTrainTime());
		 			int year=calendar.get(Calendar.YEAR);
		 			tu.setYear(String.valueOf(year));
 				}
	 		}
		}
	}

	@Override
	public List<TrainUser> findAttend(Long id, Long userId) {
		return dao.findAttend(id, userId);
	}

	@Override
	public List<TrainUser> findByTrainPlanRegist(Long planId) {
		return dao.findByTrainPlanRegist(planId);
	}

	@Override
	public void updateAllAttend(String[] attendArray, String[] notAttendArray) {
		if(attendArray != null){
			TrainUser trainUser;
			for (int i = 0; i < attendArray.length; i++) {
				trainUser = dao.get(Long.parseLong(attendArray[i]));
				trainUser.setAttend(TrainUser.ATTEND);
				trainUser.setRegist(TrainUser.REGIST);
			}
		}
		if(notAttendArray != null){
			TrainUser trainUser;
			for (int i = 0; i < notAttendArray.length; i++) {
				trainUser = dao.get(Long.parseLong(notAttendArray[i]));
				trainUser.setAttend(TrainUser.UN_ATTEND);
				trainUser.setRegist(TrainUser.UN_REGIST);
			}
		}
	}

	@Override
	public void updateByTrainPlanUser(Long trainPlanId, String uIds) {
		if(StringUtils.isNotBlank(uIds)){
			String[] userIds = uIds.split(",");
			dao.updateByTrainPlanUser(trainPlanId,userIds);
		}
	}

	@Override
	public Integer countRegist(TrainPlan trainPlan) {
		
		return dao.countRegist(trainPlan);
	}

	@Override
	public Integer countRegist(long trainPlanId) {
		return dao.countRegist(trainPlanId);
	}

}