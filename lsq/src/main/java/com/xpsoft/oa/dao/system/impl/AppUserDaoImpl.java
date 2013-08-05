package com.xpsoft.oa.dao.system.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.xpsoft.core.Constants;
import com.xpsoft.core.command.CriteriaCommand;
import com.xpsoft.core.command.FieldCommandImpl;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.command.SortCommandImpl;
import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.system.AppUserDao;
import com.xpsoft.oa.model.hrm.EmpProfile;
import com.xpsoft.oa.model.system.AppRole;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.AppUserUpdate;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.model.system.RosterDTO;

public class AppUserDaoImpl extends BaseDaoImpl<AppUser> implements AppUserDao, UserDetailsService {

	public AppUserDaoImpl() {
		super(AppUser.class);
	}

	public AppUser findByUserName(String username) {
		String hql = "from AppUser au where au.username=?";
		Object[] params = { username };
		List list = findByHql(hql, params);
		AppUser user = null;
		if (list.size() != 0) {
			user = (AppUser) list.get(0);
			String hql2 = "select count(*) from AppUser";
			Object obj = findUnique(hql2, null);
			// if (new Integer(obj.toString()).intValue() > 11) {
			if (new Integer(obj.toString()).intValue() > new Integer(AppUtil.getSysConfig().get("limit").toString()).intValue()) {
				user.setStatus(Short.valueOf((short) 0));
			}
		}

		return user;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		final String uName = username;
		AppUser user = (AppUser) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "from AppUser ap where ap.username=? and ap.delFlag = ?";
				Query query = session.createQuery(hql);
				query.setString(0, uName);
				query.setShort(1, Constants.FLAG_UNDELETED.shortValue());
				AppUser user = null;
				try {
					user = (AppUser) query.uniqueResult();

					if (user != null) {
						Hibernate.initialize(user.getRoles());
						Hibernate.initialize(user.getDepartment());

						Set roleSet = user.getRoles();
						Iterator it = roleSet.iterator();

						while (it.hasNext()) {
							AppRole role = (AppRole) it.next();
							if (role.getRoleId().equals(AppRole.SUPER_ROLEID)) {
								user.getRights().clear();
								user.getRights().add("__ALL");
								break;
							}
							if (StringUtils.isNotEmpty(role.getRights())) {
								String[] items = role.getRights().split("[,]");
								for (int i = 0; i < items.length; i++) {
									if (!user.getRights().contains(items[i])) {
										user.getRights().add(items[i]);
									}
								}
							}
						}
					}
				} catch (Exception ex) {
					AppUserDaoImpl.this.logger.warn("user:" + uName + " can't not loding rights:" + ex.getMessage());
				}
				return user;
			}
		});
		String hql2 = "select count(*) from AppUser";
		Object obj = findUnique(hql2, null);
		// if (new Integer(obj.toString()).intValue() > new
		// Integer(AppUtil.getSysConfig().get("limit").toString())) {
		if (new Integer(obj.toString()).intValue() > new Integer(AppUtil.getSysConfig().get("limit").toString()).intValue()) {
			user.setStatus(Short.valueOf((short) 0));
		}
		return user;
	}

	public List findByDepartment(String path, PagingBean pb) {
		List list = new ArrayList();
		String hql = new String();
		if ("0.".equals(path)) {
			hql = "from AppUser vo2 where vo2.delFlag = ? and (vo2.status=? or vo2.status=? )";
			list.add(Constants.FLAG_UNDELETED);
			list.add(Constants.FLAG_ACTIVATION);
			list.add(Constants.FLAG_GOLDMANTIS);
		} else {
			hql = "select vo2 from Department vo1,AppUser vo2 where vo1=vo2.department and vo1.path like ? and vo2.delFlag = ? and (vo2.status=? or vo2.status=? )";
			list.add(path + "%");
			list.add(Constants.FLAG_UNDELETED);
			list.add(Constants.FLAG_ACTIVATION);
			list.add(Constants.FLAG_GOLDMANTIS);
		}
		return findByHql(hql, list.toArray(), pb);
	}

	public List findUserRoleByDepartment(String fullname, String roleName, String path, PagingBean pb) {
		List list = new ArrayList();
//		String hql = new String();
//		if ("0.".equals(path)) {
//			hql = "select vo2 from AppUser vo2 where vo2.delFlag = ? and (vo2.status=? or vo2.status=? )";
//			list.add(Constants.FLAG_UNDELETED);
//			list.add(Constants.FLAG_ACTIVATION);
//			list.add(Constants.FLAG_GOLDMANTIS);
//		} else if (StringUtils.isNotBlank(roleName)) {
//			hql = "select vo2 from AppUser vo2 join vo2.roles as roles  where roles.roleName like ? and vo2.delFlag = ? and (vo2.status=? or vo2.status=? )";
//			list.add("%" + roleName + "%");
//			list.add(Constants.FLAG_UNDELETED);
//			list.add(Constants.FLAG_ACTIVATION);
//			list.add(Constants.FLAG_GOLDMANTIS);
//		} else if (StringUtils.isNotBlank(fullname)) {
//			hql = "select vo2 from AppUser vo2 where vo2.fullname like ? and vo2.delFlag = ? and (vo2.status=? or vo2.status=? )";
//			list.add("%" + fullname + "%");
//			list.add(Constants.FLAG_UNDELETED);
//			list.add(Constants.FLAG_ACTIVATION);
//			list.add(Constants.FLAG_GOLDMANTIS);
//		} else {
//			hql = "select vo2 from Department vo1,AppUser vo2 where vo1=vo2.department and vo1.path like ? and vo2.delFlag = ? and (vo2.status=? or vo2.status=? )";
//			list.add(path + "%");
//			list.add(Constants.FLAG_UNDELETED);
//			list.add(Constants.FLAG_ACTIVATION);
//			list.add(Constants.FLAG_GOLDMANTIS);
//		}
//		list.clear();
		StringBuffer hqlBuf=new StringBuffer();
		hqlBuf.append("select vo2 from AppUser vo2 " );

		if (StringUtils.isNotBlank(roleName)){
			hqlBuf.append(" left join fetch vo2.roles as roles" );
		}
		
		hqlBuf.append(" left  join fetch vo2.department as vo1   where  vo2.delFlag = ? and (vo2.status=? or vo2.status=? )");
		list.add(Constants.FLAG_UNDELETED);
		list.add(Constants.FLAG_ACTIVATION);
		list.add(Constants.FLAG_GOLDMANTIS);
		
		if (!"0.".equals(path)) {
			hqlBuf.append(" and vo1.path like ?");
			list.add(path + "%");
		}
		if(StringUtils.isNotBlank(fullname)) {
			hqlBuf.append(" and vo2.fullname like ?");
			list.add("%" + fullname + "%");
		}	
		if (StringUtils.isNotBlank(roleName)){
			hqlBuf.append(" and roles.roleName like ?");
			list.add("%" + roleName + "%");
		}
		
		return findByHql(hqlBuf.toString(), list.toArray(), pb);
	}

	public List findByDepartment(Department department) {
		String hql = "select vo2 from Department vo1,AppUser vo2 where vo1=vo2.department and vo1.path like ? and vo2.delFlag = ?";
		Object[] params = { department.getPath() + "%", Constants.FLAG_UNDELETED };
		return findByHql(hql, params);
	}

	public List findByRole(Long roleId) {
		String hql = "select vo from AppUser vo join vo.roles roles where roles.roleId=? and vo.delFlag = ?";
		Object[] objs = { roleId, Constants.FLAG_UNDELETED };
		return findByHql(hql, objs);
	}

	public List findByRole(Long roleId, PagingBean pb) {
		String hql = "select vo from AppUser vo join vo.roles roles where roles.roleId=? and vo.delFlag = ? and (vo.status=? or vo.status=? )";
		Object[] objs = { roleId, Constants.FLAG_UNDELETED, Constants.FLAG_ACTIVATION, Constants.FLAG_GOLDMANTIS };
		return findByHql(hql, objs, pb);
	}

	public List<AppUser> findByDepartment(String path) {
		String hql = "select vo2 from Department vo1,AppUser vo2 where vo1.depId=vo2.depId and vo1.path like ? and vo2.delFlag =?";
		Object[] params = { path + "%", Constants.FLAG_UNDELETED };
		return findByHql(hql, params);
	}

	public List findByRoleAndDept(String roleName, String deptName) {
		String hql = "select vo2 from Department vo1,AppUser vo2 join vo2.roles  roles where vo1.depId=vo2.department.depId and  roles.roleName like ? and vo1.depName like ? and vo2.delFlag =?";
		return findByHql(hql, new Object[] { roleName, deptName, Constants.FLAG_UNDELETED });
	}

	public List findByRoleId(Long roleId) {
		String hql = "select vo from AppUser vo join vo.roles as roles where roles.roleId=? and vo.delFlag =?";
		return findByHql(hql, new Object[] { roleId, Constants.FLAG_UNDELETED });
	}

	public List findByUserIds(Long[] userIds) {
		String hql = "select vo from AppUser vo where vo.delFlag=? ";

		if ((userIds == null) || (userIds.length == 0))
			return null;
		hql = hql + " where vo.userId in (";
		int i = 0;
		for (Long userId : userIds) {
			if (i++ > 0) {
				hql = hql + ",";
			}
			hql = hql + "?";
		}
		hql = hql + " )";

		return findByHql(hql, new Object[] { Constants.FLAG_UNDELETED, userIds });
	}

	public List<AppUser> findSubAppUser(String path, Set<Long> userIds, PagingBean pb) {
		String st = "";
		if (userIds.size() > 0) {
			Iterator it = userIds.iterator();
			StringBuffer sb = new StringBuffer();
			while (it.hasNext()) {
				sb.append(((Long) it.next()).toString() + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			st = sb.toString();
		}

		List list = new ArrayList();
		StringBuffer hql = new StringBuffer();
		if (path != null) {
			hql.append("select vo2 from Department vo1,AppUser vo2 where vo1=vo2.department ");
			hql.append(" and vo1.path like ?");
			list.add(path + "%");
		} else {
			hql.append("from AppUser vo2 where 1=1 ");
		}
		if (st != "") {
			hql.append(" and vo2.userId not in (" + st + ")");
		}
		hql.append(" and vo2.delFlag = ?");
		list.add(Constants.FLAG_UNDELETED);
		return findByHql(hql.toString(), list.toArray(), pb);
	}

	public List<AppUser> findSubAppUserByRole(Long roleId, Set<Long> userIds, PagingBean pb) {
		String st = "";
		if (userIds.size() > 0) {
			Iterator it = userIds.iterator();
			StringBuffer sb = new StringBuffer();
			while (it.hasNext()) {
				sb.append(((Long) it.next()).toString() + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			st = sb.toString();
		}
		StringBuffer hql = new StringBuffer("select vo from AppUser vo join vo.roles roles where roles.roleId=?");
		List list = new ArrayList();
		list.add(roleId);
		if (st != "") {
			hql.append(" and vo.userId not in (" + st + ")");
		}
		hql.append(" and vo.delFlag =?");
		list.add(Constants.FLAG_UNDELETED);
		return findByHql(hql.toString(), list.toArray(), pb);
	}

	public List<AppUser> findByDepId(Long depId) {
		String hql = "from AppUser vo where vo.delFlag=0 and vo.department.depId=?";
		Object[] objs = { depId };
		return findByHql(hql, objs);
	}

	public List findByRoleName(String roleName) {
		String hql = "select vo from AppUser vo join vo.roles as roles  where roles.roleName like ? and vo.delFlag =?";
		return findByHql(hql, new Object[] { roleName, Constants.FLAG_UNDELETED });
	}

	@Override
	public List findByUpdateTime(String updateTime) {
		Date date = DateUtil.parseDate(updateTime);
		// String hql =
		// "select a,e,au from AppUser a inner join fetch a.department,EmpProfile e,AppUserUpdate au  where a.userId = e.userId and a.userId = au.id and au.updateTime>=:updateTime";
		String sql = "select a.*,d.*,e.*,au.* from app_user a left join department d on a.depId = d.depId left join emp_profile e on a.userId = e.userId inner join app_user_update au on a.userId = au.id where au.updateTime>=? and a.status!=3";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setParameter(0, date);
		List list = query.addEntity("a", AppUser.class).addEntity("d", Department.class).addEntity("e", EmpProfile.class).addEntity("au", AppUserUpdate.class).list();
		return list;
	}

	@Override
	public List getAllHasEMP(QueryFilter filter) {

		String sortDesc = "";
		String condition = "";
		for (int i = 0; i < filter.getCommands().size(); i++) {
			CriteriaCommand command = (CriteriaCommand) filter.getCommands().get(i);
			if (command instanceof FieldCommandImpl)
				condition = (new StringBuilder(String.valueOf(condition))).append(" and ").append(((FieldCommandImpl) command).getPartHql()).toString();
			else if (command instanceof SortCommandImpl) {
				if (!"".equals(sortDesc))
					sortDesc = (new StringBuilder(String.valueOf(sortDesc))).append(",").toString();
				sortDesc = (new StringBuilder(String.valueOf(sortDesc))).append(((SortCommandImpl) command).getPartHql()).toString();
			}
		}

		StringBuffer hql = new StringBuffer("select u.userId,u.username,u.fullname,u.staffNo,u.email,u.department.depName,u.position,"
				+ "u.accessionTime,u.status,ef.profileId from EmpProfile ef right join ef.appUser u where 1=1 ");
		hql.append(condition);

		if (!StringUtils.isEmpty(sortDesc)) {
			hql.append(" order by ").append(sortDesc);
		}

		Query query = getSession().createQuery(hql.toString());

		Object[] parmets = filter.getParamValueList().toArray();
		filter.getPagingBean().setTotalItems(getTotalItems(hql.toString(), parmets).intValue());
		for (int i = 0; i < parmets.length; i++) {
			query.setParameter(i, parmets[i]);
		}
		query.setFirstResult(filter.getPagingBean().getFirstResult());
		query.setMaxResults(filter.getPagingBean().getPageSize());

		return query.list();
	}

	@Override
	public AppUser findByFullnameAndDepName(String fullname, String depName) {
		String hql = " from AppUser where fullname =:fullname and department.depName =:depName ";
		Query query = this.getSession().createQuery(hql).setParameter("fullname", fullname).setParameter("depName", depName);
		return (AppUser) query.uniqueResult();
	}

	@Override
	public List<RosterDTO> getAllRoster(QueryFilter filter) {

		String sortDesc = "";
		String condition = "";
		for (int i = 0; i < filter.getCommands().size(); i++) {
			CriteriaCommand command = (CriteriaCommand) filter.getCommands().get(i);
			if (command instanceof FieldCommandImpl)
				condition = (new StringBuilder(String.valueOf(condition))).append(" and ").append(((FieldCommandImpl) command).getPartHql()).toString();
			else if (command instanceof SortCommandImpl) {
				if (!"".equals(sortDesc))
					sortDesc = (new StringBuilder(String.valueOf(sortDesc))).append(",").toString();
				sortDesc = (new StringBuilder(String.valueOf(sortDesc))).append(((SortCommandImpl) command).getPartHql()).toString();
			}
		}

		StringBuffer hql = new StringBuffer("select new com.xpsoft.oa.model.system.RosterDTO( u.staffNo,u.fullname,ef.sex,u.department.depName,ef.dicPlait.itemValue,u.position,"
				+ "u.accessionTime,ef.dicWorkContractType.itemValue,ef.workContractEndDate,u.mobile) from EmpProfile ef right join ef.appUser u  where u.status=1 ");
		hql.append(condition);

		if (!StringUtils.isEmpty(sortDesc)) {
			hql.append(" order by ").append(sortDesc);
		}

		Query query = getSession().createQuery(hql.toString());

		Object[] parmets = filter.getParamValueList().toArray();
		filter.getPagingBean().setTotalItems(getTotalItems(hql.toString(), parmets).intValue());
		for (int i = 0; i < parmets.length; i++) {
			query.setParameter(i, parmets[i]);
		}
		query.setFirstResult(filter.getPagingBean().getFirstResult());
		query.setMaxResults(filter.getPagingBean().getPageSize());

		return query.list();
	}
}
