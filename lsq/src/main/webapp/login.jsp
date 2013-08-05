<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="com.xpsoft.oa.model.system.UserAgent"%>
<%@page import="com.xpsoft.core.util.UserAgentUtil"%>

<%
	Log log = LogFactory.getLog(this.getClass());
	//ip
	String ip = request.getHeader("x-forwarded-for");
	if (ip == null || ip.length() == 0
			|| "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0
			|| "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0
			|| "unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
	}
	String userAgentStr = request.getHeader("User-Agent");
	UserAgent userAgent = UserAgentUtil.getUserAgent(userAgentStr);
	if (null == userAgent) {
		log.warn("无法匹配客户端系统类型" + "访问地址：" + ip + "    User-Agent:"
				+ userAgentStr);
	} else {
		if ("Phone".equals(userAgent.getPlatformType())) {
			log.warn("[Phone]" + "访问地址：" + ip);
			response.sendRedirect("mobile/m_login.jsp");//移动平台跳转
		} else if ("Pad".equals(userAgent.getPlatformType())) {
			log.warn("[Pad]" + "访问地址：" + ip);
			response.sendRedirect("mobile/m_login.jsp");//移动平台跳转
		} else if ("Linux".equals(userAgent.getPlatformType())) {
			log.warn("[Linux]" + "访问地址：" + ip);
		} else if ("Windows".equals(userAgent.getPlatformType())) {
			log.warn("[Windows]" + "访问地址：" + ip);
			//response.sendRedirect("mobile/m_login.jsp");//移动平台跳转
		} else if ("Mac OS".equals(userAgent.getPlatformType())) {
			log.warn("[Mac OS]" + "访问地址：" + ip);
		} else {
			log.warn("无法匹配客户端系统类型" + "访问地址：" + ip);
		}
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 
<title>网上办公系统</title>
 -->
		<title>欢迎登录<%=AppUtil.getCompanyName()%>协同办公系统</title>
		<link rel="Shortcut Icon"
			href="<%=request.getContextPath()%>/images/yh.ico" />
		<link rel="Bookmark"
			href="<%=request.getContextPath()%>/images/yh.ico" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/ext3/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/ext3/resources/css/ext-patch.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/login.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/admin.css" />
		<%
			response.addHeader("__timeout", "true");
			String codeEnabled = (String) AppUtil.getSysConfig().get(
					"codeConfig");
			if (StringUtils.isEmpty(codeEnabled)) {//若当前数据库没有配置验证码参数
				codeEnabled = "1";//代表需要输入
			}
			request.setAttribute("codeEnabled", new Integer(codeEnabled));
			HttpSession sessionTmp=request.getSession(true);//解决 java.lang.IllegalStateException: getAttribute: Session already invalidated
		%>
		<script type="text/javascript">
var __ctxPath = "<%=request.getContextPath()%>";
var __loginImage = __ctxPath + "<%=AppUtil.getCompanyLogo()%>";
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/ext3/adapter/ext/ext-base.gzjs">
		</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/ext3/ext-all.gzjs">
		</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/forgotPWD.js">
		</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/ext3/ext-lang-zh_CN.js">
		</script>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px; //
	-webkit-transform: rotate(-30deg);
}
-->
</style>
		<script type="text/javascript">
