// Chargement du module "Profil"
var app = angular.module("Profil",  ['ngAnimate','ngMaterial', 'ngMessages']);



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
app.controller("ProfilCtrl", function($scope, $http) {
	
	//affichage des input d'edition du profil
	$scope.editBio=false;
	 
    $scope.editAdr =false;
	// On va se connecter sur la route permettant de récupèrer le profil de l'utilisateur
	$http.get('/utilisateur/particulier/profil').success(
		function(donnees) {
			// Quand on reçoit les données, on les envoie à la vue (stockage dans la variable profil)
			$scope.profil = donnees;
		}
	);
	
	// On va rechercher toutes les pays en se connectant à la route consacrée
	$http.get('/settings/pays').success(
			function(donnees) {
				$scope.count = donnees;
				$scope.saveCountry = $scope.count;
			}
	);

	
	  $scope.hoverIn = function(){
	        this.hoverEdit = true;
	     };

	     
	    $scope.hoverOut = function(){
	        this.hoverEdit = false;
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
	    
	    
	    // decoupe l'adresse si exsistante
	    $scope.homeAction = function() {
	    	
			if($scope.editAdr){

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
		
		
		
		 $scope.submitEdition = function(){
			 if ($scope.ProfilForm.$valid){
				 
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
					//build de l'adresse
					var adresse = {
							voie : $scope.numero + " " + $scope.rue + "" + $scope.complement,
							ville : ville,
					};
					
					var donnee = {
							adresse : adresse,
							description : profil.description,
							image : null
							
							
					}
			
				 
				 console.log('valid');
				 
				 
				 
			 }
		 };
	    
	
});