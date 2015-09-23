
var validationApp = angular.module('validationOfferApp', []);

validationApp.directive('ensureExpression', ['$http', '$parse', function($http, $parse) {
	return {
	    require: 'ngModel',
	    link: function(scope, ele, attrs, ngModelController) {
	    scope.$watch(attrs.ngModel, function(value) {
	    	var booleanResult = $parse(attrs.ensureExpression)(scope);
	    	ngModelController.$setValidity('badages', booleanResult);
	    });
	    }
	};
}]);

$('#datetimepicker').datetimepicker({
	minDate:'+1970/01/2',
	step: 15,
});


validationApp.controller('OfferController', function($scope) {  

	$scope.submitForm = function() {
		if ($scope.offerForm.$valid) {

			var date_string = $scope.date;
			var date_repas = new Date(date_string);

		    var data = {
		    	dateCreation : new Date().toLocaleString(),
		    	titre : $scope.title,
		    	prix : Double.parseDouble($scope.price),
		    	nombrePersonne : parseInt($scope.nbpers),
		    	dureeMinute : parseInt($scope.time), // optionnel
		    	dateRepas : date_repas.toUTCString() ,
		    	note : $scope.complementary, //optionnel
		    	menu : $scope.menu,
		    	ageMin : parseInt($scope.agemin), //optionnel
		    	ageMax : parseInt($scope.agemax), //optionnel
		    	animaux : Boolean($scope.animal),
		    	adresse : ???????,
		    	typeCuisine : $scope.cooktype,
		    };

		    $http.put('/offres', data);

		}

	};
});