package com.xpsoft.oa.service.hrm;


import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.hrm.TrainPlan;
import com.xpsoft.oa.model.hrm.TrainUser;

public interface TrainUserService extends BaseService<TrainUser>{
	 public void updateByAttend(Long trainPlanId,Long userId,String attend);
	 public void updateAllAttend(String[] attendArray,String[] notAttendArray);
	 public List<TrainUser> findAttend(Long id, Long userId); 
	 public List<TrainUser> findByTrainPlanRegist(Long planId);
	 public void updateByTrainPlanUser(Long trainPlanId,String uIds);
	 /**
	     * 统计愿意参加本次培训的人数
	     * @param trainPlan
	     * @return
	     */
	 public Integer countRegist(TrainPlan trainPlan);
	 public Integer countRegist(long trainPlanId);
}


