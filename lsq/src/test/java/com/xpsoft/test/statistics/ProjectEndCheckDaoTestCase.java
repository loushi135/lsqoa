package com.xpsoft.test.statistics;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.statistics.ProjectEndCheckDao;
import com.xpsoft.oa.model.statistics.ProjectEndCheck;

public class ProjectEndCheckDaoTestCase extends BaseTestCase {
	@Resource
	private ProjectEndCheckDao projectEndCheckDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		ProjectEndCheck projectEndCheck=new ProjectEndCheck();
//		TODO

		projectEndCheckDao.save(projectEndCheck);
	}
}