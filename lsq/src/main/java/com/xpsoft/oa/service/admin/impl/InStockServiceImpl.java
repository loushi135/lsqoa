 package com.xpsoft.oa.service.admin.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.admin.InStockDao;
 import com.xpsoft.oa.model.admin.InStock;
 import com.xpsoft.oa.service.admin.InStockService;
 
 public class InStockServiceImpl extends BaseServiceImpl<InStock>
   implements InStockService
 {
   private InStockDao dao;
 
   public InStockServiceImpl(InStockDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public Integer findInCountByBuyId(Long buyId)
   {
     return this.dao.findInCountByBuyId(buyId);
   }
 }

