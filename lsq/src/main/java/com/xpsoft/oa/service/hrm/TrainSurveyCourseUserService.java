package com.xpsoft.oa.service.hrm;


import java.util.List;
import java.util.Map;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.hrm.TrainSurveyCourseUser;

public interface TrainSurveyCourseUserService extends BaseService<TrainSurveyCourseUser>{
	public List<Object> queryTrainSurveyCourse(Long trainSurveyId,Long userId);
	public List<Object> viewVoteResult(Long trainSurveyId);
	public void updateVote(Long trainSurveyId,Long trainSurveyUserId,String[] trainCourseIds ,Long userId);
	/**
	 * 
	 * @param trainSurveyId
	 * @param courseId
	 */
	public void delTrainSurveyCourse(Long trainSurveyId,Long courseId);
	public void delTrainSurveyCourse(Long trainSurveyId);
	
	public void saveTrainPlanByTrainSurveyCourseUser(Map<String, Object> map);
}


