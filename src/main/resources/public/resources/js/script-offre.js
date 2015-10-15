// Chargement du module "OffreApp"
var app = angular.module("OffreApp", []);

//Création du controller "ReservationController"
app.controller('ReservationController', function($scope, $http, $location, $filter) {  

	// On récupère l'identifiant de l'offre à afficher
	$scope.$location = $location;
	$scope.$location.url(window.location);
	var id = $scope.$location.search().id ; 
	
	// On en déduit la route sur laquelle se connecter
	var route = 'http://localhost:8080/offres/'+id;

	// On se branche dessus
	$http.get(route).success(
			function(data) {
				// On transfert dans "offre" les données
				$scope.offre = data;
				// On calcule le nombre de places restantes, que l'on transfert aussi à la vue
				$scope.nombreRestant = $scope.offre.nombrePersonne - $scope.offre.reservations.length;
				
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
						$scope.age = age + "moins de " + data.ageMin + " ans.";
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
			var date = "";
			if ((aujourdhui.getMonth()+1) < 10)
				date = aujourdhui.getFullYear()+'-0'+(aujourdhui.getMonth()+1)+'-'+aujourdhui.getDate();
			else
				date = aujourdhui.getFullYear()+'-'+(aujourdhui.getMonth()+1)+'-'+aujourdhui.getDate();

			// On constitue les données
			var data = {
					//nombre : $scope.place,     !!!!!!! 
					offre : $scope.offre,
					dateReservation : date,
			};

			// On les envoieS
			$http({
				method: 'PUT',
				url: 'http://localhost:8080/reservation',
				contentType: "application/json",
				data: data
			}).success(function(response, status, headers, config){
				// UN TOASTER ICI !!!!
				window.location.href = "liste_offres.html";
			}).error(function(err, status, headers, config){
				// UN TOASTER ICI !!!!
			});
			
		}

	};

});