package com.xpsoft.test.flow;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.flow.HandleTaskDao;
import com.xpsoft.oa.model.flow.HandleTask;

public class HandleTaskDaoTestCase extends BaseTestCase {
	@Resource
	private HandleTaskDao handleTaskDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		HandleTask handleTask=new HandleTask();
//		TODO

		handleTaskDao.save(handleTask);
	}
}