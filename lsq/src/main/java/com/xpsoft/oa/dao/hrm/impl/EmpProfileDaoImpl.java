package com.xpsoft.oa.dao.hrm.impl;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.hrm.EmpProfileDao;
import com.xpsoft.oa.model.hrm.EmpProfile;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class EmpProfileDaoImpl extends BaseDaoImpl<EmpProfile> implements
		EmpProfileDao {
	public EmpProfileDaoImpl() {
		super(EmpProfile.class);
	}

	public boolean checkProfileNo(String profileNo) {
		final String hql = "select count(*) from EmpProfile ep where ep.profileNo = ?";
		final String profNo = profileNo;
		Long count = (Long) getHibernateTemplate().execute(new HibernateCallback() {
			
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery(hql);
						query.setString(0, profNo);
						return query.uniqueResult();
					}
				});
		return count.longValue() == 0L;
	}

	@Override
	public EmpProfile getByUserId(Long userId) {
		return (EmpProfile) findUnique("from EmpProfile where appUser.userId = ?", new Object[]{userId});
	}
	
	
}

