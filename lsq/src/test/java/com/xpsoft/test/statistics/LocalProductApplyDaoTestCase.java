package com.xpsoft.test.statistics;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.statistics.LocalProductApplyDao;
import com.xpsoft.oa.model.statistics.LocalProductApply;

public class LocalProductApplyDaoTestCase extends BaseTestCase {
	@Resource
	private LocalProductApplyDao localProductApplyDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		LocalProductApply localProductApply=new LocalProductApply();
//		TODO

		localProductApplyDao.save(localProductApply);
	}
}