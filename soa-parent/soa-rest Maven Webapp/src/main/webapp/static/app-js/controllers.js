angular.module('controllers',[])

.controller('index',function($scope,$routeParams){
	
})

.controller('user.id',function($scope,$routeParams){
		alert($routeParams.id);
})

.controller('form',function($scope){
	
})
.controller('table',function($scope){
	$scope.fileds = [
	                 {name:'姓名',type:'text'}
	                 ,{name:'年龄',type:'text'}
	                 ];
	$scope.metas = ['姓名','性别','年龄','电话'];
	
	$scope.datas = [
	                			{name:'张三',sex:'男',age:'20',phoneNumber:'13568541456'}
								,{name:'李四',sex:'男',age:'18',phoneNumber:'13568541456'}
								];
	
	$scope.cli = function(id){
		alert(id);
	};
})

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