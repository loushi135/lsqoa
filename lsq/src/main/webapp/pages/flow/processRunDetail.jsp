<%@page import="java.util.List"%>
<%@page import="com.xpsoft.oa.service.flow.ProcessFormService"%>
<%@page import="com.xpsoft.oa.model.flow.ProcessRun"%>
<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@page import="com.xpsoft.oa.service.flow.ProcessRunService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//显示流程的明细，（为流程的标题及内容）
	//传入piId,即流程实例ID
%>
<%
	String basePath=request.getContextPath();
%>
<style>
<!--	
.thumbJsp{
	float: left;
	margin:5px;
}
.thumbJsp img{
	height: 100px;
	width: 125px;
}
.thumbJsp-wrap1{
	margin: 4px;
	margin-right: 0;
	padding: 5px;
}
.thumbJsp-wrap1 span{
	display: block;
	overflow: hidden;
	text-align: center;
}
-->
</style>

 <div id="divContext${runId}">
 <c:if test="${empty pfList}"><span style="padding-left:40%;font-size:18px;color:red;">流程审批表单不存在！</span></c:if>
<c:forEach items="${pfList}" var="processForm">
	<table class="table-info" cellpadding="0" cellspacing="1" width="96%">
		<tr>
			<th colspan="2">流程任务：[${processForm.activityName}]
			    <c:if test="${processForm.status=='1'}">
							<font style="color: green;">[审批通过]</font>
						</c:if>
						<c:if test="${processForm.status=='0'}">
							<font style="color: red;">[审批不通过]</font>
						</c:if>
						<c:if test="${processForm.status=='2'}">
							<font style="color: red;">[退回审批人]</font>
						</c:if>
						<c:if test="${processForm.status=='3'}">
							<font style="color: red;">[退回发起人]</font>
						</c:if>
				<div style="float:right;width:280">
					${processForm.creatorName} 执行于:<fmt:formatDate value="${processForm.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
			</th>
		</tr>
		<c:forEach items="${processForm.formDatas}" var="formData">
		<c:if test="${formData.isShowed==1}">
			<tr>
				<th width="20%">${formData.fieldLabel}</th>
				<td width="80%">
					<c:choose>
						<c:when test="${formData.fieldType=='file'}">
							<c:set var="fileArr" value="${fn:split(formData.strValue,',')}"></c:set>
							<c:forEach items="${fileArr}" var="file" varStatus="fileIndex">
								<c:if test="${fn:containsIgnoreCase(file, '.jpg')||fn:containsIgnoreCase(file, '.bmp')||fn:containsIgnoreCase(file, '.png')||fn:containsIgnoreCase(file, '.gif')||fn:containsIgnoreCase(file, '.jpeg')}">
									<c:set var="fileProperties" value="${fn:split(file,':')}"></c:set>
									<c:set var="filePath" value="${fileProperties[0]}"></c:set>
									<c:set var="fileName" value="${fileProperties[1]}"></c:set>
									<a href="attachFiles/${filePath}"
											onclick="return hs.expand(this)" id="${filePath}" title="点击打开">
											<span class="" style='margin: 3px;float:left'>
											<c:set var="fileLength" value="${fn:length(fileName)}"/>
											<img src="images/flag/attachment.png" />
											<c:if test="${fileLength le 20}">${fileName}</c:if>
											<c:if test="${fileLength gt 20}">${fn:substring(fileName,0,18)}...</c:if>
											</span>
									</a>
								</c:if>
							</c:forEach>
							<c:forEach items="${fileArr}" var="file" varStatus="fileIndex">
								<c:if test="${!fn:containsIgnoreCase(file, '.jpg')&&!fn:containsIgnoreCase(file, '.bmp')&&!fn:containsIgnoreCase(file, '.png')&&!fn:containsIgnoreCase(file, '.gif')&&!fn:containsIgnoreCase(file, '.jpeg')}">
									<c:set var="fileProperties" value="${fn:split(file,':')}"></c:set>
									<c:set var="filePath" value="${fileProperties[0]}"></c:set>
									<c:set var="fileName" value="${fileProperties[1]}"></c:set>
									<div style='margin: 3px;float:left'>
										<c:if test="${fn:length(fileName)!=0}">
											<a title='${fileName}' href="javascript:window.location.href='<%=basePath%>/file-Download?filePath=attachFiles/${filePath}&fileName=${fileName}'">
												<img src="images/flag/attachment.png" />${fileName}
											</a>
										</c:if>
									</div>
								</c:if>
							</c:forEach>
						</c:when>
						<c:when test="${'资产列表' == formData.fieldLabel|| '领用物品列表' == formData.fieldLabel || '档案列表' == formData.fieldLabel || '出行人员列表' == formData.fieldLabel || '报告详情' == formData.fieldLabel}">
									<c:set var="list" value="${fn:split(formData.val,';')}"/>
									<c:forEach items="${list}" var="listItem" varStatus="listItemIndex">
										<c:set var="itemDetailList" value="${fn:split(listItem,',')}"/>
										<tr>
										<c:if test="${listItemIndex.count == 1}">
											<td rowspan="${fn:length(list)}"></td>
										</c:if>
										<td>
										<c:forEach items="${itemDetailList}" var="itemDetail" varStatus="itemDetailIndex">
											<c:if test="${itemDetailIndex.count == 1}"><span>${itemDetail}</span></c:if>
											<c:if test="${itemDetailIndex.count == 2}"><span>${itemDetail}</span></c:if>
											<c:if test="${itemDetailIndex.count == 3}"><span>${itemDetail}</span></c:if>
											<c:if test="${itemDetailIndex.count == 4}"><span>${itemDetail}</span></c:if>
											<c:if test="${itemDetailIndex.count == 5}"><span>${itemDetail}</span></c:if>
											<c:if test="${itemDetailIndex.count == 6}"><span>${itemDetail}</span></c:if>
											<c:if test="${itemDetailIndex.count == 7}"><span>${itemDetail}</span></c:if>
											<c:if test="${itemDetailIndex.count == 8}"><span>${itemDetail}</span></c:if>
										</c:forEach>
										</td>
										</tr>
									</c:forEach>
								</c:when>
						<c:otherwise>
							${formData.val}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:if>
		</c:forEach>
	</table>
</c:forEach>
</div>