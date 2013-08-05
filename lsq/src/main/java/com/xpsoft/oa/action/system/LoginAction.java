package com.xpsoft.oa.action.system;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import nl.captcha.Captcha;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;

import com.xpsoft.core.util.StringUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.LoginSituation;
import com.xpsoft.oa.model.system.SysConfig;
import com.xpsoft.oa.model.system.UserLog;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.LoginSituationService;
import com.xpsoft.oa.service.system.SysConfigService;
import com.xpsoft.oa.service.system.UserLogService;

public class LoginAction extends BaseAction {
	private AppUser user;
	private String username;
	private String password;
	private String checkCode;
	private String key = "RememberAppUser";

	@Resource
	private AppUserService userService;

	@Resource
	private SysConfigService sysConfigService;

	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager = null;

	@Resource
	private UserLogService userLogService;
	@Resource
	private LoginSituationService loginSituationService;
	@Resource
	private AppUserService appUserService;

	public String login() {
		StringBuffer msg = new StringBuffer("{msg:'");

		SysConfig sysConfig = this.sysConfigService.findByKey("codeConfig");

		Captcha captcha = (Captcha) getSession().getAttribute("simpleCaptcha");
		Boolean login = Boolean.valueOf(false);
		boolean checkCode = true;// 验证码标志

		String newPassword = null;

		if ((!"".equals(this.username)) && (this.username != null)
				&& StringUtils.isNotEmpty(this.password)) {

			if (sysConfig.getDataValue().equals(SysConfig.CODE_OPEN)
					|| showCodeOrNot()) {
				if (captcha == null) {
					msg.append("请刷新验证码再登录.'");
				} else if (!captcha.isCorrect(this.checkCode)) {
					msg.append("验证码不正确.'");
					checkCode = false;
				}
			}

			if (checkCode) {
				setUser(this.userService.findByUserName(this.username));

				if (this.user != null) {
					if (this.user.getStatus().shortValue() == 0) {
						msg.append("此用户已被禁用,联系管理员'");
					} else {
						newPassword = StringUtil.encryptSha256(this.password);

						if (this.user.getPassword().equalsIgnoreCase(
								newPassword)) {
							if (this.user.getStatus().shortValue() == 1
									|| this.user.getStatus().shortValue() == 3)
								login = Boolean.valueOf(true);
							else
								msg.append("此用户已被禁用.'");
						} else
							msg.append("用户名或密码不正确.'");
					}
				} else
					msg.append("用户名或密码不正确.'");

			}
		} else {
			msg.append("用户名或密码不能为空.'");
		}
		if (login.booleanValue()) {

			getSession().setAttribute("logInErrCount", 0);// 清空sesion内累计的登陆错误数
			getSession().setAttribute("showCode", false);

			// 解决 ----- 一小时内登陆正确了没有错误清除记录或做其它处理BUG

			loginSituationService.clearHourErr(user.getUserId());

			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					this.username, this.password);
			SecurityContext securityContext = SecurityContextHolder
					.getContext();
			securityContext.setAuthentication(this.authenticationManager
					.authenticate(authRequest));
			SecurityContextHolder.setContext(securityContext);
			getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME",
					this.username);
			String rememberMe = getRequest().getParameter(
					"_spring_security_remember_me");
			if ((rememberMe != null) && (rememberMe.equals("true"))) {
				long tokenValiditySeconds = 1209600L;
				long tokenExpiryTime = System.currentTimeMillis()
						+ tokenValiditySeconds * 1000L;

				String signatureValue = DigestUtils.md5Hex(this.username + ":"
						+ tokenExpiryTime + ":" + this.user.getPassword() + ":"
						+ this.key);

				String tokenValue = this.username + ":" + tokenExpiryTime + ":"
						+ signatureValue;
				String tokenValueBase64 = new String(Base64
						.encodeBase64(tokenValue.getBytes()));
				getResponse().addCookie(
						makeValidCookie(tokenExpiryTime, tokenValueBase64));
			}

