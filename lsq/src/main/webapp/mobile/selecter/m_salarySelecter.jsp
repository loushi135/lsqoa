<%@page import="com.xpsoft.oa.model.hrm.StandSalary"%>
<%@page import="com.xpsoft.oa.service.hrm.StandSalaryService"%>
<%@page import="com.xpsoft.core.util.ContextUtil"%>
<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<%
	StandSalaryService standSalaryService=(StandSalaryService)AppUtil.getBean("standSalaryService");
	List<StandSalary> list= standSalaryService.findByPassCheck();
	
	request.setAttribute("standSalaryList", list);
 %>


<input type="hidden" name="${param.selecterName}" id="${param.selecterName}">
<select name="${param.selecterId}" id="${param.selecterId}"  data-role="none" style="display: none;">
       	<option value=""></option>
       	<c:forEach items="${standSalaryList }" var="item">
       		<option value="${item.standardId }">${item.standardName }</option>
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
	        label: '薪资名称',
	        groupLabel: 'A-Z',
	        onSelect:function(value,inst){
	        	$('#${param.selecterName}').val(value);
	        	
	        }
	    });
    	$('#${param.selecterId}'+'_dummy').attr('name','${param.selecterId}'+'_dummy');
</script>