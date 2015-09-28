var validationApp = angular.module('ReservationApp', []);


validationApp.controller('ReservationController', function($scope) {  


  $scope.submitForm = function() {
    if ($scope.ReservationForm.$valid) {
      console.log("je suis content !");
    }

  };
});