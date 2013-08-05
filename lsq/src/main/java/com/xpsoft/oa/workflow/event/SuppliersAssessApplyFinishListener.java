package com.xpsoft.oa.workflow.event;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import com.xpsoft.oa.service.system.CityService;
import com.xpsoft.oa.service.system.FileAttachService;
import com.xpsoft.oa.service.system.ProvinceService;

public class SuppliersAssessApplyFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(SuppliersAssessApplyFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String suppliersName = (String)pi.getVariable("suppliersAssess.suppliersName");
		String suppliersNature = (String)pi.getVariable("suppliersAssess.suppliersNature");
		String mainProduct = (String)pi.getVariable("suppliersAssess.mainProduct");
		String cooperateType = (String)pi.getVariable("suppliersAssess.cooperateType");
		String supplierAddress = (String)pi.getVariable("suppliersAssess.supplierAddress");
		String legalRepresentative = (String)pi.getVariable("suppliersAssess.legalRepresentative");
		String registeredCapital = (String)pi.getVariable("suppliersAssess.registeredCapital");
		String establishDate = (String)pi.getVariable("suppliersAssess.establishDate");
		String supplierContacter = (String)pi.getVariable("suppliersAssess.supplierContacter");
		String supplierPhone = (String)pi.getVariable("suppliersAssess.supplierPhone");
		String companyNature = (String)pi.getVariable("suppliersAssess.companyNature");
		String suppliersbank = (String)pi.getVariable("suppliersAssess.suppliersbank");
		String bankAccount = (String)pi.getVariable("suppliersAssess.bankAccount");
		Integer staffNum = (Integer)pi.getVariable("suppliersAssess.staffNum");
		Integer managerNum = (Integer)pi.getVariable("suppliersAssess.managerNum");
		Integer salesNum = (Integer)pi.getVariable("suppliersAssess.salesNum");
		Integer techNum = (Integer)pi.getVariable("suppliersAssess.techNum");
		String institutionNature = (String)pi.getVariable("suppliersAssess.institutionNature");
		String businessArea = (String)pi.getVariable("suppliersAssess.businessArea");
		String brand = (String)pi.getVariable("suppliersAssess.brand");
		String systemType = (String)pi.getVariable("suppliersAssess.systemType");
		String provinceId = (String)pi.getVariable("suppliersAssess.province.provinceId");
		String cityId = (String)pi.getVariable("suppliersAssess.city.cityId");
		String attachIds = (String)pi.getVariable("suppliersAssess.attachIds");
		String recommendReason = (String)pi.getVariable("suppliersAssess.recommendReason");
		String proId = (String)pi.getVariable("suppliersAssess.project.id");
		
		SuppliersAssess suppliersAssess = new SuppliersAssess();
		SuppliersAssessService suppliersAssessService = (SuppliersAssessService)AppUtil.getBean("suppliersAssessService");
		
		suppliersAssess.setSuppliersName(suppliersName.trim());
		suppliersAssess.setSuppliersNature(suppliersNature);
		suppliersAssess.setMainProduct(mainProduct);
		suppliersAssess.setSupplierAddress(supplierAddress);
		suppliersAssess.setCooperateType(cooperateType);
		suppliersAssess.setLegalRepresentative(legalRepresentative);
		suppliersAssess.setRegisteredCapital(registeredCapital);
		suppliersAssess.setEstablishDate(establishDate);
		suppliersAssess.setSupplierContacter(supplierContacter);
		suppliersAssess.setSupplierPhone(supplierPhone);
		suppliersAssess.setCompanyNature(companyNature);
		suppliersAssess.setSuppliersbank(suppliersbank);
		suppliersAssess.setBankAccount(bankAccount);
		suppliersAssess.setStaffNum(staffNum);
		suppliersAssess.setManagerNum(managerNum);
		suppliersAssess.setSalesNum(salesNum);
		suppliersAssess.setTechNum(techNum);
		suppliersAssess.setInstitutionNature(institutionNature);
		suppliersAssess.setBusinessArea(businessArea);
		suppliersAssess.setBrand(brand);
		suppliersAssess.setSystemType(systemType);
		suppliersAssess.setRecommendReason(recommendReason);
		suppliersAssess.setDelFlag("0");//可用
		suppliersAssess.setCreatetime(new Date());
		suppliersAssess.setType(SuppliersAssess.SUPPLIER_TYPE);
		
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
