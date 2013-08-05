package com.xpsoft.oa.dao.statistics.impl;


import java.text.DecimalFormat;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.OtherProjectDao;
import com.xpsoft.oa.model.statistics.OtherProject;

public class OtherProjectDaoImpl extends BaseDaoImpl<OtherProject> implements OtherProjectDao{

	public OtherProjectDaoImpl() {
		super(OtherProject.class);
	}

	@Override
	public String getNewProNo() {
		String newNo = "";
		String search = "Z.";
		String hql = "select proNo from OtherProject where proNo like '%"+search+"%' order by proNo desc";
		Object obj = this.getSession().createQuery(hql).setMaxResults(1).uniqueResult();
		if(obj==null){
			newNo = search+"00";
		}else{
			String maxNo = obj.toString();
			DecimalFormat df = new DecimalFormat("00");
			newNo = search + df.format(1 + Integer.parseInt(maxNo.substring(2)));
		}
		return newNo;
	}

	@Override
	public OtherProject getByProName(String proName) {
		String hql="from OtherProject  where proName=?";
		return (OtherProject) findUnique(hql, new Object[]{proName});
	}

}