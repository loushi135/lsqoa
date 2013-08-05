package com.xpsoft.oa.dao.statistics.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.xpsoft.core.command.CriteriaCommand;
import com.xpsoft.core.command.FieldCommandImpl;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.command.SortCommandImpl;
import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.BankpayapplyDao;
import com.xpsoft.oa.model.statistics.Bankpayapply;
import com.xpsoft.oa.model.statistics.PayAndBillDetailDTO;
import com.xpsoft.oa.model.system.AppUser;

public class BankpayapplyDaoImpl extends BaseDaoImpl<Bankpayapply> implements BankpayapplyDao {

	public BankpayapplyDaoImpl() {
		super(Bankpayapply.class);
	}

	public List listForReport(QueryFilter filter, Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		String hql = "select bpaProjectName,bpaProjectNo,sum(bpaApplyMoneyXX) from Bankpayapply where 1=1 ";// and
		// bpaStauts=0";
		String bpaProjectName = (String) dataMap.get("bpaProjectName");
		String bpaProjectNo = (String) dataMap.get("bpaProjectNo");
		if (StringUtils.isNotBlank(bpaProjectName)) {
			hql += " and bpaProjectName like '%" + bpaProjectName + "%'";
		}
		if (StringUtils.isNotBlank(bpaProjectNo)) {
			hql += " and bpaProjectNo like '%" + bpaProjectNo + "%'";
		}
		hql += "  group by bpaProjectNo";

		Query query = getSession().createQuery(hql).setFirstResult(filter.getPagingBean().getFirstResult()).setMaxResults(filter.getPagingBean().getPageSize());
		return query.list();
	}

	@Override
	public BigDecimal getPayedByProNo(String proNo) {
		// TODO Auto-generated method stub
		String hql = "select sum(bpaApplyMoneyXX) from Bankpayapply where bpaProjectNo=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, proNo);
		BigDecimal temp = (BigDecimal) query.uniqueResult();
		if (temp == null) {
			temp = new BigDecimal(0);
		}
		return temp;
	}

	@Override
	@Deprecated
	public BigDecimal getPayedByMaterialId(Long materialId) {
		// TODO Auto-generated method stub
		String hql = "select sum(bpaApplyMoneyXX) from Bankpayapply where materialContract.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, materialId);
		BigDecimal temp = (BigDecimal) query.uniqueResult();
		if (temp == null) {
			temp = new BigDecimal(0);
		}
		return temp;
	}

	@Override
	public BigDecimal getPayedByProAndSupp(String proNo, Long suppId) {
		String hql = "select sum(bpaApplyMoneyXX) from Bankpayapply where bpaProjectNo=? and suppliersAssess.suppliersId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, proNo);
		query.setParameter(1, suppId);
		BigDecimal temp = (BigDecimal) query.uniqueResult();
		if (temp == null) {
			temp = new BigDecimal(0);
		}
		return temp;
	}

