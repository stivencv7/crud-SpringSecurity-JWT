package com.evelasco.crud.rapido.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evelasco.crud.rapido.models.dto.UsuarioDTO;
import com.evelasco.crud.rapido.models.entity.ERole;
import com.evelasco.crud.rapido.models.entity.Role;
import com.evelasco.crud.rapido.models.entity.Usuario;
import com.evelasco.crud.rapido.models.service.IServiceUsuario;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	private IServiceUsuario usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/usuario")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDTO usuario){
	
	Set<Role>roles=usuario.getRoles().stream()
				.map(role->Role.builder().name(ERole.valueOf(role))
						.build()
						).collect(Collectors.toSet());
	
		Usuario newUsuario=Usuario.builder()
				.username(usuario.getUsername())
				.email(usuario.getEmail())
				.password(passwordEncoder.encode(usuario.getPassword()))
				.roles(roles)
				.build();
		
		Map<String,Object>response=new HashMap<>();
		try {
			newUsuario=usuarioService.save(newUsuario);
		}catch (DataAccessException e) {
			response.put("mensaje", "no se realizo el inser");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("usuario",newUsuario);
		response.put("mensaje","se realizó la accion exitosamente");
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/usuario/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String,Object>response=new HashMap<>();
		try {
			usuarioService.delete(id);
		}catch (DataAccessException e) {
			response.put("mensaje", "no se realizo el delete");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","se realizó la accion exitosamente");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
