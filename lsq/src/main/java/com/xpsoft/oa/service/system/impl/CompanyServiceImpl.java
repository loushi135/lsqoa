 package com.xpsoft.oa.service.system.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.system.CompanyDao;
 import com.xpsoft.oa.model.system.Company;
 import com.xpsoft.oa.service.system.CompanyService;
 import java.util.List;
 
 public class CompanyServiceImpl extends BaseServiceImpl<Company>
   implements CompanyService
 {
   private CompanyDao companyDao;
 
   public CompanyServiceImpl(CompanyDao companyDao)
   {
     super(companyDao);
     this.companyDao = companyDao;
   }
 
   public List<Company> findCompany()
   {
     return this.companyDao.findCompany();
   }
 
   public List<Company> findByHql(String hql)
   {
     return null;
   }
 }

