//Chargement du module "Accueil"
var app = angular.module("Accueil", ['angular-notification-icons', 'ngAnimate', 'ui.bootstrap']);

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


	;


});