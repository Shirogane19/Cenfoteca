package com.cenfotec.cenfoteca.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;



/**
 * The persistent class for the TipoUsuario database table.
 * 
 */
@Entity
@NamedQuery(name="TipoUsuario.findAll", query="SELECT q FROM TipoUsuario q")
public class TipoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTipoUsuario;

	private String tipo;
	
	//bi-directional many-to-one association to Usuarios
	@OneToMany(mappedBy="tipos")
	private List<Usuario> usuarios;

	public TipoUsuario() {
	}
	
	public int getIdTipoUsuario() {
		return this.idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String name) {
		this.tipo = name;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setTipoUsuario(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setTipoUsuario(null);

		return usuario;
	}
	

}