			//
			if(password.matches("^(?![0-9]+$)(?![a-zA-Z]+$).+$")){
				getSession().setAttribute("modifyPWD", false);
				setJsonString("{success:true,modifyPWD:false}");
			}else{
				getSession().setAttribute("modifyPWD", true);
				setJsonString("{success:true,modifyPWD:true}");
			}
			
			// User-Agent
			String userAgentStr = getRequest().getHeader("User-Agent");
			UserLog userLog = new UserLog();
			userLog.setAction(UserLog.ACTION_IN);
			userLog.setActionTime(new Date());
			userLog.setFullname(this.user.getFullname());
			userLog.setUserId(this.user.getUserId());
			userLog.setUserName(this.username);
			userLog.setStatus("{success:true}");
			userLog.setIp(getRequest().getRemoteAddr());
			userLog.setUserAgentStr(userAgentStr);
			userLogService.save(userLog);
		} else {
			if (showCodeOrNot()) {
				if (null != user && (msg.substring(5).contains("用户名或密码不正确.'"))) {
					LoginSituation loginS = new LoginSituation();
					loginS.setUserId(user.getUserId());
					loginS.setMessage(msg.substring(5).toString());
					loginS.setUserName(this.username);
					loginS.setIp(getRequest().getRemoteAddr());
					loginS.setActionTime(new Date());
					loginSituationService.save(loginS); // 一小时内登陆对了没有清除记录或做其它处理BUG

					Long count = loginSituationService.getMessageCount(user
							.getUserId());
					// msg.delete(0, msg.length());
					// msg.append("{msg:'密码不正确.");
					msg.deleteCharAt(msg.length() - 1);
					if (count > 2L) {// 显示验证码连续一小时内登陆三次后用户被禁用
						AppUser appUser = appUserService.get(Long.valueOf(user
								.getUserId()));
						appUser.setStatus((short) 0);
						appUserService.save(appUser);
						msg.append("<br>此用户已被禁用,联系管理员.'");
					} else {

						msg.append("<br>您还有" + (3 - count) + "次机会.'");
					}
				}
			}
			msg.append(",failure:true");

			int allows = 1;// 登陆三次错误时显示验证码,改成一次
			
			Integer logInErrCount =null;
			if (null == getSession().getAttribute("logInErrCount")) {
				getSession().setAttribute("logInErrCount", 1);
				getSession().setAttribute("showCode", false);
				logInErrCount=1;
			}else{
				logInErrCount = (Integer) getSession().getAttribute("logInErrCount");
				getSession().setAttribute("logInErrCount", logInErrCount + 1);
			}
			
			if (logInErrCount >= allows) {
				getSession().setAttribute("showCode", true);
			}
			

			msg.append(",showCode:"+ getSession().getAttribute("showCode")+ "}");

			setJsonString(msg.toString());

			// User-Agent
			String userAgentStr = getRequest().getHeader("User-Agent");

			UserLog userLog = new UserLog();
			userLog.setAction(UserLog.ACTION_IN);
			userLog.setActionTime(new Date());
			userLog.setUserName(this.username);
			userLog.setStatus(msg.toString());
			userLog.setIp(getRequest().getRemoteAddr());
			userLog.setUserAgentStr(userAgentStr);
			userLogService.save(userLog);
		}
		return "success";
	}

	protected Cookie makeValidCookie(long expiryTime, String tokenValueBase64) {
		HttpServletRequest request = getRequest();
		Cookie cookie = new Cookie("SPRING_SECURITY_REMEMBER_ME_COOKIE",
				tokenValueBase64);
		cookie.setMaxAge(157680000);
		cookie.setPath(org.springframework.util.StringUtils.hasLength(request
				.getContextPath()) ? request.getContextPath() : "/");
		return cookie;
	}

	public AppUser getUser() {
		return this.user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckCode() {
		return this.checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	/**
	 * 检测是否在验证三次后开启验证码
	 * 
	 * @return
	 */
	public boolean showCodeOrNot() {
		Boolean show = (Boolean) getSession().getAttribute("showCode");
		if (null == show || (!show)) {
			return false;
		} else {
			return true;
		}
	}
}
