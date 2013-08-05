 package com.xpsoft.test.personal;
 
 import com.xpsoft.oa.dao.personal.HolidayRecordDao;
 import com.xpsoft.oa.model.personal.HolidayRecord;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class HolidayRecordDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private HolidayRecordDao holidayRecordDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     HolidayRecord holidayRecord = new HolidayRecord();
 
     this.holidayRecordDao.save(holidayRecord);
   }
 }

