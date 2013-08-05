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
import com.xpsoft.oa.model.statistics.Prequalificareport;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectSeal;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.PrequalificareportService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.statistics.ProjectSealService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;

public class PreQualificaReportFinishListener implements EventListener {
	private Log logger=LogFactory.getLog(PreQualificaReportFinishListener.class);
	
	@Override
	public void notify(EventListenerExecution  execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the PreQualificaReportFinishListener notify method...");
		}

	//获取流程发起者对象
	AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	PrequalificareportService prequalificareportService = (PrequalificareportService)AppUtil.getBean("prequalificareportService");
	
	//获取表单数据
	OpenProcessInstance pi=execution.getProcessInstance();
	
	
	//String proId=(String)pi.getVariable("flowPreQ.proId");//项目主键
	String proName=(String)pi.getVariable("flowPreQ.proName");//项目名称
	String designUnit=(String)pi.getVariable("flowPreQ.designUnit");//设计单位
	String buildUnit=(String)pi.getVariable("flowPreQ.buildUnit");//建设单位
	String bidType=(String)pi.getVariable("flowPreQ.bidType");//招标类型
	String bidProxy=(String)pi.getVariable("flowPreQ.bidProxy");//招标代理
	
	BigDecimal ProEstimatPrice=(BigDecimal)pi.getVariable("flowPreQ.ProEstimatPrice");//项目估算价（万元）
	
	Date timeNotice=(Date)pi.getVariable("flowPreQ.timeNotice");//公告发布时间
	Date timePreQualifica=(Date)pi.getVariable("flowPreQ.timePreQualifica");//资格预审时间
	Date timePerBidOpen=(Date)pi.getVariable("flowPreQ.timePerBidOpen");//预计开标时间
	String requireConstrLevel=(String)pi.getVariable("flowPreQ.requireConstrLevel");//建造师资质要求
	String sureArrivalPreQ=(String)pi.getVariable("flowPreQ.sureArrivalPreQ");//资格预审是否到场
	String sureArrivalBid=(String)pi.getVariable("flowPreQ.sureArrivalBid");//开标是否到场
	String suggestConstructor=(String)pi.getVariable("flowPreQ.suggestConstructor");//市场部推荐建造师
	String materialFitDeptId=(String)pi.getVariable("flowPreQ.materialFitDeptId");//施工配合区域ID
	String reqEnterpriseQua=(String)pi.getVariable("flowPreQ.reqEnterpriseQua");//企业资质要求
	String reqPerformance=(String)pi.getVariable("flowPreQ.reqPerformance");//业绩要求（企业、项目经理）
	String reqOther=(String)pi.getVariable("flowPreQ.reqOther");//其他要求
	
	String applyUserId=(String)pi.getVariable("flowPreQ.applyUserId");//报备人ID

	Prequalificareport prequalificareport=new Prequalificareport();
	
	ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
	ProcessRun processRun=processRunService.getByExeId(pi.getId());
	AppUserService appUserService=(AppUserService) AppUtil.getBean("appUserService");
	DepartmentService departmentService=(DepartmentService) AppUtil.getBean("departmentService");
	ProjectNewService projectNewService=(ProjectNewService) AppUtil.getBean("projectNewService");
	
	prequalificareport.setProcessRunId(processRun.getRunId());
	prequalificareport.setApplyUser(appUserService.get(Long.valueOf(applyUserId)));
	prequalificareport.setBidProxy(bidProxy);
	prequalificareport.setBidType(bidType);
	prequalificareport.setBuildUnit(buildUnit);
	prequalificareport.setDesignUnit(designUnit);
	prequalificareport.setMaterialFitDept(departmentService.get(Long.valueOf(materialFitDeptId)));
	prequalificareport.setProEstimatPrice(ProEstimatPrice);
	prequalificareport.setProName(proName);
	prequalificareport.setReqEnterpriseQua(reqEnterpriseQua);
	prequalificareport.setReqOther(reqOther);
	prequalificareport.setReqPerformance(reqPerformance);
	prequalificareport.setRequireConstrLevel(requireConstrLevel);
	prequalificareport.setSuggestConstructor(suggestConstructor);
	prequalificareport.setSureArrivalBid(sureArrivalBid);
	prequalificareport.setSureArrivalPreQ(sureArrivalPreQ);
	prequalificareport.setTimeNotice(timeNotice);
	prequalificareport.setTimePerBidOpen(timePerBidOpen);
	prequalificareport.setTimePreQualifica(timePreQualifica);
	
	prequalificareportService.save(prequalificareport);
	
	
	//所有参与流程的都发消息
	AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
}
}