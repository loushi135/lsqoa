 package com.xpsoft.oa.service.info.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.info.NoticeDao;
 import com.xpsoft.oa.model.info.Notice;
 import com.xpsoft.oa.service.info.NoticeService;
 import java.util.Date;
 import java.util.List;
 
 public class NoticeServiceImpl extends BaseServiceImpl<Notice>
   implements NoticeService
 {
   private NoticeDao noticeDao;
 
   public NoticeServiceImpl(NoticeDao noticeDao)
   {
     super(noticeDao);
     this.noticeDao = noticeDao;
   }
 
   public List<Notice> findByNoticeId(Long noticeId, PagingBean pb)
   {
     return this.noticeDao.findByNoticeId(noticeId, pb);
   }
 
   public List<Notice> findBySearch(Notice notice, Date from, Date to, PagingBean pb)
   {
     return this.noticeDao.findBySearch(notice, from, to, pb);
   }
 
   public List<Notice> findBySearch(String searchContent, PagingBean pb)
   {
     return this.noticeDao.findBySearch(searchContent, pb);
   }
 }

