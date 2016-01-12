// Chargement du module "Accueil"
var app = angular.module("Accueil", ['angular-notification-icons', 'ngAnimate', 'ui.bootstrap']);

app.controller("LogoutCtrl", function($scope, $http, $window) {
	// Fonction permettant une déconnexion :
	$scope.logout = function () {
		$http.get('/logout').success(
			function(donnees) {
				$scope.authenticated = false;
				$window.location.href = "/";
			}
		);
	};
	
	
	$http.get('/offres').success(
			function(donnees) {
				$scope.list = donnees.data;
				$scope.nombrePlaces =0;	
			}
		);
	$scope.items = [
	                'The first choice!',
	                'And another choice for you.',
	                'but wait! A third!'
	              ];
	

});