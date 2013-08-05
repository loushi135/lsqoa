package com.xpsoft.oa.dao.customer.impl;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.customer.CusLinkmanDao;
import com.xpsoft.oa.model.customer.CusLinkman;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class CusLinkmanDaoImpl extends BaseDaoImpl<CusLinkman> implements
		CusLinkmanDao {
	public CusLinkmanDaoImpl() {
		super(CusLinkman.class);
	}

	public boolean checkMainCusLinkman(Long customerId, Long linkmanId) {
		final StringBuffer hql = new StringBuffer(
				"select count(*) from CusLinkman  cl where cl.isPrimary = 1 and cl.customer.customerId =? ");
		if (linkmanId != null) {
			hql.append("and cl.linkmanId != ? ");
		}
		
		final Long custId = customerId;
		final Long linkMId =linkmanId;
		
		Long count = (Long) getHibernateTemplate().execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql.toString());
						query.setLong(0,
								custId.longValue());
						if (linkMId != null) {
							query.setLong(1,linkMId.longValue());
						}
						return query.uniqueResult();
					}
				});
		return count.longValue() == 0L;
	}
}

