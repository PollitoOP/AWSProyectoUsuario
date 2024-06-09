package com.ista.ejercicio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Data
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id_usuario;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "clave")
	private String clave;

	@Column(name = "email")
	private String email;

	@Column(name = "estado")
	private Boolean estado;
	
	@Transient
	private String fotoURL;
	private String fotoPATH;
	
	@Transient
	private String cedulaURL;
	private String cedulaPATH;

	public Usuario() {

	}

	public Usuario(Long id_usuario, String nombre, String clave, String email, Boolean estado) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.clave = clave;
		this.email = email;
		this.estado = estado;
	}

	public Usuario(String nombre, String clave, String email, Boolean estado) {
		super();
		this.nombre = nombre;
		this.clave = clave;
		this.email = email;
		this.estado = estado;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	

	public String getFotoURL() {
		return fotoURL;
	}

	public void setFotoURL(String fotoURL) {
		this.fotoURL = fotoURL;
	}

	public String getFotoPATH() {
		return fotoPATH;
	}

	public void setFotoPATH(String fotoPATH) {
		this.fotoPATH = fotoPATH;
	}

	public String getCedulaURL() {
		return cedulaURL;
	}

	public void setCedulaURL(String cedulaURL) {
		this.cedulaURL = cedulaURL;
	}

	public String getCedulaPATH() {
		return cedulaPATH;
	}

	public void setCedulaPATH(String cedulaPATH) {
		this.cedulaPATH = cedulaPATH;
	}

	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", nombre=" + nombre + ", clave=" + clave + ", email=" + email
				+ ", estado=" + estado + "]";
	}

}
