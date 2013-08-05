package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.PersonLoanDao;
import com.xpsoft.oa.model.statistics.PersonLoan;
import com.xpsoft.oa.service.statistics.PersonLoanService;

public class PersonLoanServiceImpl extends BaseServiceImpl<PersonLoan> implements PersonLoanService{
	private PersonLoanDao dao;
	
	public PersonLoanServiceImpl(PersonLoanDao dao) {
		super(dao);
		this.dao=dao;
	}

}