//Ajout du DateTimePicker.
var dateTimePicker = function() {
	return {
		restrict: "A",
		require: "ngModel",
		link: function (scope, element, attrs, ngModelCtrl) {
			var parent = $(element).parent();

			var min = new Date();

			var dtp = parent.datetimepicker({
				format: 'DD/MM/YYYY',
				ignoreReadonly: true,
				minDate: min
			});
			dtp.on("dp.change", function (e) {
				ngModelCtrl.$setViewValue(new Date(e.date));
				scope.$apply();
			});
		}
	};
};

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

	//initialisation des filtres
	$scope.plusDeFiltre=false;

	$scope.filtre = function(){
		$scope.plusDeFiltre = ! $scope.plusDeFiltre;
	};

	// On va rechercher toutes les types de cuisine en se connectant à la route consacrée
	$http.get('/settings/typescuisines').success(
			function(donnees) {
				$scope.cook = donnees.data;
				var obj ={
						id: $scope.cook.length+1,
						type: "tous"
				}
				$scope.cook.push(obj);
			}
	);
 
	$scope.prix={
			min : 0,
			max : 100
	};

	$scope.duree={
			min : 60,
			max: 300
	};



	$scope.premium=false;

	
	$scope.foo={
			recherche : "",
			typeCusisine : 0,
			animaux : false,
			ville : "",
			note : 0,
			age :  false,
			duree: $scope.duree,
			prix : $scope.prix,
			places : 0,
			date : undefined
			};


	 $scope.change = function(note) {
	    	$scope.foo.note = note;
	    }
	 
	
	$scope.simpleRecherche= function(){
		
		var donnees = {
				global : $scope.foo.recherche,
				premium :       undefined,
				prixMin :       undefined,
				prixMax:        undefined,
				dureeMin:       undefined,
				dureeMax :      undefined,
				noteMinimal :   undefined,
				ageValide :     undefined,
				villeOuCP :     undefined,
				idTypeCuisine : undefined,
				animaux :       undefined,
				nbPlaceRestanteMinimum :undefined,
				date :          undefined
		}
		
		console.log(donnees);
		// On envoie les données
		$http({
		method: 'POST',
		url: '/offres/recherche',
		contentType: "application/json",
		data: donnees
		}).success(
		function(donnees) {
			$scope.list = donnees;
			for(var i=0;i<donnees.length; i++){
				$scope.getNote(donnees[i]);
				$scope.getNbPlaces(donnees[i]);
			}
			$scope.nombrePlaces =0;	
			$scope.noteMoyenne = 0;
		});


	};

	$scope.rechercher= function(){

		var date_repas = new Date($scope.foo.date);
		console.log($scope.foo);
		var date = moment(date_repas).format('YYYY-MM-DD');
		var donnees ={
				global : $scope.foo.recherche,
				premium : $scope.foo.premium? $scope.foo.premium : undefined,
				prixMin :  $scope.foo.prix.min*100,
				prixMax: $scope.foo.prix.max*100,
				dureeMin: $scope.foo.duree.min,
				dureeMax : $scope.foo.duree.max,
				noteMinimal :	$scope.foo.note,
				ageValide : $scope.foo.age? $scope.foo.age : undefined,
				villeOuCP : $scope.foo.ville == "" ? undefined : $scope.foo.ville,
				idTypeCuisine : $scope.foo.typeCuisine.id == ($scope.cook.length) ? undefined : $scope.foo.typeCuisine.id,
				animaux : $scope.foo.animaux ? !$scope.foo.animaux : undefined,
				nbPlaceRestanteMinimum : $scope.foo.places == 0 ? undefined : $scope.foo.places,
				date : $scope.foo.date == undefined ? $scope.foo.date : date
				
		}
		console.log(donnees);
		// On envoie les données
		$http({
			method: 'POST',
			url: '/offres/recherche',
			contentType: "application/json",
			data: donnees
			}).success(
			function(donnees) {
			$scope.list = donnees;
			for(var i=0;i<donnees.length; i++){
				$scope.getNote(donnees[i]);
				$scope.getNbPlaces(donnees[i]);
			}
			$scope.nombrePlaces =0;	
			$scope.noteMoyenne = 0;
			});

		
	};
	// On se connecte à la route consacrée pour récupèrer les offres
	$http.get('/offres').success(
			function(donnees) {
				
				$scope.nombrePlaces =0;	
				$scope.noteMoyenne = 0;
				for(var i=0;i<donnees.data.length; i++){
					$scope.getNote(donnees.data[i]);
					$scope.getNbPlaces(donnees.data[i]);
					
				}
				$scope.list = donnees.data;
				
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
}).directive('dateTimePicker', dateTimePicker);


