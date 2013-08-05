package com.xpsoft.oa.service.hrm.impl;


import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.hrm.TrainPlanDao;
import com.xpsoft.oa.dao.hrm.TrainReportDao;
import com.xpsoft.oa.dao.system.FileAttachDao;
import com.xpsoft.oa.model.hrm.TrainPlan;
import com.xpsoft.oa.model.hrm.TrainReport;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.hrm.TrainReportService;

public class TrainReportServiceImpl extends BaseServiceImpl<TrainReport> implements TrainReportService{
	private TrainReportDao dao;
	@Resource
	private FileAttachDao fileAttachDao;
	@Resource
	private TrainPlanDao trainPlanDao;
	public TrainReportServiceImpl(TrainReportDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public void saveTrainReport(String[] trainReportIds, TrainReport trainReport) {
		String[] ids =trainReportIds;
		if(trainReportIds!=null){
			for(int i=0;i<ids.length;i++){
				if(StringUtils.isNotBlank(ids[i])){
					FileAttach fileAttach=fileAttachDao.get(new Long(ids[i]));
					trainReport.getTrainReportFiles().add(fileAttach);
				}
			}
		}
	     Long trainPlanId =trainReport.getTrainPlan().getId();
	     TrainPlan trainPlan =trainPlanDao.get(trainPlanId);
	 	 if(trainPlan.getTrainTime()!=null){
	 		  trainPlan.setTrainStatus(TrainPlan.COMPLETE);
	 	 }
	     trainPlanDao.save(trainPlan);
		dao.save(trainReport);
	}

}