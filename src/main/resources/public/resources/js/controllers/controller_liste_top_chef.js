// Chargement du module "ListeApp"
var app = angular.module("ListeApp", ['appFilters', 'angular-notification-icons', 'ngAnimate', 'ui.bootstrap']);

app.controller("LogoutCtrl", function($scope, $http, $window, $interval) {
//	notif
	$http.get('/utilisateur/particulier/profil').success(
			function(donnees){
				$scope.idUser = donnees.data.idUtilisateur;

				var msgUrl = 'msg/'+$scope.idUser+'/nonLus';
				$http.get(msgUrl).success(function(donnees) { 

					$scope.items = donnees.data;
					$scope.nbNotif = $scope.items.length;
				});
				
			}	 
	);


	$scope.notification = function(){
		
		$window.location.href = "/notification.html"
	};

	
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
			$scope.list = donnees.data;	
		}
	);
	    
	// Permet de créer un listener qui va rediriger vers la visualisation du profil cliqué
	$scope.visualize = function (valeur, event) {
		$http.get('/utilisateur/particulier/profil').success(
				function(donnees){
					if (donnees.data.idUtilisateur == valeur.idUtilisateur)
						$window.location.href = "/profil.html";
					else
						$window.location.href = "/visualiser_profil.html?id="+valeur.idUtilisateur;
				}	 
		);
				
	};
	
	
	
	//calcule l'age
	$scope.getAge = function (item) {
		var date = item.dateNaissance;
		var today = new Date();
		var dob = new Date(date.replace(/(\d{2})-(\d{2})-(\d{4})/, "$2-$1-$3"));
	
		$scope.age = today.getFullYear() - dob.getFullYear(); //This is the age
		item.dateNaissance = $scope.age;
		  
				
	};

});


