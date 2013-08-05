 package com.xpsoft.oa.service.archive.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.archive.ArchHastenDao;
 import com.xpsoft.oa.model.archive.ArchHasten;
 import com.xpsoft.oa.service.archive.ArchHastenService;
 import java.util.Date;
 
 public class ArchHastenServiceImpl extends BaseServiceImpl<ArchHasten>
   implements ArchHastenService
 {
   private ArchHastenDao dao;
 
   public ArchHastenServiceImpl(ArchHastenDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public Date getLeastRecordByUser(Long archivesId)
   {
     return this.dao.getLeastRecordByUser(archivesId);
   }
 }

