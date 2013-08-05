<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.xpsoft.oa.model.admin.MeetingRoom"%>
<%@page import="com.xpsoft.oa.service.admin.MeetingRoomService"%>
<%@page import="com.xpsoft.core.util.ContextUtil"%>
<%@page import="com.xpsoft.core.util.AppUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


 
<div data-role="fieldcontain" id="dateStartPanel">
       <label for="txtBirthday">开始时间：</label>
		<input type="text" data-role="datebox"   id="startTime" name="startTime" class="required"/>
</div>
<div data-role="fieldcontain" id="dateEndPanel">
       <label for="txtBirthday">结束时间：</label>
       <input type="text" data-role="datebox"   id="endTime" name="endTime" class="required"/>
</div>
<div data-role="fieldcontain" id="roomNamePanel">
	  <label for="txtBirthday">会议室名称：</label>
	  <select name="${param.selecterName}" id="${param.selecterName}"  data-role="none" style="display: none;">
		      <option value=""></option>
	  </select>
</div>



<script>
	
	//日期组件
	var opt = {
	   preset: 'datetime', //日期
	   theme: 'jqm', //皮肤样式
	   display: 'bottom', //显示方式 
	   mode: 'mixed', //日期选择模式
	   dateFormat: 'yy-mm-dd', // 日期格式
	   timeFormat:'HH:ii:ss',
	   setText: '确定', //确认按钮名称
	   cancelText: '取消',//取消按钮名籍我
	   dateOrder: 'yymmdd', //面板中日期排列格式
	   timeWheels:'HHiiss',
	   dayText: '日', 
	   monthText: '月', 
	   hourText:'时',
	   minuteText:'分',
	   yearText: '年', //面板中年月日文字
	   endYear:2020, //结束年份
	   onSelect:function(value,inst){
		   
		   if($('#startTime').val()!=''&&$('#startTime').val()!='undefined'
				   &&$('#endTime').val()!=''&&$('#endTime').val()!='undefined'){
			   $.post(
			    		"<%=request.getContextPath()%>/admin/listMeetingRoom.do",
			    		{"SEARCH_startTime":$('#startTime').val(),"SEARCH_endTime":$('#endTime').val()},
					  	function(data,status){
					  		//alert("Data: " + data.success + "\nStatus: " + status);
					  		if(data.success){
					  			$("#${param.selecterName}").empty();
					  			$.each(data.result,function(index,item){
					  				$('#${param.selecterName}').append("<option value='"+item.name+"'>"+item.name+"</option>");
					  			});
					  		}
					  	},"json");
		   }
		  
	   }
	};
	$('#startTime').mobiscroll(opt);
	$('#endTime').mobiscroll(opt);
	

    $('#${param.selecterName}').mobiscroll().select({
      		theme:'jqm',
      		setText:'确认',
      		cancelText:'取消',
        display: 'bottom',
        mode: 'mixed',
        width: 50,
        inputClass: 'i-txt required',
        group: true,
        label: '会议室名称',
        onSelect:function(value,inst){
        	$('#${param.selecterName}'+'_dummy').val(inst.temp[0].split('_')[1]);
        }
    });
   	$('#${param.selecterName}'+'_dummy').attr('name','${param.selecterName}'+'_dummy');
</script>