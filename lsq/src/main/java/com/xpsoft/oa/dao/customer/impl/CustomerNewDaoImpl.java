package com.xpsoft.oa.dao.customer.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.customer.CustomerNewDao;
import com.xpsoft.oa.model.customer.CustomerNew;

public class CustomerNewDaoImpl extends BaseDaoImpl<CustomerNew> implements CustomerNewDao{

	public CustomerNewDaoImpl() {
		super(CustomerNew.class);
	}

}