package com.xpsoft.core.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.xpsoft.core.sms.SMSFactory;
import com.xpsoft.core.sms.SMSMessageHandler;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.system.AppUserService;

public class SendSmsUtil {

	/**
	 * 
	 * @param userIds 接收的用户
	 * @param msgcontent 消息内容
	 */
	public static void sendMessage(String userIds,String msgcontent){
		AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
		//发送短信
	    List<String> mobileList = new ArrayList<String>();
		if(StringUtils.isNotBlank(userIds)){
			if(userIds.contains(",")){
				String[] ids = userIds.split(",");
				if(ids!=null&&ids.length>0){
					for(String id:ids){
						AppUser appUser = appUserService.get(Long.valueOf(id));
						mobileList.add(appUser.getMobile());
					}
				}
			}else{
				AppUser appUser = appUserService.get(Long.valueOf(userIds));
				mobileList.add(appUser.getMobile());
			}
//			SMSMessageHandler sms = new SMSMessageHandler();
//		    Boolean flag = sms.sendSMS(mobileList, msgcontent);
		    Boolean flag = SMSFactory.send(mobileList, msgcontent);
		    if(flag){
		    	System.out.println("发送成功！");
		    }else{
		    	System.out.println("发送失败！");
		    }
		}
	}
}
