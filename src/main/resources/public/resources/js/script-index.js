// Chargement du module "Index"
var app = angular.module("Index", []);

// Création du controller "MenuCtrl"
app.controller("MenuCtrl", function($scope, $http) {

	// On va se connecter sur la route permettant de récupèrer le profil de l'utilisateur
	$http.get('/utilisateur/particulier/profil', {headers: { "Authorization" : "BasicCustom" }}).success(
		function(donnees) {
			// Si on reçoit les données, c'est que l'utilisateur est connecté, on n'affiche pas les boutons de connexion et d'inscription... par contre on peut se déconnecter
			$scope.connecte = true;
		}
	).error(function (data, status){
		$scope.connecte = false;
    });
});
