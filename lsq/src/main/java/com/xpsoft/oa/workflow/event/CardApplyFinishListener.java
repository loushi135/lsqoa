package com.xpsoft.oa.workflow.event;


import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.CardApply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.CardApplyService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;
import com.xpsoft.oa.service.system.FileAttachService;

public class CardApplyFinishListener implements EventListener {

	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		// TODO Auto-generated method stub
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
//		String contractNo = (String)pi.getVariable("contractNo");
		String cardProposer = (String)pi.getVariable("cardProposer");
		String phone = (String)pi.getVariable("phone");
		String companyName = (String)pi.getVariable("companyName");
		String fax = (String)pi.getVariable("fax");
		String deptId = (String)pi.getVariable("deptId");
		String mobile = (String)pi.getVariable("mobile");
		String position = (String)pi.getVariable("position");
		String postalCode = (String)pi.getVariable("postalCode");
		String isType = (String)pi.getVariable("isType");
		String Email = (String)pi.getVariable("Email");
		String other = (String)pi.getVariable("other");
		String amount = (String)pi.getVariable("amount");
		String type = (String)pi.getVariable("type");
		String cardAttachIDs=(String)pi.getVariable("cardAttachIDs");
		CardApplyService cardApplyService = (CardApplyService) AppUtil.getBean("cardApplyService");
		DepartmentService departmentService=(DepartmentService)AppUtil.getBean("departmentService");
		FileAttachService fileAttachService=(FileAttachService)AppUtil.getBean("fileAttachService");
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		CardApply cardApply =new CardApply();
		cardApply.setAmount(Integer.valueOf(amount));
		cardApply.setCardProposer(cardProposer);
		cardApply.setEmail(Email);
		cardApply.setFax(fax);
		cardApply.setIsType("是".equals(isType)?1:0);
		cardApply.setMobile(mobile);
		cardApply.setOther(other);
		cardApply.setPhone(phone);
		cardApply.setPosition(position);
		cardApply.setCompanyName(companyName);
		cardApply.setType(type);
//		cardApply.setPostalCode(Integer.valueOf(postalCode));
		cardApply.setProcessRunId(processRun.getRunId());
		
		if(!deptId.equals(null)){
			Department dept=departmentService.get(Long.valueOf(deptId));
			cardApply.setDept(dept);
		}
		String fileIds = cardAttachIDs;
		if(StringUtils.isNotEmpty(fileIds)){
			String[]fIds=fileIds.split(",");
			for(int i=0;i<fIds.length;i++){
				FileAttach fileAttach=fileAttachService.get(new Long(fIds[i]));
				cardApply.getCardFiles().add(fileAttach);
			}
		}
		cardApply.setTimeCreate(new Date());
		cardApplyService.save(cardApply);
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
