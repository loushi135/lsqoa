 package com.xpsoft.test.communicate;
 
 import com.xpsoft.oa.dao.communicate.PhoneBookDao;
 import com.xpsoft.test.BaseTestCase;
 import flexjson.JSONSerializer;
 import java.io.PrintStream;
 import java.util.List;
 import javax.annotation.Resource;
 import org.junit.Test;
 
 public class PhoneBookDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private PhoneBookDao phoneBookDao;
 
   @Test
   public void test()
   {
     List phoneBook = this.phoneBookDao.getAll();
 
     JSONSerializer serializer = new JSONSerializer();
 
     System.out.println("josn:" + serializer.exclude(new String[] { "class", "phoneGroup", "appUser.department" }).prettyPrint(phoneBook));
   }
 }

