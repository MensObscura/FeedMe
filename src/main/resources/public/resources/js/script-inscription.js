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

$('#datetimepicker').datetimepicker({
  format: "Y/m/d",
  timepicker: false,
});

validationApp.controller('InscriptionController', function($scope) {  

  $scope.submitForm = function() {
    if ($scope.InscriptionForm.$valid) {
      console.log("good");
    }

  };
});