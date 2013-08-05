package com.xpsoft.oa.workflow.event;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.statistics.CarSubsidy;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.statistics.CarSubsidyService;
import com.xpsoft.oa.service.system.DepartmentService;
import com.xpsoft.oa.service.system.FileAttachService;

public class CarSubsidyFinishListener implements EventListener {
	private Log logger=LogFactory.getLog(CarSubsidyFinishListener.class);
	
	@Override
	public void notify(EventListenerExecution  execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the TicketApplyFinishListener notify method...");
		}

	//获取流程发起者对象
	AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	CarSubsidyService carSubsidyService = (CarSubsidyService) AppUtil.getBean("carSubsidyService");
	DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
	
	//获取表单数据
	OpenProcessInstance pi=execution.getProcessInstance();
	String reporter=(String)pi.getVariable("carReporter");//报告人
	String deptId=(String)pi.getVariable("carDeptId");//所属部门
	String companyName=(String)pi.getVariable("companyName");//所在公司
	String carNo=(String)pi.getVariable("carNo");//车牌号
	String brand=(String)pi.getVariable("brand");//品牌型号
	String displacement=(String)pi.getVariable("displacement");//排量
	String boughtYear=(String)pi.getVariable("boughtYear");//购置年份
	String subsidyReason=(String)pi.getVariable("subsidyReason");//申请原因
	String attachIds=(String)pi.getVariable("carSubsidy.attachIds");
	
	CarSubsidy carSubsidy = new CarSubsidy();
	carSubsidy.setReporter(reporter);
	carSubsidy.setCompanyName(companyName);
	carSubsidy.setCarNo(carNo);
	carSubsidy.setBrand(brand);
	carSubsidy.setDisplacement(displacement);
	carSubsidy.setSubsidyReason(subsidyReason);
	if(StringUtils.isNotBlank(deptId)){
		Department dept = departmentService.get(Long.valueOf(deptId));
		carSubsidy.setDept(dept);
	}
	carSubsidy.setBoughtYear(boughtYear);
	
	FileAttachService fileAttachService = (FileAttachService)AppUtil.getBean("fileAttachService");
	if(StringUtils.isNotBlank(attachIds)){
		Set<FileAttach> fileSet = new HashSet<FileAttach>();
		String fileIds[] = attachIds.split(",");
		carSubsidy.getFileAttachs().clear();
		for(String fileId:fileIds){
			fileSet.add(fileAttachService.get(Long.valueOf(fileId)));
		}
		carSubsidy.getFileAttachs().addAll(fileSet);
	}
	
	ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
	ProcessRun processRun=processRunService.getByExeId(pi.getId());
	carSubsidy.setProcessRunId(processRun.getRunId());
	
	carSubsidyService.save(carSubsidy);


	
	//所有参与流程的都发消息
	AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
}
}