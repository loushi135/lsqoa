package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.statistics.Entrant;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.statistics.EntrantService;
import com.xpsoft.oa.service.system.FileAttachService;


public class EntrantFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(EntrantFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		if(logger.isDebugEnabled()){
			logger.info("enter the UnpunchListener notify method...");
		}
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		FileAttachService fileAttachService = (FileAttachService)AppUtil.getBean("fileAttachService");
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		String entrantType = (String)pi.getVariable("entrantType");
		Date signTime = (Date)pi.getVariable("signTime");
		String position = (String)pi.getVariable("position");
		String inviteWay = (String)pi.getVariable("inviteWay");
		String inviteReason = (String)pi.getVariable("inviteReason");
		String professional = (String)pi.getVariable("professional");
		String experience = (String)pi.getVariable("experience");
		String confident = (String)pi.getVariable("confident");
		String thinkability = (String)pi.getVariable("thinkability");
		String expressability = (String)pi.getVariable("expressability");
		String looks = (String)pi.getVariable("looks");
		BigDecimal inspect = (BigDecimal)pi.getVariable("inspect");
		BigDecimal inspectSalary = (BigDecimal)pi.getVariable("inspectSalary");
		BigDecimal probation = (BigDecimal)pi.getVariable("probation");
		BigDecimal probationSalary = (BigDecimal)pi.getVariable("probationSalary");
		String entrantFileIDs=(String)pi.getVariable("entrantFileIDs");//上传文件id
		
		EntrantService entrantService = (EntrantService)AppUtil.getBean("entrantService");
		Entrant entrant = new Entrant();
		entrant.setSignName(flowStartUser.getFullname());
		entrant.setEntrantType(entrantType);
		entrant.setSignTime(signTime);
		entrant.setPosition(position);
		entrant.setInviteWay(inviteWay);
		entrant.setInviteReason(inviteReason);
		entrant.setProfessional(professional);
		entrant.setExperience(experience);
		entrant.setConfident(confident);
		entrant.setThinkability(thinkability);
		entrant.setExpressability(expressability);
		entrant.setLooks(looks);
		entrant.setInspect(inspect);
		entrant.setInspectSalary(inspectSalary);
		entrant.setProbation(probation);
		entrant.setProbationSalary(probationSalary);
		
		String fileIds = entrantFileIDs;
		if(StringUtils.isNotEmpty(fileIds)){
			String[]fIds=fileIds.split(",");
			for(int i=0;i<fIds.length;i++){
				FileAttach fileAttach=fileAttachService.get(new Long(fIds[i]));
				entrant.getEntrantFiles().add(fileAttach);
			}
		}
		entrantService.save(entrant);
		
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
