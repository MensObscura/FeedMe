// Chargement du module "validationOffre"
var validationApp = angular.module('validationOffre', []);

//On ajoute une directive qui va se charger de de contrôler l'expression régulière contenue dans le formulaire
validationApp.directive('ensureExpression', ['$http', '$parse', function($http, $parse) {
	return {
	    require: 'ngModel',
	    link: function(scope, ele, attrs, ngModelController) {
	    scope.$watch(attrs.ngModel, function(value) {
			// On calcule le résultat de l'expression
			var resultat = $parse(attrs.ensureExpression)(scope);
			// On informe la vue du résultat
	    	ngModelController.$setValidity('mauvaisetranche', resultat);
	    });
	    }
	};
}]);

// On installe le calendrier sur la division consacrée
$('#dateRepas').datetimepicker({
	minDate:'+1970-01-2 00:00',
	step: 15,
});

//Création du controller "OffreCtrl"
validationApp.controller('OffreCtrl', function($scope, $http) {
	// On pre-remplissage des champs 'durée' et 'prix' par le minimum attendu
	$scope.duree = 60;
	$scope.prix = 1;
 
	// On va rechercher toutes les types de cuisine en se connectant à la route consacrée
	$http.get('http://localhost:8080/settings/typescuisines').success(
			function(data) {
				 $scope.cook = data;
			}
		);
	
	// On va rechercher toutes les pays en se connectant à la route consacrée
	$http.get('http://localhost:8080/settings/pays').success(
			function(data) {
				 $scope.count = data;
			}
		);

	// Fonction permettant la disponibilité (ou non) du bouton de validation
	$scope.nonValide = function() {
		// Le formulaire est invalide quand les champs sont invalides et que la date n'a pas été renseignée
		return $scope.OffreForm.$invalid || $('#dateRepas').val() == "";
	};

	// Fonction utilisé lors de la validation du formulaire
	$scope.submitForm = function() {
		if ($scope.OffreForm.$valid) {
			// On récupère la date du repas
			var date_repas = new Date($('#dateRepas').val());
			var aujourdhui = new Date();
			var date = "";
			// On créé la date de réservarion (que l'on met sous le bon format)
			if ((aujourdhui.getMonth()+1) < 10)
		    	date = aujourdhui.getFullYear()+'-0'+(aujourdhui.getMonth()+1)+'-'+aujourdhui.getDate();
		    else
		    	date = aujourdhui.getFullYear()+'-'+(aujourdhui.getMonth()+1)+'-'+aujourdhui.getDate();

			// On créé on objet pays
			var pays = {
				id : $scope.pays,
			};

			// On créé un objet ville
			var ville = {
				nom : $scope.ville,
				cp : $scope.cp,
				pays : pays,
			};

			// On créé un objet adresse
			var adresse = {
				voie : $scope.numero + " " + $scope.rue + "" + $scope.complement,
				ville : ville,
			};

			// On créé un objet pour le type de cuisine
			var typeCuisine = {
				id : $scope.typeCuisine,
			};

			// Enfin on peut créer les données que l'on souhaite envoyer
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

		    // On envoie les données
            $http({
        		method: 'PUT',
        		url: 'http://localhost:8080/offres',
        		contentType: "application/json",
        		data: data
     		}).success(function(response, status, headers, config){
     			// UN TOASTER ICI !!!!!!
           		window.location.href = '/accueil.html';
      		}).error(function(err, status, headers, config){
      			// UN TOASTER ICI !!!!!!
     		});

		}
	};
});
