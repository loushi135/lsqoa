 package com.xpsoft.test.flow;
 
 import com.xpsoft.oa.dao.flow.ProDefinitionDao;
 import com.xpsoft.oa.dao.flow.ProTypeDao;
 import com.xpsoft.oa.model.flow.ProDefinition;
 import com.xpsoft.oa.model.flow.ProType;
 import com.xpsoft.test.BaseTestCase;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ProDefinitionDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ProDefinitionDao proDefinitionDao;
 
   @Resource
   private ProTypeDao proTypeDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ProDefinition pro = new ProDefinition();
 
     pro.setDefXml("xml");
     pro.setDescription("descriptin");
     pro.setName("vtest");
     pro.setDrawDefXml("drawXml");
     pro.setCreatetime(new Date());
 
     ProType proType = (ProType)this.proTypeDao.get(new Long(1L));
 
     pro.setProType(proType);
     pro.setDeployId("1");
     this.proDefinitionDao.save(pro);
   }
 
   public void get()
   {
     List list = this.proDefinitionDao.getAll();
   }
 }

