package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.oa.model.system.AppUser;



//申请人权限判断
public class LeaveDecisionFirstHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		
		String result="";
		int flag=1;
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		String totalDays=(String)pi.getVariable("totalDays");//请假天数
		
		flag = ContextUtil.getHighestRole(flowStartUser);
		if(flag==3){
			if(Integer.parseInt(totalDays)>3){
				flag=3;
			}else{
				flag=4;
			}
		}
		
		switch (flag){
		  	case 1 :result="至部门经理审批";break;
		  	case 2 :result="至分管领导审批";break;
		  	case 3 :result="至总经理";break;
		  	case 4 :result="至结束";break;
		}
		return result;
	}

}
