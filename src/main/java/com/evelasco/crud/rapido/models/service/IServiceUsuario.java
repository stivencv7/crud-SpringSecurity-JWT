package com.evelasco.crud.rapido.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.evelasco.crud.rapido.models.entity.Usuario;
@Service
public interface IServiceUsuario {
	
	Usuario save(Usuario usuario);
	List<Usuario>findAll();
	Usuario findById(Long id);
	void delete(Long id);
	Optional<Usuario> findByUsername(String username);
}
