package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.CommonReportDao;
import com.xpsoft.oa.model.statistics.CommonReport;
import com.xpsoft.oa.service.statistics.CommonReportService;

public class CommonReportServiceImpl extends BaseServiceImpl<CommonReport> implements CommonReportService{
	private CommonReportDao dao;
	
	public CommonReportServiceImpl(CommonReportDao dao) {
		super(dao);
		this.dao=dao;
	}

}