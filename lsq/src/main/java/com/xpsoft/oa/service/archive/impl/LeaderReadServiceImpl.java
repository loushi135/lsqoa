 package com.xpsoft.oa.service.archive.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.archive.LeaderReadDao;
 import com.xpsoft.oa.model.archive.LeaderRead;
 import com.xpsoft.oa.service.archive.LeaderReadService;
 
 public class LeaderReadServiceImpl extends BaseServiceImpl<LeaderRead>
   implements LeaderReadService
 {
   private LeaderReadDao dao;
 
   public LeaderReadServiceImpl(LeaderReadDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

