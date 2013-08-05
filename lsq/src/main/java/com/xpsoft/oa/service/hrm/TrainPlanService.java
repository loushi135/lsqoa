package com.xpsoft.oa.service.hrm;


import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.hrm.TrainPlan;

public interface TrainPlanService extends BaseService<TrainPlan>{
	public void saveTrainPlan(TrainPlan trainPlan,String sendMsgFlag,String userId);
	public void removeTrainPlan(String[]ids);

	public String genSn(String year);

	public List<TrainPlan> getListNew();

}


