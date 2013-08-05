<%@page import="com.xpsoft.oa.model.hrm.Job"%>
<%@page import="com.xpsoft.oa.service.hrm.JobService"%>
<%@page import="com.xpsoft.core.util.ContextUtil"%>
<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<%
	JobService jobService=(JobService)AppUtil.getBean("jobService");
	List<Job> list=jobService.findByDep(ContextUtil.getCurrentUser().getDepartment().getDepId());
	
	request.setAttribute("jobList", list);
 %>

<input type="hidden" name="${param.newJobRank}" id="${param.newJobRank}">
<input type="hidden" name="${param.newJobLevel}" id="${param.newJobLevel}">
<input type="hidden" name="${param.newJob}" id="${param.newJob}">

<input type="hidden" name="${param.selecterName}" id="${param.selecterName}">
<select name="${param.selecterId}" id="${param.selecterId}"  data-role="none" style="display: none;">
       	<option value=""></option>
       	<c:forEach items="${jobList }" var="item">
       		<option value="${item.jobId }">${item.jobName }</option>
       	</c:forEach>
</select>


<script>
	//选人组件
       	$('#${param.selecterId}').mobiscroll().select({
       		theme:'jqm',
       		setText:'确认',
       		cancelText:'取消',
	        display: 'bottom',
	        mode: 'mixed',
	        width: 50,
	        inputClass: 'i-txt required',
	        group: true,
	        label: '职位名称',
	        onSelect:function(value,inst){
	        	$('#${param.selecterName}').val(value);
	        	
	        	$('#${param.newJobRank}').val(value.split('-')[0]);
	        	$('#${param.newJobLevel}').val(value.split('-')[2]);
	        	$('#${param.newJob}').val(value.split('-')[1]);
	        }
	    });
    	$('#${param.selecterId}'+'_dummy').attr('name','${param.selecterId}'+'_dummy');
</script>