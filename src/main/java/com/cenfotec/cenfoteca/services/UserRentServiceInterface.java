package com.cenfotec.cenfoteca.services;

import java.util.List;

import com.cenfotec.cenfoteca.contracts.UserRentRequest;
import com.cenfotec.cenfoteca.pojo.UsuarioHasAlquilerPOJO;

public interface UserRentServiceInterface {

	List<UsuarioHasAlquilerPOJO> findByAlquiler(UserRentRequest ur);
	List<UsuarioHasAlquilerPOJO> findByUsuario(UserRentRequest ur);
	List<UsuarioHasAlquilerPOJO> getAll(UserRentRequest ur);
	Boolean saveUserRent(UserRentRequest ut);
	Boolean deleteUserRent(int idItem); 
	
	
}
