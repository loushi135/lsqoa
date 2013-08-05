package com.xpsoft.webservice.service.inner.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.xml.rpc.holders.StringHolder;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.model.HandleJob;
import org.model.YhoaHandleJob;
import org.service.HandleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import com.xpsoft.core.Constants;
import com.xpsoft.core.engine.MailEngine;
import com.xpsoft.core.util.BeanUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.webservice.service.HandleImp;
import com.xpsoft.webservice.service.inner.GoldMantisService;

public class GoldMantisServiceImpl implements GoldMantisService{
	private final Log logger = LogFactory.getLog(GoldMantisServiceImpl.class);
	@Autowired
	private HandleJobService handleJobService;
	
	/**
	 *  添加代办事项
	 * String workflowName：流程名称
	 * String requestJobCode：请求者工号  流程发启人
	 * String handlerJobCode：处理者工号  下一个审批人
	 * String workflowNodeName：当前节点名称
	 * String waitHandleJobUrl：点击后跳转的url
	 */
	@Override
	public void AddWaitHandleJob(HandleJob handleJob) {
		String sourceApp = Constants.INTERGRATION_LJTOA_NAME; //调用客户端,请一定传LJTOA，不然无法调用
		StringHolder message = new StringHolder(""); //message错误信息,若调用成功 则为空
		String waitHandleJobId = String.valueOf(System.currentTimeMillis()); //代办ID，请确保唯一性,以便这里处理
		String billCode=GenerateBillCode(sourceApp); //代办事项编号，编号建议同ERP系统中编号风格，建议"LJTOA-1212-0001"
		handleJob.setSourceApp(sourceApp);
		handleJob.setMessage(message);
		handleJob.setWaitHandleJobId(waitHandleJobId);
		handleJob.setBillCode(billCode);
		handleJob.setRequestMethod("AddWaitHandleJob");
		handleJobService.save(handleJob);
		logger.error("----成功存储handleJob----");
		//执行 发送金螳螂流程接口
		HandleImp.AddWaitHandleJob(handleJob);
		logger.error("----已调用ERP接口发出流程指令，请查看系统处理情况----");
	}
	
	@Override
	public void processWaitHandleJob(String runId) {
		HandleJob handleJob = handleJobService.getHandleJobByRunId(runId);
		//执行 处理金螳螂流程接口
		HandleImp.processWaitHandleJob(handleJob);
		logger.error("----已处理ERP返回的操作结果，请查看系统处理情况----");
	}
	
	
	@Override
	public void addYhteToGoldMantis(YhoaHandleJob yhoaHandleJob)throws Exception {
		HandleJob handleJob = new HandleJob();
		BeanUtil.copyNotNullProperties(handleJob, yhoaHandleJob);
		handleJob.setMessage(new StringHolder(yhoaHandleJob.getMessage()));
		handleJobService.save(handleJob);
		logger.error("----成功存储handleJob----");
		//执行 发送金螳螂流程接口
		HandleImp.AddWaitHandleJob(handleJob);
		logger.error("----已调用ERP接口发出流程指令，请查看系统处理情况----");
	}

	/**
	 * 代办事项编号，编号建议同ERP系统中编号风格，建议"LJTOA-1212-0001"
	 * @param sourceApp
	 * @return
	 */
	private String GenerateBillCode(String sourceApp){
		String billCode="";
		String middle="";
		
		//中间年月  1212
		SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
		middle=sdf.format(DateUtil.now());
		//整个前缀   LJTOA-1212-
		String prebillCode=sourceApp+"-"+middle+"-";
		billCode=handleJobService.getMaxBillCode(prebillCode);
		
		if(StringUtils.isEmpty(billCode)){
			billCode = prebillCode + "0001";
		}else{
			DecimalFormat df = new DecimalFormat("0000");
			billCode = prebillCode + df.format(1 + Integer.parseInt(billCode.substring(11)));

		}
		return billCode;
	}
	
	
}
