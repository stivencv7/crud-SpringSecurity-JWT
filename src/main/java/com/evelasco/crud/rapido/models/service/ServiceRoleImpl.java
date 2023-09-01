package com.evelasco.crud.rapido.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evelasco.crud.rapido.models.dao.ICrudRoleRepository;
import com.evelasco.crud.rapido.models.entity.Role;

@Service
public class ServiceRoleImpl implements IServiceRole {

	@Autowired
	private ICrudRoleRepository roleDao;

	@Override
	public Role save(Role role) {
		return roleDao.save(role) ;
	}

	@Override
	public List<Role> findAll() {
		return (List<Role>) roleDao.findAll();
	}

	@Override
	public Role findById(Long id) {
		return roleDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		
		
	}
	
	

}
