// Test du controller script-profil.js
describe('ReservationController', function() {
	// Variables pour le scope et le controller
	var $scope, controller, form;

	// Variable du mock pour le formulaire HTML
	var formulaire = '<form name="ReservationForm" ng-submit="submitForm()" novalidate>'
+'<input type="number" name="place" ng-model="place" step="0" ng-required="true" min="1" max="{{nombreRestant}}" id="Nbplace" value="n"/> couvert(s)'
+'<span style="color:red" ng-show="ReservationForm.place.$invalid && !ReservationForm.place.$pristine">'
+'<span ng-show="ReservationForm.place.$error.required">Veuillez saisir un nombre de place</span>'
+'<span ng-show="ReservationForm.place.$error.number">Veuillez saisir un nombre.</span>'
+'<span ng-show="ReservationForm.place.$error.min">Vous devez reserver au moins un repas.</span>'
+'<span ng-show="ReservationForm.place.$error.max">Vous ne pouvez pas reserver autant de places.</span>'
+'</span>'
+'<input type="submit" ng-disabled="ReservationForm.$invalid" value="Réserver">'
+'</form>';

	// Variable du mock pour la redirection
	var windowMock = {location: {href: '/offre.html?id=1'}};

	// Création d'une offre de test
	var uneoffre = { id: 1, dateCreation: '2012-10-13', titre: 'Chocolat Farcis', prix: 12,
		nombrePersonne: 5, dureeMinute: 123, dateRepas: '2012-10-19T03:14:07', note: 'Chocolat noir et bio',
		menu: 'Farce de foie de canard dans son coufin de chocolat', ageMin: 20, ageMax: 25, animaux: true,
		adresse: { id: 1, voie: '45 Rue des poissons', ville: { id: 1, nom: 'Lille', cp: '59650',
		pays: { id: 1, codePays: 'FR', nom: 'France'}}}, typeCuisine: { id: 1, typeCuisine: 'Cuisine régionale'},
		hote: { idUtilisateur : 1, nom: 'Me', mail: 'feed.me@univ-lille1.fr'}, reservations: [{id: 2,
		offre: {id: 1}, convive: { idUtilisateur: 1}, dateReservation: '2015-10-16', nb_places: 2} ]};

	// On se place dans le module Profil
	beforeEach(module('OffreApp'));

	// Initialisation du scope et du controller
	beforeEach(inject(function ($rootScope, $controller, $compile) {
		$scope = $rootScope.$new();
		controller = $controller('ReservationController', {
			$scope : $scope,
			$window : windowMock
		});

		$compile(formulaire)($scope);
		form = $scope.ReservationForm;

	}));

	it('test de getUrlVars() avec un paramètre présent dans l\'url', function() {
		expect($scope.getUrlVars()['id']).toBeDefined();
		expect($scope.getUrlVars()['id']).toEqual('1');
	});

	it('test de getUrlVars() avec un paramètre absent de l\'url', function() {
		expect($scope.getUrlVars()['offre']).toBeUndefined();
	});

	it('la route /offres/1 doit être appelée', inject(function($httpBackend) {
		$httpBackend.expectGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();
	}));

	it('cette offre renvoyée par la route doit être stockée dans offre', inject(function($httpBackend) {
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		expect($scope.offre).toEqual(uneoffre);
	}));

	it('test de l\'affichage de la variable couverts_restants quand il reste des places', inject(function($httpBackend) {
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		expect($scope.nombreRestant).toBe(3);
		expect($scope.couverts_restants).toEqual('3 sur 5');
	}));

	it('test de l\'affichage de la variable couverts_restants quand il ne reste aucune place', inject(function($httpBackend) {
		uneoffre.reservations[0].nb_places = 5;
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		expect($scope.nombreRestant).toBe(0);
		expect($scope.couverts_restants).toEqual('COMPLET');
	}));

	it('test de l\'affichage de la variable animaux quand il y a un animal de compagnie', inject(function($httpBackend) {
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		expect($scope.offre.animaux).toBe(true);
		expect($scope.animaux).toBeDefined();
	}));

	it('test de l\'affichage de la variable animaux quand il n\'y a pas d\'animal de compagnie', inject(function($httpBackend) {
		uneoffre.animaux = false;
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		expect($scope.offre.animaux).toBe(false);
		expect($scope.animaux).toBeUndefined();
	}));


	it('test de l\'affichage de la variable note quand il y en a une', inject(function($httpBackend) {
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		expect($scope.offre.note).toBeDefined();
		expect($scope.note).toBeDefined();
	}));

	it('test de l\'affichage de la variable note quand il n\'y en a pas', inject(function($httpBackend) {
		uneoffre.note = undefined;
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		expect($scope.offre.note).toBeUndefined();
		expect($scope.note).toBeUndefined();
	}));

	it('test de l\'affichage de la variable age quand on a un ageMin et un ageMax', inject(function($httpBackend) {
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		expect($scope.offre.ageMin).toBe(20);
		expect($scope.offre.ageMax).toBe(25);
		expect($scope.age).toEqual('Ce repas s\'adresse aux 20-25 ans.');
	}));

	it('test de l\'affichage de la variable age quand on a seulement un ageMax', inject(function($httpBackend) {
		uneoffre.ageMin = undefined;
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		expect($scope.offre.ageMin).toBeUndefined();
		expect($scope.offre.ageMax).toBe(25);
		expect($scope.age).toEqual('Ce repas s\'adresse aux moins de 25 ans.');
	}));

	it('test de l\'affichage de la variable age quand on a ni ageMin ni ageMax', inject(function($httpBackend) {
		uneoffre.ageMax = undefined;
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		expect($scope.offre.ageMin).toBeUndefined();
		expect($scope.offre.ageMax).toBeUndefined();
		expect($scope.age).toBeUndefined();
	}));

	it('test de l\'affichage de la variable age quand on a seulement un ageMin', inject(function($httpBackend) {
		uneoffre.ageMin = 20;
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		expect($scope.offre.ageMin).toBe(20);
		expect($scope.offre.ageMax).toBeUndefined();
		expect($scope.age).toEqual('Ce repas s\'adresse aux plus de 20 ans.');
	}));

	it('test du validateur : le nombre de place est requis', inject(function($httpBackend) {
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		form.place.$setViewValue('')

		expect(form.place.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le nombre de place doit être un nombre', inject(function($httpBackend) {
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		form.place.$setViewValue('blabla')

		expect(form.place.$error.number).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le nombre de place doit être supérieur à 0 ', inject(function($httpBackend) {
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		form.place.$setViewValue('0')

		expect(form.place.$error.min).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le nombre de place doit être inférieur à 3 ', inject(function($httpBackend) {
		uneoffre.reservations[0].nb_places = 3;
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		form.place.$setViewValue('3')

		expect(form.place.$error.max).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le nombre de place 2 est valide -> PUT sur reservation', inject(function($httpBackend) {
		$httpBackend.whenGET('/offres/1').respond(uneoffre);
		$httpBackend.flush();

		form.place.$setViewValue('2')

		expect(form.place.$valid).toBe(true);
		expect(form.$valid).toBe(true);
		
		$scope.submitForm();
		$httpBackend.expectPUT('/reservation').respond();
		$httpBackend.flush();
		expect(windowMock.location.href).toEqual('/liste_offres.html');

	}));

});
