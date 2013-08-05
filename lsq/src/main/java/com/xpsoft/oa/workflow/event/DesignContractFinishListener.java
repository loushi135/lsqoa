package com.xpsoft.oa.workflow.event;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.struts2.ServletActionContext;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.statistics.DesignContract;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.statistics.DesignContractService;

public class DesignContractFinishListener implements EventListener {

	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		// TODO Auto-generated method stub
			
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
//		String contractNo = (String)pi.getVariable("contractNo");
		String companyName = (String)pi.getVariable("companyName");
		String designDept = (String)pi.getVariable("designDept");
		String companyProperty = (String)pi.getVariable("companyProperty");
		String cooperation = (String)pi.getVariable("cooperation");
		Integer cooperationNum = (Integer)pi.getVariable("cooperationNum");
		String companyCredit = (String)pi.getVariable("companyCredit");
		String projectName = (String)pi.getVariable("projectName");
		String projectAddr = (String)pi.getVariable("projectAddr");
		BigDecimal contractAmount = (BigDecimal)pi.getVariable("contractAmount");
		BigDecimal designArea = (BigDecimal)pi.getVariable("designArea");
		BigDecimal singlePrice = (BigDecimal)pi.getVariable("singlePrice");
		BigDecimal projectPrice = (BigDecimal)pi.getVariable("projectPrice");
		BigDecimal chargeRate = (BigDecimal)pi.getVariable("chargeRate");
		String isEndWork = (String)pi.getVariable("isEndWork");
		String workArea = (String)pi.getVariable("workArea");
		String isLetDesignFee = (String)pi.getVariable("isLetDesignFee");
		
		DesignContractService designContractService = (DesignContractService)AppUtil.getBean("designContractService");
		DesignContract designContract = new DesignContract();
		//生成合同编号
		String contractType = "01";
		String year = new SimpleDateFormat("yyyy").format(new Date());
		String search = "LJT"+contractType+year;
		HttpServletRequest request = ServletActionContext.getRequest();
		QueryFilter filter = new QueryFilter(request);
		filter.addFilter("Q_contractNo_S_LK", search);
		filter.addSorted("contractNo", "desc");
		filter.addFilter("limit", "1");
		List<DesignContract> conList = designContractService.getAll(filter);
		String newNo="";
		if(conList!=null&&conList.size()>0){
			DesignContract lastCon = conList.get(0);
			String contractNo = lastCon.getContractNo();
			Integer num = Integer.parseInt(contractNo.substring(contractNo
					.lastIndexOf(search)+search.length()));
			newNo = String.valueOf(num+1);
			if(newNo.length()<2){
				newNo = "00" + String.valueOf((num + 1));
			}else if(newNo.length()<3){
				newNo = "0" + String.valueOf((num + 1));
			}
		}else{
			newNo = "001";
		}
		String contractNo = search + newNo;
		designContract.setContractNo(contractNo);
		designContract.setCompanyName(companyName);
		designContract.setDesignDept(designDept);
		designContract.setCompanyProperty(companyProperty);
		designContract.setCooperation(cooperation);
		designContract.setCooperationNum(cooperationNum);
		designContract.setCompanyCredit(companyCredit);
		designContract.setProjectName(projectName);
		designContract.setProjectAddr(projectAddr);
		designContract.setContractAmount(contractAmount);
		designContract.setDesignArea(designArea);
		designContract.setSinglePrice(singlePrice);
		designContract.setProjectPrice(projectPrice);
		designContract.setChargeRate(chargeRate);
		designContract.setIsEndWork(isEndWork);
		designContract.setWorkArea(workArea);
		designContract.setIsLetDesignFee(isLetDesignFee);
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		designContract.setProcessRunId(processRun.getRunId());
		
		designContractService.save(designContract);
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
