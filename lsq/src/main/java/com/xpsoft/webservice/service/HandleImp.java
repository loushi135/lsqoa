package com.xpsoft.webservice.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.holders.BooleanWrapperHolder;
import javax.xml.rpc.holders.StringHolder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.datacontract.schemas._2004._07.GoldMantis_WaitHandle_Common_Interface.ProcessMethod;
import org.model.HandleJob;
import org.tempuri.IWaitHandleService;
import org.tempuri.WaitHandleServiceLocator;

import com.xpsoft.core.Constants;

public class HandleImp {
	private static final Log logger = LogFactory.getLog(HandleImp.class);
	
	public static void main(String[] args) {
//		HandleJob handleJob = new HandleJob();
//		handleJob.setRequestJobCode("J272971");
//		handleJob.setWaitHandleJobId("33333333");
//		handleJob.setBillCode("LJTOA-1212-00001");
//		handleJob.setHandlerJobCode("Q010001");
//		handleJob.setWaitHandleJobUrl("www.sina.com");
//		handleJob.setWorkflowName("22222");
//		handleJob.setSourceApp("LJTOA");
//		AddWaitHandleJob(handleJob);
//		//processWaitHandleJob();
	}
	
	
	/**
	 *  添加代办事项
	 * String waitHandleJobId:代办ID，请确保唯一性,以便这里处理
	 * String billCode：代办事项编号，编号建议同ERP系统中编号风格，建议"LJTOA-1212-0001"
	 * String workflowName：流程名称
	 * String requestJobCode：请求者工号
	 * String handlerJobCode：处理者工号
	 * String workflowNodeName：当前节点名称
	 * String waitHandleJobUrl：点击后跳转的url
	 * String sourceApp:调用客户端,请一定传LJTOA，不然无法调用
	 * StringHolder message错误信息,若调用成功 则为空
	 */
	public static void AddWaitHandleJob(HandleJob hj) {
		/*
		 * 准备WebService服务器
		 */
		IWaitHandleService handler = null;
		try {
			URL url = new URL(Constants.INTERGRATION_WEBSERVICE_URL);
			WaitHandleServiceLocator locator = new WaitHandleServiceLocator();
			handler = locator.getBasicHttpBinding_IWaitHandleService(url);
		} catch (Exception e1) {
			logger.error("WebService 服务器没有开启 - 远程服务器URL is - " + Constants.INTERGRATION_WEBSERVICE_URL);
			System.out.println("======WebService 服务器没有开启 - 远程服务器URL is - " + Constants.INTERGRATION_WEBSERVICE_URL);
		}
		
		BooleanWrapperHolder processWaitHandleJobResult = new BooleanWrapperHolder(true);
		try {
			handler.addWaitHandleJob(hj.getWaitHandleJobId(), hj.getBillCode(), hj.getWorkflowName(), hj.getRequestJobCode(), hj.getHandlerJobCode(), hj.getWorkflowNodeName(), hj.getSourceApp(), hj.getWaitHandleJobUrl(), "", processWaitHandleJobResult, hj.getMessage());
			System.out.println("-----调用ERP接口Add  ：：WaitHandleJobId="+hj.getWaitHandleJobId()+" || BillCode="+hj.getBillCode()+" || WorkflowName="+hj.getWorkflowName()+" || RequestJobCode="+hj.getRequestJobCode()+" || HandlerJobCode="+hj.getHandlerJobCode()+" || WorkflowNodeName="+hj.getWorkflowNodeName() +" || SourceApp="+hj.getSourceApp()+" || WaitHandleJobUrl="+hj.getWaitHandleJobUrl());
		} catch (RemoteException e) {
			logger.error("Remote service is exception, error message is: " + e.getMessage());
			System.out.println("======Remote service is exception, error message is: " + e.getMessage());
		}
	}
	
	/**
	*  处理代办事项(成功处理的进入已办)添加代办事项
	 * String waitHandleJobId:代办ID，请确保唯一性,以便这里处理
	 * String sourceApp:调用客户端,请一定传LJTOA，不然无法调用
	 * String factHandleJobCode:处理者工号
	 * ProcessMethod ProcessMethod:处理方式 枚举型-1:处理,2:删除,3:其他
	 * BooleanWrapperHolder processWaitHandleJobResult
	 * StringHolder message错误信息,若调用成功 则为空
	 */
	public static void processWaitHandleJob(HandleJob hj) {
		BooleanWrapperHolder processWaitHandleJobResult = new BooleanWrapperHolder(true);
		StringHolder message = new StringHolder("process");
		try {
			URL url = new URL(Constants.INTERGRATION_WEBSERVICE_URL);
			WaitHandleServiceLocator locator = new WaitHandleServiceLocator();
			IWaitHandleService handler = locator.getBasicHttpBinding_IWaitHandleService(url);
			handler.processWaitHandleJob(hj.getWaitHandleJobId(), hj.getSourceApp(), hj.getFactHandleJobCode(), ProcessMethod.Process, processWaitHandleJobResult, message);
		} catch (MalformedURLException e) {
			logger.error("url for gold mantis sms service is malformed: " + e.getMessage());
			System.out.println("url for gold mantis sms service is malformed: " + e.getMessage());
		} catch (RemoteException e) {
			logger.error("Remote service is exception, error message is: " + e.getMessage());
			System.out.println("Remote service is exception, error message is: " + e.getMessage());
		} catch (ServiceException e) {
			logger.error("cannot get local stab for gold mantis sms service, error message is: " + e.getMessage());
			System.out.println("cannot get local stab for gold mantis sms service, error message is: " + e.getMessage());
		}
	}
	
}
