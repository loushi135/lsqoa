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
		$("#bpaApplyMoneyDX").val(result);
		$("#bpaApplyPanel span").html(result);
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
     	<div data-role="fieldcontain" id="bpaApplyPanel">
		       <label for="txtBirthday">本次申请用款(元)：</label></br>
		       <input type="text" name="bpaApplyMoneyXX" value="${bpaApplyMoneyXX}" class="required money" onkeyup="moneyToUCase(this.value);"/></br>
		       <span style="color: red;">(${bpaApplyMoneyDX})</span>
		       <input type="hidden" id="bpaApplyMoneyDX"  name="bpaApplyMoneyDX" value="${bpaApplyMoneyDX}" class="required"/>
		 </div>
     </div>
 </div>




 