var baking = angular.module('baking', ['ionic']);

baking.controller('orderCosts', [ '$scope', '$http','$ionicPopup', function($scope, $http,$ionicPopup) {

	$scope.list = [];
	$scope.orderId=1;
	$scope.getlist=function(){
	$http.post("json_order_costs.action",{orderId:$scope.orderId}).then(function(value) {
		$scope.list=value.data.costDetail;
	})};
} ]);