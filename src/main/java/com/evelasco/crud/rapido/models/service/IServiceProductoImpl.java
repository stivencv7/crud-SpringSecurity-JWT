package com.evelasco.crud.rapido.models.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evelasco.crud.rapido.models.dao.ICrudRepository;
import com.evelasco.crud.rapido.models.entity.Producto;
@Service
public class IServiceProductoImpl implements IServiceProducto{
	
	@Autowired
	private ICrudRepository productoDao;

	@Override
	@Transactional
	public Producto save(Producto producto) {
		
		return productoDao.save(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productoDao.deleteById(id);
	
	}

	@Override
	@Transactional
	public List<Producto> findAllByModoVisibleFalse() {
		return productoDao.findAllByModoVisibleFalse();
	}

	@Override
	@Transactional
	public List<Producto> findAllByModoVisibleTrue() {
		return productoDao.findAllByModoVisibleTrue();
	}

	@Override
	@Transactional
	public List<Producto> findAllByNombreLikeAndModoVisibleTrue(String nombre) {
		return productoDao.findAllByNombreLikeAndModoVisibleTrue("%"+nombre+"%");
	}

	@Override
	public List<Producto> findAllByFechaRegistroBetween(LocalDate desde, LocalDate hasta) {
		System.out.println("d== "+desde+"has== "+hasta);
		return productoDao.findAllByFechaRegistroBetweenAndModoVisibleTrue(desde, hasta);
	}
	
	
}
