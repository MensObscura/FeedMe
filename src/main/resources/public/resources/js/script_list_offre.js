// Chargement du module "ListeApp"
var app = angular.module("ListeApp", []);

// Création du controller "ListeCtrl"
app.controller("ListeCtrl", function($scope, $http, $window) {
	// On se connecte à la route consacrée pour récupèrer les offres
	//$http.get('/offres').success(
	//	function(donnees) {
	//		$scope.list = donnees;
	//	}
	//);
	
    $scope.list = [ {
  "id" : 1,
  "dateCreation" : "2012-10-13",
  "titre" : "Tomates nordiques",
  "prix" : 12,
  "nombrePersonne" : 5,
  "dureeMinute" : 123,
  "dateRepas" : "2012-10-19T03:14:07",
  "note" : "Des tomates dÃ©licieuses",
  "menu" : "EntrÃ©e : Salade composÃ©e. Plat Principal : Tomates Farcies, riz Ã  la crÃ¨me fraiche et au vin blanc. Dessert : Poires belle HÃ©lÃ¨ne",
  "ageMin" : 10,
  "ageMax" : 45,
  "animaux" : true,
  "adresse" : {
    "id" : 1,
    "voie" : "45 Rue des sacres",
    "ville" : {
      "id" : 1,
      "nom" : "Lille",
      "cp" : "59650",
      "pays" : {
        "id" : 1,
        "codePays" : "FR",
        "nom" : "France"
      }
    }
  },
  "typeCuisine" : {
    "id" : 1,
    "type" : "Cuisine rÃ©gionale"
  },
  "hote" : {
    "idUtilisateur" : 1,
    "nom" : "Me",
    "mail" : "feed.me@univ-lille1.fr"
  },
  "reservations" : [ ]
}, {
  "id" : 2,
  "dateCreation" : "2012-10-13",
  "titre" : "Poulet subsaharien",
  "prix" : 19,
  "nombrePersonne" : 4,
  "dureeMinute" : 168,
  "dateRepas" : "2012-10-19T03:14:07",
  "note" : "Attention les arachides sont un allergÃ¨ne",
  "menu" : "EntrÃ©e : Buffet froid ,Plat : Poulet aux arachides, riz, carottes, piment. Dessert : Kakis et autres fruits exotiques",
  "ageMin" : 18,
  "ageMax" : 29,
  "animaux" : true,
  "adresse" : {
    "id" : 2,
    "voie" : "21 Avenue des champs",
    "ville" : {
      "id" : 2,
      "nom" : "Reims",
      "cp" : "51100",
      "pays" : {
        "id" : 1,
        "codePays" : "FR",
        "nom" : "France"
      }
    }
  },
  "typeCuisine" : {
    "id" : 1,
    "type" : "Cuisine rÃ©gionale"
  },
  "hote" : {
    "idUtilisateur" : 1,
    "nom" : "Me",
    "mail" : "feed.me@univ-lille1.fr"
  },
  "reservations" : [ ]
}, {
  "id" : 3,
  "dateCreation" : "2012-10-13",
  "titre" : "Tomates nordiques",
  "prix" : 12,
  "nombrePersonne" : 5,
  "dureeMinute" : 123,
  "dateRepas" : "2012-10-19T03:14:07",
  "note" : "Des tomates dÃ©licieuses",
  "menu" : "EntrÃ©e : Salade composÃ©e. Plat Principal : Tomates Farcies, riz Ã  la crÃ¨me fraiche et au vin blanc. Dessert : Poires belle HÃ©lÃ¨ne",
  "ageMin" : 10,
  "ageMax" : 45,
  "animaux" : true,
  "adresse" : {
    "id" : 1,
    "voie" : "45 Rue des sacres",
    "ville" : {
      "id" : 1,
      "nom" : "Lille",
      "cp" : "59650",
      "pays" : {
        "id" : 1,
        "codePays" : "FR",
        "nom" : "France"
      }
    }
  },
  "typeCuisine" : {
    "id" : 1,
    "type" : "Cuisine rÃ©gionale"
  },
  "hote" : {
    "idUtilisateur" : 1,
    "nom" : "Me",
    "mail" : "feed.me@univ-lille1.fr"
  },
  "reservations" : [ ]
}, {
  "id" : 4,
  "dateCreation" : "2012-10-13",
  "titre" : "Poulet subsaharien",
  "prix" : 19,
  "nombrePersonne" : 4,
  "dureeMinute" : 168,
  "dateRepas" : "2012-10-19T03:14:07",
  "note" : "Attention les arachides sont un allergÃ¨ne",
  "menu" : "EntrÃ©e : Buffet froid ,Plat : Poulet aux arachides, riz, carottes, piment. Dessert : Kakis et autres fruits exotiques",
  "ageMin" : 18,
  "ageMax" : 29,
  "animaux" : true,
  "adresse" : {
    "id" : 2,
    "voie" : "21 Avenue des champs",
    "ville" : {
      "id" : 2,
      "nom" : "Reims",
      "cp" : "51100",
      "pays" : {
        "id" : 1,
        "codePays" : "FR",
        "nom" : "France"
      }
    }
  },
  "typeCuisine" : {
    "id" : 1,
    "type" : "Cuisine rÃ©gionale"
  },
  "hote" : {
    "idUtilisateur" : 1,
    "nom" : "Me",
    "mail" : "feed.me@univ-lille1.fr"
  },
  "reservations" : [ ]
} ]
    
    
    
	// Permet de créer un listener qui va rediriger vers la visualisation de l'offre cliquée
	$scope.visualize = function (valeur) {
		   $window.location.href = "/offre.html?id="+valeur.id;
	};
 
});


