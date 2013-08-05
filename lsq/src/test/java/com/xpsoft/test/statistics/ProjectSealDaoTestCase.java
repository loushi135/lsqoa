package com.xpsoft.test.statistics;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.statistics.ProjectSealDao;
import com.xpsoft.oa.model.statistics.ProjectSeal;

public class ProjectSealDaoTestCase extends BaseTestCase {
	@Resource
	private ProjectSealDao projectSealDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		ProjectSeal projectSeal=new ProjectSeal();
//		TODO

		projectSealDao.save(projectSeal);
	}
}