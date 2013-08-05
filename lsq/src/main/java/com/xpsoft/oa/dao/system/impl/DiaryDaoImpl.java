 package com.xpsoft.oa.dao.system.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.system.DiaryDao;
 import com.xpsoft.oa.model.system.Diary;
 import java.util.List;
 
 public class DiaryDaoImpl extends BaseDaoImpl<Diary>
   implements DiaryDao
 {
   public DiaryDaoImpl()
   {
     super(Diary.class);
   }
 
   public List<Diary> getSubDiary(String userIds, PagingBean pb)
   {
     String hql = "from Diary vo where vo.appUser.userId in (" + userIds + ") and vo.diaryType=1";
     return findByHql(hql, null, pb);
   }
 }

