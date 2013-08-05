package com.xpsoft.oa.service.hrm.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.dao.hrm.TrainCourseDao;
import com.xpsoft.oa.dao.hrm.TrainPlanDao;
import com.xpsoft.oa.dao.hrm.TrainReportDao;
import com.xpsoft.oa.dao.hrm.TrainUserDao;
import com.xpsoft.oa.dao.system.AppUserDao;
import com.xpsoft.oa.model.hrm.TrainCourse;
import com.xpsoft.oa.model.hrm.TrainPlan;
import com.xpsoft.oa.model.hrm.TrainReport;
import com.xpsoft.oa.model.hrm.TrainUser;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.hrm.TrainPlanService;
import com.xpsoft.oa.service.info.ShortMessageService;

public class TrainPlanServiceImpl extends BaseServiceImpl<TrainPlan> implements TrainPlanService{
	private TrainPlanDao dao;
	@Resource
	private AppUserDao appUserdao;
	@Resource
	private TrainUserDao trainUserDao;
	@Resource
	private TrainCourseDao trainCourseDao;
	@Resource
	private TrainReportDao trainReportDao;
	
	public TrainPlanServiceImpl(TrainPlanDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public void saveTrainPlan(TrainPlan trainPlan,String sendMsgFlag,String userId) {
		//将选中的人员id放入list
		List<Long> userIdList = new ArrayList<Long>();
		if(StringUtils.isNotBlank(userId)){
			String[] userIds = userId.split(",");
			for (int i = 0; i < userIds.length; i++) {
				userIdList.add(Long.parseLong(userIds[i]));
			}
		}
		ShortMessageService smService = (ShortMessageService) AppUtil.getBean("shortMessageService");
		TrainCourse trainCourse =trainCourseDao.get(trainPlan.getTrainCourse().getCourseId());
		if(trainPlan.getId() == null){
			if(trainPlan.getTrainTime()==null){
				trainPlan.setTrainStatus(TrainPlan.UNSCHEDULED);
			}else{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(trainPlan.getTrainTime());
				trainPlan.setSn(genSn(calendar.get(Calendar.YEAR)+""));
				trainPlan.setTrainStatus(TrainPlan.SCHEDULED);
			}
			dao.save(trainPlan);
			List<AppUser> userlist = appUserdao.findByHql("from AppUser vo where vo.delFlag =? and vo.status= ?", new Object[]{(short)0,(short)1});
			for(AppUser appUser:userlist){
				if("on".equals(sendMsgFlag)){
					if(userIdList.contains(appUser.getUserId())){
						smService.save(AppUser.SYSTEM_USER, appUser.getUserId().toString(), "新开的【"+trainCourse.getCourseName()+"】培训课程指定您为参与人员，可自行在“我的培训中”取消参与！", ShortMessage.MSG_TYPE_SYS);
					}else{
						smService.save(AppUser.SYSTEM_USER, appUser.getUserId().toString(), "新开的【"+trainCourse.getCourseName()+"】培训课程请关注！", ShortMessage.MSG_TYPE_SYS);
					}
				}
				TrainUser trainUser = new TrainUser();
				if(trainPlan.getTrainTime()!=null){
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(trainPlan.getTrainTime());
					int year=calendar.get(Calendar.YEAR);
					trainUser.setYear(String.valueOf(year));
				} 
					trainUser.setAppUser(appUser);
					if(userIdList.contains(appUser.getUserId())){
						trainUser.setAttend(TrainPlan.ATTEND);
						trainUser.setRegist(TrainPlan.REGIST);
						trainUser.setCredit(TrainPlan.NOT_FULL);
					}else{
						trainUser.setAttend(TrainPlan.NOT_ATTEND);
						trainUser.setRegist(TrainPlan.NOT_REGIST);
						trainUser.setCredit(TrainPlan.NOT_FULL);
					}
					trainUser.setTrainPlan(trainPlan);
					trainUserDao.save(trainUser);
			}
		}else{
			if(!trainPlan.getTrainStatus().equals(TrainPlan.COMPLETE)){
				if(trainPlan.getTrainTime()==null){
					trainPlan.setTrainStatus(TrainPlan.UNSCHEDULED);
				}else{
					trainPlan.setTrainStatus(TrainPlan.SCHEDULED);
					if(StringUtils.isBlank(trainPlan.getSn())){
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(trainPlan.getTrainTime());
						trainPlan.setSn(genSn(calendar.get(Calendar.YEAR)+""));
						trainPlan.setTrainStatus(TrainPlan.SCHEDULED);
					}
				}
			}
			dao.save(trainPlan);
			List<TrainUser> userlist = trainUserDao.findByHql("from TrainUser where trainPlan.id = ?", new Object[]{trainPlan.getId()});
			if(userlist.isEmpty()){
				List<AppUser> appUserList = appUserdao.findByHql("from AppUser vo where vo.delFlag =? and vo.status= ?", new Object[]{(short)0,(short)1});
				for(AppUser appUser:appUserList){
					if("on".equals(sendMsgFlag)){
						if(userIdList.contains(appUser.getUserId())){
							smService.save(AppUser.SYSTEM_USER, appUser.getUserId().toString(), "新开的【"+trainCourse.getCourseName()+"】培训课程指定您为参与人员，可自行在“我的培训中”取消参与！", ShortMessage.MSG_TYPE_SYS);
						}else{
							smService.save(AppUser.SYSTEM_USER, appUser.getUserId().toString(), "新开的【"+trainCourse.getCourseName()+"】培训课程请关注！", ShortMessage.MSG_TYPE_SYS);
						}
					}
					TrainUser trainUser = new TrainUser();
					if(trainPlan.getTrainTime()!=null){
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(trainPlan.getTrainTime());
						int year=calendar.get(Calendar.YEAR);
						trainUser.setYear(String.valueOf(year));
					} 
						trainUser.setAppUser(appUser);
						trainUser.setAttend(TrainPlan.NOT_ATTEND);
						trainUser.setRegist(TrainPlan.NOT_REGIST);
						trainUser.setCredit(TrainPlan.NOT_FULL);
						trainUser.setTrainPlan(trainPlan);
						trainUserDao.save(trainUser);
				}
			}else{
				if("on".equals(sendMsgFlag)){
					List<AppUser> appUserList = appUserdao.findByHql("from AppUser vo where vo.delFlag =? and vo.status= ?", new Object[]{(short)0,(short)1});
					for(AppUser appUser:appUserList){
						if(userIdList.contains(appUser.getUserId())){
							smService.save(AppUser.SYSTEM_USER, appUser.getUserId().toString(), "新开的【"+trainCourse.getCourseName()+"】培训课程指定您为参与人员，可自行在“我的培训中”取消参与！", ShortMessage.MSG_TYPE_SYS);
						}else{
							smService.save(AppUser.SYSTEM_USER, appUser.getUserId().toString(), "新开的【"+trainCourse.getCourseName()+"】培训课程请关注！", ShortMessage.MSG_TYPE_SYS);
						}
					}
				}
				for(TrainUser trainUser:userlist){
					if(trainPlan.getTrainTime()!=null){
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(trainPlan.getTrainTime());
						int year=calendar.get(Calendar.YEAR);
						trainUser.setYear(String.valueOf(year));
					} 
					trainUserDao.save(trainUser);
				}
			}
		}
	}

	@Override
	public void removeTrainPlan(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			List<TrainUser> userlist = trainUserDao.findByHql("from TrainUser where trainPlan.id = ?", new Object[]{Long.parseLong(ids[i])});
			List<TrainReport> reportlist =trainReportDao.findByHql("from TrainReport where trainPlan.id = ?", new Object[]{Long.parseLong(ids[i])});
			for(TrainUser trainUser:userlist){
				trainUserDao.remove(trainUser);
			}
			for(TrainReport trainReport:reportlist){
				   trainReportDao.remove(trainReport);
			     }
			   dao.remove(Long.parseLong(ids[i]));
		}
	}
	public List<TrainPlan> getListNew(){
       return dao.getListNew();		
	}
	@Override
	public String genSn(String year){
		String pattern3="0000";
		java.text.DecimalFormat df3 = new java.text.DecimalFormat(pattern3);
		int count = dao.queryCount(year);
		int sn = count+1;
		return year+"-"+df3.format(sn);
	}

}