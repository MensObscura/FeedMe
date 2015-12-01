// Chargement du module "Profil"
var app = angular.module("Profil", []);

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

// Création du controller "ProfilCtrl"
app.controller("ProfilCtrl", function($scope, $http) {
	// On va se connecter sur la route permettant de récupèrer le profil de l'utilisateur
	$http.get('/utilisateur/particulier/profil').success(
		function(donnees) {
			// Quand on reçoit les données, on les envoie à la vue (stockage dans la variable profil)
			$scope.profil = donnees;
		}
	);
	
	  $scope.hoverIn = function(){
	        this.hoverEdit = true;
	     };

	    $scope.hoverOut = function(){
	        this.hoverEdit = false;
	    };
	
});