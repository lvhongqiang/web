var baking = angular.module('baking', ['ionic']);

baking.controller('makeOrder', [ '$scope', '$http','$ionicPopup', function($scope, $http,$ionicPopup) {

	$scope.orders = {};

	$scope.goods = [];

	$http.post("json_goods_listall.action").then(function(value) {
		$scope.goods = value.data.goodsList;
		for (g in $scope.goods) {
			$scope.orders[$scope.goods[g].id] = null;
		}
	})
	
	$scope.subtract=function(i){
		if($scope.orders[i]>0)
			$scope.orders[i]--;
	}

	$scope.add=function(i){
		if($scope.orders[i]==null)
			$scope.orders[i]=0;
		$scope.orders[i]++;
	}
	
	$scope.save=function(){
		//首先检查是否选择了商品
		var noorder=true;
		for(i in $scope.orders){
			if($scope.orders[i]!=null&&$scope.orders[i]>0)
				noorder=false;
		}
		if(noorder==true)
		{
			$ionicPopup.alert({
				title: 'Wait~',
			       template: 'Honey, you have not chosen anything. (●\'◡\'●) '
			     });
		}else{
			$http.post("json_order_save.action",{orders : $scope.orders})
			.then(function(value) {
				$scope.order = value.data.order;
				if($scope.order !=null){
					$ionicPopup.alert({
						title: 'Congratulations！',
					       template: 'Honey, you have just saved one order. (●\'◡\'●) '
					     })
					     .then(function(res) {
					    	 window.location.href='order_detail.jsp?orderId='+$scope.order.id;
					     });
				}
			});
		}
		
	}
	
} ]);