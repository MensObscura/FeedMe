 
var validationApp = angular.module('validationOffre', []);

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

$('#dateRepas').datetimepicker({
	minDate:'+1970-01-2 00:00',
	step: 15,
});


validationApp.controller('OffreCtrl', function($scope, $http) {

 
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


  $scope.nonValide = function() {
	return $scope.OffreForm.$invalid || $('#dateRepas').val() == "";
  };

	$scope.submitForm = function() {
		if ($scope.OffreForm.$valid) {

			var date_repas = new Date($('#dateRepas').val());
			var today = new Date();
			var date = "";

			if ((today.getMonth()+1) < 10)
		    	date = today.getFullYear()+'-0'+(today.getMonth()+1)+'-'+today.getDate();
		    else
		    	date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();


			var pays = {
				id : $scope.pays,
			};

			var data_ville = {
				nom : $scope.ville,
				cp : $scope.cp,
				pays : pays,
			};

			var adresse = {
				voie : $scope.numero + " " + $scope.rue + "" + $scope.complement,
				ville : data_ville,
			};

			var typeCuisine = {
				id : $scope.typeCuisine,
			};


		    var data = {
		    	dateCreation : date,
		    	titre : $scope.titre,
		    	prix : parseFloat($scope.prix),
		    	nombrePersonne : parseInt($scope.nbpers),
		    	dureeMinute : parseInt($scope.duree), // optionnel
		    	dateRepas : date_repas.toISOString().substr(0,22),
		    	note : $scope.note, //optionnel
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
           		window.location.href = '/resources/accueil.html';
      		}).error(function(err, status, headers, config){
           		console.log(err.message);
     		 });


		}

	};
});
