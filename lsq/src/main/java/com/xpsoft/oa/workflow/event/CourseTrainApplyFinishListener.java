package com.xpsoft.oa.workflow.event;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.oa.model.hrm.TrainApply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.service.hrm.TrainApplyService;
import com.xpsoft.oa.service.hrm.TrainCourseService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;

public class CourseTrainApplyFinishListener implements EventListener {
	private Log logger=LogFactory.getLog(CourseTrainApplyFinishListener.class);

	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.info("enter the CourseTrainApplyFinishListener notify method...");
		}
		TrainApplyService trainApplyService = (TrainApplyService) AppUtil.getBean("trainApplyService");
		AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
		DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
		TrainCourseService trainCourseService = (TrainCourseService)AppUtil.getBean("trainCourseService");
		OpenProcessInstance  openProcessInstance = execution.getProcessInstance();
		
		String userId = (String)openProcessInstance.getVariable("trainApply.applyUser.userId");
		String depId = (String)openProcessInstance.getVariable("trainApply.department.depId");
		String trainTarget = (String)openProcessInstance.getVariable("trainApply.trainTarget");
		String trainType = (String)openProcessInstance.getVariable("trainApply.trainType");
		String courseId = (String)openProcessInstance.getVariable("trainApply.trainCourse.courseId");
		String createTime = (String)openProcessInstance.getVariable("trainApply.createTime");
		
		AppUser appUser = null;
		Department department = null;
		if(StringUtils.isNotBlank(userId)){
			appUser = appUserService.get(Long.parseLong(userId));
			department = appUser.getDepartment();
		}
		String[] courses = courseId.split(",");
		TrainApply trainApply = null;
		for (int i = 0; i < courses.length; i++) {
			trainApply = new TrainApply();
			trainApply.setApplyUser(appUser);
			trainApply.setDepartment(department);
			trainApply.setTrainType(trainType);
			trainApply.setTrainTarget(trainTarget);
			trainApply.setCreateTime(DateUtil.parseDate(createTime));
			trainApply.setTrainCourse(trainCourseService.get(Long.parseLong(courses[i])));
			trainApplyService.save(trainApply);
		}
		
	}

}
