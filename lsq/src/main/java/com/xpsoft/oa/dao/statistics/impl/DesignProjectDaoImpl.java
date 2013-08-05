package com.xpsoft.oa.dao.statistics.impl;


import java.text.DecimalFormat;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.oa.dao.statistics.DesignProjectDao;
import com.xpsoft.oa.model.statistics.DesignProject;

public class DesignProjectDaoImpl extends BaseDaoImpl<DesignProject> implements DesignProjectDao{

	public DesignProjectDaoImpl() {
		super(DesignProject.class);
	}

	@Override
	public String getNewProNo() {
		String newNo = "";
		String search = "SJ."+DateUtil.shortYear();
		String hql = "select proNo from DesignProject where proNo like '%"+search+"%' order by proNo desc";
		Object obj = this.getSession().createQuery(hql).setMaxResults(1).uniqueResult();
		if(obj==null){
			newNo = search+"01";
		}else{
			String maxNo = obj.toString();
			DecimalFormat df = new DecimalFormat("00");
			newNo = search + df.format(1 + Integer.parseInt(maxNo.substring(5)));
		}
		return newNo;
	}

	@Override
	public DesignProject getByProName(String proName) {
		String hql="from DesignProject where proName=?";
		return (DesignProject) findUnique(hql, new Object[]{proName});

	}

}