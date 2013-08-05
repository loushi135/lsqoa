<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1,user-scalable=0">
<jsp:include page="includeJquery.jsp"></jsp:include>
<jsp:include page="includeJqueryMobile.jsp"></jsp:include>
<script type="text/javascript">
var __ctxPath = "<%=request.getContextPath()%>";
</script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/highslide/highslide.css" />
<script type="text/javascript"src="<%=basePath%>/ext3/ux/highslide/highslide-with-gallery.min.js"></script>
<script type="text/javascript"src="<%=basePath%>/ext3/ux/highslide/highslideUse.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/jquery/css/showLoading.css" />
<script type="text/javascript"src="<%=basePath%>/jquery/jquery.showLoading.min.js"></script>
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

<script type="text/javascript">
	function submitProcess(signalName,destinationDisplay,destination){
		$("#signalName").val(signalName);
		$("#returnBack").val(destinationDisplay=='审批不通过'?1:-1);
		$("#destName").val(destination);
		
		if(!$("#flowForm").validate().form()){
			return;
		}
		
		$('#activity_pane').showLoading();
		$.post(
    		"<%=request.getContextPath()%>/flow/nextProcessActivity.do",
    		$("#flowForm").serialize(),
		  	function(data,status){
		  		//alert("Data: " + data.success + "\nStatus: " + status);
		  		if(data.success){		  			
		  			setTimeout(function(){
		  				window.location.href="<%=request.getContextPath()%>/system/taskListMobile.do";
		  				$('#activity_pane').hideLoading();
		  			},1000);
		  			
		  		}
		  	},"json");
		  	
		  	//.success(function() { alert("second success"); })
            //.error(function(XMLHttpRequest, textStatus, errorThrown) { alert(XMLHttpRequest.status); })
            //.complete(function() { alert("complete"); });
	}
	
	
	function returnBackProcess(){
		$("#returnBack").val(0);
		
		if(!$("#flowForm").validate().form()){
			return;
		}
		
		$('#activity_pane').showLoading();
		$.post(
    		"<%=request.getContextPath()%>/flow/nextProcessActivity.do",
    		$("#flowForm").serialize(),
		  	function(data,status){
		  		//alert("Data: " + data.success + "\nStatus: " + status);
		  		if(data.success){		  			
		  			$('#activity_pane').hideLoading();
		  			window.location.href="<%=request.getContextPath()%>/system/taskListMobile.do";
		  		}
		  	},"json");
	}
	
	$(function(){
		$.post(
	   		"<%=request.getContextPath()%>/system/transMobile.do",
	   		$("#flowForm").serialize(),
		  	function(data,status){
		  		//alert("Data: " + data + "\nStatus: " + status);
		  		$("#piId").val(data.executionId);
		  		
		  		var redsult=data.data;
				for(var i =0 ; i<redsult.length;i++){
					//alert(redsult[i].destination);
					$("#signalName").val();
					var html;
					if(redsult[i].destinationDisplay=='审批不通过'){
						html="<a data-role='button' id='unpass' href='javaScript:submitProcess(\""+redsult[i].name+"\",\""+redsult[i].destinationDisplay+"\",\""+redsult[i].destination+"\");'  data-theme='b' data-icon='back' data-iconpos='left'>"+redsult[i].destinationDisplay+"</a>";
					}else{
						html="<a data-role='button' id='pass' href='javaScript:submitProcess(\""+redsult[i].name+"\",\""+redsult[i].destinationDisplay+"\",\""+redsult[i].destination+"\");' data-theme='b' data-icon='forward' data-iconpos='left'>"+redsult[i].destinationDisplay+"</a>";
					}
					
					$("#nextButton").append(html).trigger("create");
				}
		  	},"json");
	});
</script>


</head>

<body>
	<div data-role="page" id="activity_pane">
		<div data-role="header" data-position="inline">
			<a href="javaScript:history.back();" data-icon="back" data-direction="reverse" class="ui-btn-left">返回</a>
			<h1>待办事项</h1>
			<a href="<%=request.getContextPath()%>/j_logout.do" data-icon="back" data-direction="reverse" class="ui-btn-right" data-ajax="false">退出</a>
		</div>
		<div data-role="content">
			<h4 class='title'>${processRun.subject }</h4>
			<div class='TextContent'>
				 <c:if test="${empty pfList}"><span style="padding-left:40%;font-size:18px;color:red;">流程审批表单不存在！</span></c:if>
				<c:forEach items="${pfList}" var="processForm">
					<table class="table-info" cellpadding="0" cellspacing="1" width="96%">
						<tr>
							<th colspan="2">
								<div style="float:left;">
									流程任务：[${processForm.activityName}]
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
								</div>
								<div style="float:right;">
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
											<div class="thumbJsp-wrap1">
												<c:forEach items="${fileArr}" var="file" varStatus="fileIndex">
													<c:if test="${fn:containsIgnoreCase(file, '.jpg')||fn:containsIgnoreCase(file, '.bmp')||fn:containsIgnoreCase(file, '.png')||fn:containsIgnoreCase(file, '.gif')||fn:containsIgnoreCase(file, '.jpeg')}">
														<c:set var="fileProperties" value="${fn:split(file,':')}"></c:set>
														<c:set var="filePath" value="${fileProperties[0]}"></c:set>
														<c:set var="fileName" value="${fileProperties[1]}"></c:set>
														<div class="thumbJsp">
															<a href="attachFiles/${filePath}" class="highslide"
																	onclick="return hs.expand(this)" id="${filePath}">
																<img style="margin-top: 10px"
																        src="attachFiles/${filePath}" alt="Highslide JS" 
																	title="点击放大" />
															</a>
															<span class="" style='width: 125px;height:30px;'>
																	<c:set var="fileLength" value="${fn:length(fileName)}"/>
																	<c:if test="${fileLength le 20}">${fileName}</c:if>
																	<c:if test="${fileLength gt 20}">${fn:substring(fileName,0,18)}...</c:if>
															</span>
														</div>
													</c:if>
												</c:forEach>
											</div>
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
										<c:when test="${'资产列表' == formData.fieldLabel|| '领用物品列表' == formData.fieldLabel || '档案列表' == formData.fieldLabel || '出行人员列表' == formData.fieldLabel}">
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
			
	      	<form id="flowForm" class="validate">
	      		<jsp:include page="${formUrl}"></jsp:include>
	      		<input type="hidden" name="piId" id="piId">
			    <input type="hidden" name="taskId" value="${taskId }">
			    <input type="hidden" name="activityName" value="${activityName }">
			    <input type="hidden" name="signalName" id="signalName">
			    <input type="hidden" name="destName" id="destName">	
			    <input type="hidden" name="returnBack" id="returnBack">				
				<div data-role="content" id="nextButton"></div>
				
				<div data-role="content" id="freeTranPanel">
					<label>自由跳转节点：</label></br>
				    <jsp:include page="selecter/m_freeTranSelecter.jsp">
				    	<jsp:param value="${taskId }" name="taskId"/>
				    </jsp:include>
				    <a data-role='button' id='returnback' href='javaScript:returnBackProcess();' data-theme='b' data-icon='back' data-iconpos='left'>转至[审批人]</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
