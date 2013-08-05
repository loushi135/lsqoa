package com.xpsoft.oa.dao.hrm.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.hrm.TrainReportDao;
import com.xpsoft.oa.model.hrm.TrainReport;

public class TrainReportDaoImpl extends BaseDaoImpl<TrainReport> implements TrainReportDao{

	public TrainReportDaoImpl() {
		super(TrainReport.class);
	}

}