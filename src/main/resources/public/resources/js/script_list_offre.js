var app = angular.module("ListApp", []);
var page_data;

app.controller("ListCtrl", function($scope, $http, $location,srvShareData) {
	$http.get('http://localhost:8080/offres').success(
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

	 $scope.nombreRestant = $scope.offre.nombrePersonne - $scope.offre.reservations.length;
	
  $scope.submitForm = function() {
    if ($scope.ReservationForm.$valid) {

        var today = new Date();
        var date = "";

        if ((today.getMonth()+1) < 10)
            date = today.getFullYear()+'-0'+(today.getMonth()+1)+'-'+today.getDate();
         else
            date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
     
    	 var data = {
		    	//nombre : $scope.place,
                offre : $scope.offre,
                dateReservation : date,
		    };


      $http({
        method: 'PUT',
        url: 'http://localhost:8080/reservation',
        contentType: "application/json",
        data: data
     }).success(function(response, status, headers, config){
           console.log(response);
      }).error(function(err, status, headers, config){
           console.log(err.message);
      });

    }

  };
  
});
