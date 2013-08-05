 package com.xpsoft.oa.dao.admin.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.admin.DepreRecordDao;
 import com.xpsoft.oa.model.admin.DepreRecord;
 import java.util.Date;
 import java.util.List;
 import org.hibernate.Query;
 import org.hibernate.Session;
 
 public class DepreRecordDaoImpl extends BaseDaoImpl<DepreRecord>
   implements DepreRecordDao
 {
   public DepreRecordDaoImpl()
   {
     super(DepreRecord.class);
   }
 
   public Date findMaxDate(Long assetsId)
   {
     String hql = "select max(vo.calTime) from DepreRecord vo where vo.fixedAssets.assetsId=?";
     Query query = getSession().createQuery(hql);
     query.setLong(0, assetsId.longValue());
     Date date = (Date)query.list().get(0);
     return date;
   }
 }

