 package com.xpsoft.oa.dao.archive.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.archive.ArchivesAttendDao;
 import com.xpsoft.oa.model.archive.ArchivesAttend;
 
 public class ArchivesAttendDaoImpl extends BaseDaoImpl<ArchivesAttend>
   implements ArchivesAttendDao
 {
   public ArchivesAttendDaoImpl()
   {
     super(ArchivesAttend.class);
   }
 }

