package com.xpsoft.core.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.xpsoft.core.model.OnlineUser;
import com.xpsoft.core.sms.SmsClientStart;
import com.xpsoft.core.web.filter.SecurityInterceptorFilter;
import com.xpsoft.oa.model.flow.ProDefNotice;
import com.xpsoft.oa.model.flow.ProcessForm;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.system.AppFunction;
import com.xpsoft.oa.model.system.AppRole;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Company;
import com.xpsoft.oa.model.system.FunUrl;
import com.xpsoft.oa.model.system.SysConfig;
import com.xpsoft.oa.model.system.UserLog;
import com.xpsoft.oa.service.flow.ProDefNoticeService;
import com.xpsoft.oa.service.flow.ProcessRunService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.system.AppFunctionService;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.CompanyService;
import com.xpsoft.oa.service.system.FunUrlService;
import com.xpsoft.oa.service.system.SysConfigService;
import com.xpsoft.oa.service.system.UserLogService;

import edu.emory.mathcs.backport.java.util.Arrays;

public class AppUtil implements ApplicationContextAware {
	private static Log logger = LogFactory.getLog(AppUtil.class);

	private static Map configMap = new HashMap();

	private static ServletContext servletContext = null;

	private static Map<String, OnlineUser> onlineUsers = new LinkedHashMap();
	private static ApplicationContext appContext;
	private static Document lefMenuDocument = null;

	private static Document publicDocument = null;

