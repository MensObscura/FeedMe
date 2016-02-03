//Chargement du module "ListeApp"
var app = angular.module("ListeApp", ['ngMaterial','ui-rangeSlider', 'appFilters', 'angular-notification-icons', 'ngAnimate', 'ui.bootstrap', 'ngRateIt']);

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


//Création du controller "ListeCtrl"
app.controller("ListeCtrl", function($scope, $http, $window) {
	$scope.plusDeFiltre=false;

	$scope.filtre = function(){
		$scope.plusDeFiltre = ! $scope.plusDeFiltre;
	};

	$scope.prix={
			min:0,
			max:100
	};
	
	$scope.duree={
			min:60,
			max:300
	};
	
	$scope.premium=false;
	
	$scope.age=false;
	
	$scope.ville="";
	$scope.animaux=false;
	$scope.places=0;
	
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
					item.hote.note = donnees.data.note/10;	

				}
		);
	}


	// Permet de créer un listener qui va rediriger vers la visualisation de l'offre cliquée
	$scope.visualize = function (valeur, event) {

		if (event.target.className == "img-circle img-tile img-profil") {

			$http.get('/utilisateur/particulier/profil').success(
					function(donnees){
						if (donnees.data.idUtilisateur == valeur.hote.idUtilisateur)
							$window.location.href = "/profil.html";
						else
							$window.location.href = "/visualiser_profil.html?id="+valeur.hote.idUtilisateur;
					}	 
			);

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


