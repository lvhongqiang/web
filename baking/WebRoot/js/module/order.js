var baking = angular.module('baking', ['ionic']);

baking.controller('order', [ '$scope', '$http','$ionicPopup', function($scope, $http,$ionicPopup) {

	$scope.list = [];
	$scope.page={};
	$scope.page.nextPage=1;
	$scope.page.totalPage=1;
	$scope.page.pageNo=1;
	
	$scope.more=function(){
		$http.post("json_order_list.action",{p:$scope.page.nextPage}).then(function(value) {
			for(i in value.data.page.list){
				$scope.list.push(value.data.page.list[i]);
			}
			$scope.page.totalPage=value.data.page.totalPage;
			$scope.page.pageNo=value.data.page.pageNo;
			$scope.page.nextPage=value.data.page.nextPage;
			$scope.$broadcast('scroll.infiniteScrollComplete');
		})
	}
	$scope.doRefresh=$scope.more;
	$scope.more();

	$scope.confirmDelete=function(g){
		$http.post("json_order_del.action",{orderId:g.id}).then(function() {
			for (i in $scope.list){
				if($scope.list[i].id==g.id){
					$scope.list.splice(i,1);
				}
			}
		})
		
	}
} ]);