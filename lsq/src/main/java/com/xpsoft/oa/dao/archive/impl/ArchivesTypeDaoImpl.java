 package com.xpsoft.oa.dao.archive.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.archive.ArchivesTypeDao;
 import com.xpsoft.oa.model.archive.ArchivesType;
 
 public class ArchivesTypeDaoImpl extends BaseDaoImpl<ArchivesType>
   implements ArchivesTypeDao
 {
   public ArchivesTypeDaoImpl()
   {
     super(ArchivesType.class);
   }
 }

