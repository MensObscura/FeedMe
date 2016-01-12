// Chargement du module "Profil"
var app = angular.module("Profil",['appFilters', 'ui.bootstrap']);

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
app.controller("ProfilCtrl", function($scope, $http, $window) {
	
    $scope.debutOffres = 0;
    $scope.debutRepas = 0;
	
	// Fonction permettant de récupérer les paramètres de l'url.
	$scope.getUrlVars = function() {
		var vars = {};
	    var parts = $window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,    
	    function(m, cle,valeur) {
	      vars[cle] = valeur;
	    });
        
	    return vars;
	}

	// On récupère l'identifiant de le l'utilisateur dont on veut visualiser de le profil
	var id = $scope.getUrlVars()["id"];
	
	// On va se connecter sur la route permettant de récupèrer le profil de l'utilisateur
	$http.get('/utilisateur/particulier/'+id).success(
		function(donnees) {
			if (donnees) {
				// Quand on reçoit les données, on les envoie à la vue (stockage dans la variable profil)
				$scope.profil = donnees.data;
			
			var url = "/offres/enCours/" + donnees.data.idUtilisateur;
			
			// On va rechercher les repas qu'a créé l'utilisateur.
			$http.get(url).success(
					function(donnees) {
						// Quand on reçoit les données, on place les valeurs dans listeOffres
						$scope.listeOffres = donnees.data;
					}
				);
			}
			else {
				// On essaye d'atteindre une page qui n'existe pas...
				$window.location.href = "/accueil.html";
			}
		}
	);
    
  
	
});