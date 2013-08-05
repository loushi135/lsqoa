package com.xpsoft.oa.service.hrm;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.hrm.TrainReport;

public interface TrainReportService extends BaseService<TrainReport>{
	public void  saveTrainReport(String[] trainReportIds,TrainReport trainReport);
}


