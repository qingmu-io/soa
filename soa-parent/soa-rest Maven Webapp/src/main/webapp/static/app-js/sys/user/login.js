var sys = angular.module('sys',[]);
//登录控制器
sys.controller('loginCtrl',function($scope,$http){
//	alert($http);
	 $scope.user = {
		username : ''
		,password : ''
	};
	$scope.alert = function(){
		alert($scope.user.username);
	};
	$scope.login = function(){
		var isPass = Validata.valiForm(document.forms[0]);
		if(isPass){
			$scope.user.service = 'userService';
			$scope.user.method = 'login';
			var url1 = new StringBuffer().append(Constants.root).append("/sys/service").toString();
			$http({
				method:'post',
				data:$scope.user,
				url:url1
			}).success(function(response, status, headers, config){
				console.log(response +'\n'+status+'\n'+headers+'\n'+config);
			});
//			Requset.ajax(url,$scope.user,function(result){
//				console.log(result);
//			},true);
		}
	};
});

/*$(document).on('click', '#login', function() {
	
	var isPass = Validata.valiForm(document.forms[0]);
	
	if (isPass) {//表单验证通过
		//参数对象
		var params = {
					'service':'userService'
					,'method' : 'login'
					,username : $('input[name="loginId"]').val()
					,password : $('input[name="password"]').val()	
					};
		var url = Constants.root+'/sys/service';
		
		Requset.ajax(url,params,function(result){
			console.log(result);
		},true);
	};

});
*/