<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 使用选择器需要引入的js -->
<jsp:include page="../../../mobile/includeMobiscroll.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/jquery/jquery.util.custom.js" type="text/javascript"></script> 
<script src="<%=request.getContextPath()%>/jquery/selectHelp.js" type="text/javascript"></script> 

<div data-role="content">
     <div data-role="fieldcontain">
         <label for="selectmenu1">
             	快速回复：
         </label></br>
         <select name="" data-theme="b" onchange="selectChange(this.value,['superOption'],['pass','staffTypePanel'],['unpass'],['freeTranPanel']);">
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
     
<%--     <div data-role="fieldcontain" id="staffTypePanel">--%>
<%--	       <label for="txtBirthday">员工层级：</label>--%>
<%--	       <select id="staffType" name="staffType" data-theme="b" class="required">--%>
<%--	         	 <option value="">--%>
<%--	                 	请选择--%>
<%--	             </option>--%>
<%--	             <option value="普通员工">--%>
<%--	                 	普通员工--%>
<%--	             </option>--%>
<%--	             <option value="非普通员工">--%>
<%--	                 	非普通员工--%>
<%--	             </option>--%>
<%--	        </select>--%>
<%--	</div>--%>
	

 </div>



 