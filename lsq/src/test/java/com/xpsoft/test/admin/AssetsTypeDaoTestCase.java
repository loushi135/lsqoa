 package com.xpsoft.test.admin;
 
 import com.xpsoft.oa.dao.admin.AssetsTypeDao;
 import com.xpsoft.oa.model.admin.AssetsType;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class AssetsTypeDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private AssetsTypeDao assetsTypeDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     AssetsType assetsType = new AssetsType();
 
     this.assetsTypeDao.save(assetsType);
   }
 }

