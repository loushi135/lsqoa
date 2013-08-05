<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	response.addHeader("__timeout","true");
	String codeEnabled=(String)AppUtil.getSysConfig().get("codeConfig");
	if(StringUtils.isEmpty(codeEnabled)){//若当前数据库没有配置验证码参数
		codeEnabled="1";//代表需要输入
	}
	request.setAttribute("codeEnabled", new Integer(codeEnabled));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1,user-scalable=0" />    
	<jsp:include page="includeJquery.jsp"></jsp:include>
	<jsp:include page="includeJqueryMobile.jsp"></jsp:include>
    <title><%=AppUtil.getCompanyName()%>办公协同管理系统</title>
    <script type="text/javascript">
			var __ctxPath="<%=request.getContextPath()%>";
	</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/m_login.js"></script>
  </head>
  
  <body>
	<!-- Home -->
	<div data-role="page" id="page1">
	    <div data-role="content">
	    	<div style="width: 100%; height: 100px; position: relative; background-color: #fbfbfb;">
	            <img src="<%=request.getContextPath()%>/images/yh-logo.png" alt="image" >
	        </div>
	        <form  id="loginForm" name="loginForm">
	            <div data-role="fieldcontain">
	                <fieldset data-role="controlgroup">
	                    <label for="textinput1">
	                        	用户名
	                    </label>
	                    <input name="username" id="username"  type="text" class="required">
	                </fieldset>
	            </div>
	            <div data-role="fieldcontain">
	                <fieldset data-role="controlgroup">
	                    <label for="textinput3">
	                        	密&nbsp;&nbsp;&nbsp;&nbsp;码
	                    </label>
	                    <input name="password" id="password"  type="password" class="required">
	                </fieldset>
	            </div>
	            <c:if test="${codeEnabled eq 1}">
	            	<div data-role="fieldcontain">
		                <fieldset data-role="controlgroup">
		                    <label for="textinput3">
		                        	验证码
		                    </label>
		                    <input name="checkCode" id="checkCode"  type="text" class="required">
		                    <div id="loginCode" style="float: left;">
		                    	<img border="0" height="30" width="150" src="<%=request.getContextPath()%>/CaptchaImg"/>
		                    </div>
		                    <div style="float: left;margin-top:5px;">
		                    	<a href="javascript:refeshCode()">换一张</a>
		                    </div>
		                </fieldset>
		            </div>
	            </c:if>
	            <div id="checkboxes1" data-role="fieldcontain">
		            <fieldset data-role="controlgroup" data-type="vertical">
		                <input id="rembPwd"  data-theme="c" type="checkbox">
		                <label for="rembPwd">
		                    	记住密码
		                </label>
		            </fieldset>
		        </div>
	            <input type="button" id="submit" onclick="submitForm();" data-theme="b" data-icon="forward" data-iconpos="left" value="登陆">
	            <span id="failureMsg" style="color: red;"></span>
	        </form>
	    </div>
	</div>
  	
  	  
  </body>
</html>
