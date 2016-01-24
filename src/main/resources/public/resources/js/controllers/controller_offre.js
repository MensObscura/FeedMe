//Chargement du module "OffreApp"
var app = angular.module("OffreApp", ['ngMaterial','angular-carousel','appFilters', 'angular-notification-icons', 'ngAnimate','ui.bootstrap', 'ngRateIt']);

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




//Création du controller "ReservationController"
app.controller('ReservationController', function($scope, $http, $window, $mdToast, $location, $anchorScroll) {  
	// initialisation de la popover
	$scope.dynamicPopover = {
			content: 'Hello, World!',
			templateUrl: 'paypal-fake.html',
			title: 'Paiement'
	};

	//init affichage photo à false
	$scope.allowDisplay =false;

	// on init images à 0
	$scope.images=[];

	// affichage photo
	$scope.display= function(img) {

		$scope.allowDisplay =true;
		$scope.current = img;

		$location.hash('top');
		$anchorScroll();
	};
	// disable photo
	$scope.disable= function() {

		$scope.allowDisplay =false;
	};

	$scope.submited =false;
	$scope.minCouvert = 1;
	// Fonction permettant de récupérer les paramètres de l'url.
	$scope.getUrlVars = function() {
		var vars = {};
		var parts = $window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,    
				function(m,cle,valeur) {
			vars[cle] = valeur;
		});
		return vars;
	}

	// On récupère l'identifiant de l'offre à afficher
	var id = $scope.getUrlVars()["id"];

	// On en déduit la route sur laquelle se connecter
	var route = '/offres/'+id;

	// On se branche dessus
	$http.get(route).success(
			function(data) {

				// On transfert dans "offre" les données
				$scope.offre = data.data;
				// On calcule le nombre de places restantes, que l'on transfert aussi à la vue
				var place_reservees = 0;
				for (i = 0; i < data.data.reservations.length; i++) {
					place_reservees += data.data.reservations[i].nbPlaces;
				}
				$scope.nombreRestant = data.data.nombrePersonne - place_reservees;

				// Si le nombre de places restantes est 0, on affiche "complet"
				if ($scope.nombreRestant == 0) {
					$scope.couverts_restants = "COMPLET"
						$scope.minCouvert = 0;
				}
				else {
					$scope.couverts_restants = $scope.nombreRestant+" sur "+data.data.nombrePersonne;
				}

				// On met à jour la rubrique "animal" s'il existe des données
				if (data.data.animaux)
					$scope.animaux = "Un animal de compagnie sera présent lors du repas.";

				// On met à jour la rubrique "age" s'il existe des données
				var age = "Ce repas s'adresse aux ";
				if (data.data.ageMin) {
					if (data.data.ageMax) {
						// Les deux bornes sont remplies.
						$scope.age = age + data.data.ageMin + "-" + data.data.ageMax + " ans.";
					}
					else {
						// On a juste l'âge minimum.
						$scope.age = age + "plus de " + data.data.ageMin + " ans.";
					}
				}
				else {
					if (data.data.ageMax) {
						// On a juste l'âge maximum.
						$scope.age = age + "moins de " + data.data.ageMax + " ans.";
					}
				}
				// On met à jour la rubrique "note" s'il existe des données
				if (data.data.note)
					$scope.note = data.data.note;

				$scope.faireListe();

			}

	);




	//on recupère les donnée de l'utilisateur courrant
	$http.get('/utilisateur/particulier/profil').success(
			function(donnees) {
				// Quand on reçoit les données, on les envoie à la vue (stockage dans la variable profil)
				$scope.profil = donnees.data;
				$scope.noteMoyenne = donnees.data.note/10;

			}
	);
	//on va voir le profil, correspondant à la photo cliquée
	$scope.voir = function(id){
		if(id != -1)
			$window.location.href = "/visualiser_profil.html?id="+id;
	};
	//Création de la list de reservation pour le carrousel

	$scope.faireListe = function(){

		var image = {
				path :  "resources\\img\\offre\\libre.png"	
		};
		var convive = {
				idUtilisateur : "-1",
				prenom : "place libre",
				image :	image,
		};

		var libre ={ 
				convive : convive

		};


		var liste = [];
		for (i =  0; i < $scope.offre.nombrePersonne; i++) {
			if(i < $scope.offre.reservations.length){
				//on peut avoir plusieur place par reservation
				for(j = 0;j< $scope.offre.reservations[i].nbPlaces; j++){
					liste.push( $scope.offre.reservations[i]);
				}
				i += $scope.offre.reservations[i].nbPlaces - 1;
			}else{
				liste.push(libre);
			}


		}
		$scope.liste= liste;
	}



	//popover fonction on met payé a true et on ferme la popup
	$scope.valider = function() {
		$scope.dynamicPopover.isOpen =false;
		$scope.submited=true
		$scope.submitForm();
	};
	//popover fonction  on ferme la popup
	$scope.annuler = function() {
		$scope.dynamicPopover.isOpen =false;

	};



	//edition de l'offre
	$scope.edition = function(){

		$window.location.href = "/edition-offre.html?id="+id;
	};
	// Fonction utilisé lors de la validation du formulaire de reservation
	$scope.submitForm = function() {
		if ($scope.ReservationForm.$valid && $scope.place > 0 ) {

			// Première étape : convertir la date est la mettre sous la bonne forme
			var aujourdhui = new Date();
			var date = moment(aujourdhui).format('YYYY-MM-DD');

			// On constitue les données
			var donnees = {
					nbPlaces : $scope.place, 
					offre : $scope.offre,
					dateReservation : date,
			};

			// On les envoies
			$http({
				method: 'PUT',
				url: '/reservation',
				contentType: "application/json",
				data: donnees
			}).success(function(response, status, headers, config){

				//$mdToast.show($mdToast.simple().position('bottom left right').content('Votre réservation a été enregistrée.').hideDelay(2000));
				//setTimeout(function() {$window.location.href = '/login.html';},2000);
				$window.location.href = "/liste_offres.html";
			}).error(function(err, status, headers, config){
				//$mdToast.show($mdToast.simple().position('bottom left right').content('Vous avez déja réservé une place pour cette offre.').hideDelay(2000));
			});

		}

	};

});