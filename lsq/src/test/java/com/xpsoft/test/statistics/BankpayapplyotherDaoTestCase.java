package com.xpsoft.test.statistics;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.statistics.BankpayapplyotherDao;
import com.xpsoft.oa.model.statistics.Bankpayapplyother;

public class BankpayapplyotherDaoTestCase extends BaseTestCase {
	@Resource
	private BankpayapplyotherDao bankpayapplyotherDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		Bankpayapplyother bankpayapplyother=new Bankpayapplyother();
//		TODO

		bankpayapplyotherDao.save(bankpayapplyother);
	}
}