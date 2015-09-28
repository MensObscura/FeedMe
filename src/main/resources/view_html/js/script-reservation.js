var validationApp = angular.module('ReservationApp', []);


validationApp.controller('ReservationController', function($scope) {  


  $scope.submitForm = function() {
    if ($scope.ReservationForm.$valid) {
     
    	 var data = {
		    	nombre : $scope.place,
		    };

		    $http.put('/reservations', data);


    }

  };
});