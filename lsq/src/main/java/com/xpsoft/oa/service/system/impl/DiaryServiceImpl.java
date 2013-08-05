 package com.xpsoft.oa.service.system.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.system.DiaryDao;
 import com.xpsoft.oa.model.system.Diary;
 import com.xpsoft.oa.service.system.DiaryService;
 import java.util.List;
 
 public class DiaryServiceImpl extends BaseServiceImpl<Diary>
   implements DiaryService
 {
   private DiaryDao dao;
 
   public DiaryServiceImpl(DiaryDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<Diary> getAllBySn(PagingBean pb)
   {
     return null;
   }
 
   public List<Diary> getSubDiary(String userIds, PagingBean pb)
   {
     return this.dao.getSubDiary(userIds, pb);
   }
 }

