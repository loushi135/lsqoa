package com.xpsoft.oa.dao.hrm;


import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.hrm.TrainPlan;
import com.xpsoft.oa.model.hrm.TrainUser;

/**
 * 
 * @author 
 *
 */
public interface TrainUserDao extends BaseDao<TrainUser>{
	public void updateByAttend(Long id,Long userId,String attend);
    public List<TrainUser> findAttend(Long id,Long userId);
    public List<TrainUser> findByTrainPlanRegist(Long planId);
    public void updateByTrainPlanUser(Long trainPlanId,String[] userIds);
    /**
     * 统计愿意参加本次培训的人数
     * @param trainPlan
     * @return
     */
	public Integer countRegist(TrainPlan trainPlan);
	public Integer countRegist(long trainPlan);
} 