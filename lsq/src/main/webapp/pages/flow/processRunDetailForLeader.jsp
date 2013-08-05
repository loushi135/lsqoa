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
	String basePath=request.getContextPath();
%>
<html>
<head>
<title>朗捷通OA代办事项审批页面</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/highslide/highslide.css" />
<style>
body { 
font: normal 12px auto "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
color: #4f6b72; 
background: #EEEEEE; 
} 
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
</style>
<script type="text/javascript"src="<%=basePath%>/ext3/ux/highslide/highslide-with-gallery.min.js"></script>
<script type="text/javascript">
function validNull(theId,msg){
  if(document.getElementById(theId).value=="")
  {  	      
	  document.getElementById("v_"+theId).innerHTML=msg;
	  document.getElementById(theId).focus();
	  return true;
  }else{
      document.getElementById("v_"+theId).innerHTML='';
      return false;
  }
}
   
 function submitCheck(){
  if (validNull("result","请输选择快速回复！"))
  {
  	return false;
  }
  if (validNull("superOption","请输入审批信息！"))
  {
  	return false;
  }
  return true;
}
 function doSubmit(){
	 if(!submitCheck()){
		 return;
	  };
	 var taskId = document.getElementById("taskId").value;
	 var piId = document.getElementById("piId").value;
	 var runId = document.getElementById("runId").value;
	 var executionId = document.getElementById("executionId").value;
	 var activityName = document.getElementById("activityName").value;
	 var assignee = document.getElementById("assignee").value;
	 var result = document.getElementById("result").value;
	 var superOption = document.getElementById("superOption").value;
	 var params='taskId='+taskId+"&piId="+piId+"&runId="+runId+"&executionId="+executionId+
	 			"&activityName="+encodeURI(activityName)+
	 			"&assignee="+encodeURI(assignee)+
	 			"&result="+encodeURI(result)+
	 			"&superOption="+encodeURI(superOption);
	var url = "<%=basePath%>/flow/nextForLeaderProcessActivity.do";
	var result = ajaxSyncCall(url,params);
	if(result==true){
		window.location.reload();
	}
	//window.opener=null;window.open('','_self','');window.close();
 }
 function ajaxSyncCall(urlStr, paramsStr) {   
	    var obj;   
	    var value;   
	    if (window.ActiveXObject) {   
	        obj = new ActiveXObject('Microsoft.XMLHTTP');   
	    } else if (window.XMLHttpRequest) {   
	        obj = new XMLHttpRequest();   
	    }   
	    obj.open('POST', urlStr, false);   
	    obj.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');   
	    obj.send(paramsStr);   
	    var result = eval(obj.responseText);
	    
	    return result;   
 }
 	hs.graphicsDir = '<%=basePath%>'+'/ext3/ux/highslide/graphics/';
	hs.align = 'center';
	hs.transitions = ['expand', 'crossfade'];
	hs.wrapperClassName = 'dark borderless floating-caption';
	hs.fadeInOut = true;
	hs.dimmingOpacity = .75;
	// Add the controlbar
	if (hs.addSlideshow) hs.addSlideshow({
		//slideshowGroup: 'group1',
		interval: 5000,
		repeat: false,
		useControls: true,
		fixedControls: 'fit',
		overlayOptions: {
			opacity: .6,
			position: 'bottom center',
			hideOnMouseOut: true
		}
	});
</script>
</head>
<body>




 <div id="divContext" > 
 <div style="text-align:center;background-color:#ffffff;width:96.2%;margin-left:5px;margin-bottom: 0px;height:50px;line-height:50px;filter:progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=4);-moz-box-shadow: 2px 2px 10px #909090;box-shadow:2px 2px 10px #909090;-webkit-box-shadow: 2px 2px 5px #909090;">
	<img alt="" src="../images/yh-logo.png" style="float: left;">
	<div style="width:100%;text-align:center;font-family:'微软雅黑'; ">
		<font size="5" color="#03386C" style="vertical-align: middle;">代办事项[${processRun.subject}]</font>
	</div>
</div>
<c:forEach items="${pfList}" var="processForm">
	<table class="table-info" cellpadding="0" cellspacing="1" width="96%" style="background-color:#80C8FE;filter:progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=4);-moz-box-shadow: 2px 2px 10px #909090;box-shadow:2px 2px 10px #909090;-webkit-box-shadow: 2px 2px 5px #909090;">
		<tr>
			<th colspan="2" style="text-align:left">流程任务：[${processForm.activityName}]
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
				<div style="float:right;width:250">
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
												<img src="<%=basePath%>/images/flag/attachment.png" />${fileName}
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
<c:if test="${task != null}">
<div style='margin-top:30px;'>
<form action="<%=basePath%>/flow/nextForLeaderProcessActivity.do" method="post" onsubmit="return submitCheck();">
	<table class="table-info" cellpadding="0" cellspacing="1" width="96%" style="background-color:#80C8FE;filter:progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=4);-moz-box-shadow: 2px 2px 10px #909090;box-shadow:2px 2px 10px #909090;-webkit-box-shadow: 2px 2px 5px #909090;">
		<tr>
			<th width="20%">快速回复</td>
			<td width="80%">
					<select NAME="result" id="result" style="width: 80%"  onchange="superOption.value =this.options[this.selectedIndex].value">
						<option value="">---请选择---</option>
						<option value="同意">同意</option>
						<option value="不同意">不同意</option>
					</select>
					<span  id="v_result" style="display;color:red;"></span>
			</td>
		</tr>
		<tr>
			<th width="20%">审批意见</td>
			<td width="80%"><textarea name="superOption" id="superOption" style="width: 80%" rows = "10"></textarea>
			<span  id="v_superOption" style="display;color:red;"></span>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<img  src="../images/btn/submitBtn.png" style="cursor:hand" onclick="doSubmit();">
				<input type="hidden" id="taskId" name="taskId" value="${task.id }"/>
				<input type="hidden" id="piId" name="piId" value="${processRun.piId }"/>
				<input type="hidden" id="executionId" name="executionId" value="${processRun.piId }"/>
				<input type="hidden" id="runId" name="runId" value="${processRun.runId }"/>
				<input type="hidden" id="activityName" name="activityName" value="${task.activityName }"/>
				<input type="hidden" id="assignee" name="assignee" value="${task.assignee }"/>
			</td>
		</tr>
	</table>
</form>
</div>
</c:if>


</body>
</html>