//验证码显示标志初始化
var showCode = ${codeEnabled eq 1}||(${null!=sessionTmp.showCode}&&${sessionTmp.showCode eq true});
			function checkForm() {
				var checkBlank = true;
<%--				checkBlank = Ext.getCmp("namefield").isValid();--%>
<%--				checkBlank = Ext.getCmp("pwdfield").isValid();--%>
<%--				<c:if test="${codeEnabled eq 1}">--%>
<%--							checkBlank=Ext.getCmp("codefield").isValid();			--%>
<%--				</c:if>--%>
				
				if(showCode){
					checkBlank=Ext.getCmp("codefield").isValid();	
				}
				
				
				
				if(!(Ext.getCmp("pwdfield").isValid()&&Ext.getCmp("namefield").isValid()&&checkBlank)){
					document.getElementById("msg").innerHTML="请正确输入登陆信息！";
					return;
				}
				var username = Ext.getCmp("namefield").getValue();
				var password = Ext.getCmp("pwdfield").getValue();
				var checkCode = "";
				/*if(username==""){
						Ext.Msg.alert("提示", "用户名不能为空！");
						return;
				}
				if(password==""){
					Ext.Msg.alert("提示", "密码不能为空！");
						return;
				}*/
<%--				<c:if test="${codeEnabled eq 1}">--%>
<%--					checkCode = Ext.getCmp("codefield").getValue();--%>
<%--					/*if(checkCode==""){--%>
<%--							Ext.Msg.alert("提示", "验证码不能为空！");--%>
<%--							return;--%>
<%--					}*/--%>
<%--				</c:if>--%>
					
				if(showCode){
					checkCode = Ext.getCmp("codefield").getValue();
				}
					
				var rememberMe = document.getElementById("rememberMe").checked;
				Ext.Ajax.request({
					url : __ctxPath + "/login.do",
					method :"Post",
					params : {username:username,
						password:password,
						checkCode:checkCode,
						'_spring_security_remember_me':rememberMe
						},
					success : function(d, g) {
						var f = Ext.util.JSON.decode(d.responseText);
						handleLoginResult(f);
					}
				});
		}
		
		function loginSuccess(){
			var b = new Ext.ProgressBar({
						text : "正在登录..."
					});
					b.show();
					window.location.href = __ctxPath + "/index.jsp";
		}
			
		function handleLoginResult(a) {
			if (a.success) {
				if(<%=AppUtil.getPropertity("app.debug")%>){
					loginSuccess()
				}else{
					if(a.modifyPWD){
					Ext.MessageBox.show({
										title : '提示',
										msg : '请在2013年7月20号之前修改您的密码，密码中必须含有字母和数字',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.INFO,
										fn : loginSuccess
									});
					
					}else{
						loginSuccess()
					}
				}
				
			} else {
				document.getElementById("msg").innerHTML=a.msg;
				Ext.getCmp("pwdfield").setValue('');
				if(a.showCode){//由三次改成一次
					//显示验证码
					var l = document.getElementById("loginCode");
					showCode=true;//开启验证码检测
					if(null==l||'undefined'==l){
						
						var newDiv='<div '+
								' style="border-style: none; text-align: left; padding-top: 5px; margin-left: 30px;">'+
								'<span '+
									'style="font-size: 18px; font-family: \'微软雅黑\'; color: #838383; float: left;">验证：</span>'+
								'<div id="checkCode" style="float: left;"></div>'+
								'<div id="loginCode">'+
									'<a href="javascript:refeshCode()"><img border="0"'+
											' height="30" width="150"'+
											' src="'+ __ctxPath +'/CaptchaImg" /> </a>'+
								'</div>'+
							'</div>'
						
						AppAfter('pwdDiv',newDiv);
							
						var checkCode = new Ext.form.TextField({id:"codefield",allowBlank:false,height:30,width:80,name:"checkCode",renderTo :"checkCode"});
					}else{
						refeshCode();
					}
				}
			}
		}
		
		//向指定结点后插入新结点函数  
	   function AppAfter(nodeId, str)      
	   {  
	      var node = document.getElementById(nodeId);  
	      var newNode = document.createElement('div');
	      		newNode.innerHTML=str;
	      //如果存在上一级结点  
	      if(node.parentNode)      
	      {  
	         //如果存在下一子结点  
	         if(node.nextSibling)    
	         {  
	            //在下一子结点前插入子结点  
	            node.parentNode.insertBefore(newNode, node.nextSibling);  
	         }else{  
	            //如果没有下一子结点向后追加子结点  
	            node.parentNode.appendChild(newNode);  
	         }  
	      }  
	   }  
		
		function refeshCode() {
			var a = document.getElementById("loginCode");
			a.innerHTML='<a href="javascript:refeshCode()"><img border="0" height="30" width="150" src="' + __ctxPath
					+ '/CaptchaImg?rand=' + Math.random() + '"/></a>';
		}
		
		Ext.onReady(function(){	
		    Ext.QuickTips.init();
		    Ext.state.Manager.setProvider(new Ext.state.CookieProvider()); 
			var username = new Ext.form.TextField({id:"namefield",allowBlank:false,height:30,width:210,name:"username",renderTo :"username"});
			var password = new Ext.form.TextField({id:"pwdfield",allowBlank:false,height:30,width:210,name:"password",value:'1',inputType:"password",renderTo :"password"});
			if(${codeEnabled eq 1}||(${null!=sessionTmp.showCode}&&${sessionTmp.showCode eq true})){
				var checkCode = new Ext.form.TextField({id:"codefield",allowBlank:false,height:30,width:80,name:"checkCode",renderTo :"checkCode"});
			}
		});

		
