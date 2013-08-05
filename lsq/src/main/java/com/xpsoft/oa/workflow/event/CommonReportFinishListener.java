package com.xpsoft.oa.workflow.event;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.statistics.CommonReport;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.statistics. CommonReportService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.system.AppUserService;

public class CommonReportFinishListener implements EventListener {


	private Log logger=LogFactory.getLog(PaymentListApplyFinishListener.class);
	
	@Override
	public void notify(EventListenerExecution  execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the PaymentListApplyFinishListener notify method...");
		}

	//获取流程发起者对象
	AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	CommonReportService cmService = (CommonReportService) AppUtil.getBean("commonReportService");
	
	//获取表单数据
	OpenProcessInstance pi=execution.getProcessInstance();
	Object reporter=pi.getVariable("reporter");//报告人
	Object deptName=pi.getVariable("deptName");//所属部门
	Object state=pi.getVariable("state");//类型
	Object company=pi.getVariable("company");//所在公司
	Object sendDept=pi.getVariable("sendDept");//报告抄送部门
	Object content=pi.getVariable("content");//报告内容
	
	
	CommonReport commonReport =new  CommonReport();
	if(reporter !=null){
		commonReport.setReporter(reporter.toString());
	}
	if(deptName !=null){
		commonReport.setDeptName(deptName.toString());
	}
	if(state!=null){
		commonReport.setState(state.toString());
	}
	if(company!=null){
		commonReport.setCompany(company.toString());
	}
	if(sendDept!=null){
		commonReport.setSendDept(sendDept.toString());
	}
	if(content!=null){
		commonReport.setContent(content.toString());
		
	}
	
	cmService.save(commonReport);
	
	
	
	//所有参与流程的都发消息
	AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
}


}
