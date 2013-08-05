 package com.xpsoft.oa.service.communicate.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.communicate.PhoneBookDao;
 import com.xpsoft.oa.model.communicate.PhoneBook;
 import com.xpsoft.oa.service.communicate.PhoneBookService;
 import java.util.List;
 
 public class PhoneBookServiceImpl extends BaseServiceImpl<PhoneBook>
   implements PhoneBookService
 {
   private PhoneBookDao dao;
 
   public PhoneBookServiceImpl(PhoneBookDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<PhoneBook> sharedPhoneBooks(String fullname, String ownerName, PagingBean pb)
   {
     return this.dao.sharedPhoneBooks(fullname, ownerName, pb);
   }
 }

