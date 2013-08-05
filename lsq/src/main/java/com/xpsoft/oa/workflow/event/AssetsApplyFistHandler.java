package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.oa.model.system.AppUser;

public class AssetsApplyFistHandler implements DecisionHandler {
	public static final String  WORK_DEPTS = "工程一区工程二区工程三区工程五区工程六区工程七区直属一部直属二部";
	@Override
	public String decide(OpenExecution execution) {
		String result="";
		int flag=2;
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		
		int roleFlag = ContextUtil.getHighestRole(flowStartUser);
		if(roleFlag==1){
			flag = roleFlag;
		}
		if(WORK_DEPTS.contains(flowStartUser.getDepartment().getDepName())){//施工部门
			flag = 3;
		}
		switch (flag){
		  	case 1 :result="普通员工";break;
		  	case 2 :result="部门经理及以上";break;
		  	case 3 :result="施工部门";break;
		  	default :break;
		}
		return result;
	}

}
