package com.xpsoft.oa.dao.statistics.impl;


import java.text.DecimalFormat;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.util.PinyingUtil;
import com.xpsoft.oa.dao.statistics.SalesProjectDao;
import com.xpsoft.oa.model.statistics.SalesProject;

public class SalesProjectDaoImpl extends BaseDaoImpl<SalesProject> implements SalesProjectDao{
	
	private static final String names = "周苏建";
	public SalesProjectDaoImpl() {
		super(SalesProject.class);
	}

	@Override
	public String getNewProNo(String depName) {
		String newNo = "";
		int len = depName.indexOf("团队");
		String shortName = "";
		String shortDepName = depName.substring(0,len);
		if(names.contains(shortDepName)){
			shortName = PinyingUtil.getHeadString(shortDepName)+"I";
			len++;
		}else{
			shortName = PinyingUtil.getHeadString(shortDepName);
		}
		String search = shortName +"."+DateUtil.shortYear();
		String hql = "select proNo from SalesProject where proNo like '%"+search+"%' order by proNo desc";
		Object obj = this.getSession().createQuery(hql).setMaxResults(1).uniqueResult();
		if(obj==null){
			newNo = search+"01";
		}else{
			String maxNo = obj.toString();
			DecimalFormat df = new DecimalFormat("00");
			newNo = search + df.format(1 + Integer.parseInt(maxNo.substring(len+3)));
		}
		return newNo;
	}

	@Override
	public SalesProject getByProName(String proName) {
		String hql="from SalesProject p where p.proName=?";
		return (SalesProject) findUnique(hql, new Object[]{proName});
	}

}