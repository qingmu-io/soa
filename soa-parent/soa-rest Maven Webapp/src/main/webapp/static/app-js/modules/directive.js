angular.module('soaDirective',[])
.directive('page',function(){
    return {
        restrict : 'E',
        templateUrl : '/soa-rest/template/directive/page.html',
        controller:['$scope',function($scope){
        	$scope.pages = [];
        	for(var i=context.startpage;i<=context.endpage;i++){
        		var map = {};
        		map.i=i;
        		if(context.page === i)
        		map.class='active';
        		$scope.pages.push(map);
        	}
        }],
        link: function(scope, element, attrs){
        }
};
})
.directive('table',function(){
	return {
		restrict : 'E'
		,templateUrl :'/soa-rest/template/directive/table.html'
		,controller : ['$scope',function($scope){
			
		}]	
	};
});
