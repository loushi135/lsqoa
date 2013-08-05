package com.xpsoft.test.statistics;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.statistics.EndInnerCheckDao;
import com.xpsoft.oa.model.statistics.EndInnerCheck;

public class EndInnerCheckDaoTestCase extends BaseTestCase {
	@Resource
	private EndInnerCheckDao endInnerCheckDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		EndInnerCheck endInnerCheck=new EndInnerCheck();
//		TODO

		endInnerCheckDao.save(endInnerCheck);
	}
}