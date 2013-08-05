<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.xpsoft.core.util.ContextUtil"%>
<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	
	//登录成功后，需要把该用户显示至在线用户
	AppUtil.addOnlineUser(request.getSession().getId(),ContextUtil.getCurrentUser());
	if (ContextUtil.getCurrentUser().getRights().contains("__ALL")) {
		request.setAttribute("IS_MANAGER", true);
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title><%=AppUtil.getCompanyName()%>办公协同管理系统</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1,user-scalable=0" />
<jsp:include page="includeJquery.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/jquery/dust-full-1.1.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/mobileLazyloder.js"></script>
<script type='text/javascript'>
    	$.mobileLazyLoder('<%=request.getContextPath()%>/system/noticeListMobile.do');
</script>
<jsp:include page="includeJqueryMobile.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/jquery/jquery.mobile.lazyloader.min.js"></script>

</head>

<body>
	<!-- Home -->
	<div data-role="page" id="artist">
		<div data-role="header" id="header">
			<h1>通知公告</h1>
			<a href="<%=request.getContextPath()%>/index.jsp?change=manual" data-direction="reverse"  data-ajax="false">切换传统版</a>
			<a href="<%=request.getContextPath()%>/j_logout.do" data-icon="back"
				data-direction="reverse" class="ui-btn-right" data-ajax="false">退出</a>
			<div data-role="navbar">
				<ul>
					<li><a
						href="<%=request.getContextPath()%>/system/noticeListMobile.do"
						class="ui-btn-active" data-icon="Grid" data-ajax="false">公告</a>
					</li>
					<li><a
						href="<%=request.getContextPath()%>/system/taskListMobile.do"
						data-icon="Star" data-ajax="false">待办事项</a>
					</li>
					<li><a href="#" data-icon="Grid">待开放</a>
					</li>
					<!-- 
					<li><a
						href="<%=request.getContextPath()%>/system/contactListMobile.do"
						data-icon="Info" data-ajax="false">通讯录</a>
					</li> -->
					<li><a href="#" data-icon="Grid">待开放</a>
					</li>
					<li><a href="<%=request.getContextPath()%>/mobile/m_systemConfig.jsp"  data-icon="gear" data-ajax="false">设置</a></li>
				</ul>
			</div>
			<!-- /navbar -->
		</div>
		<!-- /header -->

		<div data-role="content" role="main">
			<ul id="listcontent" data-role="listview">
				<c:if test="${fn:length(noticeList) == 0}">
					<li class="ui-link-inherit">暂无公告</li>
				</c:if>
				<c:if test="${fn:length(noticeList) > 0}">
					<c:forEach items="${noticeList }" var="notice">
						<li class="ui-link-inherit"><a
							href="<%=request.getContextPath()%>/system/getNoticeMobile.do?noticeId=${notice.noticeId }"
							data-transition="slide" data-ajax="false">
								<h3 class="ui-li-heading">${notice.noticeTitle }</h3>
								<p class="ui-li-desc">发布于:${notice.effectiveDate }</p> </a></li>
					</c:forEach>
				</c:if>
			</ul>
			<div id="queueLazyloaderProgressDiv"
				class="width-hundred-percent display-none">
				<div class="width-two-hundred-pixels center-element align-center">
					<img
						src="<%=request.getContextPath()%>/jquery/css/images/5-121204193R0-50.gif"
						width="20"> 正在努力中...
				</div>
			</div>
		</div>

		<div data-role="footer" class="footer-docs" data-theme="c">
			<p>&nbsp;&copy; 2013 怡和科技</p>
		</div>
	</div>

	<script id="queueDustTemplate" type="text/x-dust-template">
		{#json}
    		<li class="ui-link-inherit">
            	<a href="<%=request.getContextPath()%>/system/getNoticeMobile.do?noticeId={noticeId}" data-transition="slide">
                	<h3 class="ui-li-heading">{noticeTitle}</h3>
	           		<p class="ui-li-desc">发布于:{effectiveDate}</p>	
            	</a>
        	</li>
		{/json}
		
	</script>

</body>
</html>
