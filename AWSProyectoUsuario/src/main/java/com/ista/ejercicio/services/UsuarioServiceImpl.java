package com.ista.ejercicio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.ejercicio.dao.IUsuarioDao;
import com.ista.ejercicio.entity.Usuario;
import com.ista.ejercicio.factory.UsuarioFactory;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDao UDao;

    @Autowired
    private UsuarioFactory usuarioFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarTodo() {
        return (List<Usuario>) UDao.findAll();
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return UDao.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorID(Long id) {
        return UDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void Eliminar(Long id) {
        UDao.deleteById(id);
    }

    public Usuario crearUsuario(String nombre, String clave, String email, Boolean estado) {
        Usuario usuario = usuarioFactory.crearUsuario(nombre, clave, email, estado);
        return save(usuario);
    }
}