	@Override
	public List<Bankpayapply> getByProAndSupp(String proNo, Long suppliersId) {
		String hql = "from Bankpayapply where bpaProjectNo=? and suppliersAssess.suppliersId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, proNo);
		query.setParameter(1, suppliersId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bankpayapply> getMyData(QueryFilter filter, AppUser currentUser) {

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

		final String hql = "select r from Bankpayapply r,ProjectNew p where r.bpaProjectName=p.proName and (p.proChargerUser=? or p.proFollower=? or p.area in ("
				+ "select vo1.depName from Department vo1,AppUser vo2 join vo2.roles  roles where vo1.depId=vo2.department.depId and  roles.roleName='流程-部门经理' and vo2=? ))"
				+ condition + " order by " + sortDesc;

		List params = new ArrayList();

		params.add(currentUser);
		params.add(currentUser.getFullname());
		params.add(currentUser);
		params.addAll(filter.getParamValueList());

		int totalItems = getTotalItems(hql, params.toArray()).intValue();
		filter.getPagingBean().setTotalItems(totalItems);
		return find(hql, params.toArray(), filter.getPagingBean().getFirstResult(), filter.getPagingBean().getPageSize());
	}

	@Override
	public List<Object[]> getAllAndPayBase(String proName, String sDate, String eDate, QueryFilter filter) {

		StringBuffer sql = new StringBuffer("select bpaProjectName,bpaProjectNo,bpaApplyMoneyXX,bpaApplyMoneyDX,payMoneyDate from bankpayapply where 1=1 ");

		List<Object> pamtrs = new ArrayList<Object>();

		if (StringUtils.isNotEmpty(proName)) {
			sql.append(" and bpaProjectName like ? ");
			pamtrs.add("%" + proName + "%");
		}
		if (StringUtils.isNotEmpty(sDate)) {
			sql.append(" and payMoneyDate >= ? ");
			pamtrs.add(sDate);
		}
		if (StringUtils.isNotEmpty(eDate)) {
			sql.append(" and payMoneyDate <= ? ");
			pamtrs.add(eDate);
		}

		sql.append(" UNION ALL select p.proName,p.proNo,paymentBase,paymentBaseBig,paymentTime from paybase b,project_new p  where p.id=b.proId ");

		if (StringUtils.isNotEmpty(proName)) {
			sql.append(" and p.proName like ? ");
			pamtrs.add("%" + proName + "%");
		}
		if (StringUtils.isNotEmpty(sDate)) {
			sql.append(" and paymentTime >= ? ");
			pamtrs.add(sDate);
		}
		if (StringUtils.isNotEmpty(eDate)) {
			sql.append(" and paymentTime <= ? ");
			pamtrs.add(eDate);
		}

		Query total = getSession().createSQLQuery("select count(*) from (" + sql.toString() + ")  as total");
		Query query = getSession().createSQLQuery(sql.toString());

		for (int i = 0; i < pamtrs.size(); i++) {
			total.setParameter(i, pamtrs.get(i));
			query.setParameter(i, pamtrs.get(i));
		}

		filter.getPagingBean().setTotalItems(((BigInteger) total.uniqueResult()).intValue());

		query.setFirstResult(filter.getPagingBean().getFirstResult());
		query.setMaxResults(filter.getPagingBean().getPageSize());

		return query.list();
		// return find(sql.toString(), pamtrs.toArray(),
		// filter.getPagingBean().getFirstResult(),
		// filter.getPagingBean().getPageSize());
	}

	@Override
	public List<Object[]> getMyDataAndPayBase(String proName, String sDate, String eDate, QueryFilter filter, AppUser currentUser) {
		StringBuffer sql = new StringBuffer(
				"select b.bpaProjectName,b.bpaProjectNo,b.bpaApplyMoneyXX,b.bpaApplyMoneyDX,b.payMoneyDate from bankpayapply b,project_new p where 1=1 ");

		sql.append(" and b.bpaProjectName=p.proName and (p.proChargerId=? or p.proFollower=? or p.area in (");
		sql.append(" select d.depName from department d,app_user a join user_role ur on a.userId=ur.userId, user_role uurr join app_role r on uurr.roleId=r.roleId");
		sql.append(" where d.depId=a.depId and r.roleName='流程-部门经理' and a.userId=? ))");

		List<Object> pamtrs = new ArrayList<Object>();

		if (StringUtils.isNotEmpty(proName)) {
			sql.append(" and bpaProjectName like ? ");
			pamtrs.add("%" + proName + "%");
		}
		if (StringUtils.isNotEmpty(sDate)) {
			sql.append(" and payMoneyDate >= ? ");
			pamtrs.add(sDate);
		}
		if (StringUtils.isNotEmpty(eDate)) {
			sql.append(" and payMoneyDate <= ? ");
			pamtrs.add(eDate);
		}

		sql.append(" UNION ALL select p.proName,p.proNo,paymentBase,paymentBaseBig,paymentTime from paybase b,project_new p  where p.id=b.proId ");

		if (StringUtils.isNotEmpty(proName)) {
			sql.append(" and p.proName like ? ");
			pamtrs.add("%" + proName + "%");
		}
		if (StringUtils.isNotEmpty(sDate)) {
			sql.append(" and paymentTime >= ? ");
			pamtrs.add(sDate);
		}
		if (StringUtils.isNotEmpty(eDate)) {
			sql.append(" and paymentTime <= ? ");
			pamtrs.add(eDate);
		}

		sql.append(" and (p.proChargerId=? or p.proFollower=? or p.area in (");
		sql.append(" select d.depName from department d,app_user a join user_role ur on a.userId=ur.userId, user_role uurr join app_role r on uurr.roleId=r.roleId");
		sql.append(" where d.depId=a.depId and r.roleName='流程-部门经理' and a.userId=? ))");

		Query total = getSession().createSQLQuery("select count(*) from (" + sql.toString() + ")  as total");
		Query query = getSession().createSQLQuery(sql.toString());

		total.setParameter(0, currentUser.getUserId());
		query.setParameter(0, currentUser.getUserId());
		total.setParameter(1, currentUser.getFullname());
		query.setParameter(1, currentUser.getFullname());
		total.setParameter(2, currentUser.getUserId());
		query.setParameter(2, currentUser.getUserId());
		int i = 0;
		for (i = 3; i < pamtrs.size() + 3; i++) {
			total.setParameter(i, pamtrs.get(i - 3));
			query.setParameter(i, pamtrs.get(i - 3));
		}
		total.setParameter(i, currentUser.getUserId());
		query.setParameter(i++, currentUser.getUserId());
		total.setParameter(i, currentUser.getFullname());
		query.setParameter(i++, currentUser.getFullname());
		total.setParameter(i, currentUser.getUserId());
		query.setParameter(i, currentUser.getUserId());

		filter.getPagingBean().setTotalItems(((BigInteger) total.uniqueResult()).intValue());

		query.setFirstResult(filter.getPagingBean().getFirstResult());
		query.setMaxResults(filter.getPagingBean().getPageSize());

		return query.list();
		// return find(sql.toS
	}

	@Override
	public List<PayAndBillDetailDTO> getPayAndBillDetail(QueryFilter filter, String pIds) {
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

		StringBuffer sql = new StringBuffer();

		sql.append(" select paySum.pNO proNO,paySum.pNE proName,paySum.sUPP supplierName,billSum.billCount billCount,paySum.payMoney payCount,(billSum.billCount-paySum.payMoney) billBalance ");
		sql.append("   from (SELECT pay.proNo pNO,pay.proName pNE,pay.sn sUPP,sum(payCount) payMoney  ");
		sql.append("		      from( " );
		sql.append("			      SELECT pn.proNo proNo,pn.proName proName,sa.suppliersName sn,p.paymentBase payCount FROM paybase p,project_new pn,suppliers_assess sa  ");
		sql.append("						where p.proId=pn.id and p.suppId=sa.suppliersId  ");
		sql.append("	                  UNION ALL ");
		sql.append("	              SELECT pn.proNo proNo,pn.proName proName,sa.suppliersName sn,bpa.bpaApplyMoneyXX payCount FROM bankpayapply bpa,project_new pn,suppliers_assess sa  ");
		sql.append("						where bpa.bpaReceiptDeptId=sa.suppliersId and  bpa.bpaProjectNo=pn.proNo ");
		sql.append("                ) AS pay GROUP BY proName, pay.sn ORDER BY sn desc  ");
		sql.append("      ) as paySum , ");
		sql.append("    (SELECT *,SUM(billTmp.billCount) FROM(select pn.id proId,pn.proNo proNo,pn.proName proName,sa.suppliersName sn,b.amount billCount ");
		sql.append("	     from bill b,project_new pn,suppliers_assess sa ");
		sql.append("			 where b.projectNew=pn.id and b.suppliersAssess=sa.suppliersId) as billTmp GROUP BY billTmp.proNo,billTmp.sn ) as billSum ");
		sql.append(" where paySum.pNO=billSum.proNo and paySum.sUPP=billSum.sn  ");

		if (!StringUtils.isEmpty(pIds)) {
			sql.append(" and billSum.proId in(" + pIds + ")");
		}

		sql.append(condition);

		if (!StringUtils.isEmpty(sortDesc)) {
			sql.append(" order by ").append(sortDesc);
		}else {
			sql.append(" order by paySum.pNO ");
		}

		Query total = getSession().createSQLQuery("select count(*) from (" + sql.toString() + ")  as total");
		Query query = getSession().createSQLQuery(sql.toString());

		Object[] parmets = filter.getParamValueList().toArray();

		for (int i = 0; i < parmets.length; i++) {
			query.setParameter(i, parmets[i]);
			total.setParameter(i, parmets[i]);
		}

		filter.getPagingBean().setTotalItems(((BigInteger) total.uniqueResult()).intValue());
		query.setFirstResult(filter.getPagingBean().getFirstResult());
		query.setMaxResults(filter.getPagingBean().getPageSize());
		((SQLQuery) query).addScalar("proNO", Hibernate.STRING)
		 .addScalar("proName", Hibernate.STRING)
		 .addScalar("supplierName", Hibernate.STRING)
		 .addScalar("billCount", Hibernate.BIG_DECIMAL)
			 .addScalar("payCount", Hibernate.BIG_DECIMAL)
			 .addScalar("billBalance", Hibernate.BIG_DECIMAL);
		return query.setResultTransformer(Transformers.aliasToBean(PayAndBillDetailDTO.class)).list();
	}
}