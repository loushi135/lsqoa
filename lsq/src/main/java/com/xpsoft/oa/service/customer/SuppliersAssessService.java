package com.xpsoft.oa.service.customer;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.customer.SuppliersAssess;

public interface SuppliersAssessService extends BaseService<SuppliersAssess>{
	
	public String getMaxNo(String search);

	public SuppliersAssess getByName(String assessName);

	public SuppliersAssess getByName(String assessName, String assessNO);
}


