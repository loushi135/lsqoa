 package com.xpsoft.oa.service.archive.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.archive.ArchivesTypeDao;
 import com.xpsoft.oa.model.archive.ArchivesType;
 import com.xpsoft.oa.service.archive.ArchivesTypeService;
 
 public class ArchivesTypeServiceImpl extends BaseServiceImpl<ArchivesType>
   implements ArchivesTypeService
 {
   private ArchivesTypeDao dao;
 
   public ArchivesTypeServiceImpl(ArchivesTypeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