</script>
		<link href="<%=request.getContextPath()%>/css/css.css"
			rel="stylesheet" type="text/css" />
		<style type="text/css">
.bottom {
	height: 75px;
	background: #269cdf;
	position: fixed;
	_position: absolute;
	z-index: 39;
	bottom: 0;
	right: 0;
	_top: expression_r(eval_r(document.documentElement.scrollTop +    
		    document.documentElement.clientHeight-this.offsetHeight-0) );
	width: 100%;
	color: #ffffff;
	padding-top: 20px;
}
</style>
	</head>

	<body>



		<center>
			<form id="loginForm" method="post" onsubmit="checkForm()">
				<div
					style="width: 1000px; height: 560px; background: url(images/login/background.png); padding-top: 105px; margin-top: 50px;">
					<div id="logoArea"
						style="width: 350px; height: 70px; border-style: none;">
						<div style="float: left; margin-top: 10px; margin-left: 5px;">
							<img src="images/login/ljt.png" width='60'>
						</div>
						<div
							style="float: left; margin-top: 20px; margin-left: 5px; border-style: none;">
							<span
								style="font-size: 25px; font-family: '微软雅黑'; color: #2080b2;">朗捷通智能协同办公</span>
						</div>
					</div>
					<div id="loginArea"
						style="width: 350px; height: 190px; border-style: none;">
						<div
							style="border-style: none; text-align: left; padding-top: 15px; margin-left: 30px;">
							<span
								style="font-size: 18px; font-family: '微软雅黑'; color: #838383; float: left;">账号：</span>
							<div id="username"></div>
						</div>
						<div id="pwdDiv"
							style="border-style: none; text-align: left; padding-top: 5px; margin-left: 30px;">
							<span
								style="font-size: 18px; font-family: '微软雅黑'; color: #838383; float: left;">密码：</span>
							<div id="password"></div>
						</div>
						<c:if
							test="${(codeEnabled eq 1)||(null!=sessionTmp.showCode&&sessionTmp.showCode eq true)}">
							<div
								style="border-style: none; text-align: left; padding-top: 5px; margin-left: 30px;">
								<span
									style="font-size: 18px; font-family: '微软雅黑'; color: #838383; float: left;">验证：</span>
								<div id="checkCode" style="float: left;"></div>
								<div id="loginCode">
									<a href="javascript:refeshCode()"><img border="0"
											height="30" width="150"
											src="<%=request.getContextPath()%>/CaptchaImg" /> </a>
								</div>
							</div>
						</c:if>

						<div
							style="float: right; width: 350px; height: 60px; border-style: none;">
							<div id="msg"
								style="float: left; margin-top: 20px; margin-left: 85px; color: red; font-size: 12px; font-family: '微软雅黑';"></div>
							<a href="#"><img src="images/login/login_btn.png" width='53'
									style="float: right; margin-right: 50px; margin-top: 20px;"
									onclick="checkForm();">
							</a>

						</div>
						<div style="margin-top: 20px; margin-right: 0px;">
						<span id="forgotPassword"
							style="float: left;margin-left: 80px; font-size: 12px; font-family: '微软雅黑';">
							<a href="javascript:void(0)" onclick=" new forgotPWD().show()" >忘记密码?</a>
							
						</span>
						<span id="remberme"
							style="loat: right;margin-right: -30px;font-size: 12px; font-family: '微软雅黑';">
							<input type="checkbox" id="rememberMe"
								name="_spring_security_remember_me" value="checkbox" />
							记住密码
						</span>
						
						</div>
						
					</div>
					<div id="textArea"
						style="width: 350px; height: 50px; border-style: none; margin-top: 40px; text-align: left; padding-top: 10px;">
						<div
							style="font-size: 12px; font-family: '微软雅黑'; color: red; margin-left: 12px;">
							输入密码请注意保护您的密码安全，防止病毒或者木马窃取您的账户信息
						</div>
					</div>
				</div>
				<div class="bottom">
					<span style="font-size: 12px; font-family: '微软雅黑'; color: #65c2f6;">Design
						By 怡和信息中心</span>
				</div>
			</form>
		</center>
		<script>
document.getElementById('loginForm').onkeydown = function(e) {
	if (!e)
		e = window.event;//火狐中是window.event
	if ((e.keyCode || e.which) == 13) {
		checkForm();
	}
}
</script>
	</body>
</html>
