 package com.xpsoft.oa.service.customer.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.customer.CustomerDao;
 import com.xpsoft.oa.model.customer.Customer;
 import com.xpsoft.oa.service.customer.CustomerService;
 
 public class CustomerServiceImpl extends BaseServiceImpl<Customer>
   implements CustomerService
 {
   private CustomerDao dao;
 
   public CustomerServiceImpl(CustomerDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public boolean checkCustomerNo(String customerNo)
   {
     return this.dao.checkCustomerNo(customerNo);
   }
 }

