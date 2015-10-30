// Test du controller script-formulaire-offre.js
describe('OffreCtrl', function() {
	// Variables pour le scope et le controller
	var $scope, controller, form;

	//Jeu de données pour typeCuisine
	var typeCuisine = [{id: 1, typeCuisine: 'Bretonne'}, {id: 2, typeCuisine: 'Flamande'}];

	//Jeu de données pour pays
	var pays = [{id: 1, codePays: 'FRA', nom: 'France'}, {id: 2, codePays: 'BEL', nom: 'Belgique'}];

	// Variable du mock pour le formulaire HTML
	var formulaire = '<form name="OffreForm" ng-submit="submitForm()" novalidate>'	
+'<input type="text" name="titre" ng-model="titre" placeholder="Titre de l\'offre." ng-required="true">'
+'<span style="color:red" ng-show="OffreForm.titre.$invalid && !OffreForm.titre.$pristine">'
+'<span ng-show="OffreForm.titre.$error.required">Veuillez saisir un titre pour l\'offre.</span>'
+'</span>'
+'<input type="number" name="nbpers" ng-model="nbpers" placeholder="Nombre de couverts." ng-min="1" ng-required="true">'
+'<span style="color:red" ng-show="OffreForm.nbpers.$invalid && !OffreForm.nbpers.$pristine">'
+'<span ng-show="OffreForm.nbpers.$error.required">Veuillez saisir un nombre de convives.</span>'
+'<span ng-show="OffreForm.nbpers.$error.number">Veuillez saisir un nombre.</span>'
+'<span ng-show="OffreForm.nbpers.$error.min">Veuillez saisir un nombre supérieur à 1.</span>'
+'</span>'
+'<input type="number" step="0.01" name="prix" ng-model="prix" placeholder="Prix par convive." ng-min="1.00" ng-required="true">'
+'<span style="color:red" ng-show="OffreForm.prix.$invalid && !OffreForm.prix.$pristine">'
+'<span ng-show="OffreForm.prix.$error.required">Veuillez saisir un prix.</span>'
+'<span ng-show="OffreForm.prix.$error.number">Veuillez saisir un nombre.</span>'
+'<span ng-show="OffreForm.prix.$error.min">Veuillez saisir un prix supérieur à 1.00.</span>'
+'</span>'
+'<select type="select" name="typeCuisine" ng-model="typeCuisine" ng-required="true">'
+'<option ng-repeat="item in cook" value="{{item.id}}">{{item.typeCuisine}}</option>'
+'</select>'
+'<span style="color:red" ng-show="OffreForm.typeCuisine.$invalid">'
+'<span ng-show="OffreForm.typeCuisine.$error.required">Veuillez selectionner un type de cuisine.</span>'
+'</span>'
+'<input type="text" name="menu" ng-model="menu" placeholder="Votre menu." ng-required="true">'
+'<span style="color:red" ng-show="OffreForm.menu.$invalid && !OffreForm.menu.$pristine">'
+'<span ng-show="OffreForm.menu.$error.required">Veuillez saisir votre menu.</span>'
+'</span>'
+'<input type="number" ng-model="duree" name="duree" ng-min="60" placeholder="Durée prévisionnelle." ng-required="true">'
+'<span style="color:red" ng-show="OffreForm.duree.$invalid && !OffreForm.duree.$pristine">'
+'<span ng-show="OffreForm.duree.$error.required">Veuillez saisir une durée.</span>'
+'<span ng-show="OffreForm.duree.$error.number">Veuillez saisir un nombre.</span>'
+'<span ng-show="OffreForm.duree.$error.min">Veuillez saisir une durée supérieur à 60 min.</span>'
+'</span>'
+'<input type="number" ng-model="numero" name="numero" ng-min="1" placeholder="Numéro de voirie." ng-required="true">'
+'<span style="color:red" ng-show="OffreForm.numero.$invalid && !OffreForm.numero.$pristine">'
+'<span ng-show="OffreForm.numero.$error.required">Veuillez saisir un numéro de voirie.</span>'
+'<span ng-show="OffreForm.numero.$error.number">Veuillez saisir un nombre.</span>'
+'<span ng-show="OffreForm.numero.$error.min">Veuillez saisir un numéro valide.</span>'
+'</span>'
+'<input type="text" ng-model="rue" name="rue" placeholder="Nom de voirie." ng-required="true">'
+'<span style="color:red" ng-show="OffreForm.rue.$invalid && !OffreForm.rue.$pristine">'
+'<span ng-show="OffreForm.rue.$error.required">Veuillez saisir un nom de rue.</span>'
+'</span>'
+'<input type="text" name="complement" ng-model="complement" placeholder="Compléments.">'
+'<input type="text" name="ville" ng-model="ville" placeholder="Votre ville." ng-required="true">'
+'<span style="color:red" ng-show="OffreForm.ville.$invalid && !OffreForm.ville.$pristine">'
+'<span ng-show="OffreForm.ville.$error.required">Veuillez saisir votre ville.</span>'
+'</span>'
+'<input type="text" name="cp" ng-model="cp" ng-pattern="/^[1-9][0-9A-B][0-9]{3}$/" placeholder="Votre code postal." ng-required="true">'
+'<span style="color:red" ng-show="OffreForm.cp.$invalid && !OffreForm.cp.$pristine">'
+'<span ng-show="OffreForm.cp.$error.required">Veuillez saisir un code postal.</span>'
+'<span ng-show="OffreForm.cp.$error.pattern">Le code postal saisit est incorrect.</span>'
+'</span>'
+'<select type="select" name="pays" ng-model="pays" ng-required="true">'
+'<option ng-repeat="item in count" value="{{item.id}}">{{item.nom}}</option>'
+'</select>'
+'<span style="color:red" ng-show="OffreForm.pays.$invalid">'
+'<span ng-show="OffreForm.pays.$error.required">Veuillez selectionner un pays.</span>'
+'</span>'
+'<input type="number" name="agemin" ng-model="agemin" ng-min="18" placeholder="min."> - '
+'<input type="number" name="agemax" ng-model="agemax" ng-min="18" placeholder="max." ensure-expression="(agemin == null && agemax == null) || (agemin == null && agemax >= 18) || (agemax == null && agemin >= 18) || agemin <= agemax">'
+'<span style="color:red" ng-show="OffreForm.agemin.$invalid && !OffreForm.agemin.$pristine">'
+'<span ng-show="OffreForm.agemin.$error.number">Veuillez saisir un nombre.</span>'
+'<span ng-show="OffreForm.agemin.$error.min">L\'âge minimal est de 18 ans.</span>'
+'</span>'
+'<span style="color:red" ng-show="OffreForm.agemax.$invalid && !OffreForm.agemax.$pristine">'
+'<span ng-show="OffreForm.agemax.$error.number">Veuillez saisir un nombre.</span>'
+'<span ng-show="OffreForm.agemax.$error.min">L\'âge minimal est de 18 ans.</span>'
+'<span ng-show="OffreForm.agemax.$error.mauvaisetranche">La tranche d\'âge n\'est pas valide.</span>'
+'</span>'
+'<input type="checkbox" ng-model="animal" name="animal" value="true"> Présence d\'un animal.'
+'<input type="text" name="note" ng-model="note" placeholder="Informations complémentaires.">'
+'<button type="submit" ng-disabled="nonValide()">Ajouter</button>'	      
+'</form>';

	// Variable du mock pour la redirection
	var windowMock = {location: {href: ''}};


	// On se place dans le module validationOffre
	beforeEach(module('validationOffre'));

	// Initialisation du scope et du controller
	beforeEach(inject(function ($rootScope, $controller, $compile) {
		$scope = $rootScope.$new();
		controller = $controller('OffreCtrl', {
			$scope : $scope,
			$window : windowMock
		});

		$compile(formulaire)($scope);
		form = $scope.OffreForm;

	}));

	it('les routes /settings/pays et /settings/typescuisines doivent être appelées', inject(function($httpBackend) {
		$httpBackend.expectGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.expectGET('/settings/pays').respond(pays);
		$httpBackend.flush();
	}));

	it('les pays et les types de cuisine renvoyées par les routes doivent être stockées dans cook et count', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);
		$httpBackend.flush();

		expect($scope.cook.length).toEqual(2);
		expect($scope.count.length).toEqual(2);
	}));

	it('test du validateur : le titre est requis', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);
		$httpBackend.flush();

		form.titre.$setViewValue('');

		expect(form.titre.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le titre est rempli', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.titre.$setViewValue('Lapin aux pruneaux');

		expect(form.titre.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le nombre de personne est requis', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);
		$httpBackend.flush();

		form.nbpers.$setViewValue('');

		expect(form.nbpers.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le nombre de personne ne doit pas être une chaine de caractères', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.nbpers.$setViewValue('string');

		expect(form.nbpers.$error.number).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le nombre de personne doit être au moins 1', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.nbpers.$setViewValue('0');

		expect(form.nbpers.$error.min).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le nombre de personne est rempli', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.nbpers.$setViewValue('3');

		expect(form.nbpers.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le prix est requis', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);
		$httpBackend.flush();

		form.prix.$setViewValue('');

		expect(form.prix.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le prix ne doit pas être une chaine de caractères', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.prix.$setViewValue('string');

		expect(form.prix.$error.number).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le prix doit être au moins 1', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.prix.$setViewValue('0.99');

		expect(form.prix.$error.min).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le prix est rempli', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.prix.$setViewValue('5.76');

		expect(form.prix.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le menu est requis', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);
		$httpBackend.flush();

		form.menu.$setViewValue('');

		expect(form.menu.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le menu est rempli', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.menu.$setViewValue('Lapin aux pruneaux, trou normand pour la digestion et mousse au chocolat.');

		expect(form.menu.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : la durée est requise', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);
		$httpBackend.flush();

		form.duree.$setViewValue('');

		expect(form.duree.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : la durée ne doit pas être une chaine de caractères', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.duree.$setViewValue('string');

		expect(form.duree.$error.number).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : la durée doit être au moins 60', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.duree.$setViewValue('32');

		expect(form.duree.$error.min).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : la durée est remplie', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.duree.$setViewValue('200');

		expect(form.duree.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le numéro est requis', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);
		$httpBackend.flush();

		form.numero.$setViewValue('');

		expect(form.numero.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le numéro ne doit pas être une chaine de caractères', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.numero.$setViewValue('string');

		expect(form.numero.$error.number).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le numéro doit être au moins 1', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.numero.$setViewValue('0');

		expect(form.numero.$error.min).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le numero est rempli', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.numero.$setViewValue('20');

		expect(form.numero.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : la rue est requise', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);
		$httpBackend.flush();

		form.rue.$setViewValue('');

		expect(form.rue.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : la rue est remplie', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.rue.$setViewValue('rue de Berlinpinpin');

		expect(form.rue.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le complément n\'est pas requis', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);
		$httpBackend.flush();

		form.complement.$setViewValue('');

		expect(form.complement.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le complément est rempli', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.complement.$setViewValue('appartement ter');

		expect(form.complement.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : la ville est requise', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);
		$httpBackend.flush();

		form.ville.$setViewValue('');

		expect(form.ville.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : la ville est remplie', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.ville.$setViewValue('Lille');

		expect(form.ville.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le code postal est requis', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);
		$httpBackend.flush();

		form.cp.$setViewValue('');

		expect(form.cp.$error.required).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le code postal n\'est pas bon', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.cp.$setViewValue('34');

		expect(form.cp.$error.pattern).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : le code postal est rempli', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.cp.$setViewValue('59000');

		expect(form.cp.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur :  aucune selection pays', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.pays.$setViewValue('');

		expect(form.pays.$invalid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : selection pays', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.pays.$setViewValue('France');

		expect(form.pays.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur :  aucune selection type de cuisine', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.typeCuisine.$setViewValue('');

		expect(form.typeCuisine.$invalid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : selection type de cuisine', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.typeCuisine.$setViewValue('Bretonne');

		expect(form.typeCuisine.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : l\'âge minimal n\'est pas rempli', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);
		$httpBackend.flush();

		form.agemin.$setViewValue('');

		expect(form.agemin.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : l\'âge minimal ne doit pas être une chaine de caractères', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.agemin.$setViewValue('string');

		expect(form.agemin.$error.number).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : l\'âge minimal doit être au moins 18', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.agemin.$setViewValue('13');

		expect(form.agemin.$error.min).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : l\'âge minimal est rempli', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.agemin.$setViewValue('20');

		expect(form.agemin.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : l\'âge maximal n\'est pas rempli', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);
		$httpBackend.flush();

		form.agemax.$setViewValue('');

		expect(form.agemax.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : l\'âge maximal ne doit pas être une chaine de caractères', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.agemax.$setViewValue('string');

		expect(form.agemax.$error.number).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : l\'âge maximal doit être au moins 18', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.agemax.$setViewValue('13');

		expect(form.agemax.$error.min).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : l\'âge maximal est rempli', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.agemax.$setViewValue('20');

		expect(form.agemax.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : les âges maximal et minimal sont remplis', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.agemax.$setViewValue('30');
		form.agemax.$setViewValue('20');

		expect(form.agemax.$valid).toBe(true);
		expect(form.agemin.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : les âges maximal et minimal sont remplis mais incorrects', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.agemin.$setViewValue('60');
		form.agemax.$setViewValue('20');

		expect(form.agemax.$valid).toBe(false);
		expect(form.agemin.$valid).toBe(true);
		expect(form.agemax.$error.mauvaisetranche).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : animal à oui rempli', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.animal.$setViewValue(true);

		expect(form.animal.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : animal à non rempli', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.animal.$setViewValue(false);

		expect(form.animal.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur :  note non remplie', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.note.$setViewValue('');

		expect(form.note.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test du validateur : note rempli', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.note.$setViewValue('ceci est une note!');

		expect(form.note.$valid).toBe(true);
		expect(form.$valid).toBe(false);
	}));

	it('test de bonne création d\'offre', inject(function($httpBackend) {
		$httpBackend.whenGET('/settings/typescuisines').respond(typeCuisine);
		$httpBackend.whenGET('/settings/pays').respond(pays);

		form.titre.$setViewValue('Titre');
		form.nbpers.$setViewValue('2');
		form.prix.$setViewValue('7.5');
		form.menu.$setViewValue('menu');
		form.duree.$setViewValue('85');
		form.numero.$setViewValue('5');
		form.rue.$setViewValue('rue de la paix');
		form.ville.$setViewValue('Wayward-Pines');
		form.cp.$setViewValue('55555');
		form.typeCuisine.$setViewValue('Bretonne');
		form.pays.$setViewValue('France');

		expect(form.$valid).toBe(true);
		
		$scope.submitForm();
		$httpBackend.expectPUT('/offres').respond();
		$httpBackend.flush();
		expect(windowMock.location.href).toEqual('/accueil.html');

	}));

});
