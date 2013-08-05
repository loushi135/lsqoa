package com.xpsoft.oa.service.system;

import java.util.List;
import java.util.Set;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.service.BaseService;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.RosterDTO;

public abstract interface AppUserService extends BaseService<AppUser>
{
  public abstract AppUser findByUserName(String paramString);

  public abstract List findByDepartment(String paramString, PagingBean paramPagingBean);
  public abstract List findUserRoleByDepartment(String fullname, String roleName,String paramString, PagingBean paramPagingBean);

  public abstract List findByRole(Long paramLong, PagingBean paramPagingBean);

  public abstract List findByRoleId(Long paramLong);

  public abstract List<AppUser> findSubAppUser(String paramString, Set<Long> paramSet, PagingBean paramPagingBean);

  public abstract List<AppUser> findSubAppUserByRole(Long paramLong, Set<Long> paramSet, PagingBean paramPagingBean);

  public abstract List<AppUser> findByDepId(Long paramLong);
  
  public abstract List findByRoleAndDept(String roleName,String deptName);
  
  public abstract List findByRoleName(String roleName);
  public List findByRoleNameFZ(String roleName);
  public Boolean getSuperManager();
  public Boolean isAreaManager();
  public List findByUpdateTime(String updateTime);
  
  public void saveAppUser(AppUser appUser);
  
  public List<AppUser> findByFullnames(String fullnames);

  public abstract List getAllHasEMP(QueryFilter filter);
  
  public AppUser  findByFullnameAndDepName(String fullname,String depName);

  public abstract List<RosterDTO> getAllRoster(QueryFilter filter);
}

