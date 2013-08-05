package com.xpsoft.oa.dao.admin.impl;




import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.admin.BookDao;
import com.xpsoft.oa.model.admin.Book;

public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {

	public BookDaoImpl() {
		super(Book.class);
	}

	@Override
	public List<Book> findByTypeId(final Long typeId, final PagingBean pb) {
		final String hql = "from Book b where b.bookType.typeId=?";
		Object[] params = { typeId };
		return findByHql(hql, params, pb);
	}

	public String getNextID(final Long typeId) {

		Object nextisbn = super.getHibernateTemplate().execute(
				new HibernateCallback() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {

//						Object typeSN = session
//								.createQuery(
//										"select typeSN from BookType b where b.typeId = :typeId")
//								.setParameter("typeId", typeId).uniqueResult();

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

						String isbn = "LJT-" + sdf.format(new Date()) + "-";

//						if (typeSN != null) {
//							isbn += typeSN;
//							isbn += "-";
//						}

						Book prev = (Book) session
								.createQuery(
										"from Book b where b.bookType.typeId = :typeId and b.isbn like :isbn order by b.bookId desc")
								.setParameter("typeId", typeId).setParameter("isbn", isbn + "%").setMaxResults(1)
								.setFirstResult(0).uniqueResult();

						if (prev != null) {
							int nextnum = Integer.parseInt(prev.getIsbn().split(isbn)[1]);
							nextnum += 1;
							isbn += nextnum;
						} else {
							isbn += 1;
						}
						return isbn;
					}
				});
		return nextisbn.toString();
	}
	
	@Override
	public void saveAll(List<Book> list) {
		this.saveAll(list);
		
	}

	

}