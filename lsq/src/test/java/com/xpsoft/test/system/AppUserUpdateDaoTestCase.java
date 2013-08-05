package com.xpsoft.test.system;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.system.AppUserUpdateDao;
import com.xpsoft.oa.model.system.AppUserUpdate;

public class AppUserUpdateDaoTestCase extends BaseTestCase {
	@Resource
	private AppUserUpdateDao appUserUpdateDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		AppUserUpdate appUserUpdate=new AppUserUpdate();
//		TODO

		appUserUpdateDao.save(appUserUpdate);
	}
}