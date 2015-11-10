// Chargement du module "validationOffre"
var validationApp = angular.module('validationOffre', ['ngMaterial', 'ngMessages', 'mdDateTime']);

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

//Création du controller "OffreCtrl"
validationApp.controller('OffreCtrl', function($scope, $http, $window,$mdToast) {
	   
	// On va rechercher toutes les types de cuisine en se connectant à la route consacrée
	$http.get('/settings/typescuisines').success(
			function(donnees) {
				 $scope.cook = donnees;
				 $scope.typeCuisine= $scope.cook[0].id;
			}
		);
	
	// On va rechercher toutes les pays en se connectant à la route consacrée
	$http.get('/settings/pays').success(
			function(donnees) {
				 $scope.count = donnees;
				 $scope.pays= $scope.count[0].id;
			}
		);
	
	// On pre-remplissage des champs 'durée', 'nbpers' et 'prix' par le minimum attendu
	$scope.duree = 60;
	$scope.prix = 1;
	$scope.nbpers = 1;

	// Fonction permettant la disponibilité (ou non) du bouton de validation
	$scope.nonValide = function() {
		// Le formulaire est invalide quand les champs sont invalides et que la date n'a pas été renseignée
		return $scope.OffreForm.$invalid || $('#dateRepas').val() == "";
	};

	// Fonction utilisé lors de la validation du formulaire
	$scope.submitForm = function() {
		if ($scope.OffreForm.$valid) {
			// On récupère la date du repas
			var date_repas = new Date();
			var aujourdhui = new Date();
			var date = aujourdhui.toLocaleFormat('%Y-%m-%d');
		    	
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
			
			var menu = {
				entree: $scope.entree,
				plat: $scope.plat,
				dessert: $scope.dessert,
				boisson: $scope.boisson
			};

			// Enfin on peut créer les données que l'on souhaite envoyer
		    var donnees = {
		    	dateCreation : date,
		    	titre : $scope.titre,
		    	prix : parseFloat($scope.prix),
		    	nombrePersonne : parseInt($scope.nbpers),
		    	dureeMinute : parseInt($scope.duree), // optionnel
		    	dateRepas : date_repas.toISOString().substr(0,22),
		    	note : $scope.note, //optionnel
		    	menu : menu,
		    	ageMin : parseInt($scope.agemin), //optionnel
		    	ageMax : parseInt($scope.agemax), //optionnel
		    	animaux : Boolean($scope.animal),
		    	adresse : adresse,
		    	typeCuisine : typeCuisine,
		    };
/*
		    // On envoie les données
            $http({
        		method: 'PUT',
        		url: '/offres',
        		contentType: "application/json",
        		data: donnees
     		}).success(function(response, status, headers, config){
     			// DECLENCHEMENT D'UN TOASTER ICI : Offre ajoutée
           		$window.location.href = '/accueil.html';
      		}).error(function(err, status, headers, config){
      			// DECLENCHEMENT D'UN TOASTER ICI : Erreur interne
     		});
*/	
		    console.log(donnees);
		}
	};
});
