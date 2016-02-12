package com.cenfotec.cenfoteca.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenfotec.cenfoteca.contracts.UserTypeRequest;
import com.cenfotec.cenfoteca.ejb.TipoUsuario;
import com.cenfotec.cenfoteca.pojo.TipoUsuarioPOJO;
import com.cenfotec.cenfoteca.repositories.TipoUsuarioRepository;



@Service
public class TipoUsuarioService implements TipoUsuarioServiceInterface{

	@Autowired private TipoUsuarioRepository tipoUsuarioRepository;
	
	@Override
	@Transactional
	public List<TipoUsuarioPOJO> getAll() {
		
		List<TipoUsuario> tipos = tipoUsuarioRepository.findAll();
		List<TipoUsuarioPOJO> dtos = new ArrayList<TipoUsuarioPOJO>();
		tipos.stream().forEach(ta ->{
			TipoUsuarioPOJO dto = new TipoUsuarioPOJO();
			BeanUtils.copyProperties(ta, dto);
			dtos.add(dto);
		});
		return dtos;
	}

	@Override
	public TipoUsuario getTipoUsuarioById(int idTipoUsuario) {
		return tipoUsuarioRepository.findOne(idTipoUsuario);
	}

	
	@Transactional
	public Boolean saveUserType(UserTypeRequest ut) {
		
		TipoUsuario newUserType = new TipoUsuario();
		TipoUsuario nuserT = null;
		BeanUtils.copyProperties(ut.getUserType(), newUserType);	
		
		if(ut.getUserType().getIdTipoUsuario() <= -1){		
	
			nuserT = tipoUsuarioRepository.save(newUserType);
			
		}else{		
			TipoUsuario oldUserType = getTipoUsuarioById(newUserType.getIdTipoUsuario());
			
			oldUserType.setTipo(newUserType.getTipo());
			nuserT = tipoUsuarioRepository.save(oldUserType);	
		}

		return (nuserT == null) ? false : true;
		
	}


	@Override
	@Transactional
	public Boolean deleteUserType(UserTypeRequest ut) {

		TipoUsuario userType = new TipoUsuario();
		BeanUtils.copyProperties(ut.getUserType(), userType);
		
		tipoUsuarioRepository.delete(userType);
		
		return (userType == null) ? false : true;
		
	}
	
	
	
	

}
