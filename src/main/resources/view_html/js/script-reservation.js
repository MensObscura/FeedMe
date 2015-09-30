var validationApp = angular.module('ReservationApp', []);


validationApp.controller('ReservationController', function($scope) {  


  $scope.submitForm = function() {
    if ($scope.ReservationForm.$valid) {
     
    	 var data = {
		    	nombre : $scope.place,
		    };

		  $http({
			url: 'http://localhost:8080/reservations',dataType: 'json',method: 'POST',data: data}

			).success(function(response)
			{
				$scope.response = response;
			}
			).error(function(error)
			{
				$scope.error = error;
			}


    }

  };
});