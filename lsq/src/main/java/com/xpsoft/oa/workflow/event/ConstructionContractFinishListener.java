package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.Constructioncontract;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.ConstructioncontractService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;


public class ConstructionContractFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(ConstructionContractFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the UnpunchListener notify method...");
		}
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
//		String contractNo = (String)pi.getVariable("contractNo");
		String contractor = (String)pi.getVariable("contractor");
		String contractVersion = (String)pi.getVariable("contractVersion");
		String projectName = (String)pi.getVariable("projectName");
		String projectAbbreviation = (String)pi.getVariable("projectAbbreviation");
		String linkmanAndTel = (String)pi.getVariable("linkmanAndTel");
		String projectRegional = (String)pi.getVariable("projectRegional");
		String projectCharger = (String)pi.getVariable("projectCharger");
		String businessCharger = (String)pi.getVariable("businessCharger");
		String numOrArea = (String)pi.getVariable("numOrArea");
		BigDecimal sumPrice = (BigDecimal)pi.getVariable("sumPrice");
		String contractRemark = (String)pi.getVariable("contractRemark");
		String payWay = (String)pi.getVariable("payWay");
		String projectTime = (String)pi.getVariable("projectTime");
		String quality = (String)pi.getVariable("quality");
		String isFullContract = (String)pi.getVariable("isFullContract");
		String isDesignCost = (String)pi.getVariable("isDesignCost");
		BigDecimal designCost = (BigDecimal)pi.getVariable("designCost");
		String isModelCommunity = (String)pi.getVariable("isModelCommunity");
		String guarantee = (String)pi.getVariable("guarantee");
		String constructionBackUp = (String)pi.getVariable("constructionBackUp");
		String constructionBackUpPerson = (String)pi.getVariable("constructionBackUpPerson");
		Date constructionBackUpFinishTime = (Date)pi.getVariable("constructionBackUpFinishTime");
		String constructionLicense = (String)pi.getVariable("constructionLicense");
		String constructionLicensePerson = (String)pi.getVariable("constructionLicensePerson");
		Date constructionLicenseFinishTime = (Date)pi.getVariable("constructionLicenseFinishTime");
		String isoverBudget = (String)pi.getVariable("isoverBudget");
		String quote = (String)pi.getVariable("quote");
		BigDecimal quoteloss = (BigDecimal)pi.getVariable("quoteloss");
		String remark = (String)pi.getVariable("remark");
		String meno = (String)pi.getVariable("meno");
		String deptId = (String)pi.getVariable("deptId");
		String projectChargerUserId = (String)pi.getVariable("projectChargerUserId");
		String rewardOrPunish = (String)pi.getVariable("rewardOrPunish");
		String applyDate = (String)pi.getVariable("applyDate");
		String constructionLicensePersonId = (String)pi.getVariable("constructionLicensePersonId");
		String constructionBackUpPersonId = (String)pi.getVariable("constructionBackUpPersonId");
		String constructionAttachIDs = (String)pi.getVariable("constructionAttachIDs");
		String constructionAttachFile = (String)pi.getVariable("constructionAttachFile");
		ConstructioncontractService constructioncontractService = (ConstructioncontractService)AppUtil.getBean("constructioncontractService");
		Constructioncontract constructioncontract  = new Constructioncontract();
		//生成合同编号
		String contractType = "02";
		String year = new SimpleDateFormat("yyyy").format(new Date());
		if(StringUtils.isNotBlank(applyDate)){
			String str = new SimpleDateFormat("yyyy").format(new Date())+"-10-30";
			if(applyDate.compareTo(str)>0){
				year = Integer.valueOf(year)+1+"";
			}
		}
		String search = "LJT"+contractType+year;
		HttpServletRequest request = ServletActionContext.getRequest();
		QueryFilter filter = new QueryFilter(request);
		filter.addFilter("Q_contractNo_S_LK", search);
		filter.addSorted("contractNo", "desc");
		filter.getPagingBean().setPageSize(1);
		List<Constructioncontract> conList = constructioncontractService.getAll(filter);
		String newNo="";
		if(conList!=null&&conList.size()>0){
			Constructioncontract lastCon = conList.get(0);
			String contractNo = lastCon.getContractNo();
			Integer num = Integer.parseInt(contractNo.substring(contractNo
					.lastIndexOf(search)+search.length()));
			newNo = String.valueOf(num+1);
			if(newNo.length()<2){
				newNo = "00" + String.valueOf((num + 1));
			}else if(newNo.length()<3){
				newNo = "0" + String.valueOf((num + 1));
			}
		}else{
			newNo = "001";
		}
		String contractNo = search + newNo;
		constructioncontract.setContractNo(contractNo);
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
		constructioncontract.setProjectName(projectName.trim());
		constructioncontract.setProjectAbbreviation(projectAbbreviation);
		constructioncontract.setLinkmanAndTel(linkmanAndTel);
		constructioncontract.setProjectRegional(projectRegional);
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
		
		constructioncontractService.save(constructioncontract);
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
