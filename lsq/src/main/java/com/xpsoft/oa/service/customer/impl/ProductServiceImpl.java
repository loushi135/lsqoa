 package com.xpsoft.oa.service.customer.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.customer.ProductDao;
 import com.xpsoft.oa.model.customer.Product;
 import com.xpsoft.oa.service.customer.ProductService;
 
 public class ProductServiceImpl extends BaseServiceImpl<Product>
   implements ProductService
 {
   private ProductDao dao;
 
   public ProductServiceImpl(ProductDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

