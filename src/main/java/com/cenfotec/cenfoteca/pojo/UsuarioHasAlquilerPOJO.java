package com.cenfotec.cenfoteca.pojo;


public class UsuarioHasAlquilerPOJO {
	
	
	private int idUsuarioHasAlquiler;
	
	private AlquilerPOJO alquiler;

	private UsuarioPOJO usuario;
	
	private boolean estado_renta;

	public UsuarioHasAlquilerPOJO() {
		super();
	}


	public int getIdUsuarioHasAlquiler() {
		return idUsuarioHasAlquiler;
	}


	public void setIdUsuarioHasAlquiler(int idUsuarioHasAlquiler) {
		this.idUsuarioHasAlquiler = idUsuarioHasAlquiler;
	}


	public AlquilerPOJO getAlquiler() {
		return alquiler;
	}


	public void setAlquiler(AlquilerPOJO alquiler) {
		this.alquiler = alquiler;
	}


	public UsuarioPOJO getUsuario() {
		return usuario;
	}


	public void setUsuario(UsuarioPOJO usuario) {
		this.usuario = usuario;
	}
	
	public boolean isEstado_renta() {
		return estado_renta;
	}

	public void setEstado_renta(boolean estado_renta) {
		this.estado_renta = estado_renta;
	}







	
	
	
}
