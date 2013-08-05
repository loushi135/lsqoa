<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 使用选择器需要引入的js -->
<jsp:include page="../../../mobile/includeMobiscroll.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/jquery/jquery.util.custom.js" type="text/javascript"></script> 
<script src="<%=request.getContextPath()%>/jquery/selectHelp.js" type="text/javascript"></script> 

<script>
	function showinput(value){
		if(value=="A"){
			$("#inspectPanel").show();
			$("#probationPanel").hide();  
		}else if(value=="B"){
			$("#inspectPanel").hide();
			$("#probationPanel").show();  
		}else{
			$("#inspectPanel").hide();
			$("#probationPanel").hide();  
		}
	}
</script>
<div data-role="content">
     <div data-role="fieldcontain">
         <label for="selectmenu1">
             	快速回复：
         </label></br>
         <select name="" data-theme="b" onchange="selectChange(this.value,['superOption'],['pass','hiddenPanel'],['unpass'],['freeTranPanel']);">
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
     
     <div id="hiddenPanel">
     		<div data-role="fieldcontain" id="professionalPanel">
		       <label for="txtBirthday">专业知识:</label>
		       <select id="staffEmployapply@professional" name="staffEmployapply@professional" data-theme="b" class="required">
		         	 <option value="">
		                 	请选择
		             </option>
		             <option value="优秀">
		                 	优秀
		             </option>
		             <option value="良好">
		                 	良好
		             </option>
		             <option value="一般">
		                 	一般
		             </option>
		             <option value="不能接受">
		                 	不能接受
		             </option>
		        </select>
			</div>
			 <div data-role="fieldcontain" id="experiencePanel">
			       <label for="txtBirthday">工作经历:</label>
			       <select id="staffEmployapply@experience" name="staffEmployapply@experience" data-theme="b" class="required">
			         	 <option value="">
			                 	请选择
			             </option>
			             <option value="优秀">
			                 	优秀
			             </option>
			             <option value="良好">
			                 	良好
			             </option>
			             <option value="一般">
			                 	一般
			             </option>
			             <option value="不能接受">
			                 	不能接受
			             </option>
			        </select>
			</div>
			<div data-role="fieldcontain" id="confidentPanel">
			       <label for="txtBirthday">自信度:</label>
			       <select id="staffEmployapply@confident" name="staffEmployapply@confident" data-theme="b" class="required">
			         	 <option value="">
			                 	请选择
			             </option>
			             <option value="优秀">
			                 	优秀
			             </option>
			             <option value="良好">
			                 	良好
			             </option>
			             <option value="一般">
			                 	一般
			             </option>
			             <option value="不能接受">
			                 	不能接受
			             </option>
			        </select>
			</div>
			<div data-role="fieldcontain" id="thinkabilityPanel">
			       <label for="txtBirthday">思维能力:</label>
			       <select id="staffEmployapply@thinkability" name="staffEmployapply@thinkability" data-theme="b" class="required">
			         	 <option value="">
			                 	请选择
			             </option>
			             <option value="优秀">
			                 	优秀
			             </option>
			             <option value="良好">
			                 	良好
			             </option>
			             <option value="一般">
			                 	一般
			             </option>
			             <option value="不能接受">
			                 	不能接受
			             </option>
			        </select>
			</div>
			<div data-role="fieldcontain" id="expressabilityPanel">
			       <label for="txtBirthday">表达能力:</label>
			       <select id="staffEmployapply@expressability" name="staffEmployapply@expressability" data-theme="b" class="required">
			         	 <option value="">
			                 	请选择
			             </option>
			             <option value="优秀">
			                 	优秀
			             </option>
			             <option value="良好">
			                 	良好
			             </option>
			             <option value="一般">
			                 	一般
			             </option>
			             <option value="不能接受">
			                 	不能接受
			             </option>
			        </select>
			</div>
			<div data-role="fieldcontain" id="looksPanel">
			       <label for="txtBirthday">仪表:</label>
			       <select id="staffEmployapply@looks" name="staffEmployapply@looks" data-theme="b" class="required">
			         	 <option value="">
			                 	请选择
			             </option>
			             <option value="优秀">
			                 	优秀
			             </option>
			             <option value="良好">
			                 	良好
			             </option>
			             <option value="一般">
			                 	一般
			             </option>
			             <option value="不能接受">
			                 	不能接受
			             </option>
			        </select>
			</div>
			<div data-role="fieldcontain">
		       <label for="txtBirthday">同意新进:</label>
		       <select  name="staffEmployapply@agreeEnterType" data-theme="b" class="required" onchange="showinput(this.value);">
		         	 <option value="">
		                 	请选择
		             </option>
		             <option value="A">
		                 	考察期
		             </option>
		             <option value="B">
		                 	试用期
		             </option>
		        </select>
		        <div id="inspectPanel" hidden="true">
		        	<label for="txtBirthday">考察期(月):</label>
		        	<input type="text"  name="staffEmployapply@inspect" class="required number">
		        	<label for="txtBirthday">考察期月薪(元/月):</label>
		        	<input type="text"  name="staffEmployapply@inspectSalary" class="required money">
		        </div>
		        <div id="probationPanel" hidden="true">
		        	<label for="txtBirthday">试用期(月):</label>
		        	<input type="text"  name="staffEmployapply@probation" class="required number">
		        	<label for="txtBirthday">试用期月薪(元/月):</label>
		        	<input type="text"  name="staffEmployapply@probationSalary" class="required money">
		        </div>
			</div>
     		
     </div>
     
 </div>

  