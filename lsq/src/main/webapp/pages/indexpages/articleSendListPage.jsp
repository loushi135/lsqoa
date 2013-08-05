<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="articleSend" items="${articleSendList}">
		<tr>
			<td width="26"><img
				src="<%=request.getContextPath()%>/images/menus/info/notice_menu.png" /></td>
			<td align="left"><a href="#"
				onclick="App.MyDesktopClickTopTab('ArticleSendDetail',${articleSend.noticeId})">${articleSend.noticeTitle}</a></td>
			<td width="80" nowrap="nowrap"><a>${articleSend.postName}</a></td>
			<td width="80" nowrap="nowrap"><a><fmt:formatDate
				value="${articleSend.effectiveDate}" pattern="yyyy-MM-dd" /></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#"
	onclick="App.clickTopTab('SearchArticleSend')">更多...</a></span></div>