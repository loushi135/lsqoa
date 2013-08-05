package com.xpsoft.oa.dao.statistics.impl;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.xpsoft.core.command.CriteriaCommand;
import com.xpsoft.core.command.FieldCommandImpl;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.command.SortCommandImpl;
import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.ProjectReceiveDao;
import com.xpsoft.oa.dao.system.AppUserDao;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectReceive;
import com.xpsoft.oa.model.system.AppUser;

public class ProjectReceiveDaoImpl extends BaseDaoImpl<ProjectReceive> implements ProjectReceiveDao{
	@Resource
	private AppUserDao appUserDao;
	
	public ProjectReceiveDaoImpl() {
		super(ProjectReceive.class);
	}

	@Override
	public BigDecimal getTotalReceiveByProNo(String proNo) {
		// TODO Auto-generated method stub
		String hql = "select sum(amount) from ProjectReceive where project.proNo=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0,proNo);
		BigDecimal temp = (BigDecimal)query.uniqueResult();
		if(temp==null){
			temp = new BigDecimal(0);
		}
		return temp;
	}

	@Override
	public boolean checkHasImport(String amountBig, Date receiveTime, Long id) {
		
		String hql = "select count(*) as count from ProjectReceive p where p.amountBig=? and p.receiveTime=?" +
						" and p.project.id=? ";
		getSession().clear();
		Query query = getSession().createQuery(hql);
		query.setParameter(0,amountBig);
		query.setParameter(1,receiveTime);
		query.setParameter(2,id);
		Long temp = (Long)query.uniqueResult();
		if(temp==null||temp==0){
			return false;
		}
		return true;
	}

	@Override
	public boolean checkHasImport(String pzzh, String zy, String amountBig,
			Date receiveTime, Long id) {
		String hql = "select count(*) as count from ProjectReceive p where p.amountBig=? and p.receiveTime=?" +
				" and p.project.id=? and p.pzzh=? and p.zy=?";
		//getSession().clear();//清除projectReceive对象
		Query query = getSession().createQuery(hql);
		query.setParameter(0,amountBig);
		query.setParameter(1,receiveTime);
		query.setParameter(2,id);
		query.setParameter(3,pzzh);
		query.setParameter(4,zy);
		Long temp = (Long)query.uniqueResult();
		if(temp==null||temp==0){
		return false;
		}
		return true;
	}

	@Override
	public ProjectReceive getByPro(ProjectNew projectNews) {
		
		String hql="from ProjectReceive p where p.project=?";
		
		ProjectReceive receive=(ProjectReceive)this.findUnique(hql, new Object[]{projectNews});
		
		return receive;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectReceive> getMyData(final QueryFilter filter,
			final AppUser currentUser) {
		String sortDesc = "";
		String condition="";
		for (int i = 0; i < filter.getCommands().size(); i++) {
			CriteriaCommand command = (CriteriaCommand) filter
					.getCommands().get(i);
			if (command instanceof FieldCommandImpl)
				condition = (new StringBuilder(String.valueOf(condition)))
						.append(" and ").append(
								((FieldCommandImpl) command).getPartHql())
						.toString();
			else if (command instanceof SortCommandImpl) {
				if (!"".equals(sortDesc))
					sortDesc = (new StringBuilder(String.valueOf(sortDesc)))
							.append(",").toString();
				sortDesc = (new StringBuilder(String.valueOf(sortDesc)))
						.append(((SortCommandImpl) command).getPartHql())
						.toString();
			}
		}
		
		final String hql="from ProjectReceive r where (r.project.proChargerUser=? or r.project.proFollower=? or r.project.area in (" +
				"select vo1.depName from Department vo1,AppUser vo2 join vo2.roles  roles where vo1.depId=vo2.department.depId and  roles.roleName='流程-部门经理' and vo2=? ))"+condition;
		
		List params = new ArrayList();
		
		params.add(currentUser);
		params.add(currentUser.getFullname());
		params.add(currentUser);
		params.addAll(filter.getParamValueList());
		
		int totalItems = getTotalItems(hql, params.toArray()).intValue();
		filter.getPagingBean().setTotalItems(totalItems);
		return find(hql, params.toArray(), filter.getPagingBean().getFirstResult(),  filter.getPagingBean().getPageSize());
		
	}

	
}