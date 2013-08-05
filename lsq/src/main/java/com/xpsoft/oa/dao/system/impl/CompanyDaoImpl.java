 package com.xpsoft.oa.dao.system.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.system.CompanyDao;
 import com.xpsoft.oa.model.system.Company;
 import java.util.List;
 
 public class CompanyDaoImpl extends BaseDaoImpl<Company>
   implements CompanyDao
 {
   public CompanyDaoImpl()
   {
     super(Company.class);
   }
 
   public List<Company> findCompany() {
     String hql = "from Company c";
     return findByHql(hql);
   }
 }

