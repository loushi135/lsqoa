<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>



<input type="text" data-role="datebox"   id="${param.selecterName}" name="${param.selecterName}" class="required"/>

<script type="text/javascript">
       	//日期组件
       	var opt = {
	       preset: '${param.preset}', //日期
	       theme: 'jqm', //皮肤样式
	       display: 'bottom', //显示方式 
	       mode: 'mixed', //日期选择模式
	       dateFormat: '${param.dateFormat}', // 日期格式
	       timeFormat:'${param.timeFormat}',
	       setText: '确定', //确认按钮名称
	       cancelText: '取消',//取消按钮名籍我
	       dateOrder: 'yymmdd', //面板中日期排列格式
	       timeWheels:'HHii',
	       dayText: '日', 
	       monthText: '月', 
	       hourText:'时',
	       minuteText:'分',
	       yearText: '年', //面板中年月日文字
	       endYear:2020 //结束年份
	   };
       $('#${param.selecterName}').mobiscroll(opt);
       
</script>

