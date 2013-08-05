 package com.xpsoft.oa.service.admin.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.admin.DepreRecordDao;
 import com.xpsoft.oa.model.admin.DepreRecord;
 import com.xpsoft.oa.service.admin.DepreRecordService;
 import java.util.Date;
 
 public class DepreRecordServiceImpl extends BaseServiceImpl<DepreRecord>
   implements DepreRecordService
 {
   private DepreRecordDao dao;
 
   public DepreRecordServiceImpl(DepreRecordDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public Date findMaxDate(Long assetsId)
   {
     return this.dao.findMaxDate(assetsId);
   }
 }

