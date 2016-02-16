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
var validationApp = angular.module('Inscription', ['ngMaterial', 'ngMessages','ngAnimate', 'ui.bootstrap']);

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
validationApp.controller('InscriptionCtrl', function($scope, $http, $window) {
	$scope.submited =false;
	$scope.premium = false;
	// initilisation du paiement à faux
	$scope.paye = false;
	// initialisation de la popover
	 $scope.dynamicPopover = {
			    content: 'Hello, World!',
			    templateUrl: 'paypal-fake.html',
			    title: 'Paiement'
			  };
	// On va rechercher toutes les pays en se connectant à la route consacrée
	$http.get('/settings/pays').success(
			function(donnees) {
				$scope.count = donnees.data;
			}
	);
	
	//popover fonction on met payé a true et on ferme la popup
	$scope.valider = function() {
		$scope.popoverOuverte = false;
		$scope.paye=true;
	};
	//popover fonction  on ferme la popup
	$scope.annuler = function() {
		$scope.popoverOuverte = false;
		$scope.premium = false;
	};
	
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
			
			if(angular.isUndefined($scope.complement)){
				$scope.complement=' ';
			}
			
			var adresse = {
					voie : $scope.numero + " " + $scope.rue + " " + $scope.complement,
					ville : ville,
			};
	

			// On créé un objet utilisateur
			var utilisateur = {
					nom : $scope.nom,
					prenom : $scope.prenom,
					mail : $scope.email,
					dateNaissance : moment($scope.date).format('YYYY-MM-DD'),
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
				
				toastr.options = {
						  "newestOnTop": false,
						  "progressBar": false,
						  "positionClass": "toast-bottom-center",
						  "preventDuplicates": false,
						  "onclick": null,
						  "showDuration": "300",
						  "hideDuration": "1000",
						  "timeOut": "5000",
						  "extendedTimeOut": "1000",
						  "showEasing": "swing",
						  "hideEasing": "linear",
						  "showMethod": "fadeIn",
						  "hideMethod": "fadeOut"
						};
				
				toastr.error("Cette adresse email est déjà enregistrée pour un autre compte.");
			});


		}

	};
}).directive('dateTimePicker', dateTimePicker);


