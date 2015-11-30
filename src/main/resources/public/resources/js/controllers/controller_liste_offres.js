// Chargement du module "ListeApp"
var app = angular.module("ListeApp", ['appFilters']);

// Création du controller "ListeCtrl"
app.controller("ListeCtrl", function($scope, $http, $window) {
	// On se connecte à la route consacrée pour récupèrer les offres
	$http.get('/offres').success(
		function(donnees) {
			$scope.list = donnees;
			$scope.nombrePlaces =0;	
		}
	);
	    
	// Permet de créer un listener qui va rediriger vers la visualisation de l'offre cliquée
	$scope.visualize = function (valeur, event) {

		if (event.target.localName == "img")
			$window.location.href = "/visualiser_profil.html?id="+valeur.hote.idUtilisateur;
		else
			$window.location.href = "/offre.html?id="+valeur.id;
	};
	
	
	//calcule le nombres de places restante
	$scope.getNbPlaces = function (item) {
		   var place_reservees = 0;
			for (i = 0; i < item.reservations.length; i++) {
				place_reservees +=  item.reservations[i].nbPlaces;
			}
			$scope.nombrePlaces = item.nombrePersonne - place_reservees;
			item.nombrePersonne = $scope.nombrePlaces;
				
	};
});


