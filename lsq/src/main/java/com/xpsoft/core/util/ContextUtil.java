 package com.xpsoft.core.util;
 
 import java.util.Set;

import com.xpsoft.oa.model.system.AppRole;
import com.xpsoft.oa.model.system.AppUser;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 import org.springframework.security.Authentication;
 import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
 
 public class ContextUtil
 {
   private static final Log logger = LogFactory.getLog(ContextUtil.class);
 
   public static AppUser getCurrentUser()
   {
     SecurityContext securityContext = SecurityContextHolder.getContext();
     if (securityContext != null) {
       Authentication auth = securityContext.getAuthentication();
       if (auth != null) {
         Object principal = auth.getPrincipal();
         if ((principal instanceof AppUser))
           return (AppUser)principal;
       }
       else {
         logger.warn("WARN: securityContext cannot be lookuped using SecurityContextHolder.");
       }
     }
     return null;
   }
 
   public static Long getCurrentUserId() {
     AppUser curUser = getCurrentUser();
     if (curUser != null) return curUser.getUserId();
     return null;
   }
   
   /**
    * 
    * @param appUser
    * @return 返回最高角色： 1 普通员工，2 部门经理，3 分管领导
    */
	public static int getHighestRole(AppUser appUser) {
		// TODO Auto-generated method stub
		int flag = 1;
		Set<AppRole> AppRoleSet=  appUser.getRoles();
		for (AppRole appRole : AppRoleSet) {
			String roleName = appRole.getRoleName();
			if (roleName.contains("分管领导")) {
				flag = 3;
				break;
			} else if ("流程-部门经理".equals(roleName)) {
				flag = 2;
			}
		}
		return flag;
	}
 }

