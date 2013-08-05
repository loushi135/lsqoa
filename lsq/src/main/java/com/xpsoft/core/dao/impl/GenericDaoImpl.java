package com.xpsoft.core.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.hql.ast.QueryTranslatorImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xpsoft.core.command.CriteriaCommand;
import com.xpsoft.core.command.FieldCommandImpl;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.command.SortCommandImpl;
import com.xpsoft.core.dao.GenericDao;
import com.xpsoft.core.web.paging.PagingBean;

public abstract class GenericDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport
  implements GenericDao<T, PK>
{
  protected Log logger = LogFactory.getLog(GenericDaoImpl.class);
  protected JdbcTemplate jdbcTemplate;
  @SuppressWarnings("rawtypes")
  protected Class persistType;
  protected Map<String, String> querys = new HashMap();

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }
  @SuppressWarnings("rawtypes")
  public void setPersistType(Class persistType) {
    this.persistType = persistType;
  }
  	
  @SuppressWarnings("rawtypes")
  public GenericDaoImpl(Class persistType) {
    this.persistType = persistType;
  }

  @SuppressWarnings("unchecked")
  public T get(PK id) {
    return (T) getHibernateTemplate().get(this.persistType, id);
  }
  @SuppressWarnings("unchecked")
  public T load(PK id) {
    return (T) getHibernateTemplate().load(this.persistType, id);
  }

  public T save(T entity) {
    getHibernateTemplate().saveOrUpdate(entity);
    return entity;
  }

  public T merge(T entity) {
    getHibernateTemplate().merge(entity);
    return entity;
  }

  public void evict(T entity) {
    getHibernateTemplate().evict(entity);
  }
  /**
	 * return a page record of a table.
	 * 
	 * @param queryString
	 * @param values
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
  @SuppressWarnings("rawtypes")
  public List find(final String queryString, final Object[] values, final int firstResult, final int pageSize) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query queryObject = getSession().createQuery(queryString);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
				if (pageSize > 0) {
					queryObject.setFirstResult(firstResult).setMaxResults(
							pageSize).setFetchSize(pageSize);
				}
				return queryObject.list();
			}
		});
	}

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<T> getAll() {
    return (List)getHibernateTemplate().execute(new HibernateCallback()
    {
      public Object doInHibernate(Session session) throws HibernateException, SQLException {
    	  String hql="from " + persistType.getName();
		  Query query=session.createQuery(hql);
		  return query.list();
      }
    });
  }

  @SuppressWarnings("unchecked")
  public List<T> getAll(final PagingBean pb){
		final String hql="from " + persistType.getName();
		int totalItems=getTotalItems(hql,null).intValue();
		pb.setTotalItems(totalItems);
		return (List<T>)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query=session.createQuery(hql);
				query.setFirstResult(pb.getFirstResult()).setFetchSize(pb.getPageSize());
				query.setMaxResults(pb.getPageSize());
				return (List<T>)query.list();
			}
		});
  }

  /**
	 * 返回queryString查询返回的记录数
	 * 
	 * @param queryString
	 * @param values
	 * @return Long
	 */
	public Long getTotalItems(final String queryString,
			final Object[] values) {
		QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(queryString, queryString, 
		java.util.Collections.EMPTY_MAP, (org.hibernate.engine.SessionFactoryImplementor)getSessionFactory());
		queryTranslator.compile(java.util.Collections.EMPTY_MAP, false);
		final String sql="select count(*) from (" + queryTranslator.getSQLString() + ") tmp_count_t"; 
		
//		logger.info("count sql:" + sql);
//		int idx = queryString.toUpperCase().indexOf("FROM ");
//		int idx2 = queryString.toUpperCase().indexOf(" ORDER BY ");
//		if (idx2 < 0) {
//			idx2 = queryString.length();
//		}
//		String hqlNoSelect = null;
//		if (idx > 0) {
//			hqlNoSelect = queryString.substring(idx, idx2);
//		} else {
//			hqlNoSelect = queryString.substring(0, idx2);
//		}
//		return ((Long) findUnique("select count(*) " + hqlNoSelect, values));
		
		BigInteger reVal= (BigInteger)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery query= session.createSQLQuery(sql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.uniqueResult();
			}
		});
		
		return reVal.longValue();
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findByHql(final String hql,final Object[]objs){
		return (List)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query=session.createQuery(hql);
				if(objs!=null){
					for(int i=0;i<objs.length;i++){
						query.setParameter(i,objs[i]);
					}
				}
				return (List<T>)query.list();
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findByHql(final String hql,final Object[]objs,final int firstResult,final int pageSize ){
		return (List)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query=session.createQuery(hql);
				query.setFirstResult(firstResult).setMaxResults(pageSize);
				if(objs!=null){
					for(int i=0;i<objs.length;i++){
						query.setParameter(i,objs[i]);
					}
				}
				return (List<T>)query.list();
			}
		});
	}

  public List<T> findByHql(final String hql,final Object[]objs,PagingBean pb ){
		int totalItems=getTotalItems(hql,objs).intValue();
		pb.setTotalItems(totalItems);
		return findByHql(hql,objs,pb.getFirstResult(),pb.getPageSize());
  }

  public List<T> findByHql(String hql) {
    return findByHql(hql, null);
  }

  public void remove(PK id) {
    getHibernateTemplate().delete(get(id));
  }

  public void remove(T entity) {
    getHibernateTemplate().delete(entity);
  }

  /**
	 * 通过hql查找某个唯一的实体对象
	 * @author QGH
	 * @param queryString
	 * @param values
	 * @return
	 */
	public Object findUnique(final String hql, final Object[] values) {
		return (Object) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
	
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.uniqueResult();
			}
		});
   }

	//---------------------Query Filter Start----------------------------------------------------------


	public int getCountByFilter(final QueryFilter filter) {
        Integer count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(persistType);
                for(int i=0;i<filter.getCommands().size();i++){
                	CriteriaCommand command=filter.getCommands().get(i);
                	if (!(command instanceof SortCommandImpl)) {
                		criteria = command.execute(criteria);
					}
                }
                criteria.setProjection(Projections.rowCount());
                return criteria.uniqueResult();
            }
        });
        if(count==null) return new Integer(0);
        return count.intValue();
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getAll(final QueryFilter queryFilter){
		
		if(StringUtils.isNotEmpty(queryFilter.getFilterName())){
			return getAll2(queryFilter);
		}
		
    	int totalCounts=getCountByFilter(queryFilter);
    	//设置总记录数
    	queryFilter.getPagingBean().setTotalItems(totalCounts);

    	List<T> resultList=(List<T>)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,SQLException {
				  Criteria criteria = session.createCriteria(persistType);
				  //重新清除alias的命名，防止计算记录行数后名称还存在该处
				  queryFilter.getAliasSet().clear();
				  setCriteriaByQueryFilter(criteria,queryFilter);
	              return criteria.list();
			}
		});

    	return resultList;
    }

	/**
	 * 按Hql查询并返回
	 * @param queryFilter
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getAll2(QueryFilter queryFilter){	
		String hql=querys.get(queryFilter.getFilterName()).trim();

		String newHql=null;
		String condition=null;

		//重新设置排序 
		int orderIndex = hql.toUpperCase().indexOf(" ORDER BY ");
		int whereIndex = hql.toUpperCase().indexOf(" WHERE ");
		
		if (orderIndex < 0) {
			orderIndex = hql.length();
		}
		if(whereIndex<0){
			whereIndex=hql.length();
		}
		
		if(whereIndex<0){
			condition=" where 1=1 ";
		}else{
			condition=hql.substring(whereIndex+7,orderIndex);
			condition=" where ("+condition+")";
		}
		String sortDesc="";
		//取得条件以及排序
		for(int i=0;i<queryFilter.getCommands().size();i++){
			CriteriaCommand command=queryFilter.getCommands().get(i);
			if(command instanceof FieldCommandImpl){
				condition+=" and " + ((FieldCommandImpl)command).getPartHql();
			}else if(command instanceof SortCommandImpl){
				if(!"".equals(sortDesc)){
					sortDesc+=",";
				}
				sortDesc+=((SortCommandImpl)command).getPartHql();
			}
		}
		
		newHql = hql.substring(0, whereIndex);

		if(queryFilter.getAliasSet().size()>0){
			//取得hql中的表的别名，为关联外表作准备
			int fromIndex=newHql.toUpperCase().indexOf(" FROM ");
			String entityAliasName=null;
			if(fromIndex>0){
				String afterFrom=newHql.substring(fromIndex+6);
				
				String []keys=afterFrom.split("[ ]");
				if(keys.length>1){
					if(!keys[1].toUpperCase().equals("ORDER")
							&&!keys[1].toUpperCase().equals("JOIN")){
						entityAliasName=keys[1];
					}
				}
				//加上别名
				if(entityAliasName==null){
					entityAliasName="vo";
					newHql=newHql.replace(keys[0], keys[0]+" " + entityAliasName);
				}
			}
			
			//若存在外键，则进行组合
			String joinHql="";
			Iterator it=queryFilter.getAliasSet().iterator();
			while(it.hasNext()){
				String joinVo=(String)it.next();
				joinHql+=" join " + entityAliasName+"."+joinVo +" " + joinVo;
			}
	
			//加上外键的联接
			if(!"".equals(joinHql)){
				newHql+=joinHql;
			}
		}
		//加上条件限制
		newHql+= condition;
		//加上排序
		if(!"".equals(sortDesc)){//带在排序在内
			newHql+=" order by " + sortDesc;
		}else{
			newHql+=hql.substring(orderIndex);
		}
		
		Object[] params=queryFilter.getParamValueList().toArray();
		
		//显示多少条记录
		int totalItems=getTotalItems(newHql,params).intValue();
		queryFilter.getPagingBean().setTotalItems(totalItems);
		
		return find(newHql, params,queryFilter.getPagingBean().getFirstResult(),queryFilter.getPagingBean().getPageSize());
  }

  public void flush() {
    getHibernateTemplate().flush();
  }

  private Criteria setCriteriaByQueryFilter(Criteria criteria, QueryFilter filter)
  {
    for (int i = 0; i < filter.getCommands().size(); i++) {
      criteria = ((CriteriaCommand)filter.getCommands().get(i))
        .execute(criteria);
    }

    criteria.setFirstResult(filter.getPagingBean().getFirstResult());
    criteria.setMaxResults(filter.getPagingBean().getPageSize().intValue());

    return criteria;
  }

  
  @SuppressWarnings("unchecked")
  public List<Object[]> findBySql(final String sql){
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				return (List<Object[]>)query.list();
			}
		});
	}
	
	@SuppressWarnings("rawtypes")
	public List findBySql2(final String sql){
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}

public void setQuerys(Map<String, String> querys) {
    this.querys = querys;
  }
}