var app = angular.module("ListApp", []);
var page_data;

app.controller("ListCtrl", function($scope, $http, $location,srvShareData) {
	/*$http.get('http://localhost:8080/offres').success(*/
	$http.get('ressources/js/list.json').success(
		function(data) {
			$scope.list = data;
		}
	);
	
$scope.dataToShare = [];
  
  $scope.visualize = function (myValue) {

    $scope.dataToShare = JSON.stringify(myValue);
    srvShareData.addData($scope.dataToShare);
    
    window.location.href = "offre.html";
  }
});

app.service('srvShareData', function($window) {
        var KEY = 'App.SelectedValue';

        var addData = function(newObj) {
            var mydata = newObj
            if (mydata) {
                mydata = JSON.parse(mydata);
            } else {
                mydata = [];
            }
          
            $window.sessionStorage.setItem(KEY, JSON.stringify(mydata));
        };

        var getData = function(){
            var mydata = $window.sessionStorage.getItem(KEY);
            if (mydata) {
                mydata = JSON.parse(mydata);
            }
            return mydata || [];
        };

        return {
            addData: addData,
            getData: getData
        };
    });


app.controller('ReservationController', function($scope, $http, srvShareData) {  
	
	$scope.offre = srvShareData.getData();
	
  $scope.submitForm = function() {
    if ($scope.ReservationForm.$valid) {
     
    	 var data = {
		    	nombre : $scope.place,
		    };

		  $http({
			url: 'http://localhost:8080/reservations',dataType: 'json',method: 'POST',data: data}

			).success(function(response)
			{
				$scope.response = response;
			}
			).error(function(error)
			{
				$scope.error = error;
			})


    }

  };
  
});
