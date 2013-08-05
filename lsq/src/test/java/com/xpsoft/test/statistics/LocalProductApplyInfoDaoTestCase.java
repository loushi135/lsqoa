package com.xpsoft.test.statistics;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.statistics.LocalProductApplyInfoDao;
import com.xpsoft.oa.model.statistics.LocalProductApplyInfo;

public class LocalProductApplyInfoDaoTestCase extends BaseTestCase {
	@Resource
	private LocalProductApplyInfoDao localProductApplyInfoDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		LocalProductApplyInfo localProductApplyInfo=new LocalProductApplyInfo();
//		TODO

		localProductApplyInfoDao.save(localProductApplyInfo);
	}
}