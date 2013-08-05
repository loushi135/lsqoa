<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<select name="${param.selecterName}" id="${param.selecterName}"  data-role="none" style="display: none;">
       	<option></option>
    	<option value="董事长">董事长</option>
    	<option value="顾问">顾问</option>
    	<option value="专家">专家</option>
    	<option value="经理">经理</option>
    	<option value="副经理">副经理</option>
    	<option value="总经理">总经理</option>
    	<option value="副总经理">副总经理</option>
    	<option value="常务副总经理">常务副总经理</option>
    	<option value="董事长助理">董事长助理</option>
    	<option value="主任">主任</option>
    	<option value="组长">组长</option>
    	<option value="副组长">副组长</option>
    	<option value="项目经理">项目经理</option>
    	<option value="总监">总监</option>
    	<option value="副总监">副总监</option>
</select>
<script>
	//选人组件
       	$('#${param.selecterName}').mobiscroll().select({
       		theme:'jqm',
       		setText:'确认',
       		cancelText:'取消',
	        display: 'bottom',
	        mode: 'mixed',
	        width: 50,
	        inputClass: 'i-txt required',
	        group: true,
	        label: '职务名称'
	    });
    	$('#${param.selecterName}'+'_dummy').attr('name','${param.selecterName}'+'_dummy');
</script>