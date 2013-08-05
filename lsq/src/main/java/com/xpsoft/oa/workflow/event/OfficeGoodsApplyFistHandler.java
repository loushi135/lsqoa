package com.xpsoft.oa.workflow.event;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.oa.model.system.AppUser;

public class OfficeGoodsApplyFistHandler implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		String result="";
		int flag=2;
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		String applyGoodUseType=(String)execution.getProcessInstance().getVariable("applyGoodUseType");
		//获取流程发起者的角色
		if("项目使用".equals(applyGoodUseType)){
			flag=3;
		}else{
			flag = ContextUtil.getHighestRole(flowStartUser);
			if(flag!=1){
				flag = 2;
			}
		}
		switch (flag){
		  	case 1 :result="普通员工";break;
		  	case 2 :result="部门经理及以上";break;
		  	case 3 :result="项目使用";break;
		  	default :break;
		}
		return result;
	}

}
