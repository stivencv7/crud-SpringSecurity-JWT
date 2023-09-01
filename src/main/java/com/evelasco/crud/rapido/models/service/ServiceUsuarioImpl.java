package com.evelasco.crud.rapido.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evelasco.crud.rapido.models.dao.ICrudUsuarioRepository;
import com.evelasco.crud.rapido.models.entity.Usuario;
@Service
public class ServiceUsuarioImpl implements IServiceUsuario {
	
	@Autowired
	private ICrudUsuarioRepository usuarioDoa;

	@Override
	public Optional<Usuario> findByUsername(String username) {
		return usuarioDoa.findByUsername(username);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioDoa.save(usuario);
	}

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>)usuarioDoa.findAll();
	}

	@Override
	public Usuario findById(Long id) {
		return usuarioDoa.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		usuarioDoa.deleteById(id);
	}

}
