 package com.xpsoft.test.jbpm;
 
 import com.xpsoft.core.util.XmlUtil;
 import java.io.PrintStream;
 import org.dom4j.Document;
 import org.dom4j.Element;
 
 public class GsonTestCase
 {
   public static void main(String[] args)
   {
     test();
   }
 
   public static void test()
   {
     String path = "E:/yherp_workspace/oa2/test/com/xpsoft/test/jbpm/jbpmdef.xml";
 
     String defXml = XmlUtil.load(path).getRootElement().asXML();
 
     System.out.println("xml:" + defXml);
   }
 }
