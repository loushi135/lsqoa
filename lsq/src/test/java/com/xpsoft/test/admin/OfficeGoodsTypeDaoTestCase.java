 package com.xpsoft.test.admin;
 
 import com.xpsoft.oa.dao.admin.OfficeGoodsTypeDao;
 import com.xpsoft.oa.model.admin.OfficeGoodsType;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class OfficeGoodsTypeDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private OfficeGoodsTypeDao officeGoodsTypeDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     OfficeGoodsType officeGoodsType = new OfficeGoodsType();
 
     this.officeGoodsTypeDao.save(officeGoodsType);
   }
 }

