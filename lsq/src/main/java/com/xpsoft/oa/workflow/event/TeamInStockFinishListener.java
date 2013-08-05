package com.xpsoft.oa.workflow.event;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.PinyingUtil;
import com.xpsoft.oa.model.customer.SuppliersAssess;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.City;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.model.system.Province;
import com.xpsoft.oa.service.customer.SuppliersAssessService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.CityService;
import com.xpsoft.oa.service.system.FileAttachService;
import com.xpsoft.oa.service.system.ProvinceService;

public class TeamInStockFinishListener implements EventListener {
	
	private Log logger=LogFactory.getLog(TeamInStockFinishListener.class);

	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.info("enter the TeamInStockFinishListener notify method...");
		}
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String suppliersName=(String)pi.getVariable("flowItem.suppliersName");//单位名称
		String inviteUserName=(String)pi.getVariable("flowItem.inviteUserName");//推荐人
		String legalRepresentative=(String)pi.getVariable("flowItem.legalRepresentative");//法定代表人
		String supplierContacter=(String)pi.getVariable("flowItem.supplierContacter");//联系人
		String supplierAddress=(String)pi.getVariable("flowItem.supplierAddress");//通讯地址
		String supplierPhone=(String)pi.getVariable("flowItem.supplierPhone");//联系人电话
		String registeredCapital=(String)pi.getVariable("flowItem.registeredCapital");//注册资金
		String zip=(String)pi.getVariable("flowItem.zip");//邮      编
		String officeTel=(String)pi.getVariable("flowItem.officeTel");//办公电话
		String fax=(String)pi.getVariable("flowItem.fax");//传真
		String email=(String)pi.getVariable("flowItem.email");//email
		Integer fixedStaffNum=(Integer)pi.getVariable("flowItem.fixedStaffNum");//固定员工人数
		Integer techDebugNum=(Integer)pi.getVariable("flowItem.techDebugNum");//其中专业技术安装调试人员数
		Integer leaderNum=(Integer)pi.getVariable("flowItem.leaderNum");//其中班组长人数
		Integer validElecNum=(Integer)pi.getVariable("flowItem.validElecNum");//持有效电工证工人数
		Integer validWelderNum=(Integer)pi.getVariable("flowItem.validWelderNum");//持有效焊工证工人数
		Integer validQualifyNum=(Integer)pi.getVariable("flowItem.validQualifyNum");//持资格证书人数
		Integer peakNum=(Integer)pi.getVariable("flowItem.peakNum");//高峰可上人员
		String mainArea=(String)pi.getVariable("flowItem.mainArea");//工人主要来自地区
		String previousYearOutput=(String)pi.getVariable("flowItem.previousYearOutput");//前年工程产值
		String lastYearOutput=(String)pi.getVariable("flowItem.lastYearOutput");//去年工程产值
		String mainInfo=(String)pi.getVariable("flowItem.mainInfo");//主要骨干姓名及联系电话擅长系统信息
		String recommendReason=(String)pi.getVariable("flowItem.recommendReason");//推荐原因
		String applyUserId=(String)pi.getVariable("flowItem.applyUserId");//经办人ID
		String attachIds=(String)pi.getVariable("flowItem.attachIds");//附件Ids
		String suppliersbank=(String)pi.getVariable("flowItem.suppliersbank");//开户银行
		String bankAccount=(String)pi.getVariable("flowItem.bankAccount");//银行帐号
		String cooperateType=(String)pi.getVariable("flowItem.cooperateType");//合作类型
		String provinceId = (String)pi.getVariable("flowItem.province.provinceId");
		String cityId = (String)pi.getVariable("flowItem.city.cityId");
		String proId = (String)pi.getVariable("flowItem.project.id");
		
		SuppliersAssess suppliersAssess=new SuppliersAssess();
		SuppliersAssessService suppliersAssessService=(SuppliersAssessService)AppUtil.getBean("suppliersAssessService");
		
		suppliersAssess.setSuppliersName(suppliersName);
		suppliersAssess.setInviteUserName(inviteUserName);
		suppliersAssess.setLegalRepresentative(legalRepresentative);
		suppliersAssess.setSupplierContacter(supplierContacter);
		suppliersAssess.setEmail(email);
		suppliersAssess.setFax(fax);
		suppliersAssess.setSupplierAddress(supplierAddress);
		suppliersAssess.setSupplierPhone(supplierPhone);
		suppliersAssess.setCooperateType(cooperateType);
		suppliersAssess.setRecommendReason(recommendReason);
		suppliersAssess.setInviteUserName(inviteUserName);
		suppliersAssess.setLastYearOutput(lastYearOutput);
		suppliersAssess.setLeaderNum(leaderNum);
		suppliersAssess.setMainArea(mainArea);
		suppliersAssess.setMainInfo(mainInfo);
		suppliersAssess.setOfficeTel(officeTel);
		suppliersAssess.setPeakNum(peakNum);
		suppliersAssess.setPreviousYearOutput(previousYearOutput);
		suppliersAssess.setRegisteredCapital(registeredCapital);
		suppliersAssess.setFixedStaffNum(fixedStaffNum);
		suppliersAssess.setTechDebugNum(techDebugNum);
		suppliersAssess.setValidElecNum(validElecNum);
		suppliersAssess.setValidQualifyNum(validQualifyNum);
		suppliersAssess.setValidWelderNum(validWelderNum);
		suppliersAssess.setZip(zip);
		suppliersAssess.setBankAccount(bankAccount);
		suppliersAssess.setSuppliersbank(suppliersbank);
		suppliersAssess.setType(SuppliersAssess.TEAM_TYPE);
		suppliersAssess.setDelFlag("0");//可用
		suppliersAssess.setCreatetime(new Date());
		
		if(StringUtils.isNotBlank(applyUserId)){
			AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
			suppliersAssess.setApplyUser(appUserService.get(Long.valueOf(applyUserId)));
		}
		if(StringUtils.isNotBlank(proId)){
			ProjectNewService projectNewService = (ProjectNewService)AppUtil.getBean("projectNewService");
			suppliersAssess.setProject(projectNewService.get(Long.valueOf(proId)));
		}
		
		CityService cityService = (CityService)AppUtil.getBean("cityService");
		City city = null;
		Province province = null;
		if(StringUtils.isNotBlank(cityId)){
			city = cityService.get(Long.valueOf(cityId));
			suppliersAssess.setCity(city);
		}
		ProvinceService provinceService = (ProvinceService)AppUtil.getBean("provinceService");
		if(StringUtils.isNotBlank(provinceId)){
			province = provinceService.get(Long.valueOf(provinceId));
			suppliersAssess.setProvince(province);
		}
		
		// 生成供应商编号
		if(province!=null){
			String provinceName = province.getProvinceName();
			String cityName = city.getCityName();
			if(provinceName.contains("省")){
				provinceName = provinceName.substring(0,provinceName.lastIndexOf("省"));
			}else if(provinceName.contains("市")){
				provinceName = provinceName.substring(0,provinceName.lastIndexOf("市"));
			}
			if(provinceName.contains("江苏")){
				if("苏州市".equals(cityName)){
					provinceName = "苏州 ";
				}else{
					provinceName = "江苏其他";
				}
			}
			String search = PinyingUtil.getProvinceHeadString(provinceName)+".";
			String suppliersNo = suppliersAssessService.getMaxNo(search);
			suppliersAssess.setSuppliersNo(suppliersNo);
		}
		
		FileAttachService fileAttachService = (FileAttachService)AppUtil.getBean("fileAttachService");
		if(StringUtils.isNotBlank(attachIds)){
			Set<FileAttach> fileSet = new HashSet<FileAttach>();
			String fileIds[] = attachIds.split(",");
			suppliersAssess.getFileAttachs().clear();
			for(String fileId:fileIds){
				fileSet.add(fileAttachService.get(Long.valueOf(fileId)));
			}
			suppliersAssess.getFileAttachs().addAll(fileSet);
		}
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
	
		suppliersAssess.setProcessRunId(processRun.getRunId());
		
		suppliersAssessService.save(suppliersAssess);
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}