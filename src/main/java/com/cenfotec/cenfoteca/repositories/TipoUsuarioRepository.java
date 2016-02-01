package com.cenfotec.cenfoteca.repositories;

import org.springframework.data.repository.CrudRepository;


import com.cenfotec.cenfoteca.ejb.TipoUsuario;

import java.util.List;


public interface TipoUsuarioRepository extends CrudRepository<TipoUsuario, Integer> {
	List<TipoUsuario> findAll();
//	void delete(TipoUsuario userType);

	
}
