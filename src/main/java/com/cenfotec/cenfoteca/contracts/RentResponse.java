package com.cenfotec.cenfoteca.contracts;

import java.util.List;

import com.cenfotec.cenfoteca.pojo.AlquilerPOJO;

public class RentResponse extends BaseResponse{
	
	private List<AlquilerPOJO> alquiler;
	
	public RentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<AlquilerPOJO> getAlquilerList() {
		return alquiler;
	}

	public void setAlquilerList(List<AlquilerPOJO> alquiler) {
		this.alquiler = alquiler;
	}
}
