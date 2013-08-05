<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@page import="com.xpsoft.oa.service.system.AppUserService"%>
<%@page import="com.xpsoft.oa.model.system.AppUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
	AppUserService appUserService=(AppUserService)AppUtil.getBean("appUserService");
	Map<String,List<AppUser>> list=appUserService.findByHeadWord();
	request.setAttribute("appUserList", list);
 %>


<input type="hidden" name="${param.selecterName}" id="${param.selecterName}">
<select name="${param.selecterId}" id="${param.selecterId}"  data-role="none" style="display: none;" >
       	  <optgroup label="请选择">
       	  	<option value=""></option>
       	  </optgroup>
       	<c:forEach items="${appUserList }" var="item">
       		<optgroup label="${item.key}">
       			<c:forEach items="${item.value}" var="appuser">
       				<option value="${appuser.userId }">${appuser.fullname }</option>
       			</c:forEach>
       		</optgroup>
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
	        label: '员工姓名',
	        groupLabel: 'A-Z',
	        onSelect:function(value,inst){
	        	$('#${param.selecterName}').val(value);
	        }
	    });
    	$('#${param.selecterId}'+'_dummy').attr('name','${param.selecterId}'+'_dummy');
</script>