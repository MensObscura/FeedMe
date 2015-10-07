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

validationApp.controller('InscriptionController', function($scope, $http) {  

  $scope.disbutton = function() {
	return $scope.InscriptionForm.$invalid || $('#birthday').val() == "";
  };

  $scope.submitForm = function() {
    if ($scope.InscriptionForm.$valid) {
      
      var data = {
      	nom : $scope.lastname,
      	prenom : $scope.firstname,
      	password : $scope.password,
      	mail : $scope.user_email,
      	dateNaissance : $scope.birthday,
      };
      
     $http(
	{
	url: 'http://localhost:8080/inscription',dataType: 'json',method: 'POST',data: data}

	).success(function(response)
	{
		$scope.response = response;
	}

	).error(function(error)
	{
		$scope.error = error;
	}

	);
    }

  };
});
