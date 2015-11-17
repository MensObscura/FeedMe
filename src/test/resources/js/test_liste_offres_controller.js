// Test du controller script-list-offre.js
describe('ListeCtrl', function() {
	// Variables pour le scope et le controller
	var $scope, controller;
	// Variable du mock pour la redirection
	var windowMock = {location: {href: ''}};

	// On créé 3 offres (= 3 id différents)
	var offre1 = { id: 1, dateCreation: '2012-10-13', titre: 'Chocolat Farcis', prix: 12,
		nombrePersonne: 5, dureeMinute: 123, dateRepas: '2012-10-19T03:14:07', note: 'Chocolat noir et bio',
		menu: 'Farce de foie de canard dans son coufin de chocolat', ageMin: 10, ageMax: 25, animaux: false,
		adresse: { id: 1, voie: '45 Rue des poissons', ville: { id: 1, nom: 'Lille', cp: '59650',
		pays: { id: 1, codePays: 'FR', nom: 'France'}}}, typeCuisine: { id: 1, typeCuisine: 'Cuisine régionale'},
		hote: { idUtilisateur : 1, nom: 'Me', mail: 'feed.me@univ-lille1.fr'}, reservations: [{id: 2,
		offre: {id: 1}, convive: { idUtilisateur: 1}, dateReservation: '2015-10-16', nb_places: 2} ]};
	var offre2 = { id: 2, dateCreation: '2012-10-13', titre: 'Chocolat Farcis', prix: 12,
		nombrePersonne: 5, dureeMinute: 123, dateRepas: '2012-10-19T03:14:07', note: 'Chocolat noir et bio',
		menu: 'Farce de foie de canard dans son coufin de chocolat', ageMin: 10, ageMax: 25, animaux: false,
		adresse: { id: 1, voie: '45 Rue des poissons', ville: { id: 1, nom: 'Lille', cp: '59650',
		pays: { id: 1, codePays: 'FR', nom: 'France'}}}, typeCuisine: { id: 1, typeCuisine: 'Cuisine régionale'},
		hote: { idUtilisateur : 1, nom: 'Me', mail: 'feed.me@univ-lille1.fr'}, reservations: [{id: 2,
		offre: {id: 1}, convive: { idUtilisateur: 1}, dateReservation: '2015-10-16', nb_places: 2} ]};
	var offre3 = { id: 3, dateCreation: '2012-10-13', titre: 'Chocolat Farcis', prix: 12,
		nombrePersonne: 5, dureeMinute: 123, dateRepas: '2012-10-19T03:14:07', note: 'Chocolat noir et bio',
		menu: 'Farce de foie de canard dans son coufin de chocolat', ageMin: 10, ageMax: 25, animaux: false,
		adresse: { id: 1, voie: '45 Rue des poissons', ville: { id: 1, nom: 'Lille', cp: '59650',
		pays: { id: 1, codePays: 'FR', nom: 'France'}}}, typeCuisine: { id: 1, typeCuisine: 'Cuisine régionale'},
		hote: { idUtilisateur : 1, nom: 'Me', mail: 'feed.me@univ-lille1.fr'}, reservations: [{id: 2,
		offre: {id: 1}, convive: { idUtilisateur: 1}, dateReservation: '2015-10-16', nb_places: 2} ]};

	// lesoffres est un tableau contenant les offres précédentes
	var lesoffres = [offre1, offre2, offre3];

	// On se place dans le module ListeApp
	beforeEach(module('ListeApp'));
	
	// Initialisation du mock pour la redirection
	beforeEach(module(function($provide) {
		$provide.value('$window', windowMock);
	}));
	
	// Initialisation du scope et du controller
	beforeEach(inject(function ($rootScope, $controller) {
		$scope = $rootScope.$new();
		controller = $controller('ListeCtrl', {
			$scope : $scope,
		});
	}));

	it('la route /offres doit être appelée', inject(function($httpBackend) {
		$httpBackend.expectGET('/offres').respond();
		$httpBackend.flush();
	}));

	it('les offres renvoyées par la route doivent être stockées dans list', inject(function($httpBackend) {
		$httpBackend.whenGET('/offres').respond(lesoffres);
		$httpBackend.flush();

		expect($scope.list).toEqual(lesoffres);
	}));


	it('pour chaque offre, on doit rediriger sur la bonne page', inject(function($httpBackend) {
		$httpBackend.whenGET('/offres').respond(lesoffres);
		$httpBackend.flush();

		$scope.visualize($scope.list[0]);
		var url = "/offre.html?id="+$scope.list[0].id;
		expect(windowMock.location.href).toEqual(url);

		$scope.visualize($scope.list[1]);
		var url = "/offre.html?id="+$scope.list[1].id;
		expect(windowMock.location.href).toEqual(url);

		$scope.visualize($scope.list[2]);
		var url = "/offre.html?id="+$scope.list[2].id;
		expect(windowMock.location.href).toEqual(url);

	}));

});
