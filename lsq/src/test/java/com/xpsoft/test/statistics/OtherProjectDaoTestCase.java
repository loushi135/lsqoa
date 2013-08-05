package com.xpsoft.test.statistics;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.statistics.OtherProjectDao;
import com.xpsoft.oa.model.statistics.OtherProject;

public class OtherProjectDaoTestCase extends BaseTestCase {
	@Resource
	private OtherProjectDao otherProjectDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		OtherProject otherProject=new OtherProject();
//		TODO

		otherProjectDao.save(otherProject);
	}
}