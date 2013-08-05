package com.xpsoft.oa.dao.system.impl;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.DictionaryDao;
import com.xpsoft.oa.model.system.Dictionary;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class DictionaryDaoImpl extends BaseDaoImpl<Dictionary> implements
		DictionaryDao {
	public DictionaryDaoImpl() {
		super(Dictionary.class);
	}

	public List<String> getAllItems() {
		String hql = "select itemName from Dictionary group by itemName";
		return (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery("select itemName from Dictionary group by itemName");
						return query.list();
					}
				});
	}

	public List<String> getAllByItemName(String itemName) {
		final String hql = "select itemValue from Dictionary where itemName=?";
		final String item = itemName;
		return (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery(hql);
						query.setString(0, item);
						return query.list();
					}
				});
	}
}
