package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.oa.model.system.AppUser;

public class CourseTrainApplyHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		String result="";
		int flag=0;
		
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String TrainTarget = (String)pi.getVariable("trainApply.trainTarget");
		if("部门培训".equals(TrainTarget)){
			flag = ContextUtil.getHighestRole(flowStartUser);
		}else if("公司培训".equals(TrainTarget)){
			flag = 3;
		}
		switch (flag){
		  	case 1 :result="普通员工";break;
		  	case 2 :result="部门经理";break;
		  	case 3 :result="公司培训，或者分管领导申请";break;
		  	default :break;
		}
		
		return result;
	}

}
