package com.xpsoft.oa.service.hrm.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.oa.dao.hrm.TrainSurveyCourseUserDao;
import com.xpsoft.oa.dao.hrm.TrainSurveyDao;
import com.xpsoft.oa.dao.hrm.TrainSurveyUserDao;
import com.xpsoft.oa.dao.system.AppUserDao;
import com.xpsoft.oa.model.hrm.TrainCourse;
import com.xpsoft.oa.model.hrm.TrainSurvey;
import com.xpsoft.oa.model.hrm.TrainSurveyCourseUser;
import com.xpsoft.oa.model.hrm.TrainSurveyUser;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.hrm.TrainSurveyCourseUserService;
import com.xpsoft.oa.service.hrm.TrainSurveyService;

public class TrainSurveyServiceImpl extends BaseServiceImpl<TrainSurvey> implements TrainSurveyService{
	private TrainSurveyDao dao;
	@Resource
	private TrainSurveyCourseUserDao trainSurveyCourseUserDao;
	@Resource
	private TrainSurveyCourseUserService trainSurveyCourseUserService;
	@Resource
	private TrainSurveyUserDao trainSurveyUserDao;
	@Resource(name="appUserDao")
	private AppUserDao appUserDao;
	public TrainSurveyServiceImpl(TrainSurveyDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public TrainSurvey saveTrainSurvey(Map<String,Object> map) {
		TrainSurvey trainSurvey = (TrainSurvey)map.get("trainSurvey");
		String courseIds = (String)map.get("courseIds");
		if(trainSurvey.getId() != null){
			if(trainSurvey.getCreateTime() == null){
				trainSurvey.setCreateTime(new Date());
			}
			trainSurvey = dao.save(trainSurvey);
			//获取课程
			List<Object> list = trainSurveyCourseUserService.queryTrainSurveyCourse(trainSurvey.getId(),ContextUtil.getCurrentUserId());
			String[] originalString;
			String[] newString;
			if(list != null && !list.isEmpty()){
				originalString = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					//获取原来课程Id
					originalString[i] = ((Object[])list.get(i))[0].toString();
				}
			}else{
				originalString = new String[0];
			}
			if(StringUtils.isNotBlank(courseIds)){
				//获取新课程Id
				newString = courseIds.split(",");
			}else{
				newString = new String[0];
			}
			List<String> addlist = contrast(newString, originalString);
			List<String> dellist = contrast(originalString, newString);
			//获得所有未删除未离职的人员
			List<AppUser> appUserList = appUserDao.findByHql("from AppUser vo where vo.delFlag =? and vo.status= ?", new Object[]{(short)0,(short)1});
			//循环增加新的课程
			for (int i = 0; i < addlist.size(); i++) {
					String courseId = addlist.get(i);
					for (int j = 0; j < appUserList.size(); j++) {
						TrainSurveyCourseUser trainSurveyCourseUser = new TrainSurveyCourseUser();
						trainSurveyCourseUser.setTrainSurvey(trainSurvey);
						TrainCourse trainCourse = new TrainCourse();
						trainCourse.setCourseId(Long.parseLong(courseId));
						trainSurveyCourseUser.setTrainCourse(trainCourse);
						trainSurveyCourseUser.setUser(appUserList.get(j));
						trainSurveyCourseUser.setInterest(TrainSurveyCourseUser.UN_INTEREST_COURES);
						trainSurveyCourseUserDao.save(trainSurveyCourseUser);
					}
			}
			//循环删除课程
			for (int i = 0; i < dellist.size(); i++) {
				trainSurveyCourseUserService.delTrainSurveyCourse(trainSurvey.getId(), Long.parseLong(dellist.get(i)));
			}
		}else{
			trainSurvey.setCreateTime(new Date());
			trainSurvey.setStatus(TrainSurvey.STATUS_UNGEN_PANEL);
			trainSurvey = dao.save(trainSurvey);
			List<AppUser> list = appUserDao.findByHql("from AppUser vo where vo.delFlag =? and vo.status= ?", new Object[]{(short)0,(short)1});
			for (int i = 0; i < list.size(); i++) {
				TrainSurveyUser trainSurveyUser = new TrainSurveyUser();
				trainSurveyUser.setTrainSurvey(trainSurvey);
				trainSurveyUser.setUserId(list.get(i).getUserId());
				trainSurveyUser.setVoted(TrainSurveyUser.UN_VOTED);
				trainSurveyUserDao.save(trainSurveyUser);
			}
			if(StringUtils.isNotBlank(courseIds)){
				String[] courseIdsArr = courseIds.split(",");
				for (int i = 0; i < courseIdsArr.length; i++) {
					String courseId = courseIdsArr[i];
					for (int j = 0; j < list.size(); j++) {
						TrainSurveyCourseUser trainSurveyCourseUser = new TrainSurveyCourseUser();
						trainSurveyCourseUser.setTrainSurvey(trainSurvey);
						TrainCourse trainCourse = new TrainCourse();
						trainCourse.setCourseId(Long.parseLong(courseId));
						trainSurveyCourseUser.setTrainCourse(trainCourse);
						trainSurveyCourseUser.setUser(list.get(j));
						trainSurveyCourseUser.setInterest(TrainSurveyCourseUser.UN_INTEREST_COURES);
						trainSurveyCourseUserDao.save(trainSurveyCourseUser);
					}
				}
			}
		}
		return trainSurvey;
	}
	/**
	 * moreString 比 lessString 多出来的元素
	 * @param moreString
	 * @param lessString
	 * @return
	 */
	private List<String> contrast(String[] moreString, String[] lessString){
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < moreString.length; i++) {
			boolean flag = true;
			for (int j = 0; j < lessString.length; j++) {
				if(moreString[i].equals(lessString[j])){
					flag = false;
				}
			}
			if(flag){
				if(StringUtils.isNotBlank(moreString[i])){
					list.add(moreString[i]);
				}
			}
		}
		return list;
	}

	@Override
	public void removeTrainSurvey(String[] ids) {
		if(ids != null){
			for (String id : ids) {
				trainSurveyCourseUserService.delTrainSurveyCourse(Long.parseLong(id));
				trainSurveyUserDao.delBySql("DELETE FROM train_survey_user WHERE trainSurveyId= ?", new Object[]{id});
				dao.remove(Long.parseLong(id));
			}
		}
	}
	
}