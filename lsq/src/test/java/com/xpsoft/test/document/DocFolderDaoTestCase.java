 package com.xpsoft.test.document;
 
 import com.xpsoft.oa.dao.document.DocFolderDao;
 import com.xpsoft.oa.dao.system.AppUserDao;
 import com.xpsoft.test.BaseTestCase;
 import java.io.PrintStream;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import javax.annotation.Resource;
 import org.junit.Test;
 
 public class DocFolderDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DocFolderDao docFolderDao;
 
   @Resource
   private AppUserDao appUserDao;
 
   @Test
   public void move()
   {
     String st = "1.2.3.6.5.";
     boolean ss = Pattern.compile("1.3").matcher(st).find();
     System.out.println(ss);
   }
 }

