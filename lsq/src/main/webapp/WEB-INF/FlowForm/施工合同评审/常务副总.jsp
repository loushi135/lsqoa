<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script src="<%=request.getContextPath()%>/jquery/jquery.util.custom.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jquery/selectHelp.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/statistics/MoneyUtil.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
				$("#sumPriceId").keyup(function(event) {
					var sumPrice = $("#sumPriceId").val();
					var sumPriceBig = AmountInWords(sumPrice);
					$("#sumPriceLabelId").text('(' + sumPriceBig + ')');
					$("#sumPriceBigId").val(sumPriceBig);
				})
			});
</script>
<div data-role="content">
	<div data-role="fieldcontain">
		<label for="selectmenu1">
			快速回复：
		</label>
		</br>
		<select name="" data-theme="b"
			onchange="selectChange(this.value,['superOption'],['pass','sumPrice_field','sumPriceBig_field','payWay_field'],['unpass'],['freeTranPanel']);">
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
	<div data-role="fieldcontain" id="sumPrice_field">
		<label>
			合同总价:
		</label>
		</br>
		<input id="sumPriceId" name="sumPrice" data-theme="b" type="text"
			class="required money"  value="${sumPrice}" />
	</div>
	<div data-role="fieldcontain" id="sumPriceBig_field">
		<font color="red"><label id="sumPriceLabelId">
				(${sumPriceBig})
			</label>
		</font>
		<input id="sumPriceBigId" name="sumPriceBig" type="hidden"
			value="${sumPriceBig}" />
	</div>
	<div data-role="fieldcontain" id="payWay_field">
		<fieldset data-role="controlgroup" style="padding-right: 0px;">
			<label>
			付款方式:
			</label>
			</br>
			<textarea name="payWay" id="payWayId" placeholder="付款方式"
					style="width: 100%;" class="required">${payWay}</textarea>
		</fieldset>
		
	</div>
	<div data-role="fieldcontain">
		<fieldset data-role="controlgroup" style="padding-right: 0px;">
			<label>
				审批意见：
			</label>
			</br>
			<textarea name="superOption" id="superOption" placeholder="审批意见"
				style="width: 100%;" class="required"></textarea>
		</fieldset>
	</div>
</div>