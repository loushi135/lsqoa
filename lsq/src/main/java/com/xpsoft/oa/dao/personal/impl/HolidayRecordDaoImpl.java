 package com.xpsoft.oa.dao.personal.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.personal.HolidayRecordDao;
 import com.xpsoft.oa.model.personal.HolidayRecord;
 
 public class HolidayRecordDaoImpl extends BaseDaoImpl<HolidayRecord>
   implements HolidayRecordDao
 {
   public HolidayRecordDaoImpl()
   {
     super(HolidayRecord.class);
   }
 }

