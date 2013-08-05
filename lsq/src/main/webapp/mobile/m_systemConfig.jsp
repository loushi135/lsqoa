<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>设置</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1,user-scalable=0">
	<jsp:include page="includeJquery.jsp"></jsp:include>
	<jsp:include page="includeJqueryMobile.jsp"></jsp:include>
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/m_systemConfig.js"></script>
  </head>
  
  <body>
    <!-- Home -->
	<div data-role="page" id="page1">
		<div data-role="header" id="header">
			<h1>设置</h1>
			<a href="<%=request.getContextPath()%>/index.jsp?change=manual" data-direction="reverse"  data-ajax="false">切换传统版</a>
			<a href="<%=request.getContextPath()%>/j_logout.do" data-icon="back" data-direction="reverse" class="ui-btn-right" data-ajax="false">退出</a>
			<div data-role="navbar">
				<ul>
					<li><a href="<%=request.getContextPath()%>/system/noticeListMobile.do" data-icon="Grid" data-ajax="false">公告</a></li>
					<li><a href="<%=request.getContextPath()%>/system/taskListMobile.do" data-icon="Star" data-ajax="false">待办事项</a></li>
					<li><a href="#" data-icon="Grid">待开放</a></li>
					<!-- 
					<li><a href="<%=request.getContextPath()%>/system/contactListMobile.do" data-icon="Info" data-ajax="false">通讯录</a></li> -->
					<li><a href="#" data-icon="Grid">待开放</a>
					</li>
					<li><a href="<%=request.getContextPath()%>/mobile/m_systemConfig.jsp" class="ui-btn-active" data-icon="gear" data-ajax="false">设置</a></li>
				</ul>
			</div>
			<!-- /navbar -->
		</div>
		<!-- /header -->
	    <div data-role="content">
	        <ul style="list-style: none;margin-top:0px;border: 1px #CCCCCC solid;background-color: white;">
        		<li style="float:left;margin-left:-30px;margin-top:18px;">
        			<img src="<%=request.getContextPath()%>/images/btn/blogin.png">
        			 清除记住密码
        		</li>
        		<li style="text-align: right;">
        			<input type="button" value="清除" id="clearBtn" data-inline="true" data-mini="false"  data-theme="c" onclick="clearLogin();">	
        		</li>
        	</ul>
        	<ul style="list-style: none;margin-top:10px;border: 1px #CCCCCC solid;background-color: white;height:50px;">
        		<li style="margin-left:-30px;margin-top:18px;text-align: center;">
        			<span>系统版本&nbsp;&nbsp;V1.0</span>
        		</li>        		
        	</ul>
	    </div>
	</div>
  </body>
</html>
