angular.module('filterModule',[])
//日期格式化过滤器
.filter('dateformat',function(){
		return function(date){
			console.log(date);
			return new Date(date).toLocaleString();
		};
});