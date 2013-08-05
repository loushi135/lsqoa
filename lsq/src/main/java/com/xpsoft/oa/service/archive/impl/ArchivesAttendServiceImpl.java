 package com.xpsoft.oa.service.archive.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.archive.ArchivesAttendDao;
 import com.xpsoft.oa.model.archive.ArchivesAttend;
 import com.xpsoft.oa.service.archive.ArchivesAttendService;
 
 public class ArchivesAttendServiceImpl extends BaseServiceImpl<ArchivesAttend>
   implements ArchivesAttendService
 {
   private ArchivesAttendDao dao;
 
   public ArchivesAttendServiceImpl(ArchivesAttendDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }
