package com.cenfotec.cenfoteca.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.cenfoteca.contracts.TipoAlquilerResponse;
import com.cenfotec.cenfoteca.services.TipoAlquilerServiceInterface;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/tipoAlquiler")
public class TipoAlquilerController {
	
	@Autowired private TipoAlquilerServiceInterface tipoAlquilerService;

	
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public TipoAlquilerResponse getAll(){	
			
		TipoAlquilerResponse response = new TipoAlquilerResponse();
		response.setCode(200);
		response.setCodeMessage("item type fetch success");
		response.setTipoAlquilerList(tipoAlquilerService.getAll());
		return response;		
	}
}
