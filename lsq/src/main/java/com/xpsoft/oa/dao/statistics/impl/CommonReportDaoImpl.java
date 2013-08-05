package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.CommonReportDao;
import com.xpsoft.oa.model.statistics.CommonReport;

public class CommonReportDaoImpl extends BaseDaoImpl<CommonReport> implements CommonReportDao{

	public CommonReportDaoImpl() {
		super(CommonReport.class);
	}

}