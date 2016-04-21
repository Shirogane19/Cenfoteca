package com.cenfotec.cenfoteca.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenfotec.cenfoteca.contracts.RentRequest;
import com.cenfotec.cenfoteca.ejb.Alquiler;
import com.cenfotec.cenfoteca.pojo.AlquilerPOJO;
import com.cenfotec.cenfoteca.repositories.RentRepository;

@Service
public class RentService implements RentServiceInterface{

	@Autowired private RentRepository rentRepository;
	
	
	@Override
	@Transactional
	public List<AlquilerPOJO> getAll() {
		
		List<Alquiler> alquileres = rentRepository.findAll();
		List<AlquilerPOJO> dtos = new ArrayList<AlquilerPOJO>();
		
		alquileres.stream().forEach(al -> {
			
			AlquilerPOJO dto = new AlquilerPOJO();
			dto.setIdAlquiler(al.getIdAlquiler());
			dto.setName(al.getName());
			dto.setDescription(al.getDescription());
			dto.setTipo(al.getTipoAlquiler().getName());
			dtos.add(dto);				
		});
			
		return dtos;
	}	
	
	
	
	@Override
	@Transactional
	public Boolean saveRent(Alquiler alquiler) {
		Alquiler nalquiler = rentRepository.save(alquiler);
		return (nalquiler == null) ? false : true;
	}

	@Override
	@Transactional
	public Alquiler getAlquilerById(int idAlquiler) {
		return rentRepository.findOne(idAlquiler);
	}

	@Override
	@Transactional
	public Boolean editRent(Alquiler newAlquiler) {

		Alquiler oldAlquiler = getAlquilerById(newAlquiler.getIdAlquiler());
		
		oldAlquiler.setName(newAlquiler.getName());
		oldAlquiler.setDescription(newAlquiler.getDescription());
		oldAlquiler.setImage(newAlquiler.getImage());
		oldAlquiler.setTipoAlquiler(newAlquiler.getTipoAlquiler());
		
		Alquiler editAlq = rentRepository.save(oldAlquiler);	
		
		return (editAlq == null) ? false : true;
	}


	@Override
	@Transactional
	public Boolean deleteRent(RentRequest rq) {
		
		Alquiler alquiler = new Alquiler();
		BeanUtils.copyProperties(rq.getAlquiler(), alquiler);
		
		rentRepository.delete(alquiler);
		
		return (alquiler == null) ? false : true;
	}


}