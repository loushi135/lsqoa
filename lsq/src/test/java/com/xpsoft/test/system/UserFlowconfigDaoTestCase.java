package com.xpsoft.test.system;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.system.UserFlowconfigDao;
import com.xpsoft.oa.model.system.UserFlowconfig;

public class UserFlowconfigDaoTestCase extends BaseTestCase {
	@Resource
	private UserFlowconfigDao userFlowconfigDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		UserFlowconfig userFlowconfig=new UserFlowconfig();
//		TODO

		userFlowconfigDao.save(userFlowconfig);
	}
}