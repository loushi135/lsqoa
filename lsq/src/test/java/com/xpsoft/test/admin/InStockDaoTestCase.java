 package com.xpsoft.test.admin;
 
 import com.xpsoft.oa.dao.admin.InStockDao;
 import com.xpsoft.test.BaseTestCase;
 import java.io.PrintStream;
 import javax.annotation.Resource;
 import org.junit.Test;
 
 public class InStockDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private InStockDao inStockDao;
 
   @Test
   public void test1()
   {
     Integer inCount = this.inStockDao.findInCountByBuyId(Long.valueOf(1L));
     System.out.println(inCount);
   }
 }

