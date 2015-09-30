var app = angular.module("ListApp", []);

app.controller("ListCtrl", function($scope, $http) {
	$http.get('http://localhost:8080/offres').success(
		function(data) {
			$scope.list = data;
		}
	);
	
	$scope.visualize = function(item) {

    	alert(item.titre);
    	
  }
});
