angular.module('soaDirective',[])
.directive('page',function(){
    return {
        restrict : 'E',
        templateUrl : '/soa-rest/template/directive/page.html',
        controller:['$scope',function($scope){
        	$scope.pages = [];
        	alert(context);
        	for(var i=context.startpage;i<=context.endpage;i++){
        		var map = {};
        		map.i=i;
        		if(context.page === i)
        		map.active='active';
        		$scope.pages.push(map);
        	}
        }],
        link: function(scope, element, attrs){
        }
    };
});
