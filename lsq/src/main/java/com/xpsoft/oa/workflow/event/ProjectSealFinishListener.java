package com.xpsoft.oa.workflow.event;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;
import org.jbpm.pvm.internal.type.variable.LongVariable;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.PaymentList;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectSeal;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.statistics.ProjectSealService;
import com.xpsoft.oa.service.system.AppUserService;

public class ProjectSealFinishListener implements EventListener {
	private Log logger=LogFactory.getLog(ProjectSealFinishListener.class);
	
	@Override
	public void notify(EventListenerExecution  execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the ProjectSealFinishListener notify method...");
		}

	//获取流程发起者对象
	AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	ProjectSealService sealService = (ProjectSealService)AppUtil.getBean("projectSealService");
	
	//获取表单数据
	OpenProcessInstance pi=execution.getProcessInstance();
	String proId=(String)pi.getVariable("flowSeal.proId");//项目主键
	String proName=(String)pi.getVariable("flowSeal.proName");//项目名称
	String content=(String)pi.getVariable("flowSeal.content");//项目章刻印内容
	String keeperId=(String)pi.getVariable("flowSeal.keeperId");//保管责任人ID
	//String keeperName=(String)pi.getVariable("flowSeal.keeperName");//保管责任人
	String applyUserId=(String)pi.getVariable("flowSeal.applyUserId");//经办人ID
	//String applyUserName=(String)pi.getVariable("flowSeal.applyUserName");//经办人名字
	
	Date applyDate=(Date)pi.getVariable("flowSeal.applyDate");//申领时间
	Date returnDate=(Date)pi.getVariable("flowSeal.returnDate");//归还时间
	
	ProjectSeal projectSeal=new ProjectSeal();
	
	ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
	ProcessRun processRun=processRunService.getByExeId(pi.getId());
	AppUserService appUserService=(AppUserService) AppUtil.getBean("appUserService");
	
	projectSeal.setApplyDate(applyDate);
	projectSeal.setApplyUser(appUserService.get(Long.valueOf(applyUserId)));
	projectSeal.setContent(content);
	projectSeal.setKeeper(appUserService.get(Long.valueOf(keeperId)));
	projectSeal.setProId(Long.valueOf(proId));
	projectSeal.setProName(proName);
	projectSeal.setReturnDate(returnDate);
	projectSeal.setProcessRunId(processRun.getRunId());
	
	sealService.save(projectSeal);
	
	
	//所有参与流程的都发消息
	AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
}
}