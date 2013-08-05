package com.xpsoft.oa.service.hrm;


import java.util.Map;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.hrm.TrainSurvey;

public interface TrainSurveyService extends BaseService<TrainSurvey>{
	public TrainSurvey saveTrainSurvey(Map<String,Object> map);
	public void removeTrainSurvey(String[]ids);
}


