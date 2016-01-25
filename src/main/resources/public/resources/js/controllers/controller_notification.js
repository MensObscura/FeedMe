//Chargement du module "Accueil"
var app = angular.module("Notification", ['angular-notification-icons', 'ngAnimate', 'ui.bootstrap']);

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


app.controller("notificationCtrl",function($scope, $http, $window, $interval) {
//	notif
	$http.get('/utilisateur/particulier/profil').success(
			function(donnees){
				$scope.idUser = donnees.data.idUtilisateur;
					$scope.getAllNotif();
				
				
			}	 
	);


	$scope.getAllNotif = function(){

	
			if($scope.idUser){
				var msgUrl = 'msg/'+$scope.idUser+'/';
				$http.get(msgUrl).success(function(donnees) { 

					$scope.allItems = donnees.data;
					
					
				});
			}


	};
	
	$scope.afficheMessage = function(notif){
		var msgUrl ='msg/'+notif.id+'/marquerCommeLu'
		$http.put(msgUrl).success(function(donnees) { 
			notif.lu = true;
		});
		$scope.expediteur=notif.expediteur.nom;
		$scope.message=notif.texte;
		$scope.objet=notif.objet;
	};
});