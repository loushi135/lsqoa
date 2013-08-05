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
     	<div data-role="fieldcontain">
		       <label for="txtBirthday">项目名称：</label></br>
		       <input type="text" name="bpaProjectName" value="${bpaProjectName}" readonly="true" class="required"/></br>
		 </div>
		 <div data-role="fieldcontain">
		       <label for="txtBirthday">合同编号：</label></br>
		       <input type="text" name="materialContractNo" id="materialContractNo" readonly="true" class="required"/></br>
		 </div>
		 <div data-role="fieldcontain">
		       <label for="txtBirthday">收款单位：</label></br>
		       <input type="text" name="bpaReceiptDept" value="${bpaReceiptDept}" readonly="true"  class="required"/></br>
		 </div>
		 <div data-role="fieldcontain">
		       <label for="txtBirthday">系统类别：</label></br>
		       <input type="text" name="materialContractSysItem" id="materialContractSysItem" readonly="true" class="required"/></br>
		 </div>
     </div>
 </div>
 
<script>
	//得到 资金结余等
	var materialContractId = '${materialContractId}';
	//console.log(proNo);
	var url2 = '<%=request.getContextPath()%>/statistics/getMaterialContract.do';
	var params2 = "id="+materialContractId;
	//console.log(url2);
	//console.log(params2);
	if(materialContractId!=''){
		var data2 = ajaxSyncCall1(url2,params2).data;
		if(data2!=''){
			document.getElementById('materialContractNo').value = data2.contractNo;
			document.getElementById('materialContractSysItem').value = data2.sysItem;
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



 