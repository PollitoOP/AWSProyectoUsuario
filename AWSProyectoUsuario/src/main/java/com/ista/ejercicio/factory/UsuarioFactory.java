package com.ista.ejercicio.factory;

import com.ista.ejercicio.entity.Usuario;

public interface UsuarioFactory {
	
	 Usuario crearUsuario(String nombre, String clave, String email, Boolean estado);

}
