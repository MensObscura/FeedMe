// Test du controller script-profil.js
describe('ProfilCtrl', function() {
	// Variables pour le scope et le controller
	var $scope, controller;

	// On créé un profil
	var unprofil = {idUtilisateur: 1, nom: 'Me', mail: 'feed.me@univ-lille1.fr', idParticulier: 1, prenom: 'Feed', dateNaissance: '2015-01-31'};

	// On se place dans le module Profil
	beforeEach(module('Profil'));

	// Initialisation du scope et du controller
	beforeEach(inject(function ($rootScope, $controller) {
		$scope = $rootScope.$new();
		controller = $controller('ProfilCtrl', {
			$scope : $scope,
		});
	}));

	it('la route /utilisateur/particulier/profil doit être appelée', inject(function($httpBackend) {
		$httpBackend.expectGET('/utilisateur/particulier/profil').respond();
		$httpBackend.flush();
	}));

	it('le profil renvoyé par la route doit être stocké dans le profil', inject(function($httpBackend) {
		$httpBackend.whenGET('/utilisateur/particulier/profil').respond(unprofil);
		$httpBackend.flush();

		expect($scope.profil).toEqual(unprofil);
	}));

});
