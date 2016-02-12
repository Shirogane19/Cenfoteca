package com.cenfotec.cenfoteca.services;

import java.util.List;

import com.cenfotec.cenfoteca.contracts.RentRequest;
import com.cenfotec.cenfoteca.ejb.Alquiler;
import com.cenfotec.cenfoteca.pojo.AlquilerPOJO;


public interface RentServiceInterface {
	
	List<AlquilerPOJO> getAll();
	Alquiler getAlquilerById(int idAlquiler);
	Boolean saveRent(Alquiler alquiler);
	Boolean editRent(Alquiler alquiler);
	Boolean deleteRent(RentRequest rq);

}
