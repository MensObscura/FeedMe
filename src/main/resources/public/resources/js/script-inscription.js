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
      

      var utilisateur = {
        nom : $scope.lastname,
        prenom : $scope.firstname,
        mail : $scope.user_email,
        dateNaissance : $('#birthday').val(),
      };

      var data = {
        utilisateur : utilisateur,
        password : $scope.password,
      };
      


       $http.put('http://localhost:8080/utilisateur/particulier',data)
         .success(function (data, status, headers) {
            $scope.ServerResponse = data;
          })
          .error(function (data, status, header, config) {

          });

    }

  };
});
