package com.xpsoft.oa.service.hrm.impl;


import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.hrm.TrainPlanDao;
import com.xpsoft.oa.dao.hrm.TrainSurveyCourseUserDao;
import com.xpsoft.oa.dao.hrm.TrainSurveyDao;
import com.xpsoft.oa.dao.hrm.TrainSurveyUserDao;
import com.xpsoft.oa.dao.hrm.TrainUserDao;
import com.xpsoft.oa.model.hrm.TrainCourse;
import com.xpsoft.oa.model.hrm.TrainPlan;
import com.xpsoft.oa.model.hrm.TrainSurvey;
import com.xpsoft.oa.model.hrm.TrainSurveyCourseUser;
import com.xpsoft.oa.model.hrm.TrainSurveyUser;
import com.xpsoft.oa.model.hrm.TrainUser;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.hrm.TrainSurveyCourseUserService;

public class TrainSurveyCourseUserServiceImpl extends BaseServiceImpl<TrainSurveyCourseUser> implements TrainSurveyCourseUserService{
	private TrainSurveyCourseUserDao dao;
	@Resource
	private TrainPlanDao trainPlanDao;
	@Resource
	private TrainUserDao trainUserDao;
	@Resource
	private TrainSurveyDao trainSurveyDao;
	@Resource
	private TrainSurveyUserDao trainSurveyUserDao;
	public TrainSurveyCourseUserServiceImpl(TrainSurveyCourseUserDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<Object> queryTrainSurveyCourse(Long trainSurveyId,Long userId) {
		String sql = "SELECT train_course.courseId,train_course.courseNo,train_course.courseName,train_survey_course_user.interest from train_survey_course_user,train_course WHERE train_course.courseId = train_survey_course_user.trainCourseId AND trainSurveyId = ? AND train_survey_course_user.userId = ?";
		List<Object> list = dao.queryBySql(sql, new Object[]{trainSurveyId,userId});
		return list;
	}

	@Override
	public void delTrainSurveyCourse(Long trainSurveyId,Long courseId) {
		String sql = "DELETE FROM train_survey_course_user WHERE trainSurveyId= ? and trainCourseid = ?";
		Object[] obj = new Object[]{trainSurveyId,courseId};
		dao.delBySql(sql, obj);
	}
	@Override
	public void delTrainSurveyCourse(Long trainSurveyId) {
		String sql = "DELETE FROM train_survey_course_user WHERE trainSurveyId= ?";
		Object[] obj = new Object[]{trainSurveyId};
		dao.delBySql(sql, obj);
	}

	@Override
	public void updateVote(Long trainSurveyId,Long trainSurveyUserId, String[] trainCourseIds, Long userId) {
		String hql = "FROM TrainSurveyCourseUser WHERE trainSurvey.id = ? AND user.userId = ?";
		List<TrainSurveyCourseUser> list = dao.findByHql(hql, new Object[]{trainSurveyId,userId});
		if(list != null && !list.isEmpty()){
			for (TrainSurveyCourseUser trainSurveyCourseUser : list) {
				trainSurveyCourseUser.setInterest(TrainSurveyCourseUser.UN_INTEREST_COURES);
				for (String trainCourseId : trainCourseIds) {
					if(trainCourseId.equals(trainSurveyCourseUser.getTrainCourse().getCourseId().toString())){
						trainSurveyCourseUser.setInterest(TrainSurveyCourseUser.INTEREST_COURES);
					}
				}
			}
		}
		TrainSurveyUser trainSurveyUser = trainSurveyUserDao.get(trainSurveyUserId);
		trainSurveyUser.setVoted(TrainSurveyUser.VOTED);
	}

	@Override
	public List<Object> viewVoteResult(Long trainSurveyId) {
		String sql = "SELECT courseId,courseNo,courseName,COUNT(case when interest='0' then 0 end) as interestYes,COUNT(case when interest='1' then 1 end) as interestNo from train_survey_course_user,train_course WHERE trainCourseId = courseId AND trainSurveyId = ? GROUP BY trainCourseId ORDER BY interestYes DESC";
		List<Object> list = dao.queryBySql(sql, new Object[]{trainSurveyId});
		return list;
	}

	@Override
	public void saveTrainPlanByTrainSurveyCourseUser(Map<String, Object> map) {
		String trainSurveyId = (String)map.get("trainSurveyId");
		String year = (String)map.get("year");
		String[] courseIds = (String[])map.get("courseIds");
		TrainSurvey trainSurvey = trainSurveyDao.get(Long.parseLong(trainSurveyId));
		trainSurvey.setStatus(TrainSurvey.STATUS_GEN_PANEL);
		String sql = "SELECT trainCourseId,userId,interest from train_survey_course_user WHERE trainSurveyId = ? AND trainCourseId = ?";
		Integer count = null;
		DecimalFormat df3 = null;
		if(StringUtils.isNotBlank(year)){
			String pattern3="0000";
			df3 = new DecimalFormat(pattern3);
			count = trainPlanDao.queryCount(year);
		}
		for (int i = 0; i < courseIds.length; i++) {
			TrainPlan trainPlan = new TrainPlan();
			TrainCourse trainCourse = new TrainCourse();
			trainCourse.setCourseId(Long.parseLong(courseIds[i]));
			trainPlan.setTrainCourse(trainCourse);
			trainPlan.setTrainStatus(TrainPlan.UNSCHEDULED);
			if(StringUtils.isNotBlank(year)){
				int sn = count+1+i;
				trainPlan.setSn(year+"-"+df3.format(sn));
			}
			trainPlan = trainPlanDao.save(trainPlan);
			List<Object> list = dao.queryBySql(sql, new Object[]{trainSurveyId,Long.parseLong(courseIds[i])});
			if(list != null && !list.isEmpty()){
				for (int j = 0; j < list.size(); j++) {
					Object[] obj = (Object[])list.get(j);
					BigInteger userId = (BigInteger)obj[1];
					String interest = (String)obj[2];
					TrainUser trainUser = new TrainUser();
					AppUser appUser = new AppUser();
					appUser.setUserId(Long.parseLong(userId.toString()));
					trainUser.setTrainPlan(trainPlan);
					trainUser.setAppUser(appUser);
					if(TrainSurveyCourseUser.INTEREST_COURES.equals(interest)){
						trainUser.setRegist(TrainUser.REGIST);
						trainUser.setAttend(TrainUser.ATTEND);
					}else{
						trainUser.setRegist(TrainUser.UN_REGIST);
						trainUser.setAttend(TrainUser.UN_ATTEND);
					}
					trainUserDao.save(trainUser);
				}
			}
		}
	}

}