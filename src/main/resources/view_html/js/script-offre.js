
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
});


validationApp.controller('OfferController', function($scope) {  

	$scope.submitForm = function() {
		if ($scope.offerForm.$valid) {
		    console.log("good");
		}

	};
});