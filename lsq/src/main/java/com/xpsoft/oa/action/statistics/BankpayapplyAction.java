package com.xpsoft.oa.action.statistics;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.statistics.Bankpayapply;
import com.xpsoft.oa.model.statistics.BankpayapplyUpdate;
import com.xpsoft.oa.model.statistics.PayAndBillDetailDTO;
import com.xpsoft.oa.model.statistics.Paybase;
import com.xpsoft.oa.service.statistics.BankpayapplyService;
import com.xpsoft.oa.service.statistics.BankpayapplyUpdateService;
import com.xpsoft.oa.service.statistics.PaybaseService;

/**
 * 
 * @author
 * 
 */
public class BankpayapplyAction extends BaseAction {
	@Resource
	private BankpayapplyService bankpayapplyService;
	@Resource
	private PaybaseService paybaseService;

	private Bankpayapply bankpayapply;

	private Long bankPayApplyId;

	public Long getBankPayApplyId() {
		return bankPayApplyId;
	}

	public void setBankPayApplyId(Long bankPayApplyId) {
		this.bankPayApplyId = bankPayApplyId;
	}

	public Bankpayapply getBankpayapply() {
		return bankpayapply;
	}

	public void setBankpayapply(Bankpayapply bankpayapply) {
		this.bankpayapply = bankpayapply;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());

		// filter.addFilter("Q_bpaStauts_N_EQ", 0+"");

		List<Bankpayapply> list = bankpayapplyService.getAll(filter);

		Type type = new TypeToken<List<Bankpayapply>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(filter.getPagingBean().getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		jsonString = buff.toString();

		return SUCCESS;
	}

