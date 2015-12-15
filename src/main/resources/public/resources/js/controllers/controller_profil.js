// Chargement du module "Profil"
var app = angular.module("Profil",  ['ngAnimate','ngMaterial', 'ngFileUpload', 'ngMessages', 'appFilters']);

app.controller("LogoutCtrl", function($scope, $http, $window) {
    
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

// Création du controller "ProfilCtrl"
app.controller("ProfilCtrl", function($scope, $http, Upload, $q) {
	
	//affichage des inputs d'edition du profil
	$scope.editBio=false;
	$scope.editPic=false;
	$scope.picEtdited=false;
    $scope.editAdr =false;
    
	// On va se connecter sur la route permettant de récupèrer le profil de l'utilisateur
	$http.get('/utilisateur/particulier/profil').success(
		function(donnees) {
			// Quand on reçoit les données, on les envoie à la vue (stockage dans la variable profil)
			$scope.profil = donnees.data;
			
			//checkbox visible
			$scope.visible = $scope.profil.adresseVisible;
			
		}
	);
	
	// On va rechercher les repas auxquels l'utilisateur a participé.
	$http.get('/offres/aParticipe').success(
			function(donnees) {
				// Quand on reçoit les données, on les envoie à la vue
				$scope.listeRepas = donnees.data;
			}
		);	
	
	// On va rechercher toutes les pays en se connectant à la route consacrée
	$http.get('/settings/pays').success(
			function(donnees) {
				$scope.count = donnees.data;
				$scope.saveCountry = $scope.count;
			}
	);

	// Avertissements pour les hoverOut/hoverIn :
	$scope.hoverInPic = function(){
		this.hoverEditPic = true;
	};

	$scope.hoverOutPic = function(){
		this.hoverEditPic = false;
	};

	$scope.hoverInBio = function(){
		this.hoverEditBio = true;
	};

	$scope.hoverOutBio = function(){
		this.hoverEditBio = false;
	};

	$scope.hoverInAdr = function(){
		this.hoverEditAdr = true;
	};

	$scope.hoverOutAdr = function(){
		this.hoverEditAdr = false;
	};

	//on affiche l'édition de la bio
	$scope.setEditBio = function(){
		$scope.editBio = true 
	};

	//on affiche l'édition de l'addresse
	$scope.setEditAdr = function(){
		$scope.editAdr = true ;
		$scope.homeAction();
	};

	//on affiche l'édition de la photo
	$scope.setEditPic = function(){
		$scope.editPic=true;
	};



	// fonction d'auto-remplissage de l'adresse.
	$scope.homeAction = function() {
		// il faut que l'utilisateur est une adresse (logiquement oui)
		if($scope.profil.adresse != null){
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

	// propose tout les pays
	$scope.allCountry = function(){
		$scope.count = $scope.saveCountry;
	};

	// ajout d'images :
	$scope.$watch('photo', function () {
		// condition : on ajoute une nouvelle photo.
		if ($scope.photo) {
			//$scope.upload($scope.photo);

			$scope.profil.image = $scope.photo;
			$scope.picEdited=true;
		}
	});

	// fonction d'upload d'une image :
	var upload = function (file) {
		// on déclare un defer() pour que les instructions suivantes attendent la fin du téléchargement.
		var deferred = $q.defer();

		if (file) {
			if (!file.$error) {
				Upload.upload({
					url: '/image',
					data: {file: file}
				}).success(function (data, status, headers, config) {
					$scope.historique = data;
		            // quand le téléchargement est fini on débloque le "defer".
		            deferred.resolve(data);
				});
			}
		}
		else {
			deferred.resolve("pas de fichier");
		}
		
        return deferred.promise;
	};
	
    // fonction d'envoi du formulaire :
	var envoi = function() {
		console.log("yop");
		if(!$scope.editAdr){
			$scope.homeAction();
		}
		
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
		
		//construction de l'adresse :

		if(angular.isUndefined($scope.complement)){
			$scope.complement=' ';
		}
		
		var adresse = {
				voie : $scope.numero + " " + $scope.rue + " " + $scope.complement,
				ville : ville,
		};
		
		var image = null;
		
		if($scope.picEdited){
			image = $scope.historique;
		}else {
			image = $scope.profil.image;
		}

		$scope.profil.adresse = adresse;
		
		//on fabrique les données  envoyer
		var donnees = {
				idUtilisateur : $scope.profil.idUtilisateur,
				adresse : adresse,
				adresseVisible: $scope.visible,
				description : $scope.profil.description,
				image : image
		};

		// On envoie les données
		$http({
			method: 'PUT',
			url: '/utilisateur/particulier/profil',
			contentType: "application/json",
			data: donnees
		}).success(function(response, status, headers, config){
			console.log(response);
			//$mdToast.show($mdToast.simple().content('Votre offre a bien été enregistrée.').hideDelay(2000));
			//$window.location.href = "/profil.html";
			$scope.editBio=false;
			$scope.editPic=false;
			$scope.editAdr =false;
		}).error(function(err, status, headers, config){
			//$mdToast.show($mdToast.simple().content('Notre service est indisponible pour le moment, veuillez réessayer plus tard.').hideDelay(2000));
			$scope.editBio=false;
			$scope.editPic=false;
			$scope.editAdr =false;
		});

	};

    // fonction de la modification de profil :
	$scope.submitEdition = function(){
		
		if ($scope.ProfilForm.$valid){
		
    		upload($scope.photo).then(function(ee) {
				envoi();
			});

		}
	};


});