<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 使用选择器需要引入的js -->
<jsp:include page="../../../mobile/includeMobiscroll.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/statistics/MoneyUtil.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jquery/jquery.util.custom.js" type="text/javascript"></script> 
<script src="<%=request.getContextPath()%>/jquery/selectHelp.js" type="text/javascript"></script> 

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
     	<div data-role="fieldcontain" id="bpaFundBalancePanel">
		       <label for="txtBirthday">资金结余(元)：</label></br>
		       <input type="text" name="bpaFundBalance" id="bpaFundBalance" value="${bpaFundBalance}" class="required money"/>
		 </div>
		 <div data-role="fieldcontain" id="bpaPayRatioPanel">
		       <label for="txtBirthday">垫资审批额度:(元)：</label></br>
		       <input type="text" name="bpaPayRatio" id="bpaPayRatio" value="${bpaPayRatio}" class="required money"/>
		 </div>
		 <div data-role="fieldcontain" id="bpaInvoiceBalancePanel">
		       <label for="txtBirthday">发票结余:(元)：</label></br>
		       <input type="text" name="bpaInvoiceBalance" id="bpaInvoiceBalance" value="${bpaInvoiceBalance}" class="required money"/>
		 </div>
     </div>
 </div>

<script type="text/javascript">
	//得到 资金结余等
	var proNo = '${bpaProjectNo}';
	var projectId = '${bpaProId}';
	var suppliersId = '${bpaReceiptDeptId}';
	//console.log(proNo);
	var url2 = '<%=request.getContextPath()%>/statistics/getApplyDataBankpayapply.do';
	var params2 = "proNo="+proNo+"&materialId=1&projectId="+projectId+"&suppliersId="+suppliersId;
	//console.log(url2);
	//console.log(params2);
	if(proNo!=''&&projectId!=''&&suppliersId!=''){
		var data2 = ajaxSyncCall1(url2,params2).data;
		if(data2.leftMoney!=''){
			document.getElementById('bpaInvoiceBalance').value = data2.leftMoney;
		}
		if(data2.quota!=''){
			document.getElementById('bpaPayRatio').value = data2.quota;
		}
		if(data2.leftBill!=''){
			document.getElementById('bpaInvoiceBalance').value = data2.leftBill;
		}else{
			document.getElementById('bpaInvoiceBalance').value = '';
		}
	}
	function ajaxSyncCall1(urlStr, paramsStr) {
	    var obj;
	    var value;
	    if (window.ActiveXObject) {
	        obj = new ActiveXObject('Microsoft.XMLHTTP');
	    } else if (window.XMLHttpRequest) {
	        obj = new XMLHttpRequest();
	    }
	    obj.open('POST', urlStr, false);
	    obj.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    obj.send(paramsStr);
	    var result = eval('(' +obj.responseText+ ')');
	    return result;   
	}
</script>


 