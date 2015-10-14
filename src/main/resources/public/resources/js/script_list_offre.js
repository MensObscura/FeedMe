var app = angular.module("ListApp", []);

app.controller("ListCtrl", function($scope, $http, $location) {
	$http.get('http://localhost:8080/offres').success(
		function(data) {
			$scope.list = data;
		}
	);
	
	
	  $scope.visualize = function (myValue) {
		   
		    window.location.href = "offre.html?id="+myValue.id;
		  };
  

});





