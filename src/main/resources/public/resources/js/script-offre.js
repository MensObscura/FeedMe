// Chargement du module "OffreApp"
var app = angular.module("OffreApp", []);

//Création du controller "ReservationController"
app.controller('ReservationController', function($scope, $http, $window) {  

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
				$scope.offre = data;
				// On calcule le nombre de places restantes, que l'on transfert aussi à la vue
				var place_reservees = 0;
				for (i = 0; i < data.reservations.length; i++) {
					place_reservees += data.reservations[i].nbPlaces;
				}
				$scope.nombreRestant = data.nombrePersonne - place_reservees;
				
				// Si le nombre de places restantes est 0, on affiche "complet"
				if ($scope.nombreRestant == 0) {
					$scope.couverts_restants = "COMPLET"
				}
				else {
					$scope.couverts_restants = $scope.nombreRestant+" sur "+data.nombrePersonne;
				}
				
				// On met à jour la rubrique "animal" s'il existe des données
				if (data.animaux)
					$scope.animaux = "Un animal de compagnie sera présent lors du repas.";
				
				// On met à jour la rubrique "age" s'il existe des données
				var age = "Ce repas s'adresse aux ";
				if (data.ageMin) {
					if (data.ageMax) {
						// Les deux bornes sont remplies.
						$scope.age = age + data.ageMin + "-" + data.ageMax + " ans.";
					}
					else {
						// On a juste l'âge minimum.
						$scope.age = age + "plus de " + data.ageMin + " ans.";
					}
				}
				else {
					if (data.ageMax) {
						// On a juste l'âge maximum.
						$scope.age = age + "moins de " + data.ageMax + " ans.";
					}
				}
				// On met à jour la rubrique "note" s'il existe des données
				if (data.note)
					$scope.note = data.note;
				}
	);

	// Fonction utilisé lors de la validation du formulaire de reservation
	$scope.submitForm = function() {
		if ($scope.ReservationForm.$valid) {
			
			// Première étape : convertir la date est la mettre sous la bonne forme
			var aujourdhui = new Date();
			var date = aujourdhui.toLocaleFormat('%Y-%m-%d');

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
				// DECLENCHEMENT D'UN TOASTER ICI : Votre réservation a été enregistrée
				$window.location.href = "/liste_offres.html";
			}).error(function(err, status, headers, config){
				// DECLENCHEMENT D'UN TOASTER ICI : Vous avez déja reservé une place pour cette offre
			});
			
		}

	};

});