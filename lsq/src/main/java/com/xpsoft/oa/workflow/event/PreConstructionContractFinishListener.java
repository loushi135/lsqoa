package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.Constructioncontract;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.ConstructioncontractService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;


public class PreConstructionContractFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(PreConstructionContractFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the UnpunchListener notify method...");
		}
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String contractId = (String)pi.getVariable("constructionContract.contractId");
		String contractor = (String)pi.getVariable("constructionContract.contractor");
		String contractVersion = (String)pi.getVariable("constructionContract.contractVersion");
		String projectName = (String)pi.getVariable("constructionContract.projectName");
		String projectAbbreviation = (String)pi.getVariable("constructionContract.projectAbbreviation");
		String linkmanAndTel = (String)pi.getVariable("constructionContract.linkmanAndTel");
		String projectRegional = (String)pi.getVariable("constructionContract.projectRegional");
		String deptId = (String)pi.getVariable("constructionContract.deptId");
		String projectCharger = (String)pi.getVariable("constructionContract.projectCharger");
		String projectChargerUserId = (String)pi.getVariable("constructionContract.projectChargerUserId");
		String businessCharger = (String)pi.getVariable("constructionContract.businessCharger");
		String numOrArea = (String)pi.getVariable("constructionContract.numOrArea");
		BigDecimal sumPrice = (BigDecimal)pi.getVariable("constructionContract.sumPrice");
		String contractRemark = (String)pi.getVariable("constructionContract.contractRemark");
		String payWay = (String)pi.getVariable("constructionContract.payWay");
		String projectTime = (String)pi.getVariable("constructionContract.projectTime");
		String quality = (String)pi.getVariable("constructionContract.quality");
		String isFullContract = (String)pi.getVariable("constructionContract.isFullContract");
		String isDesignCost = (String)pi.getVariable("constructionContract.isDesignCost");
		BigDecimal designCost = (BigDecimal)pi.getVariable("constructionContract.designCost");
		String isModelCommunity = (String)pi.getVariable("constructionContract.isModelCommunity");
		String guarantee = (String)pi.getVariable("constructionContract.guarantee");
		String constructionBackUp = (String)pi.getVariable("constructionContract.constructionBackUp");
		String constructionBackUpPerson = (String)pi.getVariable("constructionContract.backUpPerson");
		String constructionBackUpPersonId = (String)pi.getVariable("constructionContract.backUpPersonId");
		Date constructionBackUpFinishTime = (Date)pi.getVariable("constructionContract.backUpFinishTime");
		String constructionLicense = (String)pi.getVariable("constructionContract.constructionLicense");
		String constructionLicensePersonId = (String)pi.getVariable("constructionContract.licensePersonId");
		String constructionLicensePerson = (String)pi.getVariable("constructionContract.licensePerson");
		Date constructionLicenseFinishTime = (Date)pi.getVariable("constructionContract.licenseFinishTime");
		String isoverBudget = (String)pi.getVariable("constructionContract.isoverBudget");
		String quote = (String)pi.getVariable("constructionContract.quote");
		BigDecimal quoteloss = (BigDecimal)pi.getVariable("constructionContract.quoteloss");
		String remark = (String)pi.getVariable("constructionContract.remark");
		String meno = (String)pi.getVariable("constructionContract.meno");
		String rewardOrPunish = (String)pi.getVariable("constructionContract.rewardOrPunish");
		String constructionAttachIDs = (String)pi.getVariable("constructionContract.attachIDs");
		String constructionAttachFile = (String)pi.getVariable("constructionContract.attachFile");
		ConstructioncontractService constructioncontractService = (ConstructioncontractService)AppUtil.getBean("constructioncontractService");
		Constructioncontract constructioncontract  = null;
		if(StringUtils.isNotBlank(contractId)){
			constructioncontract = constructioncontractService.get(Long.valueOf(contractId));
		}else{
			constructioncontract = new Constructioncontract();
		}
		if(StringUtils.isNotBlank(contractVersion)){
			if("有施工合同".equals(contractVersion)){
				constructioncontract.setContractVersion(0);
			}else if("有中标通知书".equals(contractVersion)){
				constructioncontract.setContractVersion(1);
			}else if("有开工指令单".equals(contractVersion)){
				constructioncontract.setContractVersion(2);
			}
		}
		constructioncontract.setContractor(contractor);
		constructioncontract.setProjectName(projectName);
		constructioncontract.setProjectAbbreviation(projectAbbreviation);
		constructioncontract.setProjectRegional(projectRegional);
		constructioncontract.setLinkmanAndTel(linkmanAndTel);
		constructioncontract.setProjectCharger(projectCharger);
		constructioncontract.setBusinessCharger(businessCharger);
		constructioncontract.setNumOrArea(numOrArea);
		constructioncontract.setSumPrice(sumPrice);
		constructioncontract.setContractRemark(contractRemark);
		constructioncontract.setPayWay(payWay);
		constructioncontract.setProjectTime(projectTime);
		constructioncontract.setQuality(quality);
		constructioncontract.setIsFullContract(isFullContract);
		constructioncontract.setIsDesignCost(isDesignCost);
		constructioncontract.setDesignCost(designCost);
		constructioncontract.setIsModelCommunity(isModelCommunity);
		constructioncontract.setGuarantee(guarantee);
		constructioncontract.setConstructionBackUp(constructionBackUp);
		constructioncontract.setConstructionBackUpPerson(constructionBackUpPerson);
		constructioncontract.setConstructionBackUpFinishTime(constructionBackUpFinishTime);
		constructioncontract.setConstructionLicense(constructionLicense);
		constructioncontract.setConstructionLicensePerson(constructionLicensePerson);
		constructioncontract.setConstructionLicenseFinishTime(constructionLicenseFinishTime);
		constructioncontract.setIsoverBudget(isoverBudget);
		constructioncontract.setQuote(quote);
		constructioncontract.setQuoteloss(quoteloss);
		constructioncontract.setRemark(remark);
		constructioncontract.setMeno(meno);
		constructioncontract.setStatus(0);
		constructioncontract.setRewardOrPunish(rewardOrPunish);
		constructioncontract.setAttachFiles(constructionAttachFile);
		constructioncontract.setAttachIds(constructionAttachIDs);
		if(StringUtils.isNotBlank(deptId)){
			DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
			constructioncontract.setDeptRegional(departmentService.get(Long.valueOf(deptId)));
		}
		AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
		if(StringUtils.isNotBlank(projectChargerUserId)){
			constructioncontract.setProjectChargerUser(appUserService.get(Long.valueOf(projectChargerUserId)));
		}
		if(StringUtils.isNotBlank(constructionLicensePersonId)){
			constructioncontract.setConstructionLicenseUser(appUserService.get(Long.valueOf(constructionLicensePersonId)));
		}
		if(StringUtils.isNotBlank(constructionBackUpPersonId)){
			constructioncontract.setConstructionBackUpUser(appUserService.get(Long.valueOf(constructionBackUpPersonId)));
		}
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		constructioncontract.setProcessRunId(processRun.getRunId());
		
		//FIXME 修改项目数据
		constructioncontractService.saveConstructioncontract(constructioncontract);
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
