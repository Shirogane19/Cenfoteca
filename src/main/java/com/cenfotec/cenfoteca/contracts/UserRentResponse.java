package com.cenfotec.cenfoteca.contracts;

import java.util.List;

import com.cenfotec.cenfoteca.pojo.UsuarioHasAlquilerPOJO;

public class UserRentResponse extends BaseResponse{
	
	private List<UsuarioHasAlquilerPOJO> userRent;

	public UserRentResponse() {
		super();
	}
	
	public List<UsuarioHasAlquilerPOJO> getUsuarRent() {
		return userRent;
	}

	public void setUserRent(List<UsuarioHasAlquilerPOJO> userRent) {
		this.userRent = userRent;
	}
	
}





