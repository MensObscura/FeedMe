var login = angular.module('login', ['ngRoute', 'ngMaterial', 'ngMessages']);

login.config(
		function($routeProvider, $httpProvider) {
			$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});

login.controller('LoginController', function($rootScope, $scope, $http, $location) {

					var authenticate = function(credentials, callback) {

						var headers = credentials ? {
							authorization : "Basic "
									+ btoa(credentials.username + ":"
											+ credentials.password)
						} : {};

						$http.get('user', {
							headers : headers
						}).success(function(data) {
							if (data.name) {
								$rootScope.authenticated = true;
							} else {
								$rootScope.authenticated = false;
							}
							callback && callback($rootScope.authenticated);
						}).error(function() {
							$rootScope.authenticated = false;
							callback && callback(false);
						});

					}

					authenticate();

					$scope.credentials = {};

					$scope.login = function() {
						authenticate($scope.credentials,
								function(authenticated) {
									if (authenticated) {
										console.log("Login succeeded")
										$rootScope.authenticated = true;
										$scope.error = false;
										window.location = "/accueil.html";
									} else {
										console.log("Login failed")
										$rootScope.authenticated = false;
										$scope.error = true;
										$location.path("login.html");
									}
								})
					};

					$scope.logout = function() {
						$http.post('logout', {}).success(function() {
							$rootScope.authenticated = false;
							$location.path("/");
						}).error(function(data) {
							console.log("Logout failed")
							$rootScope.authenticated = false;
						});
					}

});
