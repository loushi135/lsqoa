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
		
		function clearLogin(){
			setCookie('username','',30);
			setCookie('password','',30);
			setCookie('chkrember',false,30);
			window.location.reload();
		}
		
		$(function(){
			var username = getCookie('username');
			var password = getCookie('password');
			var chkrember = getCookie('chkrember');
			
			if(username!='' && username!= undefined && password!='' && password!= undefined){
				$('#clearBtn').button('enable');
			}else{
				$('#clearBtn').button('disable');
			}
		});