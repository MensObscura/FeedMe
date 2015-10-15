// Chargement du module "ListeApp"
var app = angular.module("ListeApp", []);

// Création du controller "ListeCtrl"
app.controller("ListeCtrl", function($scope, $http, $location) {
	// On se connecte à la route consacrée pour récupèrer les offres
	$http.get('http://localhost:8080/offres').success(
		function(data) {
			$scope.list = data;
		}
	);
	
	// Permet de créer un listener qui va rediriger vers la visualisation de l'offre cliquée
	$scope.visualize = function (valeur) {
		   window.location.href = "/offre.html?id="+valeur.id;
	};
  
});


