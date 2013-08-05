package com.xpsoft.core.sms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SmsClientStart {
	private static Log logger = LogFactory.getLog(SmsClientStart.class);
	
	public static void SMSConnent() {
		//系统启动时启动软件监听器
		SMSFactory.receive();
		logger.info("短信MAS监听器已启动！");
	}
}