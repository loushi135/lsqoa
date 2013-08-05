package com.xpsoft.test.statistics;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.statistics.BillAdjustDao;
import com.xpsoft.oa.model.statistics.BillAdjust;

public class BillAdjustDaoTestCase extends BaseTestCase {
	@Resource
	private BillAdjustDao billAdjustDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		BillAdjust billAdjust=new BillAdjust();
//		TODO

		billAdjustDao.save(billAdjust);
	}
}