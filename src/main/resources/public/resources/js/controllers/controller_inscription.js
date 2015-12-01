var dateTimePicker = function() {
     return {
        restrict: "A",
        require: "ngModel",
        link: function (scope, element, attrs, ngModelCtrl) {
	        var parent = $(element).parent();
	                    
	        var jour = new Date();
	    	var maxDate = new Date(jour.getFullYear()-18, jour.getMonth(), jour.getDate());
	                    
	        var dtp = parent.datetimepicker({
	               format: 'DD/MM/YYYY',
	               ignoreReadonly: true,
	               maxDate: maxDate
	         });
	        dtp.on("dp.change", function (e) {
	               ngModelCtrl.$setViewValue(new Date(e.date));
	               scope.$apply();
	        });
        }
     };
};

// Chargement du module "Inscription"
var validationApp = angular.module('Inscription', ['ngMaterial', 'ngMessages']);

// On ajoute une directive qui va se charger de de contrôler l'expression régulière contenue dans le formulaire
validationApp.directive('ensureExpression', ['$http', '$parse', function($http, $parse) {
	return {
		require: 'ngModel',
		link: function(scope, ele, attrs, ngModelController) {
			scope.$watch(attrs.ngModel, function(valeur) {
				// On calcule le résultat de l'expression
				var resultat = $parse(attrs.ensureExpression)(scope);
				// On informe la vue du résultat
				ngModelController.$setValidity('mauvaismdp', resultat);
			});
		}
	};
 }]);

//Création du controller "InscriptionCtrl"
validationApp.controller('InscriptionCtrl', function($scope, $http, $window, $mdToast) {
	$scope.submited =false;
	$scope.premium = false;
	
	// On va rechercher toutes les pays en se connectant à la route consacrée
	$http.get('/settings/pays').success(
			function(donnees) {
				$scope.count = donnees;
			}
	);
	
	// Fonction utilisé lors de la validation du formulaire
	$scope.submitForm = function() {
		if ($scope.InscriptionForm.$valid) {
			
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

			// On créé un objet utilisateur
			var utilisateur = {
					nom : $scope.nom,
					prenom : $scope.prenom,
					mail : $scope.email,
					dateNaissance : moment($scope.anniversaire).format('YYYY-MM-DD'),
					adresse : adresse,
					premium : $scope.premium
			};
			// On créé un objet authentification
			var authentification = {
					utilisateur : utilisateur,
					password : $scope.mdp,
			};
			// Envoi de ces données sur la route consacrée (PUT)
			$http({
				method: 'PUT',
				url: '/utilisateur/particulier',
				contentType: "application/json",
				data: authentification
			}).success(function(response, status, headers, config){
				//$mdToast.show($mdToast.simple().position('bottom left right').content('Votre compte a bien été enregistré, vous pouvez vous connecter.').hideDelay(2000));
				//setTimeout(function() {$window.location.href = '/login.html';},2000);
				$window.location.href = '/login.html';
			}).error(function(err, status, headers, config){
				 //$mdToast.show($mdToast.simple().position('bottom left right').content('Cette adresse email est déja enregistrée par un utilisateur.').hideDelay(2000));
			});


		}

	};
}).directive('dateTimePicker', dateTimePicker);
