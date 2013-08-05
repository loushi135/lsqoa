 package com.xpsoft.oa.dao.archive.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.archive.ArchivesDocDao;
 import com.xpsoft.oa.model.archive.ArchivesDoc;
 import java.util.List;
 
 public class ArchivesDocDaoImpl extends BaseDaoImpl<ArchivesDoc>
   implements ArchivesDocDao
 {
   public ArchivesDocDaoImpl()
   {
     super(ArchivesDoc.class);
   }
 
   public List<ArchivesDoc> findByAid(Long archivesId)
   {
     String hql = "from ArchivesDoc vo where vo.archives.archivesId=?";
     Object[] objs = { archivesId };
     return findByHql(hql, objs);
   }
 }

