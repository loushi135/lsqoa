package com.xpsoft.oa.dao.statistics;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.statistics.Bankpayapply;
import com.xpsoft.oa.model.statistics.PayAndBillDetailDTO;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.system.AppUser;

/**
 * 
 * @author 
 *
 */
public interface BankpayapplyDao extends BaseDao<Bankpayapply>{
	public List listForReport(QueryFilter filter,Map<String,Object> dataMap);
	
	/**
	 * 获得某项目所有已付款
	 * @param proNo
	 * @return
	 */
	public BigDecimal getPayedByProNo(String proNo);
	@Deprecated
	public BigDecimal getPayedByMaterialId(Long materialId);

	public BigDecimal getPayedByProAndSupp(String proNo, Long suppId);

	public List<Bankpayapply> getByProAndSupp(String proNo, Long suppliersId);

	public List<Bankpayapply> getMyData(QueryFilter filter, AppUser currentUser);


	public List<Object[]> getAllAndPayBase(String proName, String sDate,
			String eDate,QueryFilter filter);

	public List<Object[]> getMyDataAndPayBase(String proName, String sDate,
			String eDate,QueryFilter filter, AppUser currentUser);

	public List<PayAndBillDetailDTO> getPayAndBillDetail(QueryFilter filter, String pIds);
}