var app = angular.module("ProfilApp", []);

app.controller("ProfilCtrl", function($scope, $http) {
	$http.get('http://localhost:8080/profil').success(
		function(data) {
			$scope.profil = data;
		}
	);
});