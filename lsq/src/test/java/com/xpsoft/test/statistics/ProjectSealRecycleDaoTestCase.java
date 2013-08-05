package com.xpsoft.test.statistics;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.statistics.ProjectSealRecycleDao;
import com.xpsoft.oa.model.statistics.ProjectSealRecycle;

public class ProjectSealRecycleDaoTestCase extends BaseTestCase {
	@Resource
	private ProjectSealRecycleDao projectSealRecycleDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		ProjectSealRecycle projectSealRecycle=new ProjectSealRecycle();
//		TODO

		projectSealRecycleDao.save(projectSealRecycle);
	}
}