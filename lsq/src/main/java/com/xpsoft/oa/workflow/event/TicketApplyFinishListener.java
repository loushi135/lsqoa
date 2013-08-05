package com.xpsoft.oa.workflow.event;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.TicketApply;
import com.xpsoft.oa.model.statistics.TicketIdno;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.statistics.TicketApplyService;
import com.xpsoft.oa.service.statistics.TicketIdnoService;
import com.xpsoft.oa.service.system.DepartmentService;

public class TicketApplyFinishListener implements EventListener {
	private Log logger=LogFactory.getLog(TicketApplyFinishListener.class);
	
	@Override
	public void notify(EventListenerExecution  execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the TicketApplyFinishListener notify method...");
		}

	//获取流程发起者对象
	AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	TicketApplyService ticketApplyService = (TicketApplyService) AppUtil.getBean("ticketApplyService");
	ProjectNewService projectNewService = (ProjectNewService)AppUtil.getBean("projectNewService");
	DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
	TicketIdnoService ticketIdnoService = (TicketIdnoService)AppUtil.getBean("ticketIdnoService");
	
	//获取表单数据
	OpenProcessInstance pi=execution.getProcessInstance();
	String reporter=(String)pi.getVariable("reporter");//报告人
	String deptId=(String)pi.getVariable("deptId");//部门主键
	String bookUsers=(String)pi.getVariable("bookUsers");//出行人员
	String bookUserIds=(String)pi.getVariable("bookUserIds");//出行人员主键
	Integer ticketNum=(Integer)pi.getVariable("ticketNum");//需要申请票数
	String businessType=(String)pi.getVariable("businessType");//出差类型
	String projectId=(String)pi.getVariable("projectId");//项目主键
	String departure=(String)pi.getVariable("departure");//出发地
	String destination=(String)pi.getVariable("destination");//目的地
	String ticketType=(String)pi.getVariable("ticketType");//票务类型
	Date departureTime=(Date)pi.getVariable("departureTime");//出发时间
	String departureDetail=(String)pi.getVariable("departureDetail");//票务类型
	Date backTime=(Date)pi.getVariable("backTime");//返程时间
	String backDetail=(String)pi.getVariable("backDetail");//票务类型
	String backOrNot=(String)pi.getVariable("backOrNot");//是否需要预订返程票
	String applyReason=(String)pi.getVariable("applyReason");//申请原因
	String company=(String)pi.getVariable("company");//公司名
	String strResultData =(String)pi.getVariable("ticketGridData");//出行人员列表
	String ticketDataList =(String)pi.getVariable("ticketDataList");//出行人员列表
	String resultDatas[] = strResultData.split(";");
	//List<TicketIdno> ticketIdnoTemp = new ArrayList<TicketIdno>();
	BigDecimal amount=(BigDecimal)pi.getVariable("ticketApply.amount");//总费用小写
	String amountBig=(String)pi.getVariable("ticketApply.amountBig");//总费用大写
	TicketApply ticketApply = new TicketApply();
	ticketApply.setReporter(reporter);
	ticketApply.setBookUsers(bookUsers);
	ticketApply.setBookUserIds(bookUserIds);
	ticketApply.setCompany(company);
	ticketApply.setTicketNum(ticketNum);
	ticketApply.setBusinessType(businessType);
	ticketApply.setDeparture(departure);
	ticketApply.setDestination(destination);
	ticketApply.setTicketType(ticketType);
	ticketApply.setBackOrNot(backOrNot);
	ticketApply.setApplyReason(applyReason);
	ticketApply.setAmount(amount);
	ticketApply.setAmountBig(amountBig);
	ticketApply.setStatus(TicketApply.STATUS_NORMAL);
	if(StringUtils.isNotBlank(projectId)){
		ProjectNew projectNew = projectNewService.get(Long.valueOf(projectId));
		ticketApply.setProject(projectNew);
	}
	if(StringUtils.isNotBlank(deptId)){
		Department dept = departmentService.get(Long.valueOf(deptId));
		ticketApply.setDept(dept);
	}
	ticketApply.setDepartureTime(departureTime);
	ticketApply.setDepartureDetail(departureDetail);
	ticketApply.setBackTime(backTime);
	ticketApply.setBackDetail(backDetail);	
	
	ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
	ProcessRun processRun=processRunService.getByExeId(pi.getId());
	ticketApply.setProcessRunId(processRun.getRunId());
	
	ticketApplyService.save(ticketApply);
	if(StringUtils.isNotBlank(ticketDataList)){
		List<TicketIdno> ticketIdnoList = JsonUtil.fromJsonTypesWithDate(ticketDataList,
				new TypeToken<List<TicketIdno>>() {
				}.getType());
		for (TicketIdno ticketIdno:ticketIdnoList) {
			ticketIdno.setTicketApply(ticketApply);
			ticketIdnoService.save(ticketIdno);
		}
	}
	//所有参与流程的都发消息
	AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	
//	smService.save(AppUser.SYSTEM_USER, flowStartUser.getId(), "您的票务申请已通过最终审批！", ShortMessage.MSG_TYPE_SYS);
}
}