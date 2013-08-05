package com.xpsoft.core.sms;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.jasson.mas.api.common.ConnectStatus;
import com.jasson.mas.api.sms.SmsApiClient;
import com.jasson.mas.api.sms.SmsApiClientHandler;
import com.jasson.mas.api.sms.SmsApiClientImpl;
import com.jasson.mas.api.smsapi.MsgFmt;
import com.jasson.mas.api.smsapi.SmsSendRequest;
import com.jasson.mas.api.smsapi.SmsSendResponse;
import com.jasson.mas.api.smsapi.SmsType;

public class SMSMessageHandler {

	public boolean sendSMS(List<String> phoneNOList, String content) {
		InputStream in = this.getClass().getResourceAsStream("/conf/sms.properties"); 
		Properties prop = new Properties(); 
		try { 
			prop.load(in); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		//System.out.println(prop.getProperty("sms.masApiPort")); 
		//System.out.println(prop.getProperty("sms.emaIP")); 
		//System.out.println(prop.getProperty("sms.appID")); 
		//System.out.println(prop.getProperty("sms.appPwd")); 
		//System.out.println(prop.getProperty("sms.xcode")); 
	
		SmsApiClient smsApiClient = null;

		String appID = prop.getProperty("sms.appID");// API组件标识ID
		String appPwd = prop.getProperty("sms.appPwd");// API组件密码
		String xcode = prop.getProperty("sms.xcode");// 短信扩展码
		int masApiPort = Integer.parseInt(prop.getProperty("sms.masApiPort"));// 端口
		String emaIP = prop.getProperty("sms.emaIP");// IP

		SmsApiClientHandler smsHandler = new SmsAPIClientHandlerImpl();
		try {
			smsApiClient = new SmsApiClientImpl(smsHandler, emaIP, masApiPort,
					appID, appPwd);

			// 设置是否自动重连到服务器(可以不需要设置，如果要设置要在start方法设置才有效)
			smsApiClient.setAutoConnect(true);

			// 设置自动重连服务器相隔时间(单位：秒), 默认为30秒(可以不需要设置，如果要设置要在start方法设置才有效)
			smsApiClient.setReConnectInterval(60);
			// 设置与服务连接超时时长，单位：millisecond(可以不需要设置，如果要设置需要在调用start方法前设置方可生效)
			smsApiClient.setConnectTimeout(100000);

			// 设置发送超时时长，单位：millisecond(可以不需要设置，如果要设置需要在调用star方法前设置方可生效)
			smsApiClient.setSendTimeout(1000000);
			smsApiClient.start();

			// 获得短信群发提交的数量限制
			// int ret = smsApiClient.getDestAddrsLimit();
			// System.out.println("短信群发提交的数量限制:"+ret);

			// 获取网关连接状态(Connect：连接正常, Disconnect：断连, NotConnect：没有连接, Other：其他)
			ConnectStatus connectStatus = smsApiClient.getConnStatusIAGW();
			if (!ConnectStatus.Connect.equals(connectStatus)) {
				System.out.println("网关未连接");
				return false;
			}

			// 计算短信条数和字数
			// SmsCount smsCount = smsApiClient.getSmsCount(content,
			// MsgFmt.GB2312, SmsType.Normal);
			// System.out.println("短信条数和字数:"+smsCount);

			// 获得扩展服务代码(组件短信扩展号码+流水号)长度
			// int xcodeLength = smsApiClient.getXCodeLength();
			// System.out.println("扩展服务代码:"+xcodeLength);

			// 发送普通短信
			// ＝＝＝＝＝＝构造发送短信对象开始，下面代码演示发送短信对象几个比较主要的属性值，其它的属性可以不设置，如果要设置可以参考sendSms方法中
			// SmsSendRequest参数＝＝＝
			SmsSendRequest smsSendRequest = new SmsSendRequest();

			smsSendRequest.destAddrs = phoneNOList;
			smsSendRequest.validTime = 10000; // 短信存活期，单位秒
			smsSendRequest.xCode = xcode; // 短信扩展码
			smsSendRequest.message = content; // 短信内容
			smsSendRequest.msgFormat = MsgFmt.GB2312; // 短信编码类型
			smsSendRequest.isNeedReport = true; // 短信是否需要状态报告
			smsSendRequest.priority = 0; // 短信网关优先级, 短信优先级大于0
											// 的整数,0为最高优先级，数字越大级别越低
			// Normal: 普通短信,Instant：免提短信,
			// Long：长短信,Structured：二进制短信,WapPush：WapPush 短信
			smsSendRequest.type = SmsType.Normal;
			smsSendRequest.appID = appID;
			// ＝＝＝＝＝＝构造发送短信对象结束＝＝＝＝＝＝＝
			SmsSendResponse smsSendResponse = smsApiClient
					.sendSms(smsSendRequest);
			System.out.println("submit successfully，requestID:"
					+ smsSendResponse.requestID);
			return true;
		} catch (Exception e) {
			System.out
					.println("SMS client API failed to work" + e.getMessage());
			return false;
		}
	}
}