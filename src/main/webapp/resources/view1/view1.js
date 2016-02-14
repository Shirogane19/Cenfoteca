'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'resources/view1/view1.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl', ['$scope','$http',function($scope,$http) {
	$scope.usuarios = [];
	$scope.tipoUsuarios = [];
	$scope.items = [];
	$scope.rents = [];

	$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","user": {}};
	$http.post('rest/protected/users/getAll',$scope.requestObject).success(function(response) {
		console.log("response",response)
		$scope.usuarios = response.usuarios;
		console.log("$scope.usuarios",$scope.usuarios[1])
	//	console.log("Tipo de usuario:", $scope.usuarios.tipos)
	});
	

	$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","userType": {}};
	$http.post('rest/protected/tipoUsuario/getAll',$scope.requestObject).success(function(response) {
		console.log("response",response)
		$scope.tipoUsuarios = response.tipoUsuarioList;
		console.log("$scope.tipoUsuarios",$scope.tipoUsuarios[1])
	//	console.log("Tipo de usuario:", $scope.usuarios.tipos)
	});


	$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","item": {}};
	$http.post('rest/protected/rent/getAll',$scope.requestObject).success(function(response) {
		console.log("response",response)
		$scope.items = response.alquilerList;
		console.log("$scope.items",$scope.items[1])
	//	console.log("Tipo de usuario:", $scope.usuarios.tipos)
	});


$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","userRent": {}};
	$http.post('rest/protected/userRent/getAll',$scope.requestObject).success(function(response) {
		console.log("response",response)
		$scope.rents = response.userRent;
		console.log("$scope.items",$scope.rents[1])
	//	console.log("Tipo de usuario:", $scope.usuarios.tipos)
	});


}]);