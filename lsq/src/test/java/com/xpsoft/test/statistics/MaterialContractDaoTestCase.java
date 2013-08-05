package com.xpsoft.test.statistics;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.statistics.MaterialContractDao;
import com.xpsoft.oa.model.statistics.MaterialContract;

public class MaterialContractDaoTestCase extends BaseTestCase {
	@Resource
	private MaterialContractDao materialContractDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		MaterialContract materialContract=new MaterialContract();
//		TODO

		materialContractDao.save(materialContract);
	}
}