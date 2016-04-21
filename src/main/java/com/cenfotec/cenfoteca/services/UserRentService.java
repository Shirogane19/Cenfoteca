package com.cenfotec.cenfoteca.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenfotec.cenfoteca.contracts.UserRentRequest;
import com.cenfotec.cenfoteca.ejb.Alquiler;
import com.cenfotec.cenfoteca.ejb.Usuario;
import com.cenfotec.cenfoteca.ejb.UsuarioHasAlquiler;
import com.cenfotec.cenfoteca.pojo.AlquilerPOJO;
import com.cenfotec.cenfoteca.pojo.UsuarioHasAlquilerPOJO;
import com.cenfotec.cenfoteca.pojo.UsuarioPOJO;
import com.cenfotec.cenfoteca.repositories.RentRepository;
import com.cenfotec.cenfoteca.repositories.RentUserRepository;
import com.cenfotec.cenfoteca.repositories.UsersRepository;

import javassist.expr.NewArray;

@Service
public class UserRentService implements UserRentServiceInterface {

	@Autowired private RentUserRepository userRentRepository;
	@Autowired private UsersRepository    userRepository;
	@Autowired private RentRepository    itemRepository;
	
	private List<UsuarioHasAlquilerPOJO> generateUserRentDtos(List<UsuarioHasAlquiler> users){
		List<UsuarioHasAlquilerPOJO> userRent = new ArrayList<UsuarioHasAlquilerPOJO>();
		
		users.stream().forEach(u -> {
			UsuarioHasAlquilerPOJO dto = new UsuarioHasAlquilerPOJO();
			
			BeanUtils.copyProperties(u,dto);
			
			dto.setAlquiler(new AlquilerPOJO());
			dto.setUsuario(new UsuarioPOJO());
			dto.setIdUsuarioHasAlquiler(u.getId());
			
			BeanUtils.copyProperties(u.getAlquiler(),dto.getAlquiler());
			BeanUtils.copyProperties(u.getUsuario(), dto.getUsuario());
			
			userRent.add(dto);
		});	
		return userRent;
	}
	
	@Override
	@Transactional
	public List<UsuarioHasAlquilerPOJO>findByAlquiler(UserRentRequest ur) {
		
		List<UsuarioHasAlquiler> userRent = userRentRepository.findByAlquilerContaining(ur.getUserRent().getAlquiler());
		
		return generateUserRentDtos(userRent);
	}

	@Override
	@Transactional
	public List<UsuarioHasAlquilerPOJO> findByUsuario(UserRentRequest ur) {
		
		List<UsuarioHasAlquiler> userRent = userRentRepository.findByUsuarioContaining(ur.getUserRent().getUsuario());
			
		return generateUserRentDtos(userRent);
	}

	@Override
	@Transactional
	public List<UsuarioHasAlquilerPOJO> getAll(UserRentRequest ur) {
		
		List<UsuarioHasAlquiler> userRent = userRentRepository.findAll();
		return generateUserRentDtos(userRent);
	}

	@Override
	@Transactional
	public Boolean saveUserRent(UserRentRequest ut) {
		
		UsuarioHasAlquiler newUserRent = new UsuarioHasAlquiler();
		UsuarioHasAlquiler nuseR = new UsuarioHasAlquiler();
		
		Usuario u = userRepository.findOne(ut.getUserRent().getIdUsuario());
		Alquiler i = itemRepository.findOne(ut.getUserRent().getIdItem());
		
		newUserRent.setAlquiler(i);
		newUserRent.setUsuario(u);
			
		nuseR = userRentRepository.save(newUserRent);

		
		return (nuseR== null) ? false : true;
	}

	@Override
	@Transactional
	public Boolean deleteUserRent(int idRent) {

		UsuarioHasAlquiler userRent = userRentRepository.findOne(idRent);

		
		if(!userRent.isEstado_renta()){
			
			userRent.setEstado_renta(true);			
			userRentRepository.save(userRent);
		}
		

		
		return (userRent.isEstado_renta()) ? false : true;
	}

}
