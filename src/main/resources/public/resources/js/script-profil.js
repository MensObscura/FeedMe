// Chargement du module "Profil"
var app = angular.module("Profil", []);

// Création du controller "ProfilCtrl"
app.controller("ProfilCtrl", function($scope, $http) {
	// On va se connecter sur la route permettant de récupèrer le profil de l'utilisateur
	$http.get('http://localhost:8080/utilisateur/particulier/profil').success(
		function(donnees) {
			// Quand on reçoit les données, on les envoie à la vue (stockage dans la variable profil)
			$scope.profil = donnees;
		}
	);
	
});