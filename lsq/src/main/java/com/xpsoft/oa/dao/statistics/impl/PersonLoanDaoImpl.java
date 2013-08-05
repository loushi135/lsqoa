package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.PersonLoanDao;
import com.xpsoft.oa.model.statistics.PersonLoan;

public class PersonLoanDaoImpl extends BaseDaoImpl<PersonLoan> implements PersonLoanDao{

	public PersonLoanDaoImpl() {
		super(PersonLoan.class);
	}

}