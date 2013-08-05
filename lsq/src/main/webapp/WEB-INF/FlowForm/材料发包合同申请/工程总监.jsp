<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 使用选择器需要引入的js -->
<jsp:include page="../../../mobile/includeMobiscroll.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/statistics/MoneyUtil.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jquery/jquery.util.custom.js" type="text/javascript"></script> 
<script src="<%=request.getContextPath()%>/jquery/selectHelp.js" type="text/javascript"></script> 

<script>
	
	function moneyToUCase(money){
		var result=AmountInWords(money);		
		$("#chValue").val(result);
		$("#contractPanel span").html(result);
	}
	
</script>
<div data-role="content">
     <div data-role="fieldcontain">
         <label for="selectmenu1">
             	快速回复：
         </label></br>
         <select name="" data-theme="b" onchange="selectChange(this.value,['superOption'],['pass','hiddenDiv'],['unpass'],['freeTranPanel']);">
         	<option value="">
                 	请选择
             </option>
             <option value="同意">
                  	同意
              </option>
              <option value="不同意,退回审批节点">
                  	不同意,退回审批节点
              </option>
              <option value="不同意,退回发起人">
                  	不同意,退回发起人
              </option>
         </select>
     </div>
     <div data-role="fieldcontain">
         <fieldset data-role="controlgroup" style="padding-right:0px;">
             <label>
                 	审批意见：
             </label></br>
             <textarea name="superOption" id="superOption"  style="width:100%;" class="required"></textarea>
         </fieldset>
     </div>
     <div id="hiddenDiv">
     	<div data-role="fieldcontain" id="contractPanel">
		       <label for="txtBirthday">合同总价(元)：</label></br>
		       <input type="text" name="contractAmount" value="${contractAmount}" class="required money" onkeyup="moneyToUCase(this.value);"/></br>
		       <span style="color: red;">(${chValue})</span>
		       <input type="hidden" id="chValue"  name="chValue" value="${chValue}" class="required"/>
		 </div>
		 <div data-role="fieldcontain" id="yfkPanel">
		       <label for="txtBirthday">预付款(%)：</label></br>
		       <input type="text"  name="yfk" value="${yfk}" class="required number">
		 </div>
		 <div data-role="fieldcontain" id="jdkPanel">
		       <label for="txtBirthday">进度款(%)：</label></br>
		       <input type="text"  name="jdk" value="${jdk}" class="required number">
		 </div>
		 <div data-role="fieldcontain" id="wgkPanel">
		       <label for="txtBirthday">完工款(%)：</label></br>
		       <input type="text"  name="wgk" value="${wgk}" class="required number">
		 </div>
		 <div data-role="fieldcontain" id="jskPanel">
		       <label for="txtBirthday">结算款(%)：</label></br>
		       <input type="text"  name="jsk" value="${jsk}" class="required number">
		 </div>
		<div data-role="fieldcontain" id="zbjPanel">
		       <label for="txtBirthday">质保金(%)：</label></br>
		       <input type="text"  name="zbj" value="${zbj}" class="required number">
		 </div>
		 <div data-role="fieldcontain" id="jdkReceivePanel">
		       <label for="txtBirthday">进度款收取时间：</label></br>
		       <select id="jdkReceive" name="jdkReceive" data-theme="b" class="required">
		         	 <option value="">
		                 	请选择
		             </option>
		             <c:if test="${jdkReceive eq '货前' }">
		             	<option value="货前" selected="selected">
			                 	货前
			             </option>
			             <option value="货后">
			                 	货后
			             </option>
		             </c:if>
		             <c:if test="${jdkReceive eq '货后' }">
		             	<option value="货前">
			                 	货前
			             </option>
			             <option value="货后" selected="selected">
			                 	货后
			             </option>
		             </c:if>
	         </select>
		 </div>
     </div>
 </div>




 