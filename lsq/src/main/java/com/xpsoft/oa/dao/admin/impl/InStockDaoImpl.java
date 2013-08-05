 package com.xpsoft.oa.dao.admin.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.admin.InStockDao;
 import com.xpsoft.oa.model.admin.InStock;
 import java.util.Iterator;
 import java.util.List;
 import org.hibernate.Query;
 import org.hibernate.Session;
 
 public class InStockDaoImpl extends BaseDaoImpl<InStock>
   implements InStockDao
 {
   public InStockDaoImpl()
   {
     super(InStock.class);
   }
 
   public Integer findInCountByBuyId(Long buyId)
   {
     String hql = "select vo.inCounts from InStock vo where vo.buyId=?";
     Query query = getSession().createQuery(hql);
     query.setLong(0, buyId.longValue());
     Integer inCount = Integer.valueOf(Integer.parseInt(query.list().iterator().next().toString()));
     return inCount;
   }
 }

