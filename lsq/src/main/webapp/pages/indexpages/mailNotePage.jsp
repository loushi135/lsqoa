<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	var s = "${map['sid']}";
	function loginMail(obj){
		Ext.Ajax.request({
		   url: __ctxPath + "/mail/checkSidMailUtil.do",
		   success: function(response,options){
		   		//obj.href = "http://mail.goldmantis.com/coremail/XJS/index.jsp?"+Ext.util.JSON.decode(response.responseText).sid;
		   		//obj.click();
		   		//var form = document.getElementById("form1");
		   		//form.action = "http://mail.goldmantis.com/coremail/XJS/index.jsp?"+Ext.util.JSON.decode(response.responseText).sid;
		   		//form.submit();
		   		//window.open("http://mail.goldmantis.com/coremail/XJS/index.jsp?"+Ext.util.JSON.decode(response.responseText).sid,'_blank');
		   },
		   failure: function(response,options){
		   
		   },
		   params: {'sid': s}
		});
	}
</script>
<div class="contentDiv">
<form target="_blank" action="" id="form1"></form>
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
<c:if test="${map['mbox_newmsgcnt'] ne null and map['mbox_msgcnt'] ne null}">
<tr><td><a href="http://mail.goldmantis.com/coremail/XJS/index.jsp?${map['sid']}" onclick="loginMail(this)" target="_blank">您有<font color="red">${map["mbox_newmsgcnt"]}</font>封新邮件，共有${map["mbox_msgcnt"]}封邮件</a></td></tr>
</c:if>
<c:if test="${map['mbox_newmsgcnt'] eq null or map['mbox_msgcnt'] eq null}">
<tr><td><font color="blue">连接错误，请确认您的邮箱是正确的，如果有疑问请联系管理员</font></td></tr>
</c:if>
</table>
</div>
