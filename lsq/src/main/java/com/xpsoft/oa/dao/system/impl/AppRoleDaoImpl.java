package com.xpsoft.oa.dao.system.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.AppRoleDao;
import com.xpsoft.oa.model.system.AppFunction;
import com.xpsoft.oa.model.system.AppRole;
import com.xpsoft.oa.model.system.FunUrl;

public class AppRoleDaoImpl extends BaseDaoImpl<AppRole> implements AppRoleDao {
	public AppRoleDaoImpl() {
		super(AppRole.class);
	}

	public AppRole getByRoleName(String roleName) {
		String hql = "from AppRole ar where ar.roleName=?";
		return (AppRole) findUnique(hql, new Object[] { roleName });
	}

	public HashMap<String, Set<String>> getSecurityDataSource() {
		final HashMap source = new HashMap();

		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "from AppRole";
				Query query = session.createQuery(hql);
				List<AppRole> roleList = query.list();

				for (AppRole role : roleList) {
					TreeSet urlSet = new TreeSet();

					Iterator functions = role.getFunctions().iterator();

					while (functions.hasNext()) {
						AppFunction fun = (AppFunction) functions
								.next();
						Iterator urlIt = fun.getFunUrls().iterator();
						while (urlIt.hasNext()) {
							urlSet.add(((FunUrl) urlIt.next())
									.getUrlPath());
						}
					}

					source.put(role.getName(), urlSet);
				}
				return null;
			}

		});
		return source;
	}

}
