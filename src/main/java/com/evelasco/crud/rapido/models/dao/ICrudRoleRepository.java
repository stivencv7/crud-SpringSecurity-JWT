package com.evelasco.crud.rapido.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evelasco.crud.rapido.models.entity.Role;
@Repository
public interface ICrudRoleRepository extends CrudRepository<Role, Long> {

}
