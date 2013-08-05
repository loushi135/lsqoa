 package com.xpsoft.oa.dao.archive.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.archive.ArchHastenDao;
 import com.xpsoft.oa.model.archive.ArchHasten;
 import java.util.Date;
 import java.util.List;
 
 public class ArchHastenDaoImpl extends BaseDaoImpl<ArchHasten>
   implements ArchHastenDao
 {
   public ArchHastenDaoImpl()
   {
     super(ArchHasten.class);
   }
 
   public Date getLeastRecordByUser(Long archivesId)
   {
     String hql = "from ArchHasten vo where vo.archives.archivesId=? order by vo.createtime desc";
     List list = findByHql(hql, new Object[] { archivesId });
     if (list.size() > 0) {
       return ((ArchHasten)list.get(0)).getCreatetime();
     }
     return null;
   }
 }

