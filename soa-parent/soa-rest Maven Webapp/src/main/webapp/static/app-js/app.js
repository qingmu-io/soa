var tempRoot = new StringBuffer().append('/soa-rest/template/');
var sysTempRoot = new StringBuffer().append('/soa-rest/template/sys/');
var sysUserTempRoot = new StringBuffer().append('/soa-rest/template/sys/user/');
var directiveTempRoot = new StringBuffer().append("/soa-rest/template/directive/");

!function(window){
	window.import = function(jsUrl){
		document.write("<script language='javascript' src='/soa-rest/static/app-js/"+jsUrl+".js'></script>");
	};
}(window);


window.import("modules/sys/service/sysService");
window.import("modules/directive");
window.import("modules/sys/controller/userController");
window.import("modules/cookieService");

angular.module('appRoute',['ngRoute','userController','soaDirective'])
.config(function($routeProvider){
	$routeProvider
	
	//首页
	.when('/index',{
		controller:'userList',
		templateUrl : tempRoot.append('table.html').toString()
	})
	/*begin 用户管理*/
	.when('/userList/:pageNo',{
		controller:'userList',
		templateUrl : sysUserTempRoot.append('list.html').toString()
	})
	/*end 用户管理*/

	.otherwise({
        redirectTo: '/userList/1'
    })
    ;
})


;

