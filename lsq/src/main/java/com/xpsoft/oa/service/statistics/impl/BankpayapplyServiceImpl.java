package com.xpsoft.oa.service.statistics.impl;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.BankpayapplyDao;
import com.xpsoft.oa.dao.statistics.BillDao;
import com.xpsoft.oa.dao.statistics.PaybaseDao;
import com.xpsoft.oa.dao.statistics.ProjectPercentageDao;
import com.xpsoft.oa.dao.statistics.ProjectQuotaDao;
import com.xpsoft.oa.dao.statistics.ProjectReceiveDao;
import com.xpsoft.oa.dao.statistics.ProjectRepayDao;
import com.xpsoft.oa.model.statistics.Bankpayapply;
import com.xpsoft.oa.model.statistics.PayAndBillDetailDTO;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.statistics.BankpayapplyService;

public class BankpayapplyServiceImpl extends BaseServiceImpl<Bankpayapply> implements BankpayapplyService{
	private BankpayapplyDao dao;
	@Autowired
	private ProjectReceiveDao projectReceiveDao;
	@Autowired
	private ProjectPercentageDao projectPercentageDao;
	@Autowired
	private ProjectRepayDao projectRepayDao;
	@Autowired
	private ProjectQuotaDao projectQuotaDao;
	@Autowired
	private PaybaseDao paybaseDao;
	@Autowired
	private BillDao billDao;
	public BankpayapplyServiceImpl(BankpayapplyDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List listForReport(QueryFilter filter,Map<String,Object> dataMap){
		// TODO Auto-generated method stub
		return dao.listForReport(filter,dataMap);
	}

	@Override
	public Map<String, Object> getApplyDataByProNo(String proNo,String materialId,Long proId,Long suppId) {
		// TODO Auto-generated method stub
		Map<String,Object> dataMap = new HashMap<String,Object>();
		//得到此项目付款数
		BigDecimal received = projectReceiveDao.getTotalReceiveByProNo(proNo);
		//得到此项目管理费率
		BigDecimal percentage = projectPercentageDao.getPercentageByProNo(proNo);
		//得到此项目报销费用
		BigDecimal rePayed = projectRepayDao.getRePayedByProNo(proNo);
		//得到此项目下的所有的材料发包合同的累计 工程银行付款
		BigDecimal payed = dao.getPayedByProNo(proNo);
		//得到此项目下的所有的材料发包合同的付款基数和
		BigDecimal payBaseProject = paybaseDao.getTotalByProNo(proNo);
		BigDecimal materialTotalPayed = payed.add(payBaseProject);//项目对应  所有材料发包合同总付款
		
		
		//得到此项目垫资审批额度
		BigDecimal quota = projectQuotaDao.getQuotaByProNo(proNo);
		BigDecimal billAmount = new BigDecimal(0);
//		if(StringUtils.isNotBlank(materialId)){
//			billAmount = billDao.getTotalByMaterialId(Long.valueOf(materialId));
//		}
		if(suppId!=0){
			billAmount = billDao.getTotalByProAndSupp(proId,suppId);
		}
		//计算资金结余
		BigDecimal leftMoney = received.subtract(percentage.divide(new BigDecimal(100)).multiply(received))
								.subtract(rePayed).subtract(materialTotalPayed);
		
		BigDecimal payBaseMaterial = new BigDecimal(0);
		BigDecimal payedMaterial = new BigDecimal(0);
//		if(StringUtils.isNotBlank(materialId)){
		//	payBaseMaterial = paybaseDao.getTotalByMaterialId(Long.valueOf(materialId));//得到此材料发包合同的付款基数
			if(suppId!=0){
				payBaseMaterial = paybaseDao.getTotalByProAndSupp(proId,suppId);//得到此材料发包合同的付款基数
			}
		//	payedMaterial = dao.getPayedByMaterialId(Long.valueOf(materialId));//得到此材料发包合同的 累计工程银行付款
			payedMaterial = dao.getPayedByProAndSupp(proNo,suppId);//得到 项目对就的供应商  累计工程银行付款
//		}
		
		//计算发票结余  相对材料发包合同：  已收发票和  - 累计已付款
		BigDecimal leftBill = billAmount.subtract(payedMaterial.add(payBaseMaterial));
		
		dataMap.put("leftMoney", leftMoney.setScale(2, BigDecimal.ROUND_HALF_UP));//资金结余
		dataMap.put("quota", quota);//垫资审批额度
		if(suppId!=0){
			dataMap.put("leftBill", leftBill.setScale(2, BigDecimal.ROUND_HALF_UP));//发票结余
		}
		return dataMap;
	}

	@Override
	public List<Bankpayapply> getMyData(QueryFilter filter, AppUser currentUser) {
		return dao.getMyData(filter,currentUser);
	}

	@Override
	public List<Object[]> getAllAndPayBase(String proName, String sDate,
			String eDate,QueryFilter filter) {
		
		return dao.getAllAndPayBase(proName,sDate,eDate,filter);
	}

	@Override
	public List<Object[]> getMyDataAndPayBase(String proName, String sDate,
			String eDate,QueryFilter filter,
			AppUser currentUser) {
		return dao.getMyDataAndPayBase(proName,sDate,eDate,filter,currentUser);
	}

	@Override
	public List<PayAndBillDetailDTO> getPayAndBillDetail(QueryFilter filter, String pIds) {
		return dao.getPayAndBillDetail(filter,pIds);
	}

}