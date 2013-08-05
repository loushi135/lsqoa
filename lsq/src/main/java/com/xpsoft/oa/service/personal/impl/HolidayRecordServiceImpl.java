 package com.xpsoft.oa.service.personal.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.personal.HolidayRecordDao;
 import com.xpsoft.oa.model.personal.HolidayRecord;
 import com.xpsoft.oa.service.personal.HolidayRecordService;
 
 public class HolidayRecordServiceImpl extends BaseServiceImpl<HolidayRecord>
   implements HolidayRecordService
 {
   private HolidayRecordDao dao;
 
   public HolidayRecordServiceImpl(HolidayRecordDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

