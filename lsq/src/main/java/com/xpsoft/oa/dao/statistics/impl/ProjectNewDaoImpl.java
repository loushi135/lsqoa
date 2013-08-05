package com.xpsoft.oa.dao.statistics.impl;


import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.statistics.ProjectNewDao;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectRelatedData;
import com.xpsoft.oa.model.system.AppRole;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.system.AppUserService;

public class ProjectNewDaoImpl extends BaseDaoImpl<ProjectNew> implements ProjectNewDao{
	@Autowired
	private AppUserService appUserService;
	public ProjectNewDaoImpl() {
		super(ProjectNew.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectRelatedData> getAllData(PagingBean pagingBean,Map<String,String> dataMap) {
		boolean isSuperUser = appUserService.getSuperManager();
		boolean isAreaManager = appUserService.isAreaManager();
		AppUser currentUser = ContextUtil.getCurrentUser();
	    Set<AppRole> roles = currentUser.getRoles();
	    for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
			AppRole appRole = (AppRole) iterator.next();
			if("工程项目完全查看".equals(appRole.getRoleName())){
				isSuperUser = true;
			}
	    }
	    String depName = currentUser.getDepartment().getDepName();
		StringBuilder querySql = new StringBuilder("where 1=1 ");
		if(!isSuperUser){
			if(isAreaManager){
				querySql.append(" and area like '%"+depName+"%'");
				if(StringUtils.isNotBlank(dataMap.get("proChargerName"))){
					querySql.append(" and proCharger like '%"+dataMap.get("proChargerName")+"%'");
				}else{
					querySql.append(" or ( proCharger like '%"+currentUser.getFullname()+"%' or proFollower like '%"+currentUser.getFullname()+"%')");
				}
			}else{
				querySql.append(" and ( proCharger like '%"+currentUser.getFullname()+"%' or proFollower like '%"+currentUser.getFullname()+"%')");
			}
		}else{
			if(StringUtils.isNotBlank(dataMap.get("area"))){
				querySql.append(" and area like '%"+dataMap.get("area")+"%'");
			}
			if(StringUtils.isNotBlank(dataMap.get("proChargerName"))){
				querySql.append(" and proCharger like '%"+dataMap.get("proChargerName")+"%'");
			}
		}
		if(StringUtils.isNotBlank(dataMap.get("proName"))){
			querySql.append(" and proName like '%"+dataMap.get("proName")+"%'");
		}
		if(StringUtils.isNotBlank(dataMap.get("proNo"))){
			querySql.append(" and proNo like '%"+dataMap.get("proNo")+"%'");
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select set5.*,(set5.bankPay+IFNULL(set5.payBaseAmount,0)) as bankPayAmount," +
				"		(set5.bankPay+set5.cashRePayAmount+IFNULL(set5.payBaseAmount,0)) as totalPayAmount, " +
				"		(set5.receiveAmount-set5.manageAmount-set5.bankPay-set5.cashRePayAmount-IFNULL(set5.payBaseAmount,0)) as leftAmount from " +
				"		(select set4.*,pbm.payBaseAmount from " +
				"			(select set3.*,IFNULL(sum(bpa.bpaApplyMoneyXX),0) as bankPay from " +
				"				(select set2.*,IFNULL(sum(rep.amount),0) as cashRePayAmount from  " +
				"					(select set1.*,IFNULL(sum(rec.amount),0) as receiveAmount,round((IFNULL(sum(rec.amount),0)*IFNULL(set1.managePercentage,0))/100,5) as manageAmount from  " +
				"							(select pro.id,pro.area,pro.proNo,pro.proName,pro.proCharger as proChargerName, " +
				"							IFNULL(con.sumPrice,0) as contractAmount,IFNULL(au.auditAmunt,0) as auditAmount,IFNULL(per.percentage,0) as managePercentage " +
				"							from (SELECT id,area,proNo,proName,proCharger,contractId from project_new  ");
		sql.append(querySql);
		sql.append(" ) pro " +
				"							left  JOIN constructioncontract con on pro.contractId = con.contractId " +
				"							left  JOIN project_audit au on au.id = pro.id " +
				"							left  JOIN project_percentage per on per.id = pro.id " +
				"							GROUP BY pro.id) as set1 left JOIN  project_receive rec on rec.proId = set1.id GROUP BY set1.id ) set2  " +
				"							left  JOIN project_repay rep on (rep.proId = set2.id and rep.repayType = 1) GROUP BY set2.id) set3 " +
				"							left  JOIN bankpayapply bpa  on bpa.bpaProjectNo = set3.proNo GROUP BY set3.id ) set4  " +
				"							LEFT  JOIN (select IFNULL(sum(pb.paymentBase),0) as payBaseAmount,pb.proId from project_new pn LEFT JOIN paybase pb on pn.id = pb.proId group by pn.id) pbm " +
				"							ON set4.id = pbm.proId GROUP BY set4.id ) set5");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		List<ProjectRelatedData> list = query.addScalar("area", Hibernate.STRING)
											 .addScalar("proNo", Hibernate.STRING)
											 .addScalar("proName", Hibernate.STRING)
											 .addScalar("proChargerName", Hibernate.STRING)
											 .addScalar("id", Hibernate.LONG)
											 .addScalar("contractAmount", Hibernate.BIG_DECIMAL)
		 									 .addScalar("auditAmount", Hibernate.BIG_DECIMAL)
		 									 .addScalar("receiveAmount", Hibernate.BIG_DECIMAL)
		 									 .addScalar("managePercentage", Hibernate.BIG_DECIMAL)
		 									 .addScalar("manageAmount", Hibernate.BIG_DECIMAL)
		 									 .addScalar("bankPayAmount", Hibernate.BIG_DECIMAL)
		 									 .addScalar("cashRePayAmount", Hibernate.BIG_DECIMAL)
		 									 .addScalar("totalPayAmount", Hibernate.BIG_DECIMAL)
		 									 .addScalar("leftAmount", Hibernate.BIG_DECIMAL)
		 									 .setResultTransformer(new AliasToBeanResultTransformer(ProjectRelatedData.class))
		 									 .setFirstResult(pagingBean.getFirstResult())
		 									 .setMaxResults(pagingBean.getPageSize())
		 									 .list();
		String sqlCount = "select count(0) from (" + sql + ")as tem";
		SQLQuery countQuery = getSession().createSQLQuery(sqlCount);
		Object temp = countQuery.uniqueResult();
		if(temp!=null){
			pagingBean.setTotalItems(Integer.valueOf(temp.toString()));
		}
		return list;
	}

	@Override
	public ProjectNew getByProName(String trim) {
		
		String hql="from ProjectNew p where p.proName=?";
		
		return (ProjectNew) findUnique(hql, new Object[]{trim});
	}

	@Override
	public ProjectNew getByProNo(String proNo) {
		String hql="from ProjectNew p where p.proNo=?";
		
		return (ProjectNew) findUnique(hql, new Object[]{proNo});
	}
}