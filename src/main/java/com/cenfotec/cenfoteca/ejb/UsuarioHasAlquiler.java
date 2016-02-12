package com.cenfotec.cenfoteca.ejb;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuariohasalquiler database table.
 * 
 */

@Entity
@NamedQuery(name="Usuariohasalquiler.findAll", query="SELECT u FROM UsuarioHasAlquiler u")
public class UsuarioHasAlquiler implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idUsuarioHasAlquiler;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="Usuario_idUsuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Alquiler
	@ManyToOne
	@JoinColumn(name="Alquiler_idAlquiler")
	private Alquiler alquiler;
	
	private boolean estado_renta;

	public UsuarioHasAlquiler() {
	}

	public int getId() {
		return this.idUsuarioHasAlquiler;
	}

	public void setId(int id) {
		this.idUsuarioHasAlquiler = id;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Alquiler getAlquiler() {
		return this.alquiler;
	}

	public void setAlquiler(Alquiler alquiler) {
		this.alquiler = alquiler;
	}
	
	public boolean isEstado_renta() {
		return estado_renta;
	}

	public void setEstado_renta(boolean estado_renta) {
		this.estado_renta = estado_renta;
	}


}