<%@page import="com.xpsoft.oa.service.flow.JbpmService"%>
<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@page import="org.jbpm.pvm.internal.model.Transition"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String taskId=request.getParameter("taskId");
	JbpmService jbpmService=(JbpmService)AppUtil.getBean("jbpmService");
	List<Transition> trans = jbpmService.getBackTransitionsByTaskId(taskId);
	request.setAttribute("trans", trans);
 %>


<select  id="signalName_f" name="signalName_f"  data-role="none" style="display: none;" >
     	<option value=""></option>
     	<c:forEach items="${trans }" var="item">
     		<option value="${item.name }">${item.destination.name }</option>
     	</c:forEach>
</select>


<script>
	//选人组件
       	$('#signalName_f').mobiscroll().select({
       		theme:'jqm',
       		setText:'确认',
       		cancelText:'取消',
	        display: 'bottom',
	        mode: 'mixed',
	        width: 50,
	        inputClass: 'i-txt required',
	        group: true,
	        label: '审批节点名称',
	        onSelect:function(value,inst){
	        	$('#destName').val(value);
	        	$('#signalName').val($('#signalName_f option:selected').val());
	        }
	    });
    	$('#signalName_f'+'_dummy').attr('name','signalName_f'+'_dummy');
</script>