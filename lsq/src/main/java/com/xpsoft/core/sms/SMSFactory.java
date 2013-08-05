package com.xpsoft.core.sms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.royasoft.mas.api.SMSAgent;
import com.royasoft.mas.api.model.SMSReceiveMessage;
import com.royasoft.mas.api.model.SMSStatus;
import com.royasoft.mas.api.model.SMSSubmitMessage;
import com.xpsoft.core.model.ProMsgDetail;
import com.xpsoft.core.model.ProMsgReceived;
import com.xpsoft.core.service.ProMsgDetailService;
import com.xpsoft.core.service.ProMsgReceivedService;
import com.xpsoft.core.service.ProSendMsgService;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.ArrayUtils;
import com.xpsoft.oa.action.flow.FlowRunInfo;
import com.xpsoft.oa.service.system.FlowAutoCommitService;

public class SMSFactory {
	public static final String SMS_ID = "app.smsId";
	public static final String SMS_LOCATION = "app.smsLocation";
	
	public static boolean send(List<String> pboneList, String content) {
		List<String> finalList = new ArrayList<String>();
		if (pboneList.size() < 10 && pboneList.size() > 0) {
			String str[] = pboneList.toArray(new String[] {});
			String phonenums = StringUtils.join(str, ";");
			finalList.add(phonenums);
		} else if (pboneList.size() >= 10) {
			List<List<String>> list = ArrayUtils.splitList(pboneList, 9);
			for (List<String> plist : list) {
				String str[] = plist.toArray(new String[] {});
				String phonenums = StringUtils.join(str, ";");
				finalList.add(phonenums);
			}
		}
		// 测试环境
		SMSAgent.initialize(AppUtil.getPropertity(SMS_LOCATION));
		// 正式环境
		// SMSAgent.initialize("D:\\LJToa\\apache-tomcat-7.0.34-windows-x64\\apache-tomcat-7.0.34\\webapps\\ljtoa\\WEB-INF\\classes\\conf\\sms.properties");
		try {
			for (String phonenums : finalList) {
//				SMSSubmitMessage message = new SMSSubmitMessage(java.util.UUID
//						.randomUUID().toString(), phonenums, content,
//						AppUtil.getPropertity(SMS_ID));
//				message.setReqDeliveryReport(1);
//				message.setSendMethod(2);
//				SMSAgent.sendSMS(message);
				System.out.println("commit sms");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void receive() {
		// 测试环境
		SMSAgent.initialize(AppUtil.getPropertity(SMS_LOCATION));
		// 正式环境
		// SMSAgent.initialize("D:\\LJToa\\apache-tomcat-7.0.34-windows-x64\\apache-tomcat-7.0.34\\webapps\\ljtoa\\WEB-INF\\classes\\conf\\sms.properties");
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						List<SMSReceiveMessage> list = SMSAgent
								.getAllReceiveSMS();
						for (SMSReceiveMessage sms : list) {
							System.out.print("收到上行短信：");
							System.out.println(sms.getSourceAddr() + "\t" + sms.getContent());
							this.smsDoFlow(sms);
						}
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			public void smsDoFlow(SMSReceiveMessage sms) {
				// 处理并存储短信回复数据
				ProMsgReceived rm = saveReceivedMsg(sms);
				if (rm != null) {
					// 获取短信实例对象
					ProMsgDetailService proMsgDetailService = (ProMsgDetailService) AppUtil
							.getBean("proMsgDetailService");
					ProMsgDetail proMsgDetail = proMsgDetailService
							.getByStrId(rm.getStrId());

					// 该处将调用流程审批接口进行流程操作
					ProSendMsgService ProSendMsgService = (ProSendMsgService) AppUtil
							.getBean("proSendMsgService");
					if (ProSendMsgService.msgReplyDoTask(rm, proMsgDetail)) {
						FlowRunInfo flowRunInfo = new FlowRunInfo();
						flowRunInfo.setPiId(proMsgDetail.getProcessId());
						flowRunInfo.setUserId(proMsgDetail.getUserId()
								.toString());
						flowRunInfo.setStartFlow(false);
						FlowAutoCommitService flowAutoCommitService = (FlowAutoCommitService) AppUtil
								.getBean("flowAutoCommitService");
						// 自动审批
						boolean flag = true;
						while (flag) {
							flag = flowAutoCommitService
									.autoCommit(flowRunInfo);
							flowRunInfo.setStartFlow(false);
						}
					}
				}
			}

			public ProMsgReceived saveReceivedMsg(SMSReceiveMessage sms) {
				// 判断逗号分割符
				String receiveRes = sms.getContent();
				String strIdResult = "";
				String resNote = "";

				if (receiveRes.contains("#")) {
					String[] res = receiveRes.split("#");
					strIdResult = res[0];
					if (res.length > 1) {
						resNote = res[1];
					}
				} else if (receiveRes.contains("＃")) {
					String[] res = receiveRes.split("＃");
					strIdResult = res[0];
					if (res.length > 1) {
						resNote = res[1];
					}
				} else {
					strIdResult = receiveRes;
				}
				// 判断审批代码是否为纯数字，如果不是直接中止
				boolean flag = isNumeric(strIdResult);
				if (!flag) {
					return null;
				}

				// 判断回复内同是否会系统合法内容
				flag = checkReceivedMsgIfRight(strIdResult);
				if (!flag) {
					return null;
				}
				// 过滤回复的短信，判断是否已操作完成
				flag = checkReceivedMsgIfFinished(strIdResult.substring(0,
						strIdResult.length() - 1));
				if (flag) {
					// 存储短信回复数据
					ProMsgReceivedService proMsgReceivedService = (ProMsgReceivedService) AppUtil
							.getBean("proMsgReceivedService");
					ProMsgReceived pcm = new ProMsgReceived();

					// 1表示同意，0表示不同意
					pcm.setResult(strIdResult.charAt(strIdResult.length() - 1)
							+ "");
					pcm.setStrId(strIdResult.substring(0,
							strIdResult.length() - 1));
					pcm.setResNote(resNote);
					pcm.setReceivetime(new Date());
					pcm.setMobile(sms.getSourceAddr());
					ProMsgReceived prm = proMsgReceivedService.save(pcm);
					return prm;
				} else {
					return null;
				}
			}

			// 过滤回复的短信，判断是否是合法内容
			// 返回true：继续 ||flase：中止操作
			public boolean checkReceivedMsgIfRight(String strId) {
				boolean flag = true;
				ProMsgDetailService proMsgDetailService = (ProMsgDetailService) AppUtil
						.getBean("proMsgDetailService");
				flag = proMsgDetailService.checkReceivedMsgIfRight(strId);
				if (flag) {
					ProSendMsgService ProSendMsgService = (ProSendMsgService) AppUtil
							.getBean("proSendMsgService");
					flag = ProSendMsgService.isTask(strId.substring(0, strId
							.length() - 1));
				}
				return flag;
			}

			// 过滤回复的短信，判断是否已操作完成
			// 返回true：执行审批操作||flase：中止操作
			public boolean checkReceivedMsgIfFinished(String strId) {
				boolean flag = true;
				ProMsgReceivedService proMsgReceivedService = (ProMsgReceivedService) AppUtil
						.getBean("proMsgReceivedService");
				List<ProMsgReceived> list = proMsgReceivedService
						.getByStrId(strId);
				if (list.size() > 0) {
					if (list.get(0).getFinished() == 1) {
						flag = false;
					}
				}
				return flag;
			}

			// 判断是否为纯数字
			public boolean isNumeric(String str) {
				// Pattern pattern = Pattern.compile("[0-9]*");
				// boolean res = pattern.matcher(str).matches();
				int x = 0;
				try {
					x = Integer.parseInt(str);
				} catch (NumberFormatException e) {
				}
				if (x == 0) {
					return false;
				} else {
					return true;
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						List<SMSStatus> list = SMSAgent.getAllSMSStatus();
						for (SMSStatus status : list) {
							System.out.print("收到上行状态报告：");
							System.out.println(status.getDestaddr() + "\t"
									+ status.getSmsStatus() + "\t"
									+ status.getSendResult());
						}
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

//	public static void main(String[] args) throws Exception {
//		receive();
//		List<String> list = new ArrayList<String>();
//		list.add("13506206298");
//		send(list, "test1");
//	}

}
