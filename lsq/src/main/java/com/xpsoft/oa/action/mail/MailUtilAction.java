package com.xpsoft.oa.action.mail;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tebie.applib.api.APIContext;
import tebie.applib.api.IClient;

import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;

public class MailUtilAction extends BaseAction{
	private static Log logger=LogFactory.getLog(MailUtilAction.class);
	// rista@goldmantis.com abc1234567
	// Socket socket = new Socket("172.16.0.6", 6195);
	private Map<String, String> map = new HashMap<String, String>();
	
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public static Socket socket;
	public static IClient getIClinetUtil(){
		IClient client = null;
		try {
			socket = new Socket("172.16.0.6", 6195);
			client = APIContext.getClient(socket);
		} catch (UnknownHostException e) {
			logger.info(e);
//			e.printStackTrace();
		} catch (IOException e) {
			logger.info(e);
//			e.printStackTrace();
		}
		return client;
	}
	public String checkSid() throws UnknownHostException, IOException{
		String sid = getRequest().getParameter("sid");
		IClient client = MailUtilAction.getIClinetUtil();
		if(client == null){
			return SUCCESS;
		}
		APIContext context = client.sesRefresh(sid.substring(sid.indexOf("=")+1, sid.indexOf("&")));
		if(context.getRetCode() == APIContext.RC_NORMAL){
			jsonString = "{success:true,sid:'"+sid+"'}";
		}else{
			String userEmail = ContextUtil.getCurrentUser().getEmail();
			context = client.userLoginEx(userEmail, "face=XJS");
			jsonString = "{success:true,sid:'"+context.getResult()+"'}";
		}
		return SUCCESS;
	}
	public String mbox() throws UnknownHostException, IOException{
		String userEmail = ContextUtil.getCurrentUser().getEmail();
		IClient client = MailUtilAction.getIClinetUtil();
		if(client == null){
			map.put("newmsgcnt_errorInfo", "连接错误，请确认您的邮箱是正确的，如果有疑问请联系管理员");
			return "display";
		}
		APIContext context;
		context = client.getAttrsEx(userEmail,"mbox_msgcnt");
		if(StringUtils.isNotBlank(context.getResult())){
			map.put("mbox_msgcnt", context.getResult().split("=")[1]);
		}
		if(StringUtils.isNotBlank(context.getErrorInfo())){
			map.put("msgcnt_errorInfo", context.getErrorInfo());
		}
		context = client.getAttrsEx(userEmail,"mbox_newmsgcnt");
		if(StringUtils.isNotBlank(context.getResult())){
			map.put("mbox_newmsgcnt", context.getResult().split("=")[1]);
		}
		if(StringUtils.isNotBlank(context.getErrorInfo())){
			map.put("newmsgcnt_errorInfo", context.getErrorInfo());
		}
		context = client.userLoginEx(userEmail, "face=XJS");
		if(StringUtils.isNotBlank(context.getResult())){
			map.put("sid",context.getResult());
		}
		client.close();
		return "display";
	}
}
