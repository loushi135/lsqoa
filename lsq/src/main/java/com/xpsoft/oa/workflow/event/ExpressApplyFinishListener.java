package com.xpsoft.oa.workflow.event;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.oa.model.admin.ExpressApply;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.admin.ExpressApplyService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.system.DepartmentService;

public class ExpressApplyFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(ExpressApplyFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		
		ExpressApplyService expressApplyService = (ExpressApplyService) AppUtil.getBean("expressApplyService");
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		
		Long applyerId=Long.parseLong((String)pi.getVariable("applyerId"));
		Long provinceId=Long.parseLong((String)pi.getVariable("provinceId"));
		Long cityId=Long.parseLong((String)pi.getVariable("cityId"));
		String province=(String)pi.getVariable("province");
		String city=(String)pi.getVariable("city");
		String applyer=(String)pi.getVariable("applyer");
		String applyDate=(String)pi.getVariable("applyDate");
		String expressType=(String)pi.getVariable("expressType");
		String toWhereName=(String)pi.getVariable("toWhereName");
		String expressName=(String)pi.getVariable("expressName");
		String expressNo=(String)pi.getVariable("expressNo");
		String expressApplyDeptId=(String)pi.getVariable("expressApplyDeptId");
		String expressApplyProId=(String)pi.getVariable("expressApplyProId");
		
		ExpressApply expressApply=new ExpressApply();
		expressApply.setApplyerId(applyerId);
		expressApply.setApplyer(applyer);
		expressApply.setCityId(cityId);
		expressApply.setCity(city);
		expressApply.setProvinceId(provinceId);
		expressApply.setProvince(province);
		expressApply.setApplyDate(DateUtil.parse(applyDate, "yyyy-MM-dd"));
		expressApply.setExpressType(Integer.parseInt(expressType));
		expressApply.setToWhereName(toWhereName);
		expressApply.setExpressName(expressName);
		expressApply.setExpressNo(expressNo);
		expressApply.setProcessRunId(processRun.getRunId());
		if(StringUtils.isNotBlank(expressApplyDeptId)){
			DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
			expressApply.setDept(departmentService.get(Long.valueOf(expressApplyDeptId)));
		}
		if(StringUtils.isNotBlank(expressApplyProId)){
			ProjectNewService projectNewService = (ProjectNewService)AppUtil.getBean("projectNewService");
			expressApply.setProjectNew(projectNewService.get(Long.valueOf(expressApplyProId)));
		}
		expressApplyService.save(expressApply);
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);

	}

}
