<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<table class="table-info" width="98%" cellpadding="0" cellspacing="1">
	<tr>
	     <td width="20%"><h2>所属员工：</h2></td>
	     <td width="80%">${diary.appUser.fullname}</td>
	</tr>
	<tr>
		<td width="20%"><h2>记录时间：</h2></td>
		<td width="80%"><c:if test="${not empty diary.dayTime}"><fmt:formatDate value="${diary.dayTime}" pattern="yyyy-MM-dd" /></c:if></td>
	</tr>
	<tr>
		<td width="20%"><h2>内容：</h2></td>
		<td width="80%">${diary.content}</td>
	</tr>
	<c:forEach items="${diary.diaryFiles}" var="diaryFiles" varStatus="dfindex">
		<tr><td>附件${dfindex.index+1}</td><td><span><a href="#" onclick="FileAttachDetail.show(${diaryFiles.fileId})">${diaryFiles.fileName}</a></td></tr>
	</c:forEach>
</table>