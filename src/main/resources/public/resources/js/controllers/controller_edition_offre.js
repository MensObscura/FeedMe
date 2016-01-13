// Ajout du DateTimePicker.
var dateTimePicker = function() {
     return {
        restrict: "A",
        require: "ngModel",
        link: function (scope, element, attrs, ngModelCtrl) {
	        var parent = $(element).parent();
	                    
	        var min = new Date();
	        min.setHours(min.getHours()+2); // fixation de la date minimale selectionnable : aujourd'hui + 2h.
	                    
	        var dtp = parent.datetimepicker({
	               format: 'DD/MM/YYYY HH:mm', // la date doit contenir le jour, le mois, l'année ainsi que les heures et minutes.
	               ignoreReadonly: true, // la case est en readOnly, il faut lui indiquer qu'on autorise ce cas.
	               minDate: min // date minimale.
	         });
	        dtp.on("dp.change", function (e) {
	               ngModelCtrl.$setViewValue(new Date(e.date));
	               scope.$apply(); // Quand on selectionne une date on affecte au model la date en en question.
	        });
        }
     };
};

//Chargement du module "validationOffre"
var validationApp = angular.module('validationOffre', ['ngMaterial', 'ngMessages','ui-rangeSlider','ui.bootstrap', 'ngFileUpload', 'angular-carousel', 'appFilters']);

