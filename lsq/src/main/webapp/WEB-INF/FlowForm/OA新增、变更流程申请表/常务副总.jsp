<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 使用选择器需要引入的js -->
<jsp:include page="../../../mobile/includeMobiscroll.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/jquery/jquery.util.custom.js" type="text/javascript"></script> 
<script src="<%=request.getContextPath()%>/jquery/selectHelp.js" type="text/javascript"></script> 

<div data-role="content">
     <div data-role="fieldcontain">
         <label for="selectmenu1">
             	快速回复：
         </label></br>
         <select name="" data-theme="b" onchange="selectChange(this.value,['superOption'],['pass','flowName','flowDesc','flowNode'],['unpass'],['freeTranPanel']);">
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
     <div data-role="fieldcontain" id="flowName_field">
		<label>
			流程名称:
		</label>
		</br>
		<input id="flowName" name="flowApply.flowName" data-theme="b" type="text"
			class="required"  value="${flowApply_flowName}" placeholder="流程名称"/>
	 </div>
     <div data-role="fieldcontain" id="flowDesc_field">
		<fieldset data-role="controlgroup" style="padding-right: 0px;">
			<label>
			流程描述:
			</label>
			</br>
			<textarea name="flowApply.flowDesc" id="flowDesc" placeholder="流程描述"
					style="width: 100%;" class="required">${flowApply_flowDesc}</textarea>
		</fieldset>
	 </div>
	 <div data-role="fieldcontain" id="flowNode_field">
		<fieldset data-role="controlgroup" style="padding-right: 0px;">
			<label>
			流程节点:
			</label>
			</br>
			<textarea name="flowApply.flowNode" id="flowNode" placeholder="流程节点"
					style="width: 100%;" class="required">${flowApply_flowNode}</textarea>
		</fieldset>
	 </div>
     <div data-role="fieldcontain">
         <fieldset data-role="controlgroup" style="padding-right:0px;">
             <label>
                 	审批意见：
             </label></br>
             <textarea name="superOption" id="superOption"  style="width:100%;" class="required"></textarea>
         </fieldset>
     </div>
 </div>




 