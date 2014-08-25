var tempRoot = new StringBuffer().append('/soa-rest/template/');
var sysTempRoot = new StringBuffer().append('/soa-rest/template/sys/');
var sysUserTempRoot = new StringBuffer().append('/soa-rest/template/sys/user/');
var directiveTempRoot = new StringBuffer().append("/soa-rest/template/directive/");
!function(window){
	window.context = {};
	window.context.import = function(jsUrl){
		document.write("<script language='javascript' src='/soa-rest/static/app-js/"+jsUrl+".js'></script>");
	};
}(window);


window.context.import("modules/sys/service/sysService");
window.context.import("modules/directive");
window.context.import("modules/sys/controller/userController");
window.context.import("modules/cookieService");



angular.module('appRoute',['ngRoute','userController','soaDirective'])
.config(function($routeProvider){
	$routeProvider
	
	//首页
	.when('/index',{
		controller:'userList',
		templateUrl : tempRoot.append('table.html').toString()
	})
	/*begin 用户管理*/
	.when('/users/:page',{
		controller:'users',
		templateUrl : sysUserTempRoot.toString()+'list.html'
	})
	.when('/user/add',{
		controller:'userAdd',
		templateUrl : sysUserTempRoot.toString()+'add.html'
	})
	
	
	
	/*end 用户管理*/

	.otherwise({
        redirectTo: '/users/1'
    })
    ;
})


;

