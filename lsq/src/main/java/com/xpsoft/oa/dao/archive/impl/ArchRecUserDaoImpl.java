 package com.xpsoft.oa.dao.archive.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.archive.ArchRecUserDao;
 import com.xpsoft.oa.model.archive.ArchRecUser;
 import java.util.List;
 
 public class ArchRecUserDaoImpl extends BaseDaoImpl<ArchRecUser>
   implements ArchRecUserDao
 {
   public ArchRecUserDaoImpl()
   {
     super(ArchRecUser.class);
   }
 
   public List findDepAll()
   {
     String hql = "select ar,dp from ArchRecUser ar right join ar.department dp";
     return findByHql(hql);
   }
 
   public ArchRecUser getByDepId(Long depId)
   {
     String hql = "from ArchRecUser ar where ar.department.depId =?";
     List list = findByHql(hql, new Object[] { depId });
     if (list.size() > 0) {
       return (ArchRecUser)list.get(0);
     }
     return null;
   }
 }

