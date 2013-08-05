<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>公告</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1,user-scalable=true">
<jsp:include page="includeJquery.jsp"></jsp:include>
<jsp:include page="includeJqueryMobile.jsp"></jsp:include>
 <script  language="javascript"> 
    $(function(){
    	if($(".TextContent img")!='undefined'){
    		var oldwidth=$(".TextContent img").attr("width");
	    	var oldheight=$(".TextContent img").attr("height");
	    	var newwidth=$(window).width()-40;
	    	var newheight=newwidth*oldheight/oldwidth;
	    	$(".TextContent img").attr("width",newwidth);
	    	$(".TextContent img").attr("height",newheight);
	    	$(".TextContent img").attr("style","margin-left:-20px;");
    	}
    });
</script>  

</head>

<body>
	<div data-role="page">
		<div data-role="header" data-position="inline">
			<a href="javaScript:history.back();" data-icon="back" data-direction="reverse" class="ui-btn-left">返回</a>
			<h1>公告</h1>
			<a href="<%=request.getContextPath()%>/j_logout.do" data-icon="back" data-direction="reverse" class="ui-btn-right" data-ajax="false">退出</a>
		</div>
		<div data-role="content">
			<h3 class='title'>${notice.noticeTitle }</h3>
			<p style='color:#666;font-size:12;'>
				${notice.postName } 发布于${notice.effectiveDate }
			</p>
			<div class='TextContent'>
				${notice.noticeContent }
			</div>
		</div>
	</div>
	
</body>
</html>

