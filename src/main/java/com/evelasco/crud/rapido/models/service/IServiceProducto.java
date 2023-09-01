package com.evelasco.crud.rapido.models.service;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import com.evelasco.crud.rapido.models.entity.Producto;

@Service
public interface IServiceProducto {
	Producto save(Producto producto);
	List<Producto> findAll();
	Producto findById(Long id);
	void delete(Long id);
	List<Producto>findAllByModoVisibleFalse();
	List<Producto>findAllByModoVisibleTrue();
	List<Producto>findAllByNombreLikeAndModoVisibleTrue(String nombre);
	List<Producto>findAllByFechaRegistroBetween(LocalDate desde,LocalDate hasta);
	
}
