 package com.xpsoft.oa.dao.archive.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.archive.LeaderReadDao;
 import com.xpsoft.oa.model.archive.LeaderRead;
 
 public class LeaderReadDaoImpl extends BaseDaoImpl<LeaderRead>
   implements LeaderReadDao
 {
   public LeaderReadDaoImpl()
   {
     super(LeaderRead.class);
   }
 }

