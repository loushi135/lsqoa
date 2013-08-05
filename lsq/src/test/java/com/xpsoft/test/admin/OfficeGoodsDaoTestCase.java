 package com.xpsoft.test.admin;
 
 import com.xpsoft.oa.dao.admin.OfficeGoodsDao;
 import com.xpsoft.test.BaseTestCase;
 import java.io.PrintStream;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import javax.annotation.Resource;
 import org.junit.Test;
 
 public class OfficeGoodsDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private OfficeGoodsDao officeGoodsDao;
 
   @Test
   public void test1()
   {
     Date date = new Date();
     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
     System.out.print(sdf.format(date));
   }
 }

