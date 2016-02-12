package com.cenfotec.cenfoteca.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cenfotec.cenfoteca.ejb.UsuarioHasAlquiler;
import com.cenfotec.cenfoteca.pojo.AlquilerPOJO;
import com.cenfotec.cenfoteca.pojo.UsuarioPOJO;



public interface RentUserRepository extends CrudRepository<UsuarioHasAlquiler,Integer> {
	
	List<UsuarioHasAlquiler> findAll();
	List<UsuarioHasAlquiler> findByAlquilerContaining(AlquilerPOJO list);
	List<UsuarioHasAlquiler> findByUsuarioContaining(UsuarioPOJO list);
}
