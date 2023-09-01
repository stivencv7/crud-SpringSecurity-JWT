package com.evelasco.crud.rapido.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.evelasco.crud.rapido.models.dao.ICrudRepositoryCategoria;
import com.evelasco.crud.rapido.models.entity.Categoria;

@Service
public class IServiceCategoriaImpl implements IServiceCategoria{
	
	@Autowired
	private ICrudRepositoryCategoria categoriaDao;

	@Override
	public Categoria save(Categoria categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> findAll() {
		// TODO Auto-generated method stub
		return (List<Categoria>) categoriaDao.findAll() ;
	}

	@Override
	public Categoria findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	
}
