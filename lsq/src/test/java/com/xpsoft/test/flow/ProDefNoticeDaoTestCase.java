package com.xpsoft.test.flow;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.flow.ProDefNoticeDao;
import com.xpsoft.oa.model.flow.ProDefNotice;

public class ProDefNoticeDaoTestCase extends BaseTestCase {
	@Resource
	private ProDefNoticeDao proDefNoticeDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		ProDefNotice proDefNotice=new ProDefNotice();
//		TODO

		proDefNoticeDao.save(proDefNotice);
	}
}