
angular.module('userControllerModule',['sysServiceModule'])

//处理登录请求
.controller('login',['$scope','userService',function($scope,userService){
	 $scope.user = {username : '',password : ''};
	 /*登录方法*/
	angular.element('#login').bind('click',function(){
		var isPass = Validata.valiForm(document.forms[0]);
		if(isPass){
			 if(userService.login($scope.user).success)
				 window.location="/soa-rest/index.html";
		}
	});
	
}])

.controller('users',['$scope','userService','$routeParams'
               ,function($scope,userService,$routeParams){
	SOA.page = $routeParams.page;
	$scope.user={username:'',phone:''};
	$scope.metas = ['姓名','邮箱','电话号码','状态','管理员','创建时间'];
	SOA.CTX = $scope.context = userService.page($scope.user);
	$scope.users = SOA.CTX.rows;
	SOA.path='users';
		/*console.log(userService.page($scope.user));*/
}])

.controller('userAdd',['$scope','userService',function($scpoe,userService){
		$scpoe.user={
				
		};
	/*	angular.element('#insert').bind('click',function(){
			
		});*/
}])

.controller('moduleController',function($scope){
	$scope.indexName = '调度管理';
	$scope.modules = [
	                  {id:'adfadasdfa',name : '调度管理',menus:[{name:'调度设置',url:'/index'},{name:'调度告警',url:'/index'}]}
	                 ,{id:'sdfafdafdas',name : '用户管理',menus:[{name:'角色设置',url:'/index'},{name:'权限设置',url:'/index'}]}
	];
})

;