angular.module('appRoute',['ngRoute','controllers'])
.config(function($routeProvider){
	$routeProvider
	//首页
	.when('/index',{
		controller:'index',
		templateUrl : '/soa-rest/template/table.html'
	})
	//通过ID获取用户信息
	.when('/user/:id',{
		controller:'user.id',
		templateUrl : '/soa-rest/template/user_list.html'
	})
	
	.when('/form',{
		controller:'form',
		templateUrl : '/soa-rest/template/form.html'
	})
	.when('/table',{
		controller:'table'
		,templateUrl : '/soa-rest/template/table.html'	
	})
	.otherwise({
//        redirectTo: '/index'
    })
    ;
});

