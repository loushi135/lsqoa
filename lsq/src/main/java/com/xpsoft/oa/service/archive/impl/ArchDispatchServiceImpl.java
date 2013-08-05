 package com.xpsoft.oa.service.archive.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.archive.ArchDispatchDao;
 import com.xpsoft.oa.model.archive.ArchDispatch;
 import com.xpsoft.oa.model.system.AppUser;
 import com.xpsoft.oa.service.archive.ArchDispatchService;
 import java.util.List;
 
 public class ArchDispatchServiceImpl extends BaseServiceImpl<ArchDispatch>
   implements ArchDispatchService
 {
   private ArchDispatchDao dao;
 
   public ArchDispatchServiceImpl(ArchDispatchDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<ArchDispatch> findByUser(AppUser user, PagingBean pb)
   {
     return this.dao.findByUser(user, pb);
   }
 
   public int countArchDispatch(Long archivesId)
   {
     return this.dao.findRecordByArc(archivesId).size();
   }
 }

