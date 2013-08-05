package com.xpsoft.test.info;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.xpsoft.test.BaseTestCase;
import com.xpsoft.oa.dao.info.NoticeViewDetailDao;
import com.xpsoft.oa.model.info.NoticeViewDetail;

public class NoticeViewDetailDaoTestCase extends BaseTestCase {
	@Resource
	private NoticeViewDetailDao noticeViewDetailDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		NoticeViewDetail noticeViewDetail=new NoticeViewDetail();
//		TODO

		noticeViewDetailDao.save(noticeViewDetail);
	}
}