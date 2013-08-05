 package com.xpsoft.oa.dao.customer.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.customer.ProductDao;
 import com.xpsoft.oa.model.customer.Product;
 
 public class ProductDaoImpl extends BaseDaoImpl<Product>
   implements ProductDao
 {
   public ProductDaoImpl()
   {
     super(Product.class);
   }
 }

