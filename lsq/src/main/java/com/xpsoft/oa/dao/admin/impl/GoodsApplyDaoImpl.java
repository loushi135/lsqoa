package com.xpsoft.oa.dao.admin.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ToListResultTransformer;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.admin.GoodsApplyDao;
import com.xpsoft.oa.model.admin.GoodApplyDep;
import com.xpsoft.oa.model.admin.GoodApplyTotal;
import com.xpsoft.oa.model.admin.GoodsApply;

public class GoodsApplyDaoImpl extends BaseDaoImpl<GoodsApply> implements
		GoodsApplyDao {
	public GoodsApplyDaoImpl() {
		super(GoodsApply.class);
	}

	@Override
	public List<GoodApplyTotal> getGoodApplyTotals(String month) {
		// TODO Auto-generated method stub
		String format="";
		if(month.contains("-")){
			format ="%Y-%m";
		}else{
			format="%Y";
		}
		List<GoodApplyTotal> gaTotalList = new ArrayList<GoodApplyTotal>();
		String goodIdSql = "select ga.goodsId from goods_apply ga where DATE_FORMAT(ga.applyDate, ?) = ?  group by ga.goodsId";
		SQLQuery sqlQuery = getSession().createSQLQuery(goodIdSql);
		sqlQuery.setString(0,format);
		sqlQuery.setString(1,month);
		List list = sqlQuery.list();
		
		String goodsApplySql = "SELECT og.goodsId as goodsId,og.goodsName as goodsName,SUM(ga.useCounts) as num,og.price as price,SUM(ga.useCounts)*og.price as amount,userdept.depName as depName from goods_apply as ga," +
								"(SELECT app_user.userId,app_user.depId,app_user.fullname,department.depName from app_user,department where department.depId = app_user.depId) as userdept," +
								"office_goods as og WHERE ga.userId = userdept.userId AND og.goodsId = ga.goodsId and DATE_FORMAT(ga.applyDate, ?) = ? " +
								" GROUP BY userdept.depName,goodsName,goodsId  ORDER BY userdept.depName ";
		SQLQuery sqlQuery1 = getSession().createSQLQuery(goodsApplySql);
		sqlQuery1.setString(0,format);
		sqlQuery1.setString(1,month);
		sqlQuery1.addScalar("goodsId",Hibernate.LONG)
		.addScalar("goodsName",Hibernate.STRING)
		.addScalar("num",Hibernate.INTEGER)
		.addScalar("amount",Hibernate.BIG_DECIMAL)
		.addScalar("price",Hibernate.BIG_DECIMAL)
		.addScalar("depName",Hibernate.STRING);
		List<GoodApplyDep> goodDepList = sqlQuery1.setResultTransformer(new AliasToBeanResultTransformer(GoodApplyDep.class)).list();
		for(Object obj:list){
			BigInteger goodsId = (BigInteger)obj;
			GoodApplyTotal gaTotal = new GoodApplyTotal();
			int totalNum = 0;
			String goodsName = null;
			BigDecimal totalAmount = new BigDecimal(0);
			BigDecimal price = new BigDecimal(0);
			for(GoodApplyDep gaDep:goodDepList){
				if(gaDep.getGoodsId().compareTo(goodsId.longValue())==0){//相同物品类型
					if(price.compareTo(new BigDecimal(0))==0){
						price = gaDep.getPrice();
					}
					if(StringUtils.isBlank(goodsName)){
						goodsName = gaDep.getGoodsName();
					}
					totalNum+=gaDep.getNum();
					totalAmount=totalAmount.add(gaDep.getAmount());
					gaTotal.getGoodApplyDepList().add(gaDep);
				}
			}
			gaTotal.setGoodsName(goodsName);
			gaTotal.setGoodsId(goodsId.longValue());
			gaTotal.setTotalAmount(totalAmount);
			gaTotal.setPrice(price);
			gaTotal.setTotalNum(totalNum);
			gaTotalList.add(gaTotal);
		}
		return gaTotalList;
	}

	@Override
	public List<String> getDeptList(String month) {
		// TODO Auto-generated method stub
		List<String> deptList = new ArrayList<String>();
		String format="";
		if(month.contains("-")){
			format ="%Y-%m";
		}else{
			format="%Y";
		}
		String goodIdSql = "select userdept.depName as depName from goods_apply ga," +
				"(SELECT app_user.userId,app_user.depId,app_user.fullname,department.depName from app_user,department where department.depId = app_user.depId) as userdept" +
				" where DATE_FORMAT(ga.applyDate, ?) = ? and" +
				" ga.userId = userdept.userId group by userdept.depName;";
		SQLQuery sqlQuery = getSession().createSQLQuery(goodIdSql);
		sqlQuery.setString(0,format);
		sqlQuery.setString(1,month);
		sqlQuery.addScalar("depName",Hibernate.STRING);
		deptList = sqlQuery.list();
		return deptList;
	}
	
	
}
