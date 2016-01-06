var app = angular.module('PaypalApp', ['ngMaterial']);

app.controller("PaypalCtrl", function($scope, $http, $window) {

	$scope.click = function() {

		window.location = "/accueil.html";
	};
});