<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="contentDiv">
<table class="newsList" cellpadding="0" cellspacing="0" rules="rows">
	<c:forEach var="topic" items="${topicList}">
		<tr>
			<td width="26">
					<img src="<%=request.getContextPath()%>/images/bbs_hot_1.gif" title="公司论坛" />
				</td>
			<td><a id="bbsClickId" href="#" onclick="App.MyDesktopClickTopTab('BbsReplyView',{topicId:${topic.id},title:'${topic.title}'})">${topic.title}</a></td>
			<td width="80" nowrap="nowrap"><a><fmt:formatDate
				value="${topic.publishTime}" pattern="yyyy-MM-dd" /></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="moreDiv"><span><a href="#"
	onclick="App.MyDesktopClickTopTab('BbsTopicView')">更多...</a></span></div>
	
	
