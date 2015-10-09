var baking = angular.module('baking', ['ionic']);

baking.controller('Inventory', [ '$scope', '$http','$ionicPopup', function($scope, $http,$ionicPopup) {

	$scope.list = [];

	$http.post("json_inventory_listall.action").then(function(value) {
		$scope.list = value.data.list;
	})
	
	function perstep(unit){
		if(unit=='g')
			return 100;
		else
			return 1;
	}
	
	$scope.subtract=function(i){
		$scope.list[i].num-=perstep($scope.list[i].unit);
	}

	$scope.add=function(i){
		$scope.list[i].num+=perstep($scope.list[i].unit);
	}
	
	$scope.save=function(){
		$http.post("json_inventory_save.action",{list : $scope.list})
		.then(function(value) {
			$scope.list = value.data.list;
			if($scope.order !=null){
				$ionicPopup.alert({
					title: 'Congratulations！',
				       template: 'Honey, you have just saved one order. (●\'◡\'●) '
				     })
				     .then(function(res) {
				    	 window.location.href='index.jsp';
				     });
			}
		});
		
	}
	
} ]);