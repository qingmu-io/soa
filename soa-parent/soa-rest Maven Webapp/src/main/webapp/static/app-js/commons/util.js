!function($, window, undefined) {
	var Util = window.Util || {};
	window.Util = Util;
	/*获取表单中全部的那么值*/
	Util.getFormName= function(form) {
			var result = new Array();
			$($(form).serialize().split("=")).each(function(){
				if(StringUtil.isNotBlank(this)){
					var val = this;
					if(this.indexOf("&")!=-1){
					 val = this.substring(1,this.length);	
					}
					result.push(val);
				}
			});
			return result;
	};
}(jQuery, window);