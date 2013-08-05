 package com.xpsoft.oa.service.customer.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.customer.CusLinkmanDao;
 import com.xpsoft.oa.model.customer.CusLinkman;
 import com.xpsoft.oa.service.customer.CusLinkmanService;
 
 public class CusLinkmanServiceImpl extends BaseServiceImpl<CusLinkman>
   implements CusLinkmanService
 {
   private CusLinkmanDao dao;
 
   public CusLinkmanServiceImpl(CusLinkmanDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public boolean checkMainCusLinkman(Long customerId, Long linkmanId)
   {
     return this.dao.checkMainCusLinkman(customerId, linkmanId);
   }
 }

