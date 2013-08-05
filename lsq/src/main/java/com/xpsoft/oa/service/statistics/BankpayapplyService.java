package com.xpsoft.oa.service.statistics;

import java.util.List;
import java.util.Map;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.statistics.Bankpayapply;
import com.xpsoft.oa.model.statistics.PayAndBillDetailDTO;
import com.xpsoft.oa.model.system.AppUser;

public interface BankpayapplyService extends BaseService<Bankpayapply> {
	public List listForReport(QueryFilter filter, Map<String, Object> dataMap);

	/**
	 * 获取申请表单部分数据
	 * 
	 * @param proNo
	 * @param materialId
	 * @return map keys: leftMoney资金结余 ,quota垫资审批额度 ,leftBill发票结余
	 */
	public Map<String, Object> getApplyDataByProNo(String proNo,
			String materialId, Long proId, Long suppId);

	public List<Bankpayapply> getMyData(QueryFilter filter, AppUser currentUser);

	public List<Object[]> getAllAndPayBase(String proName, String sDate,
			String eDate,QueryFilter filter);

	public List<Object[]> getMyDataAndPayBase(String proName, String sDate,
			String eDate,QueryFilter filter, AppUser currentUser);

	public List<PayAndBillDetailDTO> getPayAndBillDetail(QueryFilter filter, String pIds);
}
