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
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.statistics.PaymentList;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.statistics.PaymentListService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.system.AppUserService;

public class PaymentListApplyFinishListener implements EventListener {
	private Log logger=LogFactory.getLog(PaymentListApplyFinishListener.class);
	
	@Override
	public void notify(EventListenerExecution  execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the PaymentListApplyFinishListener notify method...");
		}

	//获取流程发起者对象
	AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	PaymentListService plService = (PaymentListService) AppUtil.getBean("paymentListService");
	ProjectNewService projectNewService = (ProjectNewService)AppUtil.getBean("projectNewService");
	
	//获取表单数据
	OpenProcessInstance pi=execution.getProcessInstance();
	String proId=(String)pi.getVariable("proId");//项目主键
	String deptName=(String)pi.getVariable("deptName");//部门名称
	String paymentType=(String)pi.getVariable("paymentType");//暂支类别
//	String owedSum=(String)pi.getVariable("owedSum");//目前欠款金额
	String paymentSumBig=(String)pi.getVariable("paymentSumBig");//本次借款金额大写
	BigDecimal paymentSumSmall=(BigDecimal)pi.getVariable("paymentSumSmall");//本次借款金额小写
	Date preNowReturnTime=(Date)pi.getVariable("preNowReturnTime");//预计归还时间
	String borrower=(String)pi.getVariable("borrower");//借款人
	String payOption=(String)pi.getVariable("payOption");
	String paymentCause=(String)pi.getVariable("paymentCause");
	String attachIds=(String)pi.getVariable("payment.attachIds");
	String attachFiles=(String)pi.getVariable("payment.attachFiles");
	PaymentList paymentList = new PaymentList();
	if(StringUtils.isNotBlank(proId)){
		ProjectNew projectNew = projectNewService.get(Long.valueOf(proId));
		paymentList.setProject(projectNew);
	}
	paymentList.setDeptName(deptName);
	paymentList.setOwedSum(paymentSumSmall==null?"0":paymentSumSmall.toString());//目前欠款金额为借款金额
	paymentList.setPaymentSumBig(paymentSumBig);
	paymentList.setPaymentSumSmall(paymentSumSmall);
	paymentList.setPreNowReturnTime(preNowReturnTime);
	paymentList.setPaymentType(paymentType);
	paymentList.setBorrower(borrower);
	paymentList.setUser(flowStartUser);
	paymentList.setPayOption(payOption);
	paymentList.setPaymentCause(paymentCause);
	paymentList.setAttachFiles(attachFiles);
	paymentList.setAttachIds(attachIds);
	paymentList.setPayMoneyDate(new Date());
	
	ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
	ProcessRun processRun=processRunService.getByExeId(pi.getId());
	paymentList.setProcessRunId(processRun.getRunId());
	
	plService.save(paymentList);
	
	
	//所有参与流程的都发消息
	AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
}
}