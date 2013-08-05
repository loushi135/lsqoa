 package com.xpsoft.test.system;
 
 import com.xpsoft.oa.dao.system.DiaryDao;
 import com.xpsoft.oa.model.system.Diary;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DiaryDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DiaryDao diaryDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     Diary diary = new Diary();
 
     this.diaryDao.save(diary);
   }
 }

