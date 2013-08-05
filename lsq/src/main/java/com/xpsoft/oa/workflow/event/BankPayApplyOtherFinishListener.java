package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.statistics.Bankpayapply;
import com.xpsoft.oa.model.statistics.Bankpayapplyother;
import com.xpsoft.oa.model.statistics.Entrant;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.statistics.BankpayapplyService;
import com.xpsoft.oa.service.statistics.BankpayapplyotherService;
import com.xpsoft.oa.service.statistics.EntrantService;

public class BankPayApplyOtherFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(BankPayApplyOtherFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String bpaProjectName = (String)pi.getVariable("bpaProjectNameOT");
		String bpaProjectNo = (String)pi.getVariable("bpaProjectNoOT");
		String bpaPayType = (String)pi.getVariable("bpaPayTypeOT");
		String bpaReceiptDept = (String)pi.getVariable("bpaReceiptDeptOT");
		String bpaReceiptReason = (String)pi.getVariable("bpaReceiptReasonOT");
		BigDecimal bpaContract = (BigDecimal)pi.getVariable("bpaContractOT");
		BigDecimal bpaSumMoney = (BigDecimal)pi.getVariable("bpaSumMoneyOT");
		BigDecimal bpaSumMoneyRatio = (BigDecimal)pi.getVariable("bpaSumMoneyRatioOT");
		BigDecimal bpaInvoiceBalance = (BigDecimal)pi.getVariable("bpaInvoiceBalanceOT");
		BigDecimal bpaApplyMoneyXX = (BigDecimal)pi.getVariable("bpaApplyMoneyOTXX");
		String bpaApplyMoneyDX = (String)pi.getVariable("bpaApplyMoneyOTDX");
		String bpaAttachIDsOT = (String)pi.getVariable("bpaAttachIDsOT");
		String bpaAttachFileOT = (String)pi.getVariable("bpaAttachFileOT");
		String bpaRemark = (String)pi.getVariable("bpaRemarkOT");
		Bankpayapplyother bankpayapplyOT = new Bankpayapplyother();
		bankpayapplyOT.setBpaProjectName(bpaProjectName);
		bankpayapplyOT.setBpaProjectNo(bpaProjectNo);
		bankpayapplyOT.setBpaPayType(bpaPayType);
		bankpayapplyOT.setBpaReceiptDept(bpaReceiptDept);
		bankpayapplyOT.setBpaReceiptReason(bpaReceiptReason);
		bankpayapplyOT.setBpaContract(bpaContract);
		bankpayapplyOT.setBpaSumMoney(bpaSumMoney);
		bankpayapplyOT.setBpaSumMoneyRatio(bpaSumMoneyRatio);
		bankpayapplyOT.setBpaInvoiceBalance(bpaInvoiceBalance);
		bankpayapplyOT.setBpaApplyMoneyXX(bpaApplyMoneyXX);
		bankpayapplyOT.setBpaApplyMoneyDX(bpaApplyMoneyDX);
		bankpayapplyOT.setBpaRemark(bpaRemark);
		bankpayapplyOT.setAttachFiles(bpaAttachFileOT);
		bankpayapplyOT.setAttachIds(bpaAttachIDsOT);
		bankpayapplyOT.setPayMoneyDate(new Date());
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		bankpayapplyOT.setProcessRunId(processRun.getRunId());
		
		BankpayapplyotherService bankpayapplyotherService = (BankpayapplyotherService)AppUtil.getBean("bankpayapplyotherService");
		bankpayapplyotherService.save(bankpayapplyOT);
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
