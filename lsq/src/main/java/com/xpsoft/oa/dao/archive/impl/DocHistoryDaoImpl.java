 package com.xpsoft.oa.dao.archive.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.archive.DocHistoryDao;
 import com.xpsoft.oa.model.archive.DocHistory;
 
 public class DocHistoryDaoImpl extends BaseDaoImpl<DocHistory>
   implements DocHistoryDao
 {
   public DocHistoryDaoImpl()
   {
     super(DocHistory.class);
   }
 }

