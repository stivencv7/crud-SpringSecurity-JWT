package com.evelasco.crud.rapido.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evelasco.crud.rapido.models.entity.Usuario;
@Repository
public interface ICrudUsuarioRepository extends CrudRepository<Usuario, Long> {
	
	Optional<Usuario> findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	Optional<Usuario> getName(String username);
}
