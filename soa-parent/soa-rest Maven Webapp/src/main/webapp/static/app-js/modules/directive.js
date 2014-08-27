angular.module('soaDirective',[])
.directive('page',function(){
    return {
        restrict : 'E',
        templateUrl : '/soa-rest/template/directive/page.html',
        controller:['$scope',function($scope){
        	$scope.path = SOA.path;
        	$scope.pages = [];
        	for(var i=SOA.CTX.startpage;i<=SOA.CTX.endpage;i++){
        		var map = {};
        		map.i=i;
        		if(SOA.CTX.page === i)
        		map.active='active';
        		$scope.pages.push(map);
        	}
        }],
        link: function(scope, element, attrs){
        }
    };
});
