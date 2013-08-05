package com.xpsoft.oa.workflow.event;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.LocalProductApply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.LocalProductApplyService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;

public class LocalProductApplyFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(LocalProductApplyFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String applyUserId = (String)pi.getVariable("localProductApply.applyUser.userId");
		String deptId = (String)pi.getVariable("localProductApply.dept.depId");
		Date applyDate = (Date)pi.getVariable("localProductApply.applyDate");
		String dataList = (String)pi.getVariable("localProductApply.dataList");
		String applyReason = (String)pi.getVariable("localProductApply.applyReason");
		
		LocalProductApply localProductApply = new LocalProductApply();
		localProductApply.setApplyDate(applyDate);
		localProductApply.setApplyReason(applyReason);
		AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
		if(StringUtils.isNotBlank(applyUserId)){
			localProductApply.setApplyUser(appUserService.get(Long.valueOf(applyUserId)));
		}
		if(StringUtils.isNotBlank(deptId)){
			DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
			localProductApply.setDept(departmentService.get(Long.valueOf(deptId)));
		}
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		localProductApply.setProcessRunId(processRun.getRunId());
		
		LocalProductApplyService localProductApplyService = (LocalProductApplyService)AppUtil.getBean("localProductApplyService");
		localProductApplyService.saveLocalProductApply(localProductApply, dataList);
		
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
	
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
