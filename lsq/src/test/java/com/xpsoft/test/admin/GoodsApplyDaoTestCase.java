 package com.xpsoft.test.admin;
 
 import com.xpsoft.oa.dao.admin.GoodsApplyDao;
 import com.xpsoft.oa.model.admin.GoodsApply;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class GoodsApplyDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private GoodsApplyDao goodsApplyDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     GoodsApply goodsApply = new GoodsApply();
 
     this.goodsApplyDao.save(goodsApply);
   }
 }

