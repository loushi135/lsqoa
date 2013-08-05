//$.validator.addMethod("cRequired", $.validator.methods.required,"Customer name required");
//$.validator.addMethod("cMinlength", $.validator.methods.minlength,$.format("<font color='red'>请输入一个 长度最少是 {0} 的字符串</font>"));
// 手机号码验证 
jQuery.validator.addMethod("mobile", function(value, element) {
	var length = value.length;
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/
	return this.optional(element)
			|| (length == 11 && mobile.test(value));
}, "<font color='red'>手机号码格式错误</font>");

// 金额验证 
jQuery.validator.addMethod("money",function(value, element) {
	var money = /(^$)|^\-?(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;//加上（^$）表示可为空  加\-?表示可以为负数
	return this.optional(element)
			|| (money.test(value));
}, "<font color='red'>金额格式错误</font>");

// 电话号码验证 
jQuery.validator.addMethod("phone",function(value, element) {
	var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
	return this.optional(element)
			|| (tel.test(value));
}, "<font color='red'>电话号码格式错误</font>");

// 邮政编码验证 
jQuery.validator.addMethod("zipCode", function(value, element) {
	var tel = /^[0-9]{6}$/;
	return this.optional(element) || (tel.test(value));
}, "<font color='red'>邮政编码格式错误</font>");

// QQ号码验证 
jQuery.validator.addMethod("qq", function(value, element) {
	var tel = /^[1-9]\d{4,9}$/;
	return this.optional(element) || (tel.test(value));
}, "<font color='red'>qq号码格式错误</font>");

// IP地址验证 
jQuery.validator.addMethod("ip",function(value, element) {
	var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
	return this.optional(element)
			|| (ip.test(value) && (RegExp.$1 < 256
					&& RegExp.$2 < 256
					&& RegExp.$3 < 256 && RegExp.$4 < 256));
}, "<font color='red'>Ip地址格式错误</font>");

// 字母和数字的验证 
jQuery.validator.addMethod("chrnum", function(value, element) {
	var chrnum = /^([a-zA-Z0-9]+)$/;
	return this.optional(element) || (chrnum.test(value));
}, "<font color='red'>只能输入数字和字母(字符A-Z, a-z, 0-9)</font>");

// 中文的验证 
jQuery.validator.addMethod("chinese", function(value, element) {
	var chinese = /^[\u4e00-\u9fa5]+$/;
	return this.optional(element) || (chinese.test(value));
}, "<font color='red'>只能输入中文</font>");

// 下拉框验证 
$.validator.addMethod("selectNone", function(value, element) {
	return value == "请选择";
}, "<font color='red'>必须选择一项</font>");

// 字节长度验证 
jQuery.validator.addMethod("byteRangeLength", function(value,
		element, param) {
	var length = value.length;
	for ( var i = 0; i < value.length; i++) {
		if (value.charCodeAt(i) > 127) {
			length++;
		}
	}
	return this.optional(element)
			|| (length >= param[0] && length <= param[1]);
}, $.validator.format("<font color='red'>请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)</font>"));

//$.validator.addMethod("cMaxlength",$.validator.methods.maxlength,
//				$.format("<font color='red'>请输入一个 长度最多是 {0} 的字符串</font>"));
//$.validator.addClassRules("moneyValiate", {
//	required : true,
//	cMaxlength : 10
//});