var baking = angular.module('baking', ['ionic']);

baking.controller('orderDetail', [ '$scope', '$http','$ionicPopup', function($scope, $http,$ionicPopup) {

	$scope.showUl='details';
	$scope.list = [];
	$scope.orderId=0;
	$scope.getlist = function() {
		$http.post("json_order_detail.action", {orderId : $scope.orderId}).then(function(value) {
			$scope.list = value.data.details;
			$scope.order= value.data.order;
		})
	};
	
	$scope.costlist = [];
	$scope.getcostlist=function(){
	$http.post("json_order_costs.action",{orderId:$scope.orderId}).then(function(value) {
		$scope.costlist=value.data.costDetail;
	})};
	
	$scope.confirmDelete=function(){
		$ionicPopup.confirm({
		     title: 'Consume Delete',
		     template: 'Are you sure you want to delete this order honey?'
		   }).then(function(res) {
		     if(res) {
		    	 $http.post("json_order_del.action",{orderId:$scope.order.id}).then(function() {
		    		 history.go(-2);
		 		})
		     }
		   });
		
		
	}
	
} ]);