angular.module('appFilters', []).filter('correctPath', function() {
	return function(texte) {
		if (!texte)
			return texte;
		else
			return texte.replace(/\\/g,"/");
	};
});