var validationApp = angular.module('InscriptionApp', []);

validationApp.directive('ensureExpression', ['$http', '$parse', function($http, $parse) {
  return {
    require: 'ngModel',
    link: function(scope, ele, attrs, ngModelController) {
      scope.$watch(attrs.ngModel, function(value) {
        var booleanResult = $parse(attrs.ensureExpression)(scope);
        ngModelController.$setValidity('badpwd', booleanResult);
      });
    }
  };
 }]);

 
 
$('#birthday').datetimepicker({
  format: "Y/m/d",
  timepicker: false,
  maxDate:'0',
});

validationApp.controller('InscriptionController', function($scope) {  

  $scope.disbutton = function() {
	return $scope.InscriptionForm.$invalid || $('#birthday').val() == "";
  };

  $scope.submitForm = function() {
    if ($scope.InscriptionForm.$valid) {
      console.log($('#birthday').val()+" "+$scope.user_email);
    }

  };
});