// Chargement du module "ListeApp"
var app = angular.module("ListeApp", ['appFilters', 'angular-notification-icons', 'ngAnimate', 'ui.bootstrap', 'ngRateIt']);

app.controller("LogoutCtrl", function($scope, $http, $window, $interval) {
//	notif
	$http.get('/utilisateur/particulier/profil').success(
			function(donnees){
				$scope.idUser = donnees.data.idUtilisateur;



			}	 
	);


	$scope.getNotif = function(){

		$interval(function() {
			if($scope.idUser){
				var msgUrl = 'msg/'+$scope.idUser+'/nonLus';
				$http.get(msgUrl).success(function(donnees) { //

					$scope.items = donnees.data;
					console.log($scope.nbNotif);
					$scope.nbNotif = $scope.items.length;
				});
			}else{

				$scope.nbNotif =  1;

			}
		},3000);

	};

	$scope.getNotif();
	
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
	$http.get('/offres').success(
		function(donnees) {
			$scope.list = donnees.data;
			$scope.nombrePlaces =0;	
			$scope.noteMoyenne = 0;
		}
	);

	// On se connecte à la route permettantS de récupèrer le profil de l'utilisateur
	$scope.getNote = function (item) {
		var url = '/utilisateur/particulier/'+item.hote.idUtilisateur +'';
		$http.get(url).success(
				function(donnees) {
					console.log(donnees.data.note);
					item.hote.note = donnees.data.note/10;	
					
				}
			);
	}
	
	    
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


