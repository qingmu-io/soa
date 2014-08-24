var userService = "userService";
var url = new StringBuffer().append(Constants.root).append("/sys/invoker").toString();
angular.module('sysServiceModule',['cookieModule'])
/*系统模块中 user服务*/
.service('userService',['cookieService',function(cookieService){
		var methods =  {
				/*用户登录方法*/
			login:function(context){
				context.service = 'userService';
				context.method = 'login';
				/*进行同步请求登录*/
				Request.sync_ajax_get(url,context,function(_context){context=_context;});
				return context;
			},
			page : function(context){
				context.page = cookieService.get('page');
				context.service = userService;
				context.method="page";
				Request.sync_ajax_get(url,context,function(_context){context=_context;});
				return context;
			}
		};
	return methods;	
}])
.service('roleService',[function(){
	var methods = {};
	
	return methods;
	
}]);
