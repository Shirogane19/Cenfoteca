package com.cenfotec.cenfoteca.contracts;

import com.cenfotec.cenfoteca.pojo.TipoUsuarioPOJO;


public class UserTypeRequest extends BaseRequest{

	private TipoUsuarioPOJO userType;
	
	public UserTypeRequest(){
		
		super();
	}
	
	public TipoUsuarioPOJO getUserType() {
		return userType;
	}
	
	public void setUserType(TipoUsuarioPOJO userType) {
		this.userType = userType;
	}
	
	

	@Override
	public String toString() {
		return "UsersRequest [User Type=" + userType + "]";
	}
	
	
}
