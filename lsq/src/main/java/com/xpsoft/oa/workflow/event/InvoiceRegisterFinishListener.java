package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.util.Trans2RMB;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.Bill;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.customer.SuppliersAssessService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.BillService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.system.AppUserService;


public class InvoiceRegisterFinishListener implements EventListener {
	
	private Log logger=LogFactory.getLog(InvoiceRegisterFinishListener.class);

	@Override
	public void notify(EventListenerExecution execution) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.info("enter the InvoiceRegisterFinishListener notify method...");
		}
		
		BillService billService = (BillService) AppUtil.getBean("billService");
		AppUserService appUserService=(AppUserService)AppUtil.getBean("appUserService");
		ProjectNewService projectNewService = (ProjectNewService)AppUtil.getBean("projectNewService");
		SuppliersAssessService suppliersAssessService = (SuppliersAssessService)AppUtil.getBean("suppliersAssessService");
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		Object userId=pi.getVariable("bill.userId");//申请人ID
		Object projectId=pi.getVariable("bill.projectId");//项目ID
		Object providerId=pi.getVariable("bill.providerId");//供应商ID
		Object invoiceType=pi.getVariable("bill.invoiceType");//发票类型
		Object invoiceCount=pi.getVariable("bill.invoiceCount");//发票金额
		Object time=pi.getVariable("bill.billTime");//发票时间
		Bill bill=new Bill();
		
		if(userId!=null){
			bill.setCreateUser(appUserService.get(Long.valueOf((String)userId)));
		}
		if(projectId!=null){
			bill.setProjectNew(projectNewService.get(Long.valueOf((String)projectId)));
		}
		if(providerId!=null){
			bill.setSuppliersAssess(suppliersAssessService.get(Long.valueOf((String)providerId)));
		}
		if(invoiceType!=null){
			bill.setInvoiceType((String)invoiceType);
		}
		if(time!=null){
			bill.setBillTime((Date)time);
		}
		if(invoiceCount!=null){
			bill.setAmount(new BigDecimal((String)invoiceCount));
			Trans2RMB trans2rmb=new Trans2RMB();
			bill.setAmountBig(trans2rmb.toRMB((String)invoiceCount));
		}
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		
		bill.setProcessRunId(processRun.getRunId());
		
		bill.setCreatetime(new Date());
		billService.save(bill);
	  //获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");

			
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
//		smService.save(AppUser.SYSTEM_USER, flowStartUser.getId(), "您的申请已通过最终审批！", ShortMessage.MSG_TYPE_SYS);

	}
}
