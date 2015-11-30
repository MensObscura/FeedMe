// Chargement du module "ListeApp"
var app = angular.module("ListeApp", ['appFilters']);

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
});

// Création du controller "ListeCtrl"
app.controller("ListeCtrl", function($scope, $http, $window) {
	// On se connecte à la route consacrée pour récupèrer les offres
	$http.get('/utilisateur/particulier/premium').success(
		function(donnees) {
			$scope.list = donnees;	
		}
	);
	    
	// Permet de créer un listener qui va rediriger vers la visualisation du profil cliqué
	$scope.visualize = function (valeur, event) {

		
			$window.location.href = "/visualiser_profil.html?id="+valeur.idUtilisateur;
		
	};
	
	

});


