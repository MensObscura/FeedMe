// Test du controller script-inscription.js
describe('InscriptionCtrl', function() {
	// Variables pour le scope et le controller
	var $scope, controller, form;

	// Variable du mock pour le formulaire HTML
	var formulaire = '<form name="InscriptionForm" ng-submit="submitForm()" novalidate>'
+'<input type="text" name="nom" ng-model="nom" ng-required="true" placeholder="Nom">'
+'<span style="color:red" ng-show="InscriptionForm.nom.$invalid && !InscriptionForm.nom.$pristine">'
+'<span ng-show="InscriptionForm.nom.$error.required">Veuillez saisir votre nom.</span>'
+'</span>'
+'<input type="text" name="prenom" ng-model="prenom" ng-required="true" placeholder="Prénom">'
+'<span style="color:red" ng-show="InscriptionForm.prenom.$invalid && !InscriptionForm.prenom.$pristine">'
+'<span ng-show="InscriptionForm.prenom.$error.required">Veuillez saisir votre prénom.</span>'
+'</span>'
+'<input type="password" name="mdp" ng-model="mdp" ng-required="true" placeholder="Mot de passe">'
+'<span style="color:red" ng-show="InscriptionForm.mdp.$invalid && !InscriptionForm.mdp.$pristine"><span ng-show="InscriptionForm.mdp.$error.required">Veuillez saisir un mot de passe.</span>'
+'</span>'
+'<input type="password" name="cmdp" ng-model="cmdp" ng-required="true" placeholder="Mot de passe" ensure-expression="mdp == cmdp">'
+'<span style="color:red" ng-show="InscriptionForm.cmdp.$invalid && !InscriptionForm.cmdp.$pristine">'
+'<span ng-show="InscriptionForm.cmdp.$error.required">Veuillez confirmer le mot de passe.</span>'
+'<span ng-show="InscriptionForm.cmdp.$error.mauvaismdp && !InscriptionForm.cmdp.$error.required && !InscriptionForm.mdp.$error.required">Les mots de passe sont différents.</span>'
+'</span>'
+'<input type="email" name="email" ng-model="email" ng-required="true" placeholder="Adresse mail">'
+'<span style="color:red" ng-show="InscriptionForm.email.$invalid && !InscriptionForm.email.$pristine">'
+'<span ng-show="InscriptionForm.email.$error.required">Veuillez saisir votre email.</span>'
+'<span ng-show="InscriptionForm.email.$error.email">Votre email est invalide.</span>'
+'</span>'
+'<button type="submit" ng-disabled="nonValide()">Confirmer</button>'
+'</form>'

	// Variable du mock pour la redirection
	var windowMock = {location: {href: ''}};


	// On se place dans le module Inscription
	beforeEach(module('Inscription'));

	// Initialisation du scope et du controller
	beforeEach(inject(function ($rootScope, $controller, $compile) {
		$scope = $rootScope.$new();
		controller = $controller('InscriptionCtrl', {
			$scope : $scope,
			$window : windowMock
		});

		$compile(formulaire)($scope);
		form = $scope.InscriptionForm;

	}));


	it('test du validateur : le nom est requis', function() {

		form.nom.$setViewValue('');

		expect(form.nom.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	});

	it('test du validateur : le nom est rempli', function() {

		form.nom.$setViewValue('De Montalembert');

		expect(form.nom.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	});

	it('test du validateur : le prénom est requis', function() {

		form.prenom.$setViewValue('');

		expect(form.prenom.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	});

	it('test du validateur : le prénom est rempli', function() {

		form.prenom.$setViewValue('Pierre-Marie');

		expect(form.prenom.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	});

	it('test du validateur : le mail est requis', function() {

		form.email.$setViewValue('');

		expect(form.email.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	});

	it('test du validateur : le mail est érroné', function() {

		form.email.$setViewValue('toto');

		expect(form.email.$error.email).toBe(true);
		expect(form.$valid).toBe(false);
	});

	it('test du validateur : le mail est rempli', function() {

		form.email.$setViewValue('toto@titi.fr');

		expect(form.email.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	});

	it('test du validateur : mot de passe requis', function() {

		form.mdp.$setViewValue('');

		expect(form.mdp.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	});

	it('test du validateur : mot de passe rempli', function() {

		form.mdp.$setViewValue('monpass');

		expect(form.mdp.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	});

	it('test du validateur : confirmation de mot de passe requise', function() {

		form.cmdp.$setViewValue('');

		expect(form.cmdp.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	});

	it('test du validateur : confirmation de mot de passe remplie sans mot de passe', function() {

		form.cmdp.$setViewValue('monpass');

		expect(form.cmdp.$valid).toBe(false);
		expect(form.$valid).toBe(false);
	});

	it('test du validateur : confirmation et mot de passe différents', function() {

		form.mdp.$setViewValue('monpass');
		form.cmdp.$setViewValue('monpass3');

		expect(form.cmdp.$error.mauvaismdp).toBe(true);
		expect(form.mdp.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	});

	it('test du validateur : confirmation et mot de passe identiques', function() {

		form.mdp.$setViewValue('monpass');
		form.cmdp.$setViewValue('monpass');

		expect(form.cmdp.$valid).toBe(true);
		expect(form.mdp.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	});

	it('test de bonne inscription', inject(function($httpBackend) {

		form.nom.$setViewValue('Bernard');
		form.prenom.$setViewValue('Julien');
		form.mdp.$setViewValue('test');
		form.cmdp.$setViewValue('test');
		form.email.$setViewValue('jbernard@laposte.net');

		expect(form.$valid).toBe(true);
		
		$scope.submitForm();
		$httpBackend.expectPUT('/utilisateur/particulier').respond();
		$httpBackend.flush();
		expect(windowMock.location.href).toEqual('/index.html');

	}));

});
