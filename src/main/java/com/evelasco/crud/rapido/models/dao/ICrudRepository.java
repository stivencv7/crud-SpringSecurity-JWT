package com.evelasco.crud.rapido.models.dao;

import java.time.LocalDate;
import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evelasco.crud.rapido.models.entity.Producto;

@Repository
public interface ICrudRepository extends CrudRepository<Producto, Long> {
	
	//@Query("selec p from Producto p where modoVisible=?1")
	List<Producto>findAllByModoVisibleFalse();
	List<Producto>findAllByModoVisibleTrue();
	List<Producto>findAllByNombreLikeAndModoVisibleTrue(String nombre);
	List<Producto>findAllByFechaRegistroBetweenAndModoVisibleTrue(LocalDate desde,LocalDate hasta);
	
	
}
