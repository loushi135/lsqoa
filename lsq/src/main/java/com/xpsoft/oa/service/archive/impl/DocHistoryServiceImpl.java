 package com.xpsoft.oa.service.archive.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.archive.DocHistoryDao;
 import com.xpsoft.oa.model.archive.DocHistory;
 import com.xpsoft.oa.service.archive.DocHistoryService;
 
 public class DocHistoryServiceImpl extends BaseServiceImpl<DocHistory>
   implements DocHistoryService
 {
   private DocHistoryDao dao;
 
   public DocHistoryServiceImpl(DocHistoryDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

