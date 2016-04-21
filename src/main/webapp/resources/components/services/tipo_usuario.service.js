'use strict';

angular
	.module('myApp.tipo',[])
	.service('tipoUsuarioService',tipoUsuarioService);
	function tipoUsuarioService($http){

		this.listTypes = function(requestObject){
			return $http.post('rest/protected/tipoUsuario/getAll',requestObject);
		};	
	};
