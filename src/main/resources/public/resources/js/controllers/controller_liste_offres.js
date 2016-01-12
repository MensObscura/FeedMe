// Chargement du module "ListeApp"
var app = angular.module("ListeApp", ['appFilters', 'angular-notification-icons', 'ngAnimate', 'ui.bootstrap']);

app.controller("LogoutCtrl", function($scope, $http, $window) {
	// Fonction permettant une déconnexion :
	$scope.logout = function () {
		$http.get('/logout').success(
			function(donnees) {
				$scope.authenticated = false;
				$window.location.href = "/";
			}
		);
	};
	
	$scope.items = [
	                'The first choice!',
	                'And another choice for you.',
	                'but wait! A third!'
	              ];
});

// Création du controller "ListeCtrl"
app.controller("ListeCtrl", function($scope, $http, $window) {
	// On se connecte à la route consacrée pour récupèrer les offres
	$http.get('/offres').success(
		function(donnees) {
			$scope.list = donnees.data;
			$scope.nombrePlaces =0;	
		}
	);
	    
	// Permet de créer un listener qui va rediriger vers la visualisation de l'offre cliquée
	$scope.visualize = function (valeur, event) {

		if (event.target.className == "img-circle img-tile img-profil") {
			$window.location.href = "/visualiser_profil.html?id="+valeur.hote.idUtilisateur;
		}
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


