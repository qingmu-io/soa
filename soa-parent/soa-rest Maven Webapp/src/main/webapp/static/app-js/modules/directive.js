var pageHtml = '<div class="pagination pagination-centered pagination-small">\
	 					<ul>\
						<li disabled="{{attr}}"><a href="#/{{name}}" >上一页</a></li>\
						<li><a href="#">1</a></li>\
						<li><a href="#">2</a></li>\
						<li><a href="#">3</a></li>\
						<li><a href="#">4</a></li>\
						<li><a href="#">5</a></li>\
						<li><a href="#">下一页</a></li>\
						</ul>\
				</div>';
angular.module('soaDirective',[])
.directive('page',function(){
    return {
        restrict : 'E',
        template : pageHtml,
        controller:['$scope',function($scope){
        	$scope.attr=true;
        	$scope.name="123";
        }],
        link: function(scope, element, attrs,controller){
        	
        }
};
});

