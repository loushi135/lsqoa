 package com.xpsoft.test.archive;
 
 import com.xpsoft.oa.dao.archive.ArchTemplateDao;
 import com.xpsoft.oa.model.archive.ArchTemplate;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ArchTemplateDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ArchTemplateDao archTemplateDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ArchTemplate archTemplate = new ArchTemplate();
 
     this.archTemplateDao.save(archTemplate);
   }
 }

