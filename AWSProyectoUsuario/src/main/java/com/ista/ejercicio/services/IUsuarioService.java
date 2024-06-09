package com.ista.ejercicio.services;

import java.util.List;

import com.ista.ejercicio.entity.Usuario;

public interface IUsuarioService {
    public List<Usuario> listarTodo();
    public Usuario save(Usuario usuario);
    public Usuario buscarPorID(Long id);
    public void Eliminar(Long id);
    public Usuario crearUsuario(String nombre, String clave, String email, Boolean estado); 
}
