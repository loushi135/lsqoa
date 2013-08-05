 package com.xpsoft.oa.service.admin.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.admin.CartRepairDao;
 import com.xpsoft.oa.model.admin.CartRepair;
 import com.xpsoft.oa.service.admin.CartRepairService;
 
 public class CartRepairServiceImpl extends BaseServiceImpl<CartRepair>
   implements CartRepairService
 {
   private CartRepairDao dao;
 
   public CartRepairServiceImpl(CartRepairDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

