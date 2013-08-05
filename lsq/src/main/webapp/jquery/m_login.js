function submitForm(){
	var username = $("#username").val();
	var password = $("#password").val();
	var chkrember = $("#rembPwd").is(':checked');
	
	if(chkrember){
		setCookie('username',username,30);
		setCookie('password',password,30);
		setCookie('chkrember',true,30);
	}else{
		setCookie('username','',30);
		setCookie('password','',30);
		setCookie('chkrember',false,30);
	}

	if(!$("#loginForm").validate().form()){
				return;
	}
	
	$.post(
		__ctxPath+"/login.do",
		$("#loginForm").serialize(),
	  	function(data,status){
	  		//alert("Data: " + data + "\nStatus: " + status);
	  		//var result = eval('(' + data + ')');
	  		//alert(data.replace(/\'/g,"\""));
	  		//var result = $.parseJSON(data);
	  		if(data.success){
	  			$("#failureMsg").html("");
	  			window.location.href=__ctxPath+"/system/noticeListMobile.do";
	  		}else if(data.failure){
	  			$("#failureMsg").html(data.msg);
	  		}
	    	
  	},"json");
}

function refeshCode() {
	var a = document.getElementById("loginCode");
	a.innerHTML='<img border="0" height="30" width="150" src=__ctxPath+"/CaptchaImg?rand=' + Math.random() + '"/>';
}
$(function(){
	var username = getCookie('username');
	var password = getCookie('password');
	var chkrember = getCookie('chkrember');
	
	$("#username").val(username);
	$("#password").val(password);
	
	if(chkrember=='true'){
		$("#rembPwd").attr("checked",true).checkboxradio("refresh");
	}else{
		$("#rembPwd").attr("checked",false).checkboxradio("refresh");
	}
	

	if(username!='' && username!= undefined && password!='' && password!= undefined){
		$("#submit").click();
	}
});


/* This function is used to set cookies */
function setCookie(name,value,expires,path,domain,secure) {
	
	var today = new Date(); 
	today.setTime(today.getTime()); 
	if (expires){ 
	expires = expires * 1000 * 60 * 60 * 24; 
	} 
	var expires_date = new Date(today.getTime() + (expires)); 
	
	document.cookie = name + "=" + escape (value) +
    ((expires) ? "; expires=" + expires_date.toGMTString() : "") +
    ((path) ? "; path=" + path : "") +
    ((domain) ? "; domain=" + domain : "") + ((secure) ? "; secure" : "");
}

/*This function is used to get cookies */
function getCookie(name) {
	var prefix = name + "=" 
	var start = document.cookie.indexOf(prefix); 
	
	if (start==-1) {
		return null;
	}
	
	var end = document.cookie.indexOf(";", start+prefix.length);
	if (end==-1) {
		end=document.cookie.length;
	}

	var value=document.cookie.substring(start+prefix.length, end) ;
	return unescape(value);
}