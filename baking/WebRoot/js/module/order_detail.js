var baking = angular.module('baking', ['ionic']);

baking.controller('orderDetail', [ '$scope', '$http','$ionicPopup', function($scope, $http,$ionicPopup) {

	$scope.list = [];
	$scope.orderId=0;
	$scope.getlist = function() {
		$http.post("json_order_detail.action", {orderId : $scope.orderId}).then(function(value) {
			$scope.list = value.data.details;
			$scope.order= value.data.order;
		})
	};
} ]);