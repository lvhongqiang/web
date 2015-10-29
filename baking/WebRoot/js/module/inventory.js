var baking = angular.module('baking', ['ionic']);

baking.controller('Inventory', [ '$scope', '$http','$ionicPopup', function($scope, $http,$ionicPopup) {

	$scope.list = [];

	$http.post("json_inventory_listall.action").then(function(value) {
		$scope.list = value.data.list;
		//$scope.list.sort(function(a,b){return a.name.localeCompare(b.name)});//汉字拼音排序方法
	})
	
	function perstep(unit){
		if(unit=='g')
			return 100;
		else
			return 1;
	}
	
	$scope.subtract=function(i){
		i.num-=perstep(i.unit);
	}

	$scope.add=function(i){
		i.num+=perstep(i.unit);
	}
	
	$scope.save=function(){
		$http.post("json_inventory_save.action",{list : $scope.list})
		.then(function(value) {
			if(value.data.ok==true){
				$ionicPopup.alert({
					title: 'Congratulations！',
				    template: 'Honey, you have just saved the change. (●\'◡\'●) ',
				    buttons:[
			             {
			            	 text:'OK!',
			            	 type:'button-assertive'
			             }
				             ]
				     });
			}else{
				$ionicPopup.alert({
					title: 'OH！ NO！！',
				       template: 'Honey I\'m so sorry, there is an error when saving. (●\'◡\'●) ',
					    buttons:[
					             {
					            	 text:'OK!',
					            	 type:'button-assertive'
					             }
						             ]
				     });
			}
		});
		
	}
	
	$scope.addNew=function(){
		$http.post("json_inventory_add.action",{newone : $scope.newone})
		.then(function(value) {
			if(value.data.ok==true){
				$scope.list.push($scope.newone);
				$ionicPopup.alert({
					title: 'Congratulations！',
				       template: 'Honey, you have just added a new one. (●\'◡\'●) '
				     });
			}else{
				$ionicPopup.alert({
					title: 'OH！ NO！！',
				       template: 'Honey I\'m so sorry, there is an error when adding. (●\'◡\'●) '
				     });
			}
		});
		
	}
	
	$scope.orderfunc=function(a){
		return a.length;
		
	}
	
} ]);
