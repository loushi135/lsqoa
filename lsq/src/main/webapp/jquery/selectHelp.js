var selectChange = function(value,setValueArr,agreeShowArr,disagreeShowArr,returnBackShowArr){
	if(!$.fn.isEmpty(value) && !$.fn.isEmpty(setValueArr)){
		var id;
		for(id in setValueArr){
			$('#'+setValueArr[id]).val(value);
		}
	}
	if(value=='同意'){
		var id;
		if(!$.fn.isEmpty(agreeShowArr)){
			for(id in agreeShowArr){
				$('#'+agreeShowArr[id]).show();
			}
		}
		if(!$.fn.isEmpty(disagreeShowArr)){
			for(id in disagreeShowArr){
				$('#'+disagreeShowArr[id]).hide();
			}
		}
		if(!$.fn.isEmpty(returnBackShowArr)){
			for(id in returnBackShowArr){
				$('#'+returnBackShowArr[id]).hide();
			}
		}
	}else if(value=='不同意,退回审批节点'){
		var id;
		if(!$.fn.isEmpty(agreeShowArr)){
			for(id in agreeShowArr){
				$('#'+agreeShowArr[id]).hide();
			}
		}
		if(!$.fn.isEmpty(disagreeShowArr)){
			for(id in disagreeShowArr){
				$('#'+disagreeShowArr[id]).hide();
			}
		}
		if(!$.fn.isEmpty(returnBackShowArr)){
			for(id in returnBackShowArr){
				$('#'+returnBackShowArr[id]).show();
			}
		}
	}else if(value=='不同意,退回发起人'){
		var id;
		if(!$.fn.isEmpty(agreeShowArr)){
			for(id in agreeShowArr){
				$('#'+agreeShowArr[id]).hide();
			}
		}
		if(!$.fn.isEmpty(disagreeShowArr)){
			for(id in disagreeShowArr){
				$('#'+disagreeShowArr[id]).show();
			}
		}
		if(!$.fn.isEmpty(returnBackShowArr)){
			for(id in returnBackShowArr){
				$('#'+returnBackShowArr[id]).hide();
			}
		}
	}else{
		var id;
		if(!$.fn.isEmpty(agreeShowArr)){
			for(id in agreeShowArr){
				$('#'+agreeShowArr[id]).show();
			}
		}
		if(!$.fn.isEmpty(agreeShowArr)){
			for(id in disagreeShowArr){
				$('#'+disagreeShowArr[id]).show();
			}
		}
		if(!$.fn.isEmpty(returnBackShowArr)){
			for(id in returnBackShowArr){
				$('#'+returnBackShowArr[id]).show();
			}
		}
	}
}