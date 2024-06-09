package com.ista.ejercicio.dao;

import org.springframework.data.repository.CrudRepository;

import com.ista.ejercicio.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

}

