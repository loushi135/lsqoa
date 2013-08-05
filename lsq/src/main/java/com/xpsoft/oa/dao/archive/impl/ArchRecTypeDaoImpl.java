 package com.xpsoft.oa.dao.archive.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.archive.ArchRecTypeDao;
 import com.xpsoft.oa.model.archive.ArchRecType;
 
 public class ArchRecTypeDaoImpl extends BaseDaoImpl<ArchRecType>
   implements ArchRecTypeDao
 {
   public ArchRecTypeDaoImpl()
   {
     super(ArchRecType.class);
   }
 }