validationApp.controller("LogoutCtrl", function($scope, $http, $window, $interval) {
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
//Création du controller "OffreCtrl"
validationApp.controller('OffreCtrl', function($scope, $http, $window, $mdToast, $location, $anchorScroll, Upload, $q) {
	
	// initialisation de l'affichage photo à false
	$scope.allowDisplay =false;

	// on initialise les images.
	$scope.images=[];
		
	// affichage photo
	$scope.display= function(img) {
		$scope.allowDisplay =true;
		$scope.current = img;
		
		$location.hash('StyleFormulaireOffre');
		$anchorScroll();
	};
	// disable photo
	$scope.disable= function() {
		$scope.allowDisplay =false;
	};
	
	// initialisation de la borne d'âges
	$scope.age = {
		min: 18,
		max: 100
	};
	
	// initialement le formulaire n'est pas soumis.
	$scope.submited =false;
	
	$http.get('/utilisateur/particulier/profil').success(
			function(donnees) {
				// Quand on reçoit les données, on les envoie à la vue (stockage dans la variable profil)
				$scope.profil = donnees.data;
				$scope.premium = $scope.profil.premium;

			}
	);
	
	// Fonction permettant de récupérer les paramètres de l'url.
	$scope.getUrlVars = function() {
		var vars = {};
	    var parts = $window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,    
	    function(m,cle,valeur) {
	      vars[cle] = valeur;
	    });
	    return vars;
	}
	
	// On va rechercher toutes les types de cuisine en se connectant à la route consacrée
	$http.get('/settings/typescuisines').success(
			function(donnees) {
				$scope.cook = donnees.data;
				$scope.saveCook = $scope.cook;
			}
	);

	// On va rechercher toutes les pays en se connectant à la route consacrée
	$http.get('/settings/pays').success(
			function(donnees) {
				$scope.count = donnees.data;
				$scope.saveCountry = $scope.count;
			}
	);
	
	// On récupère l'identifiant de l'offre à editer
	var id = $scope.getUrlVars()["id"];
	
	// On en déduit la route sur laquelle se connecter
	var route = '/offres/'+id;

	// On se branche dessus
	$http.get(route).success(
			function(data) {

				// On transfert dans "offre" les données
				$scope.offre = data.data;
				// On set le pays
				$scope.count = {0:$scope.offre.adresse.ville.pays};
				// On set la ville 
				$scope.ville = $scope.offre.adresse.ville.nom;
				$scope.cp =$scope.offre.adresse.ville.cp;
				$scope.complement=$scope.offre.adresse.ville.complement;
				
				
				var rue = $scope.offre.adresse.voie;
				var num = rue.split(" ")[0];

				$scope.numero = parseInt(num);
				$scope.rue = rue.substring(num.length+1,rue.length);

				// On set le type de cuisine
				$scope.cook = {0:$scope.offre.typeCuisine};
			
				console.log($scope.offre.typeCuisine);
				// On set le menu

				$scope.entree = $scope.offre.menu.entree;
				$scope.plat = $scope.offre.menu.plat;
				$scope.dessert = $scope.offre.menu.dessert;
				$scope.boisson = $scope.offre.menu.boisson;
				
				
				// Enfin on set le reste
			
						
						$scope.titre = $scope.offre.titre;
						$scope.prix = $scope.offre.prix/100;
						$scope.nbpers = $scope.offre.nombrePersonne;
						$scope.duree = $scope.offre.dureeMinute;
						$scope.date = $scope.offre.dateRepas ;
						$scope.note = $scope.offre.note;
						$scope.age.min = $scope.offre.ageMin;
						$scope.age.max = $scope.offre.ageMax;
						$scope.animal = $scope.offre.animal;
						
						
						$scope.images= $scope.offre.images;
						$scope.premium= $scope.offre.premium;
				});
			
	//on reremplit les selects pour la modification
	$scope.refillCountry = function(){
	
		
	
		$scope.count = $scope.saveCountry;
		
	}
	$scope.refillCook = function(){
		
		
		
		$scope.cook = $scope.saveCook;
		
	}

	// fonction d'auto-remplissage de l'adresse.
	$scope.homeAction = function() {
		// il faut que l'utilisateur est une adresse (logiquement oui)
		if($scope.home){
			// remplissage des champs en conséquence :
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



	// pré-remplissage des champs 'durée', 'nbpers' et 'prix' par le minimum attendu
	$scope.duree = 60;
	$scope.prix = 1;
	$scope.nbpers = 1;
	$scope.images = new Array();
	$scope.complement = "";
	
	// ajout d'images :
	$scope.$watch('fichiers', function () {
		// condition : on ajoute une nouvelle photo.
		if ($scope.fichiers) {
			if (!$scope.fichiers.length && !$scope.premium) {
				// l'utilisateur n'a encore uploadé de photos ou l'offre n'est pas premium,
				// On initialise l'image avec celle selectionnée.
				$scope.images = [$scope.fichiers];
			}
			else {
				// dans le cas ou l'utilisateur est premium on ajoute les nouvelles photos à la liste.
				$scope.images = $scope.images.concat($scope.fichiers);
			}

		}
    });
	
	// fonction d'upload d'une image :
	var upload = function (file) {
		// on déclare un defer() pour que les instructions suivantes attendent la fin du téléchargement.
		var deferred = $q.defer();

        Upload.upload({
            url: '/image',
            data: {file: file}
        }).success(function (data, status, headers, config) {
            $scope.historique.push(data.data);
            // quand le téléchargement est fini on débloque le "defer".
            deferred.resolve(data.data);
        });

        return deferred.promise;
    };
    
    // fonction d'upload de toutes les images :
    var uploadAll = function(i, files) {
    	if (i < files.length) {
    		// on parcourt la liste et on télécharge une à une les images.
    		upload(files[i]).then(function(ee) {
				uploadAll(i+1, files);
				if (i == files.length-1) {
					envoi();
				}
			});
    	}
    };
    
    // fonction d'envoi du formulaire :
    var envoi = function() {
    	
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
		
		// On créé un objet pour le menu
		var menu = {
				entree: $scope.entree,
				plat: $scope.plat,
				dessert: $scope.dessert,
				boisson: $scope.boisson
		};
		
		// Enfin on peut créer les données que l'on souhaite envoyer
		var donnees = {
				id : $scope.offre.id,
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
    		method: 'POST',
    		url: '/offres',
    		contentType: "application/json",
    		data: donnees
 		}).success(function(response, status, headers, config){
 			//$mdToast.show($mdToast.simple().content('Votre offre a bien été enregistrée.').hideDelay(2000));
 			$window.location.href = "/liste_offres.html";
 		}).error(function(err, status, headers, config){
  			//$mdToast.show($mdToast.simple().content('Notre service est indisponible pour le moment, veuillez réessayer plus tard.').hideDelay(2000));
 		});
    }
    
    // Fonction utilisée lors de la suppression d'une image (clic sur la preview)
    $scope.supprimer = function(file) {
    	var index = $scope.images.indexOf(file);
    	$scope.images.splice(index, 1);
    };
	
	// Fonction utilisée lors de la validation du formulaire
	$scope.submitForm = function() {
					
		if ($scope.OffreForm.$valid) {

			$scope.historique = new Array();
			
		
			if ($scope.images.length > 0){
							
								uploadAll(0,$scope.images);
							}
							else{
								envoi();
							}
			
		}else{
			
			// the element you wish to scroll to.
			$location.hash('top');   
			// call $anchorScroll()
			$anchorScroll();
			   
		}
	};
}).directive('dateTimePicker', dateTimePicker);
