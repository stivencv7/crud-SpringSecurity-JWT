package com.evelasco.crud.rapido.models.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.evelasco.crud.rapido.models.entity.Categoria;


@Service
public interface IServiceCategoria {
	Categoria save(Categoria categoria);
	List<Categoria> findAll();
	Categoria findById(Long id);
	void delete(Long id);
	
}
