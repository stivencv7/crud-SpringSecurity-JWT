package com.evelasco.crud.rapido.models.service;

import java.util.List;

import com.evelasco.crud.rapido.models.entity.Role;

public interface IServiceRole {
	
	Role save(Role role);
	List<Role> findAll();
	Role findById(Long id);
	void delete(Long id);

}
