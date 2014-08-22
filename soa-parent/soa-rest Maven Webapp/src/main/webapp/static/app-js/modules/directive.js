var pageHtml = '<div class="pagination pagination-centered pagination-small">\
	 					<ul>\
						<li><a href="#/{{name}}" >上一页</a></li>';
		
						'<li ng-repeat=><a href="#">1</a></li>';
						
	pageHtml+='<li><a href="#">下一页</a></li>\
						</ul>\
				</div>';
angular.module('soaDirective',[])
.directive('page',function(){
	
    return {
        restrict : 'E',
        template : pageHtml,
        controller:['$scope',function($scope){
        	$scope.pageNo=1;
        	$scope.name="123"; 
        	$scope.pageNoList = [];
        
        	
        	
        }],
        link: function(scope, element, attrs,controller){
        	var begin = parseInt(attrs.begin);
        	var end = attrs.end;
        	for(var i = begin;i<=end;i++){
        		scope.pageNoList.push(i);
        	}
        	console.log(scope.pageNoList);
        }
};
});

