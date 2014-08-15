var tempRoot = new StringBuffer().append('/soa-rest/template/');
var sysTempRoot = new StringBuffer().append('/soa-rest/template/sys/');
var sysUserTempRoot = new StringBuffer().append('/soa-rest/template/sys/user/');

angular.module('appRoute',['ngRoute','userController'])
.config(function($routeProvider){
	$routeProvider
	//首页
	.when('/index',{
		controller:'userList',
		templateUrl : tempRoot.append('table.html').toString()
	})
	/*begin 用户管理*/
	.when('/userList',{
		controller:'userList',
		templateUrl : sysUserTempRoot.append('user_list.html').toString()
	})
	/*end 用户管理*/

	.otherwise({
        redirectTo: '/userList'
    })
    ;
});

