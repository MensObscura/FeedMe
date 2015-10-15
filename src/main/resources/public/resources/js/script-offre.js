var app = angular.module("OffreApp", []);



app.controller('ReservationController', function($scope, $http, $location, $filter) {  


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
			var today = new Date();
			var date = "";

			if ((today.getMonth()+1) < 10)
				date = today.getFullYear()+'-0'+(today.getMonth()+1)+'-'+today.getDate();
			else
				date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();

			var data = {
					//nombre : $scope.place,
					offre : $scope.offre,
					dateReservation : date,
			};


			$http({
				method: 'PUT',
				url: 'http://localhost:8080/reservation',
				contentType: "application/json",
				data: data
			}).success(function(response, status, headers, config){
				console.log(response);
			}).error(function(err, status, headers, config){
				console.log(err.message);
			});

		}


	};



});