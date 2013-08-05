 package com.xpsoft.oa.service.system.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.system.IndexDisplayDao;
 import com.xpsoft.oa.model.system.IndexDisplay;
 import com.xpsoft.oa.service.system.IndexDisplayService;
 import java.util.List;
 
 public class IndexDisplayServiceImpl extends BaseServiceImpl<IndexDisplay>
   implements IndexDisplayService
 {
   private IndexDisplayDao dao;
 
   public IndexDisplayServiceImpl(IndexDisplayDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<IndexDisplay> findByUser(Long userId)
   {
     return this.dao.findByUser(userId);
   }
 }

