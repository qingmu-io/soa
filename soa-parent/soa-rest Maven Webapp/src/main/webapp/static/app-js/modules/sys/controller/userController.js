angular.module('userController',['sysServiceModule'])

//处理登录请求
.controller('login',['$scope','userService',function($scope,userService){
	 $scope.user = {username : '',password : ''};
	 /*登录方法*/
	 $scope.login = function(){
				var isPass = Validata.valiForm(document.forms[0]);
				if(isPass){
					 if(userService.login($scope.user).success)
						 window.location="/soa-rest/index.html";
				}
			};
}])

.controller('userList',['$scope','userService',function($scope,userService){
	$scope.user={username:'',phone:''};
	$scope.metas = ['姓名','性别','年龄','电话'];
	alert($scope + userService);	
	var context = userService.page($scope.user);
	console.log(context);
	$scope.datas = [
	                {name:'张三',sex:'男',age:'20',phoneNumber:'13568541456'}
					,{name:'李四',sex:'男',age:'18',phoneNumber:'13568541456'}
					];
}])


.controller('moduleController',function($scope){
	$scope.indexName = '调度管理';
	$scope.modules = [
	                  {id:'adfadasdfa',name : '调度管理',menus:[{name:'调度设置',url:'/index'},{name:'调度告警',url:'/index'}]}
	                 , {id:'sdfafdafdas',name : '用户管理',menus:[{name:'角色设置',url:'/index'},{name:'权限设置',url:'/index'}]}
	];
})
.filter('skip',function(){
		return function(id){
			return id;
		};
})
;