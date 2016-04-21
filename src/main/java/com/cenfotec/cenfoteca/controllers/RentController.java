package com.cenfotec.cenfoteca.controllers;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cenfotec.cenfoteca.contracts.RentRequest;
import com.cenfotec.cenfoteca.contracts.RentResponse;
import com.cenfotec.cenfoteca.contracts.UsersRequest;
import com.cenfotec.cenfoteca.ejb.Alquiler;
import com.cenfotec.cenfoteca.services.RentServiceInterface;
import com.cenfotec.cenfoteca.services.TipoAlquilerServiceInterface;
import com.cenfotec.cenfoteca.utils.Utils;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/rent")
public class RentController {
	
	@Autowired private ServletContext servletContext;
	@Autowired private TipoAlquilerServiceInterface tipoAlquilerService;
	@Autowired private RentServiceInterface rentService;
	
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public RentResponse getAll(){
		
		RentResponse response = new RentResponse();
		response.setCode(200);
		response.setCodeMessage("rent type fetch success");
		response.setAlquilerList(rentService.getAll());
		
		return response;
	}
	
	
	@RequestMapping(value ="/save", method = RequestMethod.POST)
	public RentResponse create(@RequestBody RentRequest rr){	
		
		RentResponse rs = new RentResponse();
		Alquiler alquiler = new Alquiler();
		Boolean state;
		
		//String resultFileName = Utils.writeToFile(file,servletContext);
	
		
		if(rr.getAlquiler().getIdAlquiler() <= -1){
			
			alquiler.setName(rr.getAlquiler().getName());
			alquiler.setDescription(rr.getAlquiler().getDescription());
			alquiler.setImage(rr.getAlquiler().getUrl());
			alquiler.setTipoAlquiler(tipoAlquilerService.getTipoAlquilerById(rr.getAlquiler().getIdTipo()));
			
			state = rentService.saveRent(alquiler);
			
				
		}else{
			
			alquiler.setIdAlquiler(rr.getAlquiler().getIdAlquiler());
			alquiler.setName(rr.getAlquiler().getName());
			alquiler.setDescription(rr.getAlquiler().getDescription());
			alquiler.setImage(rr.getAlquiler().getUrl());
			alquiler.setTipoAlquiler(tipoAlquilerService.getTipoAlquilerById(rr.getAlquiler().getIdTipo()));
			
			state = rentService.editRent(alquiler);		
		}
		
			if(state){
				rs.setCode(200);
				rs.setCodeMessage("rent saved succesfully");
			}else{
				rs.setCode(409);
				rs.setErrorMessage("create/edit conflict");
			}	
		
		return rs;		
	}
	
	
	@RequestMapping(value ="/delete", method = RequestMethod.POST)	
	public void delete(@RequestBody  RentRequest rq){
		
		RentResponse response = new RentResponse();
		Boolean state = rentService.deleteRent(rq);
		
		if(!state){
			response.setCode(200);
			response.setCodeMessage("user type deleted succesfully");
		}

	}
	
	
}
