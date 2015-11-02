// Chargement du module "ListeApp"
var app = angular.module("ListeApp", []);

// Création du controller "ListeCtrl"
app.controller("ListeCtrl", function($scope, $http, $window) {
	// On se connecte à la route consacrée pour récupèrer les offres
	$http.get('/offres').success(
		function(donnees) {
			$scope.list = donnees;
		}
	);
	
	// Permet de créer un listener qui va rediriger vers la visualisation de l'offre cliquée
	$scope.visualize = function (valeur) {
		   $window.location.href = "/offre.html?id="+valeur.id;
	};
 
	
});


