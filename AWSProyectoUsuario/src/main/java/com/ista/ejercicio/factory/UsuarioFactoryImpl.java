package com.ista.ejercicio.factory;

import org.springframework.stereotype.Component;

import com.ista.ejercicio.entity.Usuario;

@Component
public class UsuarioFactoryImpl implements UsuarioFactory {

    @Override
    public Usuario crearUsuario(String nombre, String clave, String email, Boolean estado) {
        return new Usuario(nombre, clave, email, estado);
    }
}
