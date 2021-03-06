 package com.xpsoft.test.system;
 
 import com.xpsoft.oa.model.system.AppFunction;
 import com.xpsoft.oa.model.system.AppRole;
 import com.xpsoft.oa.service.system.AppFunctionService;
 import com.xpsoft.oa.service.system.AppRoleService;
 import com.xpsoft.test.BaseTestCase;
 import java.util.Set;
 import javax.annotation.Resource;
 import org.junit.Test;
 
 public class AppRoleTestCase extends BaseTestCase
 {
 
   @Resource
   private AppRoleService appRoleService;
 
   @Resource
   private AppFunctionService appFunctionService;
 
   public void testMerge()
   {
     AppRole role = new AppRole();
     role.setRoleId(Long.valueOf(1L));
     role.setStatus(Short.valueOf((short) 0));
     this.appRoleService.merge(role);
   }
   @Test
   public void updateFunctions() {
     AppRole role = (AppRole)this.appRoleService.get(Long.valueOf(3L));
     for (int id = 1; id <= 2; id++) {
       AppFunction appFunction = (AppFunction)this.appFunctionService.get(new Long(id));
       role.getFunctions().add(appFunction);
     }
     this.appRoleService.save(role);
   }
 }

