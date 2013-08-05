package com.xpsoft.test.admin;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.admin.ExpressApplyDao;
import com.xpsoft.oa.model.admin.ExpressApply;

public class ExpressApplyDaoTestCase extends BaseTestCase {
	@Resource
	private ExpressApplyDao expressApplyDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		ExpressApply expressApply=new ExpressApply();
//		TODO

		expressApplyDao.save(expressApply);
	}
}