	private static Set<String> publicMenuIds = null;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		appContext = applicationContext;
	}

	public static Document getLeftMenuDocument() {
		return lefMenuDocument;
	}

	public static void setLeftMenuDocument(Document doc) {
		lefMenuDocument = doc;
	}

	public static Document getPublicDocument() {
		return publicDocument;
	}

	public static void setPublicDocument(Document pubDoc) {
		publicDocument = pubDoc;
	}

	public static void setPublicMenuIds(Set<String> pubIds) {
		publicMenuIds = pubIds;
	}

	public static Object getBean(String beanId) {
		return appContext.getBean(beanId);
	}

	public static Map<String, OnlineUser> getOnlineUsers() {
		return onlineUsers;
	}

	public static void removeOnlineUser(String sessionId) {
		OnlineUser onlineUser = onlineUsers.get(sessionId);
		if(onlineUser != null){
			UserLogService userLogService = (UserLogService)getBean("userLogService");
			UserLog userLog = new UserLog();
			userLog.setAction(UserLog.ACTION_OUT);
			userLog.setActionTime(new Date());
			userLog.setFullname(onlineUser.getFullname());
			userLog.setUserId(onlineUser.getUserId());
			userLog.setUserName(onlineUser.getUsername());
			userLog.setStatus("{success:true}");
//			userLog.setIp(httpServletRequest.getRemoteAddr());
			userLogService.save(userLog);
		}
		onlineUsers.remove(sessionId);
	}

	public static void addOnlineUser(String sessionId, AppUser user) {
		if (!onlineUsers.containsKey(sessionId)) {
			OnlineUser onlineUser = new OnlineUser();
			onlineUser.setFullname(user.getFullname());
			onlineUser.setSessionId(sessionId);
			onlineUser.setUsername(user.getUsername());
			onlineUser.setUserId(user.getUserId());
			if (!user.getUserId().equals(AppUser.SUPER_USER)) {
				onlineUser.setDepPath("."
						+ user.getDepartment().getPath());
			}
			Set<AppRole> roles = user.getRoles();
			StringBuffer roleIds = new StringBuffer(",");
			for (AppRole role : roles) {
				roleIds.append(role.getRoleId() + ",");
			}
			onlineUser.setRoleIds(roleIds.toString());
			onlineUser.setTitle(user.getTitle());
			onlineUsers.put(sessionId, onlineUser);
		}
	}

	public static String getAppAbsolutePath() {
		return servletContext.getRealPath("/");
	}

	public static String getFlowFormAbsolutePath() {
		String path = (String) configMap.get("app.flowFormPath");
		if (path == null)
			path = "/WEB-INF/FlowForm/";
		return getAppAbsolutePath() + path;
	}

	public static void reloadSecurityDataSource() {
		SecurityInterceptorFilter securityInterceptorFilter = (SecurityInterceptorFilter) getBean("securityInterceptorFilter");
		securityInterceptorFilter.loadDataSource();
	}

	public static void init(ServletContext in_servletContext) {
		servletContext = in_servletContext;

		String filePath = servletContext.getRealPath("/WEB-INF/classes/conf/");

		//for change the config info to type of 'first load database info then load config file infos to override' on 2011-07-08 begin
		reloadSysConfig();
		//for change the config info to type of 'first load database info then load config file infos to override' on 2011-07-08 end
		Properties props = new Properties();
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(filePath + "/config.properties"));
			props.load(is);
			Iterator it = props.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				configMap.put(key, props.get(key));
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		/**
		 * 此处为清理掉配置文件的信息，从数据库加载配置信息
		 */
		//reloadSysConfig();
		
		CompanyService companyService = (CompanyService) getBean("companyService");
		List cList = companyService.findCompany();
		if (cList.size() > 0) {
			Company company = (Company) cList.get(0);
			configMap.put("app.logoPath", company.getLogo());
			configMap.put("app.companyName", company.getCompanyName());
		}

		String xslStyle = servletContext.getRealPath("/js/menu") + "/menu-left.xsl";
		Document doc = getOrignalMenuDocument();
		try {
			Document finalDoc = XmlUtil.styleDocument(doc, xslStyle);
			setLeftMenuDocument(finalDoc);
		} catch (Exception ex) {
			logger.error("menux.xml trasform has error:" + ex.getMessage());
		}

			String publicStyle = servletContext.getRealPath("/js/menu") + "/menu-public.xsl";
		try {
			Document publicDoc = XmlUtil.styleDocument(doc,	publicStyle);
			HashSet pubIds = new HashSet();
			Element rootEl = publicDoc.getRootElement();
			List idNodes = rootEl.selectNodes("/Menus//*");
			for (int i = 0; i < idNodes.size(); i++) {
				Element el = (Element) idNodes.get(i);
				Attribute attr = el.attribute("id");
				if (attr != null) {
					pubIds.add(attr.getValue());
				}
			}

			setPublicMenuIds(pubIds);
			setPublicDocument(publicDoc);
		} catch (Exception ex) {
			logger.error("menu.xml + menu-public.xsl transform has error:"
							+ ex.getMessage());
		}
		try {
//			SmsClientStart.SMSConnent();//启动短信
		} catch (Exception e) {
			logger.error("未连接上短信服务器");
		}
	}

	public static Document getOrignalMenuDocument() {
		String menuFilePath = servletContext.getRealPath("/js/menu")
				+ "/menu.xml";
		Document doc = XmlUtil.load(menuFilePath);
		return doc;
	}

	public static Document getGrantMenuDocument() {
		String xslStyle = servletContext.getRealPath("/js/menu") + "/menu-grant.xsl";
		Document finalDoc = null;
		try {
			finalDoc = XmlUtil.styleDocument(getOrignalMenuDocument(), xslStyle);
		} catch (Exception ex) {
			logger.error("menu.xml + menu-grant.xsl transform has error:"
							+ ex.getMessage());
		}
		return finalDoc;
	}

	public static Document getPublicMenuDocument() {
		return publicDocument;
	}

	public static Set<String> getPublicMenuIds() {
		return publicMenuIds;
	}

	public static void synMenu() {
		AppFunctionService appFunctionService = (AppFunctionService) getBean("appFunctionService");
		FunUrlService funUrlService = (FunUrlService) getBean("funUrlService");

		List funNodeList = getOrignalMenuDocument().getRootElement().selectNodes("/Menus/Items//Item/Function");

		for (int i = 0; i < funNodeList.size(); i++) {
			Element funNode = (Element) funNodeList.get(i);

			String key = funNode.attributeValue("id");
			String name = funNode.attributeValue("text");

			AppFunction appFunction = appFunctionService.getByKey(key);

			if (appFunction == null){
				appFunction = new AppFunction(key, name);
				appFunctionService.save(appFunction);
			}else if(!appFunction.getFunName().equals(name)){
				appFunction.setFunName(name);
				appFunctionService.save(appFunction);
			}

			List urlNodes = funNode.selectNodes("./url");

			

			for (int k = 0; k < urlNodes.size(); k++) {
				Node urlNode = (Node) urlNodes.get(k);
				String path = urlNode.getText();
				FunUrl fu = funUrlService.getByPathFunId(path, appFunction.getFunctionId());
				if (fu == null) {
					fu = new FunUrl();
					fu.setUrlPath(path);
					fu.setAppFunction(appFunction);
					funUrlService.save(fu);
				}
			}
		}
	}

	public static void syncRealMenu(){
		String xslStyle = servletContext.getRealPath("/js/menu") + "/menu-left.xsl";
		Document doc = getOrignalMenuDocument();
		try {
			Document finalDoc = XmlUtil.styleDocument(doc, xslStyle);
			setLeftMenuDocument(finalDoc);
		} catch (Exception ex) {
			logger.error("menux.xml trasform has error:" + ex.getMessage());
		}

			String publicStyle = servletContext.getRealPath("/js/menu") + "/menu-public.xsl";
		try {
			Document publicDoc = XmlUtil.styleDocument(doc,	publicStyle);
			HashSet pubIds = new HashSet();
			Element rootEl = publicDoc.getRootElement();
			List idNodes = rootEl.selectNodes("/Menus//*");
			for (int i = 0; i < idNodes.size(); i++) {
				Element el = (Element) idNodes.get(i);
				Attribute attr = el.attribute("id");
				if (attr != null) {
					pubIds.add(attr.getValue());
				}
			}

			setPublicMenuIds(pubIds);
			setPublicDocument(publicDoc);
		} catch (Exception ex) {
			logger.error("menu.xml + menu-public.xsl transform has error:"
							+ ex.getMessage());
		}
	}
	
	public static boolean getIsSynMenu() {
		String synMenu = (String) configMap.get("isSynMenu");

		return "true".equals(synMenu);
	}

	public static Map getSysConfig() {
		return configMap;
	}

	public static void reloadSysConfig() {
//		configMap.clear();
		SysConfigService sysConfigService = (SysConfigService) getBean("sysConfigService");
		List<SysConfig> list = sysConfigService.getAll();
		for (SysConfig conf : list)
			configMap.put(conf.getConfigKey(), conf.getDataValue());
	}

	public static String getCompanyLogo() {
		String defaultLogoPath = "/images/yh-logo.png";
		String path = (String) configMap.get("app.logoPath");
		if (StringUtils.isNotEmpty(path)) {
			defaultLogoPath = "/attachFiles/" + path;
		}
		return defaultLogoPath;
	}

	public static String getCompanyName() {
		String defaultName = "苏州朗捷通智能科技有限公司";
		String companyName = (String) configMap.get("app.companyName");
		if (StringUtils.isNotEmpty(companyName)) {
			defaultName = companyName;
		}
		return defaultName;
	}
	
	public static String getRoleName(){
		Set<AppRole> roles = ContextUtil.getCurrentUser().getRoles();
		StringBuffer roleNames = new StringBuffer();
		for (AppRole role : roles) {
			roleNames.append(role.getRoleName() + ",");
		}
		return roleNames.toString();
	}
	
	public static String getPropertity(String key) {
		try{
			return (String) configMap.get(key);
		}catch(RuntimeException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void sendSysShortMsg(String piId,AppUser flowStartUser){
		
		
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		ShortMessageService smService = (ShortMessageService) AppUtil.getBean("shortMessageService");
		ProDefNoticeService proDefNoticeService = (ProDefNoticeService)AppUtil.getBean("proDefNoticeService");
		AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
		ProcessRun processRun=processRunService.getByPiId(piId);				
		ProDefNotice proDefNotice = proDefNoticeService.get(processRun.getProDefinition().getDefId());
		//不重复的发送userId列表
		List<String> userIdList = SetUniqueList.decorate(new ArrayList<String>());
		if(proDefNotice!=null){
			if(StringUtils.isNotBlank(proDefNotice.getNoticeUserIds())){
				userIdList.addAll(Arrays.asList(proDefNotice.getNoticeUserIds().split(",")));
			}
			if(StringUtils.isNotBlank(proDefNotice.getNoticeDepIds())){
				for(String depId:proDefNotice.getNoticeDepIds().split(",")){
					if(StringUtils.isNotBlank(depId)){
						List<AppUser> userList = appUserService.findByDepId(Long.valueOf(depId));
						for(AppUser user:userList){
							userIdList.add(user.getUserId().toString());
						}
					}
				}
			}
			if(StringUtils.isNotBlank(proDefNotice.getNoticeRoleIds())){
				for(String roleId:proDefNotice.getNoticeRoleIds().split(",")){
					if(StringUtils.isNotBlank(roleId)){
						List<AppUser> userList = appUserService.findByRoleId(Long.valueOf(roleId));
						for(AppUser user:userList){
							userIdList.add(user.getUserId().toString());
						}
					}
				}
			}
		}
		for (Object obj : processRun.getProcessForms()) {
			ProcessForm processForm=(ProcessForm)obj;
			userIdList.add(processForm.getCreatorId().toString());			
		}
		//发系统短消息
		for (String userIdT : userIdList) {
			if(userIdT.equals(flowStartUser.getUserId().toString())){
				smService.save(AppUser.SYSTEM_USER, userIdT, "您的【"+processRun.getSubject()+"】审批单已通过最终审批！", ShortMessage.MSG_TYPE_SYS);
			}else{
				smService.save(AppUser.SYSTEM_USER, userIdT, flowStartUser.getFullname()+"的【"+processRun.getSubject()+"】审批单已通过最终审批！", ShortMessage.MSG_TYPE_SYS);
			}
		}
		
	}
	
	public static void sendMsgByRole(String roleName,String message){
		AppUserService appUserService = (AppUserService)AppUtil.getBean("appUserService");
		ShortMessageService smService = (ShortMessageService) AppUtil.getBean("shortMessageService");
		List<AppUser> userList = appUserService.findByRoleName(roleName);
		//不重复的发送userId列表
		List<String> userIdList = SetUniqueList.decorate(new ArrayList<String>());
		for(AppUser user:userList){
			userIdList.add(user.getUserId().toString());
		}
		//发系统短消息
		for (String userIdT : userIdList) {
			smService.save(AppUser.SYSTEM_USER, userIdT, message, ShortMessage.MSG_TYPE_SYS);
		}
	}
	
	public static void sendMsgByUserIds(Collection<AppUser> collectionUsers,String message){
		ShortMessageService smService = (ShortMessageService) AppUtil.getBean("shortMessageService");
		//不重复的发送userId列表
		List<String> userIdList = SetUniqueList.decorate(new ArrayList<String>());
		for(AppUser user:collectionUsers){
			userIdList.add(user.getUserId().toString());
		}
		//发系统短消息
		for (String userIdT : userIdList) {
			smService.save(AppUser.SYSTEM_USER, userIdT, message, ShortMessage.MSG_TYPE_SYS);
		}
	}
}