	public String listDetail() {
		QueryFilter filter = new QueryFilter(getRequest());

		// filter.addFilter("Q_bpaStauts_N_EQ", 0+"");

		List<Bankpayapply> list = bankpayapplyService.getAll(filter);
		// 付款基数记录条数
		int countPayBase = paybaseService.getCountByProName(getRequest().getParameter("Q_bpaProjectName_S_EQ"));

		int start = filter.getPagingBean().getPageSize() - list.size();
		List<Paybase> paybases = null;
		if (start > 0) {
			int first = filter.getPagingBean().getFirstResult() - filter.getPagingBean().getTotalItems();
			paybases = paybaseService.getByProName(getRequest().getParameter("Q_bpaProjectName_S_EQ"), first > 0 ? first : 0, start);
		}

		// List<Paybase>
		// paybases=paybaseService.getByProName(getRequest().getParameter("Q_bpaProjectName_S_EQ"));

		Type type = new TypeToken<List<Bankpayapply>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(filter.getPagingBean().getTotalItems() + countPayBase).append(",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));

		// Q_bpaProjectName_S_EQ

		buff.deleteCharAt(buff.length() - 1);

		StringBuffer buffTmp = new StringBuffer("");
		if (null != paybases) {
			for (Paybase p : paybases) {
				buffTmp.append(",{");

				buffTmp.append("'bpaProjectName': '" + p.getProjectNew().getProName() + "'");
				buffTmp.append(",'bpaProjectNo': '" + p.getProjectNew().getProNo() + "'");
				buffTmp.append(",'bpaPayType': '无'");
				buffTmp.append(",'bpaReceiptDept': '" + p.getSuppliersAssess().getSuppliersName() + "'");
				buffTmp.append(",'bpaReceiptReason': '付款基数'");
				buffTmp.append(",'bpaApplyMoneyXX': '" + p.getPaymentBase() + "'");
				buffTmp.append(",'bpaApplyMoneyDX': '" + p.getPaymentBaseBig() + "'");
				buffTmp.append(",'bpaApplyFirstMoneyXX': ''");
				buffTmp.append(",'bpaApplyFirstMoneyDX': ''");
				buffTmp.append(",'bpaRemark': '" + p.getCreateUser().getFullname() + "'");

				buffTmp.append("}");

			}
		}
		if (list.isEmpty() && (!paybases.isEmpty())) {
			buffTmp.deleteCharAt(0);
		}

		buff.append(buffTmp);

		buff.append("]}");

		jsonString = buff.toString();

		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				bankpayapplyService.remove(new Long(id));
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		Bankpayapply bankpayapply = bankpayapplyService.get(bankPayApplyId);

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bankpayapply));
		sb.append("}");
		setJsonString(sb.toString());

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		if (bankpayapply.getBpaDeptCharger() == null || bankpayapply.getBpaDeptCharger().getUserId() == null) {
			bankpayapply.setBpaDeptCharger(null);
		}
		if (bankpayapply.getMaterialContract() == null || bankpayapply.getMaterialContract().getId() == null) {
			bankpayapply.setMaterialContract(null);
		}
		bankpayapplyService.save(bankpayapply);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	public String getPayedByProNo() {
		QueryFilter filter = new QueryFilter(getRequest());
		// filter.addFilter("Q_bpaStauts_N_EQ", 0+"");
		List<Bankpayapply> list = bankpayapplyService.getAll(filter);
		// String materialId =
		// getRequest().getParameter("Q_materialContract.id_L_EQ");
		// BigDecimal payBaseTotal =
		// paybaseService.getTotalByMaterialId(Long.valueOf(materialId));
		// BigDecimal payed = payBaseTotal;
		BigDecimal payBaseTotal = new BigDecimal(0);
		if (StringUtils.isNotEmpty(getRequest().getParameter("proId")) && StringUtils.isNotEmpty(getRequest().getParameter("Q_suppliersAssess.suppliersId_L_EQ"))) {
			Long proId = Long.valueOf(getRequest().getParameter("proId"));
			Long suppId = Long.valueOf(getRequest().getParameter("Q_suppliersAssess.suppliersId_L_EQ"));
			payBaseTotal = paybaseService.getTotalByProAndSupp(proId, suppId);
		}

		BigDecimal payed = new BigDecimal(0);
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		for (Bankpayapply bankpayapply : list) {
			payed = payed.add(bankpayapply.getBpaApplyMoneyXX());
		}

		sb.append(payed.add(payBaseTotal).setScale(2, BigDecimal.ROUND_HALF_UP));
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	public String getApplyData() {
		String materialId = getRequest().getParameter("materialId");
		String proNo = getRequest().getParameter("proNo");
		String projectId = getRequest().getParameter("projectId");

		String suppliersId = getRequest().getParameter("suppliersId");

		if (StringUtils.isEmpty(suppliersId)) {
			suppliersId = "0";
		}

		Map<String, Object> dataMap = bankpayapplyService.getApplyDataByProNo(proNo, materialId, Long.valueOf(projectId), Long.valueOf(suppliersId));
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		sb.append(gson.toJson(dataMap));
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 显示列表
	 */
	public String listForReport() {

		QueryFilter filter = new QueryFilter(getRequest());
		String bpaProjectName = getRequest().getParameter("bpaProjectName");
		String bpaProjectNo = getRequest().getParameter("bpaProjectNo");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("bpaProjectName", bpaProjectName);
		dataMap.put("bpaProjectNo", bpaProjectNo);
		List list = bankpayapplyService.listForReport(filter, dataMap);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(list.size()).append(",result:[");
		for (Object obj : list) {
			Object[] o = (Object[]) obj;
			buff.append("{'bpaProjectName':'" + o[0] + "',");
			buff.append("'bpaProjectNo':'" + o[1] + "',");
			buff.append("'sumMoney':'" + o[2] + "'},");
		}
		buff.append("]}");

		jsonString = buff.toString();

		return SUCCESS;
	}

	public String update() {
		bankpayapply = bankpayapplyService.get(bankpayapply.getBankPayApplyId());
		BigDecimal oldApplyMoneyXX = new BigDecimal((String) getRequest().getParameter("bankpayapply_oldApplyMoneyXX"));
		BigDecimal nowApplyMoneyXX = new BigDecimal((String) getRequest().getParameter("bankpayapply_nowApplyMoneyXX"));
		String nowApplyMoneyDX = getRequest().getParameter("bankpayapply_nowApplyMoneyDX");

		String updateReason = (String) getRequest().getParameter("bankpayapply_updateReason");

		BankpayapplyUpdate bankpayapplyUpdate = new BankpayapplyUpdate();

		bankpayapplyUpdate.setBankpayapply(bankpayapply);
		bankpayapplyUpdate.setNowApplyMoneyXX(nowApplyMoneyXX);
		bankpayapplyUpdate.setOldApplyMoneyXX(oldApplyMoneyXX);
		bankpayapplyUpdate.setTimeCreate(new Date());
		bankpayapplyUpdate.setUpdateReason(updateReason);
		bankpayapplyUpdate.setUserCreate(ContextUtil.getCurrentUser());

		bankpayapply.getBankpayapplyUpdates().add(bankpayapplyUpdate);

		bankpayapply.setBpaApplyMoneyXX(nowApplyMoneyXX);
		bankpayapply.setBpaApplyMoneyDX(nowApplyMoneyDX);

		bankpayapplyService.save(bankpayapply);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	public String listForExport() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<Object[]> list = null;
		String proName = getRequest().getParameter("bpaProjectName");
		String sDate = getRequest().getParameter("Q_payMoneyDate_D_GE");
		String eDate = getRequest().getParameter("Q_payMoneyDate_D_LE");
		if (ContextUtil.getCurrentUser().getRights().contains("__ALL") || ContextUtil.getCurrentUser().getRights().contains("_ALLBankPayApplyExportQuery")) {
			list = bankpayapplyService.getAllAndPayBase(proName, sDate, eDate, filter);
		} else {
			list = bankpayapplyService.getMyDataAndPayBase(proName, sDate, eDate, filter, ContextUtil.getCurrentUser());
		}

		Type type = new TypeToken<List<Bankpayapply>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(filter.getPagingBean().getTotalItems()).append(",result:[");
		StringBuffer buffTmp = new StringBuffer("");
		for (int i = 0; i < list.size(); i++) {
			buffTmp.append(",{");

			buffTmp.append("'bpaProjectName': '" + list.get(i)[0] + "'");
			buffTmp.append(",'bpaProjectNo': '" + list.get(i)[1] + "'");
			buffTmp.append(",'bpaApplyMoneyXX': '" + list.get(i)[2] + "'");
			buffTmp.append(",'bpaApplyMoneyDX': '" + list.get(i)[3] + "'");
			buffTmp.append(",'payMoneyDate': '" + list.get(i)[4] + "'");

			buffTmp.append("}");

		}
		buffTmp.deleteCharAt(0);

		buff.append(buffTmp);

		buff.append("]}");

		jsonString = buff.toString();

		return SUCCESS;
	}

	
	public String listPayAndBillDetail(){
		
		QueryFilter filter = new QueryFilter(getRequest());
//		filter.addFilter("Q_u.delFlag_SN_EQ", Constants.FLAG_UNDELETED
//				.toString());
		
		String pIds=getRequest().getParameter("pIds");
		
		List<PayAndBillDetailDTO> list=bankpayapplyService.getPayAndBillDetail(filter,pIds);
		
		
		Type type = new TypeToken<List<PayAndBillDetailDTO>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(filter.getPagingBean().getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		jsonString = buff.toString();
		
		return SUCCESS;
	}
	
}
