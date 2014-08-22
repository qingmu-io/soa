angular.module('cookieModule',[])
.service('cookieService',[function(){
		var methods = {
				set:function(key,value){
					$.cookie(key,value);
				},
				get : function(key){
					return $.cookie(key);
				}
		};
		return methods;
}]);