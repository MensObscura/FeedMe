 
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
	minDate:'+1970-01-2 00:00',
	step: 15,
});


validationApp.controller('OfferController', function($scope,$http) {

 
	$http.get('http://localhost:8080/settings/typescuisines').success(
			function(data) {
				 $scope.cook = data;
			}
		);
	
	$http.get('http://localhost:8080/settings/pays').success(
			function(data) {
				 $scope.count = data;
			}
		);


  $scope.disbutton = function() {
	return $scope.offerForm.$invalid || $('#datetimepicker').val() == "";
  };

	$scope.submitForm = function() {
		if ($scope.offerForm.$valid) {

			var date_repas = new Date($('#datetimepicker').val());
			var today = new Date();
			var date = "";

			if ((today.getMonth()+1) < 10)
		    	date = today.getFullYear()+'-0'+(today.getMonth()+1)+'-'+today.getDate();
		    else
		    	date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();


			var pays = {
				nom : $scope.country,
			};

			var data_ville = {
				nom : $scope.town,
				cp : $scope.cp,
				pays : pays,
			};

			var adresse = {
				voie : $scope.num + " " + $scope.street + " " + $scope.complementary,
				ville : data_ville,
			};

			var typeCuisine = {
				typeCuisine : $scope.cooktype,
			};


			alert($scope.cooktype);
		    var data = {
		    	dateCreation : date,
		    	titre : $scope.title,
		    	prix : parseFloat($scope.price),
		    	nombrePersonne : parseInt($scope.nbpers),
		    	dureeMinute : parseInt($scope.time), // optionnel
		    	dateRepas : date_repas.toISOString().substr(0,22),
		    	note : $scope.complementary, //optionnel
		    	menu : $scope.menu,
		    	ageMin : parseInt($scope.agemin), //optionnel
		    	ageMax : parseInt($scope.agemax), //optionnel
		    	animaux : Boolean($scope.animal),
		    	adresse : adresse,
		    	typeCuisine : typeCuisine,
		    };

            $http({
        		method: 'PUT',
        		url: 'http://localhost:8080/offres',
        		contentType: "application/json",
        		data: data
     		}).success(function(response, status, headers, config){
           		console.log(response);
      		}).error(function(err, status, headers, config){
           		console.log(err.message);
     		 });


		}

	};
});
