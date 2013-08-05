<%@page import="com.xpsoft.oa.model.admin.Car"%>
<%@page import="com.xpsoft.oa.service.admin.CarService"%>
<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
	CarService carService=(CarService)AppUtil.getBean("carService");
	Map<String,List<Car>> list=carService.findCarNoByDept();
	request.setAttribute("carList", list);
 %>

<input type="hidden" name="${param.selecterName}" id="${param.selecterName}">
<select name="${param.selecterId}" id="${param.selecterId}"  data-role="none" style="display: none;" >
       	  <optgroup label="请选择">
       	  	<option value=""></option>
       	  </optgroup>
       	<c:forEach items="${carList }" var="item">
       		<optgroup label="${item.key}">
       			<c:forEach items="${item.value}" var=  "car">
       				<option value="${car.carId }">${car.carNo }</option>
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
	        label: '车牌号',
	        groupLabel: 'A-Z',
	        onSelect:function(value,inst){
	        	$('#${param.selecterName}').val(value);
	        }
	    });
       	$('#${param.selecterId}'+'_dummy').attr('name','${param.selecterId}'+'_dummy');
</script>