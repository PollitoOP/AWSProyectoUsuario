package com.ista.ejercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ista.ejercicio.entity.Usuario;
import com.ista.ejercicio.services.IUsuarioService;
import com.ista.ejercicio.services.S3Service;

import java.util.List;
import java.util.stream.Collectors;

@RestController // http://localhost:8080/apis/listado -
				// http://localhost:8080/swagger-ui/index.html#/
@RequestMapping("/apis")
public class UsuarioController {

	@Autowired
	private IUsuarioService Servi;

	@Autowired
	private S3Service s3Service;

	@GetMapping("/listado")
	public List<Usuario> getAll() {
		return Servi.listarTodo().stream().peek(usuario -> {
			usuario.setFotoURL(s3Service.getObjectURL(usuario.getFotoPATH()));
			usuario.setCedulaURL(s3Service.getObjectURL(usuario.getCedulaPATH()));
		}).collect(Collectors.toList());
	}

	// Buscar por ID
	@GetMapping("/listadoID/{id}")
	public Usuario show(@PathVariable Long id) {
		return Servi.buscarPorID(id);
	}

	// Guardar
	@PostMapping("/IngresarUsuario")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario create(@RequestBody Usuario usuario) {
		Servi.save(usuario);
		usuario.setFotoURL(s3Service.getObjectURL(usuario.getFotoPATH()));
		usuario.setCedulaURL(s3Service.getObjectURL(usuario.getCedulaPATH()));
		return usuario;
	}

	// Editar
	@PutMapping("/editarUsuario/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id) {
		Usuario uActual = Servi.buscarPorID(id);
		uActual.setNombre(usuario.getNombre());
		uActual.setEmail(usuario.getEmail());
		uActual.setClave(usuario.getClave());
		uActual.setEstado(usuario.getEstado());
		return Servi.save(uActual);
	}

	// Eliminar
	@DeleteMapping("/eliminarID/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable("id") Long id) {
		Servi.Eliminar(id);
	}
}
