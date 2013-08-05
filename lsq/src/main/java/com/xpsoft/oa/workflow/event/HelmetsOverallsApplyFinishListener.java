package com.xpsoft.oa.workflow.event;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.statistics.Helmetsoverallsapply;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.statistics.HelmetsoverallsapplyService;

public class HelmetsOverallsApplyFinishListener implements EventListener {
	
	private Log logger=LogFactory.getLog(HelmetsOverallsApplyFinishListener.class);

	@Override
	public void notify(EventListenerExecution execution) throws Exception {

		
		
		if (logger.isDebugEnabled()) {
			logger.info("enter the HelmetsOverallsApplyFinishListener notify method...");
		}
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		
		String areaId=(String)pi.getVariable("flowHO.areaId");//施工区域ID
		String areaName=(String)pi.getVariable("flowHO.areaName");//施工区域
		String proName=(String)pi.getVariable("flowHO.proName");//项目名称
		String proID=(String)pi.getVariable("flowHO.proID");//项目ID
		String address=(String)pi.getVariable("flowHO.address");//工程详细地址
		String designChargerId=(String)pi.getVariable("flowHO.designChargerId");//项目负责人
		String designChargerName=(String)pi.getVariable("flowHO.designChargerName");//项目负责人
		String designChargerTel=(String)pi.getVariable("flowHO.designChargerTel");//项目负责人联系电话
		String takeUserName=(String)pi.getVariable("flowHO.takeUserName");//收货人
		String takeUserId=(String)pi.getVariable("flowHO.takeUserId");//收货人ID
		String takeUserTel=(String)pi.getVariable("flowHO.takeUserTel");//收货人联系电话
		String remark=(String)pi.getVariable("flowHO.remark");//备注
		Integer redNum=(Integer)pi.getVariable("flowHO.redNum");//红色
		Integer yellowNum=(Integer)pi.getVariable("flowHO.yellowNum");//黄色
		Integer whiteNum=(Integer)pi.getVariable("flowHO.whiteNum");//白色
		Integer blueNum=(Integer)pi.getVariable("flowHO.blueNum");//蓝色
		Integer longSleeveXL=(Integer)pi.getVariable("flowHO.longSleeveXL");//长袖：XL
		Integer longSleeveXXL=(Integer)pi.getVariable("flowHO.longSleeveXXL");//长袖：XXL
		Integer longSleeveXXXL=(Integer)pi.getVariable("flowHO.longSleeveXXXL");//长袖：XXXL
		Integer shortSleeveXL=(Integer)pi.getVariable("flowHO.shortSleeveXL");//短袖：XL
		Integer shortSleeveXXL=(Integer)pi.getVariable("flowHO.shortSleeveXXL");//短袖：XXL
		Integer shortSleeveXXXL=(Integer)pi.getVariable("flowHO.shortSleeveXXXL");//短袖：XXXL
		
		Helmetsoverallsapply helmetsoverallsapply=new Helmetsoverallsapply();
		
		helmetsoverallsapply.setAreaId(Long.valueOf(areaId));
		helmetsoverallsapply.setTimeCreate(new Date());
		helmetsoverallsapply.setAddress(address);
		helmetsoverallsapply.setAreaName(areaName);
		helmetsoverallsapply.setBlueNum(blueNum);
		helmetsoverallsapply.setDesignChargerId(Long.valueOf(designChargerId));
		helmetsoverallsapply.setDesignChargerName(designChargerName);
		helmetsoverallsapply.setDesignChargerTel(designChargerTel);
		helmetsoverallsapply.setLongSleeveXL(longSleeveXL);
		helmetsoverallsapply.setLongSleeveXXL(longSleeveXXL);
		helmetsoverallsapply.setLongSleeveXXXL(longSleeveXXXL);
		
		helmetsoverallsapply.setProID(Long.valueOf(proID));
		helmetsoverallsapply.setProName(proName);
		helmetsoverallsapply.setRedNum(redNum);
		helmetsoverallsapply.setRemark(remark);
		helmetsoverallsapply.setShortSleeveXL(shortSleeveXL);
		helmetsoverallsapply.setShortSleeveXXL(shortSleeveXXL);
		helmetsoverallsapply.setShortSleeveXXXL(shortSleeveXXXL);
		helmetsoverallsapply.setTakeUserId(Long.valueOf(takeUserId));
		helmetsoverallsapply.setTakeUserName(takeUserName);
		helmetsoverallsapply.setTakeUserTel(takeUserTel);
		helmetsoverallsapply.setWhiteNum(whiteNum);
		helmetsoverallsapply.setYellowNum(yellowNum);
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
	
		helmetsoverallsapply.setProcessRunId(processRun.getRunId());
		
		HelmetsoverallsapplyService helmetsoverallsapplyService=(HelmetsoverallsapplyService)AppUtil.getBean("helmetsoverallsapplyService");
		
		helmetsoverallsapplyService.save(helmetsoverallsapply);
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}