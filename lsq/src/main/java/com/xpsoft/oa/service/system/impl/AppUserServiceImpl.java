 package com.xpsoft.oa.service.system.impl;
 
 import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.system.AppUserDao;
import com.xpsoft.oa.dao.system.AppUserUpdateDao;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.AppUserUpdate;
import com.xpsoft.oa.model.system.RosterDTO;
import com.xpsoft.oa.service.system.AppUserService;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
 
 public class AppUserServiceImpl extends BaseServiceImpl<AppUser>
   implements AppUserService
 {
   private AppUserDao dao;
   @Autowired
   private AppUserUpdateDao appUserUpdateDao;
 
   public AppUserServiceImpl(AppUserDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public AppUser findByUserName(String username)
   {
     return this.dao.findByUserName(username);
   }
 
   public List findByDepartment(String path, PagingBean pb)
   {
     return this.dao.findByDepartment(path, pb);
   }
   public List findUserRoleByDepartment(String fullname, String roleName,String path, PagingBean pb)
   {
	   return this.dao.findUserRoleByDepartment(fullname,roleName,path, pb);
   }
 
   public List findByRole(Long roleId, PagingBean pb)
   {
     return this.dao.findByRole(roleId, pb);
   }
 
   public List findByRoleNameFZ(String roleName) {
//	   if(roleName.contains("工程")||roleName.contains("直属")){
//		   roleName = "工程";
//	   }else if(roleName.contains("团队")||roleName.contains("综合管理部门")){
//		   roleName = "营销中心";
//	   }else if(roleName.contains("技术")||roleName.contains("研发")){
//		   roleName = "技术";
//	   }else if(roleName.contains("投标")){
//		   roleName = "投标";
//	   }
		return dao.findByRoleName("%"+roleName+"%领导");
   }
   
   public List findByRoleId(Long roleId) {
     return this.dao.findByRole(roleId);
   }
 
   public List<AppUser> findSubAppUser(String path, Set<Long> userIds, PagingBean pb)
   {
     return this.dao.findSubAppUser(path, userIds, pb);
   }
 
   public List<AppUser> findSubAppUserByRole(Long roleId, Set<Long> userIds, PagingBean pb)
   {
     return this.dao.findSubAppUserByRole(roleId, userIds, pb);
   }
 
   public List<AppUser> findByDepId(Long depId)
   {
     return this.dao.findByDepId(depId);
   }
   
   public List findByRoleAndDept(String roleName, String deptName) {
		return dao.findByRoleAndDept(roleName, deptName);
	}
   
   public List findByRoleName(String roleName) {
		return dao.findByRoleName(roleName);
	}
   	@Override
	public Boolean getSuperManager() {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();//取得认证器
		boolean isSuperUser=false;
		for(int i=0;i<auth.getAuthorities().length;i++){
			//logger.info("角色名称:"+auth.getAuthorities()[i].getAuthority());
			if("超级管理员".equals(auth.getAuthorities()[i].getAuthority())){
				isSuperUser=true;
				break;
			}
		}
		return isSuperUser;
	}

	@Override
	public List findByUpdateTime(String updateTime) {
		return dao.findByUpdateTime(updateTime);
	}

	@Override
	public void saveAppUser(AppUser appUser) {
		dao.save(appUser);
		AppUserUpdate appUserUpdate = appUserUpdateDao.get(appUser.getUserId());
		if(appUserUpdate==null){
			appUserUpdate = new AppUserUpdate();
			appUserUpdate.setAppUser(appUser);
		}
		appUserUpdate.setUpdateTime(new Date());
		appUserUpdateDao.save(appUserUpdate);
	}

	@Override
	public List<AppUser> findByFullnames(String fullnames) {
		if(StringUtils.isNotBlank(fullnames)){
			String hql = " from AppUser where fullname in ("+fullnames+")";
			return dao.findByHql(hql,new Object[]{});
		}
		return null;
	}

	@Override
	public List getAllHasEMP(QueryFilter filter) {
		return dao.getAllHasEMP(filter);
	}
	

	@Override
	public AppUser findByFullnameAndDepName(String fullname, String depName) {
		return dao.findByFullnameAndDepName(fullname, depName);
	}


	@Override
	public Boolean isAreaManager() {
		boolean isAreaManager = false;
		AppUser currentUser = ContextUtil.getCurrentUser();
		String depName = currentUser.getDepartment().getDepName();
	    if((depName.contains("工程")||depName.contains("直属"))&&currentUser.getPosition().equals("经理")){
	    	isAreaManager = true;
	    }
		return isAreaManager;
	}

	@Override
	public List<RosterDTO> getAllRoster(QueryFilter filter) {
		return dao.getAllRoster(filter);
	}
 }

