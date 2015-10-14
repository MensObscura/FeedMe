var app = angular.module("OffreApp", []);



app.controller('ReservationController', function($scope, $http, $location) {  


	$scope.$location = $location;
	$scope.$location.url(window.location);
	var id = $scope.$location.search().id ;  
	var route = 'http://localhost:8080/offres/'+id;

	$http.get(route).success(
			function(data) {
				$scope.offre = data;
				$scope.nombreRestant = $scope.offre.nombrePersonne - $scope.offre.reservations.length;

			}
	);

	$scope.submitForm = function() {
		if ($scope.ReservationForm.$valid) {

			var data = {
					nombre : $scope.place,
					offre : $scope.offre,
			};

			$http.put('http://localhost:8080/reservation',data)
			.success(function (data, status, headers) {
				$scope.ServerResponse = data;
			})
			.error(function (data, status, header, config) {

			});

		}

	};

});