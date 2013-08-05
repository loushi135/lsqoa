package com.xpsoft.oa.dao.system.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.FileAttachDao;
import com.xpsoft.oa.model.system.FileAttach;
public class FileAttachDaoImpl extends BaseDaoImpl<FileAttach> implements
		FileAttachDao {
	public FileAttachDaoImpl() {
		super(FileAttach.class);
	}

	public void removeByPath(String filePath) {
		final String hql = "delete from FileAttach fa where fa.filePath = ?";
		final String fp = filePath;
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setString(0, fp);
				return Integer.valueOf(query.executeUpdate());
			}
		});
	}

	public FileAttach getByPath(String filePath) {
		String hql = "from FileAttach fa where fa.filePath = ?";
		return (FileAttach) findUnique(hql, new Object[] { filePath });
	}

	@Override
	public List<FileAttach> getFileAttachById(String fileIds) {
		List list=null;
		Criteria criteria=getSession().createCriteria(FileAttach.class);
		String[] ids = fileIds.split(",");
		Long[] fildId = new Long[ids.length];
		for (int i = 0; i < ids.length; i++) {
			Long l = Long.valueOf(ids[i]);
			fildId[i] = l;
		}
		criteria.add(Restrictions.in("fileId",fildId));
		//criteria.addOrder(Order.asc("fileId"));
		list = criteria.list();
		return list;
	}

	

	
}
