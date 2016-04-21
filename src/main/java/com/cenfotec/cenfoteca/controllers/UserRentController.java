package com.cenfotec.cenfoteca.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.cenfoteca.contracts.UserRentRequest;
import com.cenfotec.cenfoteca.contracts.UserRentResponse;
import com.cenfotec.cenfoteca.services.UserRentServiceInterface;

@RestController
@RequestMapping(value ="rest/protected/userRent")
public class UserRentController {

	@Autowired private UserRentServiceInterface userRentService;
	@Autowired private HttpServletRequest request;
	
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public UserRentResponse getAll(@RequestBody UserRentRequest ur){	
			
		UserRentResponse us = new UserRentResponse();
		us.setCode(200);
		us.setCodeMessage("items rented by users fetch success");
		us.setUserRent(userRentService.getAll(ur));
		return us;		
	}
	
	@RequestMapping(value ="/rentItem", method = RequestMethod.POST)
	public UserRentResponse rentItem(@RequestBody UserRentRequest ur){	
		
		UserRentResponse us = new UserRentResponse();
		Boolean state = userRentService.saveUserRent(ur);
	
		if(state){
			us.setCode(200);
			us.setCodeMessage("user rented item succesfully");
		}
		return us;
		
	}
	
	@RequestMapping(value ="/returnItem", method = RequestMethod.POST)
	public UserRentResponse returnItem(@RequestBody UserRentRequest ur){	
		
		UserRentResponse us = new UserRentResponse();
		Boolean state = userRentService.deleteUserRent(ur.getUserRent().getIdUsuarioHasAlquiler());
	
		if(state){
			us.setCode(200);
			us.setCodeMessage("item returned succesfully");
		}
		return us;
		
	}
	

//	@RequestMapping(value ="/getAllByUser", method = RequestMethod.POST)
//	public UserRentResponse getAllByUser(@RequestBody UserRentRequest ur){	
//			
//		UserRentResponse us = new UserRentResponse();
//		us.setCode(200);
//		us.setCodeMessage("users fetch success");
//		us.setUserRent(userRentService.findByUsuario(ur));
//		
//		return us;		
//	}
//	
//	@RequestMapping(value ="/getAllByRent", method = RequestMethod.POST)
//	public UserRentResponse getAllByRent(@RequestBody UserRentRequest ur){	
//			
//		UserRentResponse us = new UserRentResponse();
//		us.setCode(200);
//		us.setCodeMessage("rents fetch success");
//		us.setUserRent(userRentService.findByAlquiler(ur));
//		
//		return us;		
//	}
	
}
