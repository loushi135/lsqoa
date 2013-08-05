package com.xpsoft.oa.workflow.event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.PinyingUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.ConstructioncontractService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;

public class ProjectFinishListener implements EventListener {
	public static Map<String, String> deptMap = new HashMap<String, String>();
	static {
		deptMap.put("直属一部", "A");
		deptMap.put("直属二部", "A");
		deptMap.put("工程一区", "B");
		deptMap.put("工程二区", "C");
		deptMap.put("工程三区", "D");
		deptMap.put("工程五区", "E");
		deptMap.put("工程六区", "F");
		deptMap.put("工程七区", "H");
	}
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		// TODO Auto-generated method stub
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String proName = (String)pi.getVariable("proName");
//		String proNo = (String)pi.getVariable("proNo");
		String proAddr = (String)pi.getVariable("proAddr");
		String area = (String)pi.getVariable("area");
		String proCharger = (String)pi.getVariable("proCharger");
		String proChargerId = (String)pi.getVariable("proChargerId");
		String proChargerTel = (String)pi.getVariable("proChargerTel");
		String proFollower = (String)pi.getVariable("proFollower");
		String proFollowerTel = (String)pi.getVariable("proFollowerTel");
		String designUnit = (String)pi.getVariable("designUnit");
		String designCharger = (String)pi.getVariable("designCharger");
		String designChargerTel = (String)pi.getVariable("designChargerTel");
		String buildUnit = (String)pi.getVariable("buildUnit");
		String buildCharger = (String)pi.getVariable("buildCharger");
		String buildChargerTel = (String)pi.getVariable("buildChargerTel");
		String watchUnit = (String)pi.getVariable("watchUnit");
		String watchCharger = (String)pi.getVariable("watchCharger");
		String watchChargerTel = (String)pi.getVariable("watchChargerTel");
		String totalUnit = (String)pi.getVariable("totalUnit");
		String totalCharger = (String)pi.getVariable("totalCharger");
		String totalChargerTel = (String)pi.getVariable("totalChargerTel");
		String contractId = (String)pi.getVariable("contractId");
		Date startDate = (Date)pi.getVariable("startDate");
		Date endDate = (Date)pi.getVariable("endDate");
		String businessMain = (String)pi.getVariable("businessMain");
		Date enterDate = (Date)pi.getVariable("enterDate");
		String manager = (String)pi.getVariable("manager");
		String deptId = (String)pi.getVariable("deptId");
		String applyDate = (String)pi.getVariable("applyDate");
		//生成项目编号
		String proNo = "";
		DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
		ProjectNewService projectNewService = (ProjectNewService)AppUtil.getBean("projectNewService");
		AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
		Department dept = departmentService.get(Long.valueOf(deptId));
		String signFirst = deptMap.get(dept.getDepName());
		HttpServletRequest request = ServletActionContext.getRequest();
		QueryFilter filter = new QueryFilter(request);
		filter.addFilter("Q_department.depId_L_EQ", deptId);
		List<AppUser> userList = appUserService.getAll(filter);
		String managerName = "";
		for (AppUser user : userList) {
			if (user.getPosition().equals("经理")) {
				managerName = user.getFullname();
				break;
			}
		}
		String signName = PinyingUtil.getHeadString(managerName);
		String signYear = new SimpleDateFormat("yy").format(new Date());
		if(StringUtils.isNotBlank(applyDate)){
			String str = new SimpleDateFormat("yyyy").format(new Date())+"-10-30";
			if(applyDate.compareTo(str)>0){
				signYear = Integer.valueOf(signYear)+1+"";
			}
		}
		String search = signFirst + "." + signName + "." + signYear;
		QueryFilter filter1 = new QueryFilter(request);
		filter1.addFilter("Q_proNo_S_LK", search);
		filter1.addSorted("proNo", "desc");
		filter1.getPagingBean().setPageSize(1);
		List<ProjectNew> proList = projectNewService.getAll(filter1);
		if (proList != null && proList.size() > 0) {
			ProjectNew lastPro = proList.get(0);
			String lastNo = lastPro.getProNo();
			Integer pn = Integer.parseInt(lastNo.substring(lastNo
					.lastIndexOf(search)+search.length()));
			proNo = String.valueOf(pn + 1);
			if (proNo.length() < 2) {
				proNo = "0" + String.valueOf((pn + 1));
			}
		} else {
			proNo = "01";
		}
		String pojectNo =search + proNo;
		ProjectNew projectNew = new ProjectNew();
		projectNew.setProName(proName);
		projectNew.setProNo(pojectNo);
		projectNew.setProAddr(proAddr);
		projectNew.setArea(area);
		projectNew.setProCharger(proCharger);
		projectNew.setProChargerTel(proChargerTel);
		projectNew.setProFollower(proFollower);
		projectNew.setProFollowerTel(proFollowerTel);
		projectNew.setDesignUnit(designUnit);
		projectNew.setDesignCharger(designCharger);
		projectNew.setDesignChargerTel(designChargerTel);
		projectNew.setBuildUnit(buildUnit);
		projectNew.setBuildCharger(buildCharger);
		projectNew.setBuildChargerTel(buildChargerTel);
		if(StringUtils.isNotBlank(watchUnit)){
			projectNew.setWatchUnit(watchUnit);
		}
		if(StringUtils.isNotBlank(watchCharger)){
			projectNew.setWatchCharger(watchCharger);
		}
		if(StringUtils.isNotBlank(watchChargerTel)){
			projectNew.setWatchChargerTel(watchChargerTel);
		}
		if(StringUtils.isNotBlank(totalUnit)){
			projectNew.setTotalUnit(totalUnit);
		}
		if(StringUtils.isNotBlank(totalCharger)){
			projectNew.setTotalCharger(totalCharger);
		}
		if(StringUtils.isNotBlank(totalChargerTel)){
			projectNew.setTotalChargerTel(totalChargerTel);
		}
		if(StringUtils.isNotBlank(proChargerId)){
			projectNew.setProChargerUser(appUserService.get(Long.valueOf(proChargerId)));
		}
		if(StringUtils.isNotBlank(contractId)){
			ConstructioncontractService constructioncontractService = (ConstructioncontractService)AppUtil.getBean("constructioncontractService");
			projectNew.setContract(constructioncontractService.get(Long.valueOf(contractId)));
		}
		projectNew.setStartDate(startDate);
		projectNew.setEndDate(endDate);
		projectNew.setEnterDate(enterDate);
		projectNew.setBusinessMain(businessMain);
		projectNew.setManager(manager);
		projectNew.setStatus(0);
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		projectNew.setProcessRunId(processRun.getRunId());
		
		projectNewService.save(projectNew);
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
//		smService.save(AppUser.SYSTEM_USER, flowStartUser.getId(), "您的申请已通过最终审批！", ShortMessage.MSG_TYPE_SYS);
	}

}
