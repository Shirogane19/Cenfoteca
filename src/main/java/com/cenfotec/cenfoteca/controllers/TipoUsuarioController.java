package com.cenfotec.cenfoteca.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.cenfoteca.contracts.TipoUsuarioResponse;
import com.cenfotec.cenfoteca.contracts.UserTypeRequest;
import com.cenfotec.cenfoteca.services.TipoUsuarioService;
import com.cenfotec.cenfoteca.services.TipoUsuarioServiceInterface;




/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/tipoUsuario")
public class TipoUsuarioController {
	

	@Autowired private TipoUsuarioServiceInterface tipoUsuarioService;
	@Autowired private HttpServletRequest request;
	
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public TipoUsuarioResponse getAll(){	
			
		TipoUsuarioResponse response = new TipoUsuarioResponse();
		response.setCode(200);
		response.setCodeMessage("user type fetch success");
		response.setTipoUsuarioList(tipoUsuarioService.getAll());
		return response;		
	}
	
	
	@RequestMapping(value ="/save", method = RequestMethod.POST)
	public TipoUsuarioResponse create(@RequestBody UserTypeRequest ut){	
		
		TipoUsuarioResponse userType = new TipoUsuarioResponse();
		Boolean state = tipoUsuarioService.saveUserType(ut);
	
		if(state){
			userType.setCode(200);
			userType.setCodeMessage("user type saved succesfully");
		}
		return userType;
		
	}
	

	@RequestMapping(value ="/delete", method = RequestMethod.POST)
	public void delete(@RequestBody UserTypeRequest ut){
		
		TipoUsuarioResponse userType = new TipoUsuarioResponse();
		Boolean state = tipoUsuarioService.deleteUserType(ut);
		
		if(state){
			userType.setCode(200);
			userType.setCodeMessage("user type deleted succesfully");
		}

		
	}
	
	
	
}