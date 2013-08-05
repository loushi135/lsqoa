package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.customer.SuppliersAssess;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.statistics.Bankpayapply;
import com.xpsoft.oa.model.statistics.Entrant;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.customer.SuppliersAssessService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.statistics.BankpayapplyService;
import com.xpsoft.oa.service.statistics.EntrantService;
import com.xpsoft.oa.service.statistics.MaterialContractService;
import com.xpsoft.oa.service.system.AppUserService;

public class BankPayApplyFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(BankPayApplyFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String bpaProjectName = (String)pi.getVariable("bpaProjectName");
		String bpaProjectNo = (String)pi.getVariable("bpaProjectNo");
		String bpaPayType = (String)pi.getVariable("bpaPayType");
		String bpaReceiptDept = (String)pi.getVariable("bpaReceiptDept");
		String bpaReceiptReason = (String)pi.getVariable("bpaReceiptReason");
		BigDecimal bpaContract = (BigDecimal)pi.getVariable("bpaContract");
		BigDecimal bpaSumMoney = (BigDecimal)pi.getVariable("bpaSumMoney");
		BigDecimal bpaSumMoneyRatio = (BigDecimal)pi.getVariable("bpaSumMoneyRatio");
		BigDecimal bpaFundBalance = (BigDecimal)pi.getVariable("bpaFundBalance");
		BigDecimal bpaPayRatio = (BigDecimal)pi.getVariable("bpaPayRatio");
		BigDecimal bpaInvoiceBalance = (BigDecimal)pi.getVariable("bpaInvoiceBalance");
		BigDecimal bpaApplyMoneyXX = (BigDecimal)pi.getVariable("bpaApplyMoneyXX");
		String bpaApplyMoneyDX = (String)pi.getVariable("bpaApplyMoneyDX");
		String bpaRemark = (String)pi.getVariable("bpaRemark");
		String bpaReceiptDeptId = (String)pi.getVariable("bpaReceiptDeptId");
		String attachIds = (String)pi.getVariable("bpaAttachIDs");
		String attachFiles = (String)pi.getVariable("bpaAttachFile");
		String bpaDeptCharger = (String)pi.getVariable("bpaDeptCharger");
		String bpaDeptChargerId = (String)pi.getVariable("bpaDeptChargerId");
		String materialContractId = (String)pi.getVariable("materialContractId");
		Bankpayapply bankpayapply = new Bankpayapply();
		bankpayapply.setBpaProjectName(bpaProjectName);
		bankpayapply.setBpaProjectNo(bpaProjectNo);
		bankpayapply.setBpaPayType(bpaPayType);
		bankpayapply.setBpaReceiptDept(bpaReceiptDept);
		bankpayapply.setBpaReceiptReason(bpaReceiptReason);
		bankpayapply.setBpaContract(bpaContract);
		bankpayapply.setBpaSumMoney(bpaSumMoney);
		bankpayapply.setBpaSumMoneyRatio(bpaSumMoneyRatio);
		
		bankpayapply.setBpaFundBalance(bpaFundBalance);
		bankpayapply.setBpaPayRatio(bpaPayRatio);
		bankpayapply.setBpaInvoiceBalance(bpaInvoiceBalance);
		
		bankpayapply.setBpaApplyMoneyXX(bpaApplyMoneyXX);
		bankpayapply.setBpaApplyMoneyDX(bpaApplyMoneyDX);
		bankpayapply.setBpaApplyFirstMoneyXX(bpaApplyMoneyXX);
		bankpayapply.setBpaApplyFirstMoneyDX(bpaApplyMoneyDX);
		
		bankpayapply.setBpaRemark(bpaRemark);
		bankpayapply.setAttachFiles(attachFiles);
		bankpayapply.setAttachIds(attachIds);
		
		if(StringUtils.isNotBlank(bpaReceiptDeptId)){
			SuppliersAssessService suppliersAssessService = (SuppliersAssessService)AppUtil.getBean("suppliersAssessService");
			SuppliersAssess  suppliersAssess = suppliersAssessService.get(Long.valueOf(bpaReceiptDeptId));
			bankpayapply.setSuppliersAssess(suppliersAssess);
		}
		bankpayapply.setBpaDeptChargerName(bpaDeptCharger);
		if(StringUtils.isNotBlank(bpaDeptChargerId)){
			AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
			bankpayapply.setBpaDeptCharger(appUserService.get(Long.valueOf(bpaDeptChargerId)));
		}
		if(StringUtils.isNotBlank(materialContractId)){
			MaterialContractService materialContractService = (MaterialContractService)AppUtil.getBean("materialContractService");
			bankpayapply.setMaterialContract(materialContractService.get(Long.valueOf(materialContractId)));
		}
		bankpayapply.setPayMoneyDate(new Date());
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		bankpayapply.setProcessRunId(processRun.getRunId());
		
		BankpayapplyService bankpayapplyService = (BankpayapplyService)AppUtil.getBean("bankpayapplyService");
		bankpayapplyService.save(bankpayapply);
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);

	}

}
