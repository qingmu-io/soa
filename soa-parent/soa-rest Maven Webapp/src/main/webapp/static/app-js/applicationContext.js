var sysUserTempRoot = '/soa-rest/template/sys/user/';
var SOA = window.SOA = {};




angular.module('applicationContext',['ngRoute','userControllerModule','filterModule','soaDirective'])

.config(function($routeProvider){
	$routeProvider
	//首页
	//begin 用户管理
	.when('/users/:page',{
		controller:'users',
		templateUrl : sysUserTempRoot+'list.html'
	})
	.when('/user/add',{
		controller:'userAdd',
		templateUrl : sysUserTempRoot+'add.html'
	})
	
	
	
	//end 用户管理

	.otherwise({
        redirectTo: '/users/1'
    })
    ;
})


;