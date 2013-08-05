<%@ page pageEncoding="UTF-8"%><% 
	response.addHeader("__forbidden","true");
	String basePath=request.getContextPath();
%>
<html>
	<head>
		<title>访问拒绝</title>
			<style type="text/css">
			<!--
			.STYLE10 {
				font-family: "黑体";
				font-size: 36px;
			}
			-->  
			</style>
	</head>
	<body>
	 <table width="510" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:100px;">
	  <tr>
    	<td><img src="<%=basePath%>/images/error_top.jpg" width="510" height="80" /></td>
  	  </tr>
	  <tr>
	    <td height="200" align="center" valign="top" background="<%=basePath%>/images/error_bg.jpg">
	    	<table width="80%" border="0" cellspacing="0" cellpadding="0">
	        <tr>
	          <td width="34%" align="right"><img src="<%=basePath%>/images/error.gif" width="128" height="128"></td>
	          <td width="66%" valign="bottom" align="center">
	          	<span class="STYLE10">访问被拒绝</span>
	          	<div style="text-align: left;line-height: 22px;margin-top:20px;">
	            	<font size="5">${errorMsg}</font>
		        </div>
		        
	     	 </td>
	      </table>
	      </td>
	  </tr>    	 
	  <tr>
    	<td><img src="<%=basePath%>/images/error_bootom.jpg" width="510" height="32" /></td>
      </tr>
	</table>
	</body>
</html>