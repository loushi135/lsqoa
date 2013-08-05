package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.LocalProductApplyInfoDao;
import com.xpsoft.oa.model.statistics.LocalProductApplyInfo;
import com.xpsoft.oa.service.statistics.LocalProductApplyInfoService;

public class LocalProductApplyInfoServiceImpl extends BaseServiceImpl<LocalProductApplyInfo> implements LocalProductApplyInfoService{
	private LocalProductApplyInfoDao dao;
	
	public LocalProductApplyInfoServiceImpl(LocalProductApplyInfoDao dao) {
		super(dao);
		this.dao=dao;
	}

}