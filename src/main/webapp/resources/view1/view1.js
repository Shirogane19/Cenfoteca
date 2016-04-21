'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'resources/view1/view1.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl', ['$scope','$http','$upload',function($scope,$http,$upload) {
	$scope.usuarios = [];
	$scope.tipoUsuarios = [];
	$scope.items = [];
	$scope.rents = [];
	$scope.tipoItem = [];
	
	$scope.init = function(){
		
		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","user": {}};
		$http.post('rest/protected/users/getAll',$scope.requestObject).success(function(response) {
		//	console.log("response",response)
			$scope.usuarios = response.usuarios;
		});
		

		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","userType": {}};
		$http.post('rest/protected/tipoUsuario/getAll',$scope.requestObject).success(function(response) {
		//	console.log("response",response)
			$scope.tipoUsuarios = response.tipoUsuarioList;
		});


		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","item": {}};
		$http.post('rest/protected/rent/getAll',$scope.requestObject).success(function(response) {
		//	console.log("response",response)
			$scope.items = response.alquilerList;
		});


		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","userRent": {}};
		$http.post('rest/protected/userRent/getAll',$scope.requestObject).success(function(response) {
			console.log("response",response)
			$scope.rents = response.userRent;
		});
		
		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","rentType": {}};
		$http.post('rest/protected/tipoAlquiler/getAll',$scope.requestObject).success(function(response) {
		//	console.log("response",response)
			$scope.tipoItem = response.tipoAlquilerList;
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
		//	console.log("response",response)
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


	//---------------------------------------------------------------------------//
	//--------------------CREATE AND EDIT ITEMS----------------------------------//
	//---------------------------------------------------------------------------//
	$scope.isCreating2 = true;//Bandera que indica si esta creando o modificando el item
	$scope.onPoint2 = false;//Controla la visibilidad del formulario de items
	$scope.newItem = {};//Variable temporal para uso de modificar items
	$scope.tipo;
	
	//---------------------------------------------------------------------------//
	$scope.showItemToEdit = function(i){
		$scope.newItem = i; 
		$scope.newItem.name = i.name; 
		$scope.newItem.description = i.description; 
		$scope.newItem.url = i.image;
		$scope.openFormItem2();
		$scope.isCreating2 = false;
	}
	//---------------------------------------------------------------------------//
	$scope.closeFormItem = function(){//Cierra el formulario de crear tipo de usuario
		$scope.onPoint2 = false;
		$scope.isCreating2 = true;
		$scope.newItem = {};
	}
	$scope.openFormItem = function(){//Abre el formulario de crear tipo de usuario
		$scope.isCreating2 = true;
		$scope.onPoint2 = true;

	}
	$scope.openFormItem2 = function(){//Abre el formulario de crear tipo de usuario
		$scope.isCreating2 = false;
		$scope.onPoint2 = true;

	}
	//---------------------------------------------------------------------------//
	$scope.saveItem = function(){
		console.log("item", $scope.newItem);

		if($scope.isCreating2){
			$scope.newItem.idAlquiler = -1;
		}
		


		$scope.newItem.tipo  = parseInt($scope.tipo, 10);
		console.log($scope.newItem.tipo);
		$scope.requestObject = 
								{"pageNumber": 0,
								 "pageSize": 0,
								 "direction": "string",
								 "sortBy": ["string"],
								 "searchColumn": "string",
								 "searchTerm": "string",
								 "alquiler": {"idAlquiler": $scope.newItem.idAlquiler,
								 			  "name": $scope.newItem.name,
								 			  "description": $scope.newItem.description,
								 			  "idTipo": $scope.newItem.tipo}};

		$http.post('rest/protected/rent/save',$scope.requestObject).success(function(response) {
			console.log("response",response)
			$scope.closeFormItem();
			$scope.init();
		});
	}
	//---------------------------------------------------------------------------//
	//---------------------------DELETE ITEM-------------------------------------//
	//---------------------------------------------------------------------------//
	$scope.deleteItem = function(i){
		console.log("item", i.idAlquiler);

		$scope.requestObject = { "pageNumber": 0,
								 "pageSize": 0,
								 "direction": "string",
								 "sortBy": ["string"],
								 "searchColumn": "string",
								 "searchTerm": "string",
								 "alquiler": {"idAlquiler": i.idAlquiler,"description": i.description}};

		console.log($scope.requestObject.userType);
		$http.post('rest/protected/rent/delete',$scope.requestObject).success(function(response) {
			console.log("response",response);
			$scope.init();
		});
	}
	//---------------------------------------------------------------------------//


	//---------------------------------------------------------------------------//
	//--------------------CREATE NEW RENT----------------------------------------//
	//---------------------------------------------------------------------------//
	$scope.isCreating3 = true;
	$scope.onPoint3 = false;
	$scope._item;
	$scope._usuario;
	$scope.usuario;
	$scope.item;
	
	//---------------------------------------------------------------------------//
	$scope.closeFormRent = function(){//Cierra el formulario de crear tipo de usuario
		$scope.onPoint3 = false;
		$scope.isCreating3 = true;
		$scope.newItem = {};
	}
	$scope.openFormRent = function(){//Abre el formulario de crear tipo de usuario
		$scope.isCreating3 = true;
		$scope.onPoint3 = true;

	}

	//---------------------------------------------------------------------------//
	$scope.rentItem = function(){

		//console.log("Usuario", $scope.usuario);
		//console.log("Item", $scope.item);

		$scope._usuario  = parseInt($scope.usuario, 10);
		$scope._item     = parseInt($scope.item, 10);

		//console.log("Usuario", $scope._usuario);
		//console.log("Item", $scope._item);

		$scope.requestObject = 
								{"pageNumber": 0,
								 "pageSize": 0,
								 "direction": "string",
								 "sortBy": ["string"],
								 "searchColumn": "string",
								 "searchTerm": "string",
								 "userRent": {"idItem":    $scope._item,
								 			  "idUsuario": $scope._usuario}};

		$http.post('rest/protected/userRent/rentItem',$scope.requestObject).success(function(response) {
			console.log("response",response)
			$scope.closeFormRent();
			$scope.init();
		});
	}
	//---------------------------------------------------------------------------//
	//---------------------------RETURN ITEM-------------------------------------//
	//---------------------------------------------------------------------------//
	$scope.returnItem = function(r){
		console.log("renta", r);

		$scope.requestObject = { "pageNumber": 0,
								 "pageSize": 0,
								 "direction": "string",
								 "sortBy": ["string"],
								 "searchColumn": "string",
								 "searchTerm": "string",
								 "userRent": {"idUsuarioHasAlquiler": r.idUsuarioHasAlquiler}};

		$http.post('rest/protected/userRent/returnItem',$scope.requestObject).success(function(response) {
			console.log("response",response);
			$scope.init();
		});
	}
	//---------------------------------------------------------------------------//

	$scope.init();
	
}]);