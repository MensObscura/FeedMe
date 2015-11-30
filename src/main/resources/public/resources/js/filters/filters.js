// Filtre permettant que corriger les défauts de path des images :
var filter = angular.module('appFilters', []).filter('correctPath', function() {
	return function(texte) {
		if (!texte)
			return texte;
		else
			return texte.replace(/\\/g,"/");
	};
});
