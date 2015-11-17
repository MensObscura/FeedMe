//Chargement du module "validationOffre"
var validationApp = angular.module('validationOffre', ['ngMaterial', 'ngMessages','ui-rangeSlider', 'ui.bootstrap.datetimepicker']);

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
validationApp.controller('OffreCtrl', function($scope, $http, $window, $mdToast) {
	
	$scope.age = {
		min: 18,
		max: 100
	};
	$scope.submited =false;
	$http.get('/utilisateur/particulier/profil').success(
			function(donnees) {
				// Quand on reçoit les données, on les envoie à la vue (stockage dans la variable profil)
				$scope.profil = donnees;
			}
	);
	
	$scope.homeAction = function() {

		if($scope.home){
			$scope.rue = $scope.profil.adresse.voie;
			$scope.ville = $scope.profil.adresse.ville.nom;
			$scope.cp = $scope.profil.adresse.ville.cp;
			$scope.pays= $scope.profil.adresse.ville.pays;

		}else{

			$scope.rue = '';
			$scope.ville ='';
			$scope.cp = '';
			$scope.pays= '';
		}


	};

	// On va rechercher toutes les types de cuisine en se connectant à la route consacrée
	$http.get('/settings/typescuisines').success(
			function(donnees) {
				$scope.cook = donnees;
			}
	);

	// On va rechercher toutes les pays en se connectant à la route consacrée
	$http.get('/settings/pays').success(
			function(donnees) {
				$scope.count = donnees;
			}
	);

	// pré-remplissage des champs 'durée', 'nbpers' et 'prix' par le minimum attendu
	$scope.duree = 60;
	$scope.prix = 1;
	$scope.nbpers = 1;
	$scope.date = new Date();
	$scope.complement = "";

	// Fonction utilisé lors de la validation du formulaire
	$scope.submitForm = function() {
		if ($scope.OffreForm.$valid) {
			
			if ($scope.date < new Date()) {
				//$mdToast.show($mdToast.simple().position('bottom left right').content('Cette date est passée !').hideDelay(2000));
			}
			
			// On récupère la date du repas
			var date_repas = $scope.date;
			var aujourdhui = new Date();
			var date = moment(aujourdhui).format('YYYY-MM-DD');

			// On créé on objet pays
			var pays = {
					id : $scope.pays,
			};

			// On créé un objet ville
			var ville = {
					nom : $scope.ville,
					cp : $scope.cp,
					pays : pays.id,
			};

			// On créé un objet adresse
			var adresse = {
					voie : $scope.numero + " " + $scope.rue + "" + $scope.complement,
					ville : ville,
			};

			// On créé un objet pour le type de cuisine
			var typeCuisine = {
					id : $scope.typeCuisine.id,
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
					ageMin : $scope.age.min,
					ageMax : $scope.age.max,
					animaux : Boolean($scope.animal),
					adresse : adresse,
					typeCuisine : typeCuisine,
			};
						
		    // On envoie les données
            $http({
        		method: 'PUT',
        		url: '/offres',
        		contentType: "application/json",
        		data: donnees
     		}).success(function(response, status, headers, config){
     			//$mdToast.show($mdToast.simple().position('bottom left right').content('Votre offre a bien été enregistrée.').hideDelay(2000));
     			$window.location.href = "/liste_offres.html";
     		}).error(function(err, status, headers, config){
      			//$mdToast.show($mdToast.simple().position('bottom left right').content('Notre service est indisponible pour le moment, veuillez réessayer plus tard.').hideDelay(2000));
     		});

		}
	};
});
