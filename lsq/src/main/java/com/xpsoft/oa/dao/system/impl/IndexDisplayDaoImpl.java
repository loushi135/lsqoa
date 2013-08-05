 package com.xpsoft.oa.dao.system.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.system.IndexDisplayDao;
 import com.xpsoft.oa.model.system.IndexDisplay;
 import java.util.List;
 
 public class IndexDisplayDaoImpl extends BaseDaoImpl<IndexDisplay>
   implements IndexDisplayDao
 {
   public IndexDisplayDaoImpl()
   {
     super(IndexDisplay.class);
   }
 
   public List<IndexDisplay> findByUser(Long userId)
   {
     String hql = "from IndexDisplay vo where vo.appUser.userId=?";
     return findByHql(hql, new Object[] { userId });
   }
 }

