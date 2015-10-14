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
  format: "Y-m-d",
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

      var data_user = {
        utilisateur : utilisateur,
        password : $scope.password,
        role : {"id":1},
      };

      $http({
        method: 'PUT',
        url: 'http://localhost:8080/utilisateur/particulier',
        contentType: "application/json",
        data: data_user
     }).success(function(response, status, headers, config){
           window.location.href = '/resources/accueil.html';
      }).error(function(err, status, headers, config){
           console.log(err.message);
      });


    }

  };
});
