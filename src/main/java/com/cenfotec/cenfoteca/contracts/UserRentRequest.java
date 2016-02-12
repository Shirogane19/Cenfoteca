package com.cenfotec.cenfoteca.contracts;

import com.cenfotec.cenfoteca.pojo.UsuarioHasAlquilerPOJO;

public class UserRentRequest extends BaseRequest {
	
	private UsuarioHasAlquilerPOJO userRent;
	
	public UserRentRequest() {
		super();
	}
	
	public UsuarioHasAlquilerPOJO getUserRent() {
		return userRent;
	}
	
	public void setUserRent(UsuarioHasAlquilerPOJO userRent) {
		this.userRent = userRent;
	}

	@Override
	public String toString() {
		return "UsersRequest [User Rent=" + userRent + "]";
	}
}
