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
	
	$scope.init = function(){
		
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
		
	}
	//---------------------------------------------------------------------------//
	//--------------------CREATE AND EDIT USER TYPE-------------------------------//
	//---------------------------------------------------------------------------//
	$scope.isCreating = true;//Bandera que indica si esta creando o modificando el tipo de usuario
	$scope.onPoint = false;//Controla la visibilidad del formulario de tipo de usuario
	$scope.userType = {};//Variable temporal para uso de modificar tipo de usuario
	
	//---------------------------------------------------------------------------//
	$scope.showUserTypeToEdit = function(t){//Pone los valores del tipo de usuario a modificar al formulario
		$scope.userType = t; // Guarda el objeto tipoUsuario a la variable temporal
		$scope.userType.name = t.tipo; // Setea el nombre del tipo de usuario al input
		$scope.openFormUserType();
		$scope.isCreating = false;
	}
	//---------------------------------------------------------------------------//
	$scope.closeFormUserType = function(){//Cierra el formulario de crear tipo de usuario
		$scope.onPoint = false;
		$scope.isCreating = true;
		$scope.userType = {};
	}
	$scope.openFormUserType = function(){//Abre el formulario de crear tipo de usuario
		$scope.onPoint = true;
	}
	//---------------------------------------------------------------------------//
	$scope.saveTipoUsuario = function(){
		if($scope.isCreating){//Si esta creando setea un -1 al tipo de usuario
			$scope.userType.idTipoUsuario = -1;
		}
		
		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "string","sortBy": ["string"],"searchColumn": "string","searchTerm": "string","userType": {"idTipoUsuario": $scope.userType.idTipoUsuario,"tipo": $scope.userType.name}};
		$http.post('rest/protected/tipoUsuario/save',$scope.requestObject).success(function(response) {
			console.log("response",response)
			$scope.closeFormUserType();
			$scope.init();
		});
	}
	//---------------------------------------------------------------------------//
	//---------------------------DELETE USER TYPE-------------------------------//
	//---------------------------------------------------------------------------//
	$scope.deleteTipoUsuario = function(t){
		//console.log(t.tipo + " " + t.idTipoUsuario);
		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "string","sortBy": ["string"],"searchColumn": "string","searchTerm": "string","userType": {"idTipoUsuario": t.idTipoUsuario,"tipo": t.tipo}};
		console.log($scope.requestObject.userType);
		$http.post('rest/protected/tipoUsuario/delete',$scope.requestObject).success(function(response) {
			console.log("response",response);
			$scope.init();
		});
	}
	//---------------------------------------------------------------------------//
	$scope.init();
	
}]);