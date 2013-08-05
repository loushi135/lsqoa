 package com.xpsoft.test.flow;
 
 import com.xpsoft.oa.dao.flow.ProTypeDao;
 import com.xpsoft.oa.model.flow.ProType;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ProTypeDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ProTypeDao proTypeDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ProType proType = new ProType();
 
     this.proTypeDao.save(proType);
   }
 }

