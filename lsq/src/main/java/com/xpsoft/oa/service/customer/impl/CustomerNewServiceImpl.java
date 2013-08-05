package com.xpsoft.oa.service.customer.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.customer.CustomerNewDao;
import com.xpsoft.oa.model.customer.CustomerNew;
import com.xpsoft.oa.service.customer.CustomerNewService;

public class CustomerNewServiceImpl extends BaseServiceImpl<CustomerNew> implements CustomerNewService{
	private CustomerNewDao dao;
	
	public CustomerNewServiceImpl(CustomerNewDao dao) {
		super(dao);
		this.dao=dao;
	}

}