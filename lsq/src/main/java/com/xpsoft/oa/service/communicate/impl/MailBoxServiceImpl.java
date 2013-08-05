 package com.xpsoft.oa.service.communicate.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.communicate.MailBoxDao;
 import com.xpsoft.oa.model.communicate.MailBox;
 import com.xpsoft.oa.service.communicate.MailBoxService;
 import java.util.List;
 
 public class MailBoxServiceImpl extends BaseServiceImpl<MailBox>
   implements MailBoxService
 {
   private MailBoxDao dao;
 
   public MailBoxServiceImpl(MailBoxDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public Long CountByFolderId(Long folderId)
   {
     return this.dao.CountByFolderId(folderId);
   }
 
   public List<MailBox> findByFolderId(Long folderId) {
     return this.dao.findByFolderId(folderId);
   }
 
   public List<MailBox> findBySearch(String searchContent, PagingBean pb)
   {
     return this.dao.findBySearch(searchContent, pb);
   }
 }

