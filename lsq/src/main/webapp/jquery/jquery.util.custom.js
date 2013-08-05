(function($) {
	$.extend($.fn, {
		isEmpty : function(v,allowBlank){
			return v === null || v === undefined || ((this.isArray(v) && !v.length)) || (!allowBlank ? v === '' : false);
		},
		isArray : function(v){
			return toString.apply(v) === '[object Array]';
		}
	})
})(jQuery);