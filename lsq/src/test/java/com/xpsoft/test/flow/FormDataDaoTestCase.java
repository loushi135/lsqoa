 package com.xpsoft.test.flow;
 
 import com.xpsoft.oa.dao.flow.FormDataDao;
 import com.xpsoft.oa.model.flow.FormData;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class FormDataDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private FormDataDao formDataDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     FormData formData = new FormData();
 
     this.formDataDao.save(formData);
   }
 }

