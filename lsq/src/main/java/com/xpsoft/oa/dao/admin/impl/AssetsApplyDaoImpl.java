package com.xpsoft.oa.dao.admin.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.xpsoft.core.command.CriteriaCommand;
import com.xpsoft.core.command.FieldCommandImpl;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.command.SortCommandImpl;
import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.admin.AssetsApplyDao;
import com.xpsoft.oa.model.admin.AssetsApply;

public class AssetsApplyDaoImpl extends BaseDaoImpl<AssetsApply> implements AssetsApplyDao{

	public AssetsApplyDaoImpl() {
		super(AssetsApply.class);
	}

	@Override
	public List<AssetsApply> getAll2000(QueryFilter filter) {
		
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

		StringBuffer hql = new StringBuffer("select ef from AssetsApply ef left join fetch ef.assetsApplycontents " +
				" left join fetch ef.useProjectManager left join fetch ef.useProject where 1=1 ");
		
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