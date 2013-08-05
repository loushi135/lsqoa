package com.xpsoft.oa.workflow.event;



import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.customer.SuppliersAssess;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.statistics.MaterialContract;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.customer.SuppliersAssessService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.statistics.MaterialContractService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.DepartmentService;
import com.xpsoft.oa.service.system.FileAttachService;




/**
 * 当请假单由上级审批完成后，会调用该事件进行相关的处理
 * @author 
 *
 */
public class ContractLettingListener implements EventListener {
	
	private Log logger=LogFactory.getLog(ContractLettingListener.class);
	
	
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.info("enter the NameCardListener notify method...");
		}
		
		MaterialContractService mcService=(MaterialContractService)AppUtil.getBean("materialContractService");
		
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
//		Object contractNo=pi.getVariable("contractNo");//合同编号
		Object proNo=pi.getVariable("proNo");//项目编号
		String materialProId=(String)pi.getVariable("materialProId");//项目主键
		Object proName=pi.getVariable("proName");//项目名称
		Object applyer=pi.getVariable("applyer");//经办人
		Object dept=pi.getVariable("dept");//拟办部门
		Object contractAmount=pi.getVariable("contractAmount");//合同金额
		Object chValue=pi.getVariable("chValue");//合同金额
		Object areanum=pi.getVariable("areanum");//区域面积
		Object mainItem=pi.getVariable("mainItem");//合同类别
		Object sysItem=pi.getVariable("sysItem");//系统类别
		Object xydw=pi.getVariable("xydw");//签约单位
		Object tel=pi.getVariable("tel");//联系人及电话
		Object yfk=pi.getVariable("yfk");//预付款
		Object jdk=pi.getVariable("jdk");//进度款
		Object wgk=pi.getVariable("wgk");//完工款
		Object jsk=pi.getVariable("jsk");//结算款
		Object zbj=pi.getVariable("zbj");//质保金
		String jdkReceive = (String)pi.getVariable("jdkReceive");//进度款收取时间
		Object usefull=pi.getVariable("usefull");//合同陈述
		Object contractFile=pi.getVariable("contractAttachFile");//合同附件
		Object contractAttachIDs=pi.getVariable("contractAttachIDs");//合同附件ID
		Object remark=pi.getVariable("remark");//经办人员
		String suppliersId = (String)pi.getVariable("suppliersId");
		String materialDeptCharger = (String)pi.getVariable("materialDeptCharger");
		String materialDeptChargerId = (String)pi.getVariable("materialDeptChargerId");
		String materialDeptId = (String)pi.getVariable("materialDeptId");
		
		//添加审批完的合同信息
		MaterialContract contract=new MaterialContract();
		
//		if(null!=contractNo)
//			contract.setContractNo(contractNo.toString());
		if(null!=proNo){
			String projectNo = proNo.toString();
			contract.setProNo(projectNo);
			QueryFilter queryFilter = new QueryFilter(ServletActionContext.getRequest());
			queryFilter.getPagingBean().setPageSize(1);
			queryFilter.addFilter("Q_proNo_S_EQ", projectNo);
			queryFilter.addSorted("contractNo", QueryFilter.ORDER_DESC);
			List<MaterialContract> conList = mcService.getAll(queryFilter);
			String newNo="";
			if(conList!=null&&conList.size()>0){
				MaterialContract mc = conList.get(0);
				String conNo = mc.getContractNo();
				Integer num = Integer.parseInt(conNo.substring(conNo.length()-4));
				newNo = String.valueOf(num+1);
				if(newNo.length()<2){
					newNo = "000" + String.valueOf((num + 1));
				}else if(newNo.length()<3){
					newNo = "00" + String.valueOf((num + 1));
				}else if(newNo.length()<4){
					newNo = "0" + String.valueOf((num + 1));
				}
			}else{
				newNo="0001";
			}
			contract.setContractNo(projectNo+newNo);
		}
		if(null!=proName)
			contract.setProName(proName.toString());
		if(null!=applyer)
			contract.setApplyer(applyer.toString());
		if(null!=dept)
			contract.setDept(dept.toString());
		if(null!=contractAmount)
			contract.setContractAmount(contractAmount.toString());
		if(null!=chValue)
				contract.setChValue(chValue.toString());
		if(null!=areanum)
			contract.setAreanum(areanum.toString());
		if(null!=mainItem)
			contract.setMainItem(mainItem.toString());
		if(null!=sysItem)
			contract.setSysItem(sysItem.toString());
		if(null!=xydw)
			contract.setXydw(xydw.toString());
		if(null!=tel)
			contract.setTel(tel.toString());
		if(null!=yfk)
			contract.setYfk(yfk.toString());
		if(null!=jdk)
			contract.setJdk(jdk.toString());
		if(null!=wgk)
			contract.setWgk(wgk.toString());
		if(null!=jsk)
			contract.setJsk(jsk.toString());
		if(null!=zbj)
			contract.setZbj(zbj.toString());
		if(null!=usefull)
			contract.setUsefull(usefull.toString());
		if(null!=contractAttachIDs)
			contract.setContractAttachIDs(contractAttachIDs.toString());
		if(null!=contractFile)
			contract.setContractFile(contractFile.toString());
		if(null!=remark)
			contract.setRemark(remark.toString());
		if(StringUtils.isNotBlank(suppliersId)){
			SuppliersAssessService suppliersAssessService = (SuppliersAssessService)AppUtil.getBean("suppliersAssessService");
			SuppliersAssess  suppliersAssess = suppliersAssessService.get(Long.valueOf(suppliersId));
			contract.setSuppliersAssess(suppliersAssess);
		}
		contract.setJdkReceive(jdkReceive);
		
		contract.setMaterialDeptChargerName(materialDeptCharger);
		if(StringUtils.isNotBlank(materialDeptChargerId)){
			AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
			contract.setMaterialDeptCharger(appUserService.get(Long.valueOf(materialDeptChargerId)));
		}
		if(StringUtils.isNotBlank(materialProId)){
			ProjectNewService projectNewService = (ProjectNewService)AppUtil.getBean("projectNewService");
			contract.setProject(projectNewService.get(Long.valueOf(materialProId)));
		}
		if(StringUtils.isNotBlank(materialDeptId)){
			DepartmentService departmentService = (DepartmentService)AppUtil.getBean("departmentService");
			contract.setDepartment(departmentService.get(Long.valueOf(materialDeptId)));
		}
//		if(null!=contractAttachIDs){
//			//合同附件
//			String[] fileIDs = contractAttachIDs.toString().split(",");
//			Set<FileAttach> contractAttachs = new HashSet<FileAttach>();
//			for (String id : fileIDs) {
//      			if (!id.equals("")) {
//      				contractAttachs.add(faService.get(new Long(id)));
//      			}
//      		}
//			contract.setContractFiles(contractAttachs);
//		}
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		contract.setProcessRunId(processRun.getRunId());
		
		mcService.save(contract);
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)pi.getVariable("flowStartUser");
		Long flowStartUserId=flowStartUser.getUserId();
		
		if(null!=flowStartUserId && flowStartUserId!=0 ){
			
			//所有参与流程的都发消息
			AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
		} 

	}
	
	

}
