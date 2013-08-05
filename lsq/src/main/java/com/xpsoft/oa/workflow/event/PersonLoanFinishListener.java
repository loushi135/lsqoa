package com.xpsoft.oa.workflow.event;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.statistics.PersonLoan;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.statistics.PersonLoanService;
public class PersonLoanFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(PersonLoanFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the UnpunchListener notify method...");
		}
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String loanReport = (String)pi.getVariable("loanReport");
		String loanDepatment =(String)pi.getVariable("loanDepatment");
		String personCompanyName = (String)pi.getVariable("personCompanyName");
		String loanMoney = (String)pi.getVariable("loanMoney");
		String loanMonth = (String)pi.getVariable("loanMonth");
		String loanCase = (String)pi.getVariable("loanCase");
		String loanYear = (String)pi.getVariable("loanYear");
		String loanMoneyBig=(String)pi.getVariable("loanMoneyBig");
		
		PersonLoanService personLoanService = (PersonLoanService)AppUtil.getBean("personLoanService");
		PersonLoan personLoan = new PersonLoan();
		personLoan.setLoanDepatment(loanDepatment);
		personLoan.setCreateDate(new Date());
		personLoan.setLoanCase(loanCase);
		personLoan.setLoanMoney(new BigDecimal(loanMoney));
		personLoan.setLoanMoneyBig(loanMoneyBig);
		personLoan.setLoanReport(loanReport);
		personLoan.setLoanYear(loanYear);
		personLoan.setPersonCompanyName(personCompanyName);
		personLoan.setLoanMonth(loanMonth);
		personLoanService.save(personLoan);
		// 获取表单参数
		ShortMessageService smService = (ShortMessageService) AppUtil.getBean("shortMessageService");
		
		smService.save(AppUser.SYSTEM_USER, flowStartUser.getId(), "您的申请已通过最终审批！", ShortMessage.MSG_TYPE_SYS);

	}

}
