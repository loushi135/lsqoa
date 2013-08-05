package com.xpsoft.core.sms;

import java.util.Date;
import java.util.List;

import com.jasson.mas.api.sms.SmsApiClientHandler;
import com.jasson.mas.api.smsapi.Report;
import com.jasson.mas.api.smsapi.Sms;
import com.xpsoft.core.model.ProMsgDetail;
import com.xpsoft.core.model.ProMsgReceived;
import com.xpsoft.core.service.ProMsgDetailService;
import com.xpsoft.core.service.ProMsgReceivedService;
import com.xpsoft.core.service.ProSendMsgService;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.action.flow.FlowRunInfo;
import com.xpsoft.oa.service.system.FlowAutoCommitService;



public class SmsAPIClientHandlerImpl implements SmsApiClientHandler {
	public void notifySmsDeliveryStatus(String submitID, Report[] report) {
		System.out.println("接收到状态报告" + report.length + ": SmsReport submitID: "
				+ submitID + " sendResul= " + report[0].sendResult);
	}
	
	public void notifyCanelSmsList(String submitID, List<String> canelMobileList){
		
	}

	public void notifySmsReception(Sms sms) {

		System.out.println("收到一条MO，destId：" + sms.destID + "；内容：" + sms.content
				+ "手机号码：" + sms.mobile);
		
		//处理并存储短信回复数据
		ProMsgReceived rm = saveReceivedMsg(sms);
		if(rm!=null){
			//获取短信实例对象
			ProMsgDetailService proMsgDetailService=(ProMsgDetailService)AppUtil.getBean("proMsgDetailService");
			ProMsgDetail proMsgDetail = proMsgDetailService.getByStrId(rm.getStrId());
			
			//该处将调用流程审批接口进行流程操作
			ProSendMsgService ProSendMsgService=(ProSendMsgService)AppUtil.getBean("proSendMsgService");
			if(ProSendMsgService.msgReplyDoTask(rm, proMsgDetail)){
				FlowRunInfo flowRunInfo=new FlowRunInfo();
				flowRunInfo.setPiId(proMsgDetail.getProcessId());
				flowRunInfo.setUserId(proMsgDetail.getUserId().toString());
				flowRunInfo.setStartFlow(false);
				FlowAutoCommitService flowAutoCommitService=(FlowAutoCommitService)AppUtil.getBean("flowAutoCommitService");
				//自动审批
				boolean flag=true;
				while(flag){
					flag=flowAutoCommitService.autoCommit(flowRunInfo);
					flowRunInfo.setStartFlow(false);
				}
			}
		}
	}
	
	
	public ProMsgReceived saveReceivedMsg(Sms sms){
		//判断逗号分割符
		String receiveRes = sms.content;
		String strIdResult = "";
		String resNote = "";
		
		if(receiveRes.contains("#")){
			String[] res = receiveRes.split("#");
			strIdResult = res[0];
			if(res.length>1){
				resNote = res[1];
			}
		} else if(receiveRes.contains("＃")){
			String[] res = receiveRes.split("＃");
			strIdResult = res[0];
			if(res.length>1){
				resNote = res[1];
			}
		} else {
			strIdResult = receiveRes;
		}
		//判断审批代码是否为纯数字，如果不是直接中止
		boolean flag = isNumeric(strIdResult);
		if(!flag){
			return null;
		}
		
		//判断回复内同是否会系统合法内容
		flag = checkReceivedMsgIfRight(strIdResult);
		if(!flag){
			return null;
		}
		//过滤回复的短信，判断是否已操作完成
		flag = checkReceivedMsgIfFinished(strIdResult.substring(0,strIdResult.length()-1));
		if(flag){
			//存储短信回复数据
			ProMsgReceivedService proMsgReceivedService=(ProMsgReceivedService)AppUtil.getBean("proMsgReceivedService");
			ProMsgReceived pcm = new ProMsgReceived();
			
			//1表示同意，0表示不同意
			pcm.setResult(strIdResult.charAt(strIdResult.length()-1)+"");
			pcm.setStrId(strIdResult.substring(0,strIdResult.length()-1));
			pcm.setResNote(resNote);
			pcm.setReceivetime(new Date());
			pcm.setMobile(sms.mobile);
			ProMsgReceived prm = proMsgReceivedService.save(pcm);
			return prm;
		} else {
			return null;
		}
	}
	
	//过滤回复的短信，判断是否是合法内容
	//返回true：继续 ||flase：中止操作
	public boolean checkReceivedMsgIfRight(String strId){
		boolean flag = true;
		ProMsgDetailService proMsgDetailService=(ProMsgDetailService)AppUtil.getBean("proMsgDetailService");
		flag = proMsgDetailService.checkReceivedMsgIfRight(strId);
		if(flag){
			ProSendMsgService ProSendMsgService=(ProSendMsgService)AppUtil.getBean("proSendMsgService");
			flag = ProSendMsgService.isTask(strId.substring(0,strId.length()-1));
		}
		return flag;
	}
	
	//过滤回复的短信，判断是否已操作完成
	//返回true：执行审批操作||flase：中止操作
	public boolean checkReceivedMsgIfFinished(String strId){
		boolean flag = true;
		ProMsgReceivedService proMsgReceivedService=(ProMsgReceivedService)AppUtil.getBean("proMsgReceivedService");
		List<ProMsgReceived> list = proMsgReceivedService.getByStrId(strId);
		if(list.size()>0){
			if(list.get(0).getFinished()==1){
				flag=false;
			}
		}
		return flag;
	}
	
	//判断是否为纯数字
	public static boolean isNumeric(String str){ 
		//Pattern pattern = Pattern.compile("[0-9]*"); 
		//boolean res = pattern.matcher(str).matches(); 
		int x = 0;
		try {
			x = Integer.parseInt(str);
		} catch (NumberFormatException e) {}
		if(x==0){
			return false;
		}else{
			return true;
		}
	} 

}