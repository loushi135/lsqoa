package com.xpsoft.oa.workflow.event;

import java.util.Date;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.service.flow.ProcessRunService;

//判断
public class TicketApplyFirstHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {

		String result = "";
		OpenProcessInstance pi = execution.getProcessInstance();
        ProcessRunService processRunService = (ProcessRunService)AppUtil.getBean("processRunService");
        ProcessRun processRun =  processRunService.getByExeId(execution.getId());
        Date applyDate = processRun.getCreatetime();
        Date departureTime = (Date)pi.getVariable("departureTime");
        Date sApply = DateUtil.parse(DateUtil.format(applyDate,"yyyy-MM-dd"),"yyyy-MM-dd");
        Date sDeparture = DateUtil.parse(DateUtil.format(departureTime,"yyyy-MM-dd"),"yyyy-MM-dd");
        Long oneDay = 3600L*1000*24;
        Long day = (sDeparture.getTime()-sApply.getTime())/oneDay+1;
        if(day.compareTo(3L)==1){
        	result = "出发时间在申请时间的三天后";
        }else{
        	result = "出发时间在申请时间三天内";
        }
		return result;
	}

}
