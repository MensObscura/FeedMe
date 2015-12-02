var dateTimePicker = function() {
     return {
        restrict: "A",
        require: "ngModel",
        link: function (scope, element, attrs, ngModelCtrl) {
	        var parent = $(element).parent();
	                    
	        var min = new Date();
	        min.setHours(min.getHours()+2); 
	                    
	        var dtp = parent.datetimepicker({
	               format: 'DD/MM/YYYY HH:mm',
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


    
//Chargement du module "validationOffre"
var validationApp = angular.module('validationOffre', ['ngMaterial', 'ngMessages','ui-rangeSlider', 'ngFileUpload', 'angular-carousel']);


validationApp.controller("LogoutCtrl", function($scope, $http, $window) {
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
//Création du controller "OffreCtrl"
validationApp.controller('OffreCtrl', function($scope, $http, $window, $mdToast, $location, $anchorScroll, Upload) {

	//init affichage photo à false
	$scope.allowDisplay =false;

	// on init images à 0
	$scope.images=[];
		
	// affichage photo
	$scope.display= function(img) {
		console.log("laoow");
		$scope.allowDisplay =true;
		$scope.current = img;
		
		$location.hash('StyleFormulaireOffre');
		$anchorScroll();
	};
	// disable photo
	$scope.disable= function() {
		console.log("disble");
		$scope.allowDisplay =false;
	};
	
	//borne d'age
	$scope.age = {
		min: 18,
		max: 100
	};
	
	$scope.submited =false;
	
	$http.get('/utilisateur/particulier/profil').success(
			function(donnees) {
				// Quand on reçoit les données, on les envoie à la vue (stockage dans la variable profil)
				$scope.profil = donnees;
				$scope.premium = $scope.profil.premium;

			}
	);
	
	$scope.homeAction = function() {

		if($scope.home){

			var rue = $scope.profil.adresse.voie;
			var num = rue.split(" ")[0];
			
			$scope.numero = parseInt(num);
			$scope.rue = rue.substring(num.length+1,rue.length);
			$scope.ville = $scope.profil.adresse.ville.nom;
			$scope.cp = $scope.profil.adresse.ville.cp;
			$scope.count= {0: $scope.profil.adresse.ville.pays};

		}else{
			$scope.numero = '';
			$scope.rue = '';
			$scope.ville ='';
			$scope.cp = '';
			$scope.count = $scope.saveCountry;
		}


	};

	// On va rechercher toutes les types de cuisine en se connectant à la route consacrée
	$http.get('/settings/typescuisines').success(
			function(donnees) {
				$scope.cook = donnees;
			}
	);

	// On va rechercher toutes les pays en se connectant à la route consacrée
	$http.get('/settings/pays').success(
			function(donnees) {
				$scope.count = donnees;
				$scope.saveCountry = $scope.count;
			}
	);

	// pré-remplissage des champs 'durée', 'nbpers' et 'prix' par le minimum attendu
	$scope.duree = 60;
	$scope.prix = 1;
	$scope.nbpers = 1;

	$scope.complement = "";
		
	$scope.$watch('fichiers', function () {
		if ($scope.fichiers) {
	        $scope.upload($scope.fichiers);
	        $scope.historique = new Array();
	        	        
			if (!$scope.fichiers.length) {
				$scope.images = [$scope.fichiers];
			}
			else {
				$scope.images = $scope.fichiers;
			}
			
		}
    });
	
	$scope.historique = new Array();
	
	$scope.upload = function (files) {
        if (files) {
            for (var i = 0; i < files.length; i++) {
              var file = files[i];

              if (!file.$error) {
                Upload.upload({
                    url: '/image',
                    data: {file: file}
                }).success(function (data, status, headers, config) {
                	$scope.historique.push(data);
                });
              }
            }
        }
    };
	
	// Fonction utilisé lors de la validation du formulaire
	$scope.submitForm = function() {
					
		if ($scope.OffreForm.$valid) {

			// On récupère la date du repas
			var date_repas = new Date($scope.date);
			var aujourdhui = new Date();
			var date = moment(aujourdhui).format('YYYY-MM-DD');

			 


			// On créé on objet pays
			var pays = {
					id : $scope.pays,
			};

			// On créé un objet ville
			var ville = {
					nom : $scope.ville,
					cp : $scope.cp,
					pays : pays.id,
			};

			// On créé un objet adresse
			if(angular.isUndefined($scope.complement)){
				$scope.complement=' ';
			}
			
			var adresse = {
					voie : $scope.numero + " " + $scope.rue + " " + $scope.complement,
					ville : ville,
			};

			// On créé un objet pour le type de cuisine
			var typeCuisine = {
					id : $scope.typeCuisine.id,
			};
			
			var menu = {
					entree: $scope.entree,
					plat: $scope.plat,
					dessert: $scope.dessert,
					boisson: $scope.boisson
			};
			
			// Enfin on peut créer les données que l'on souhaite envoyer
			var donnees = {
					dateCreation : date,
					titre : $scope.titre,
					prix : parseFloat($scope.prix)*100,
					nombrePersonne : parseInt($scope.nbpers),
					dureeMinute : parseInt($scope.duree),
					dateRepas : moment(date_repas).format('YYYY-MM-DDThh:mm:ss'),
					note : $scope.note, //optionnel
					menu : menu,
					ageMin : $scope.age.min,
					ageMax : $scope.age.max,
					animaux : Boolean($scope.animal),
					adresse : adresse,
					typeCuisine : typeCuisine,
					images : $scope.historique,
					premium : $scope.profil.premium || $scope.premium
			};
			
				

		    // On envoie les données
            $http({
        		method: 'PUT',
        		url: '/offres',
        		contentType: "application/json",
        		data: donnees
     		}).success(function(response, status, headers, config){
     			//$mdToast.show($mdToast.simple().content('Votre offre a bien été enregistrée.').hideDelay(2000));
     			$window.location.href = "/liste_offres.html";
     		}).error(function(err, status, headers, config){
      			//$mdToast.show($mdToast.simple().content('Notre service est indisponible pour le moment, veuillez réessayer plus tard.').hideDelay(2000));
     		});

		}else{
			
			
			      // the element you wish to scroll to.
			   $location.hash('top');   
			      // call $anchorScroll()
			   $anchorScroll();
			   
		}
	};
}).directive('dateTimePicker', dateTimePicker);

