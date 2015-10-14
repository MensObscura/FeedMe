// Chargement du module "Inscription"
var validationApp = angular.module('Inscription', []);

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

 
// On charge le "DateTimePicker" que l'on incruste dans la division "anniversaire"
$('#anniversaire').datetimepicker({
	format: "Y-m-d", // Format de la date
	timepicker: false, // Le choix de l'heure n'est pas utilisé
	maxDate:'0', // = On ne peut pas choisir une date supérieures à celle d'aujourd'hui
});

//Création du controller "InscriptionCtrl"
validationApp.controller('InscriptionCtrl', function($scope, $http) {  
	// Fonction permettant la disponibilité (ou non) du bouton de validation
	$scope.nonValide = function() {
		// Le formulaire est invalide quand les champs sont invalides et que la date n'a pas été renseignée
		return $scope.InscriptionForm.$invalid || $('#anniversaire').val() == "";
	};

	
	// Fonction utilisé lors de la validation du formulaire
	$scope.submitForm = function() {
		if ($scope.InscriptionForm.$valid) {

			// On créé un objet utilisateur
			var utilisateur = {
					nom : $scope.nom,
					prenom : $scope.prenom,
					mail : $scope.email,
					dateNaissance : $('#anniversaire').val(),
			};
			// On créé un objet authentification
			var authentification = {
					utilisateur : utilisateur,
					password : $scope.mdp,
			};
			// Envoi de ces données sur la route consacrée (PUT)
			$http({
				method: 'PUT',
				url: 'http://localhost:8080/utilisateur/particulier',
				contentType: "application/json",
				data: authentification
			}).success(function(response, status, headers, config){
				// ICI JE VEUX UN TOASTER !!!!!!!!
				window.location.href = '/resources/index.html';
			}).error(function(err, status, headers, config){
				// ICI JE VEUX AUSSI UN TOASTER (POUR ERREUR) !!!!
			});


		}

	};
});
