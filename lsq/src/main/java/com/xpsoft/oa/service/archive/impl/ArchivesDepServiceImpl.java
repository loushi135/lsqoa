 package com.xpsoft.oa.service.archive.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.archive.ArchivesDepDao;
 import com.xpsoft.oa.model.archive.ArchivesDep;
 import com.xpsoft.oa.service.archive.ArchivesDepService;
 
 public class ArchivesDepServiceImpl extends BaseServiceImpl<ArchivesDep>
   implements ArchivesDepService
 {
   private ArchivesDepDao dao;
 
   public ArchivesDepServiceImpl(ArchivesDepDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

