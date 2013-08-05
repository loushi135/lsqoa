package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.SalesProject;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.SalesProjectService;

public class SPRFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(SignApplyFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)pi.getVariable("flowStartUser");
		String proName = (String)pi.getVariable("proName");
		Date applyDate = (Date)pi.getVariable("applyDate");
		BigDecimal buildPrice = (BigDecimal)pi.getVariable("buildPrice");
		BigDecimal buildArea = (BigDecimal)pi.getVariable("buildArea");
		String proInfoSource = (String)pi.getVariable("proInfoSource");
		String constructFollowDept = (String)pi.getVariable("constructFollowDept");
		String proAddr = (String)pi.getVariable("proAddr");
		String proUseful = (String)pi.getVariable("proUseful");
		String proMainUnit = (String)pi.getVariable("proMainUnit");
		String owerRelation = (String)pi.getVariable("owerRelation");
		String bidDuringTime = (String)pi.getVariable("bidDuringTime");
		String progressInfo = (String)pi.getVariable("progressInfo");
		String chargeDesign = (String)pi.getVariable("chargeDesign");
		String doReview = (String)pi.getVariable("doReview");
		String bidQuotaLocalLaws = (String)pi.getVariable("bidQuotaLocalLaws");
		String competitionCompany = (String)pi.getVariable("competitionCompany");
		String recommendDesignDept = (String)pi.getVariable("recommendDesignDept");
		String proClassify = (String)pi.getVariable("proClassify");
		
		SalesProjectService signApplyService = (SalesProjectService)AppUtil.getBean("salesProjectService");
		
		SalesProject salesProject = new SalesProject();
		
		salesProject.setApplyDate(applyDate);
		salesProject.setBidDuringTime(bidDuringTime);
		salesProject.setBidQuotaLocalLaws(bidQuotaLocalLaws);
		salesProject.setBuildArea(buildArea);
		salesProject.setBuildPrice(buildPrice);
		salesProject.setBusinessUser(flowStartUser);
		salesProject.setChargeDesign(chargeDesign);
		salesProject.setCompetitionCompany(competitionCompany);
		salesProject.setConstructFollowDept(constructFollowDept);
		salesProject.setDoReview(doReview);
		salesProject.setOwerRelation(owerRelation);
		salesProject.setProAddr(proAddr);
		salesProject.setProClassify(proClassify);
		salesProject.setProInfoSource(proInfoSource);
		salesProject.setProMainUnit(proMainUnit);
		salesProject.setProName(proName);
		salesProject.setProNo(signApplyService.getNewProNo(flowStartUser.getDepartment().getDepName()));
		salesProject.setProUseful(proUseful);
		salesProject.setRecommendDesignDept(recommendDesignDept);
		salesProject.setProgressInfo(progressInfo);
		salesProject.setTeamDep(flowStartUser.getDepartment());
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		salesProject.setProcessRunId(processRun.getRunId());
		
		signApplyService.save(salesProject);
		
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
