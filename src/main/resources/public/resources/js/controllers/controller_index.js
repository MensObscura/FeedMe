// Chargement du module "Index"
var app = angular.module("Index", ['ngRoute']);

app.config(
		function($routeProvider, $httpProvider) {
			$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

		}
);

// Création du controller "MenuCtrl"
app.controller("MenuCtrl", function($scope, $http) {

	$scope.submited =false;
	var authenticate = function(credentials, callback) {

		var headers = credentials ? {
			authorization : "Basic " +
			 btoa(credentials.username + ":" + credentials.password)
		} : {};

		$http.get('user', {
			headers : headers
		}).success(function(data) {
			if (data.name) {
				$scope.authenticated = true;
			} else {
				$scope.authenticated = false;
			}
		}).error(function() {
			$scope.authenticated = false;
		});

	}
	
	authenticate();
	
	$scope.credentials = {};
	
});
