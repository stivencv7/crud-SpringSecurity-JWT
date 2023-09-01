package com.evelasco.crud.rapido.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.evelasco.crud.rapido.models.entity.Producto;
import com.evelasco.crud.rapido.models.service.IServiceProducto;

import jakarta.validation.Valid;


@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductoController {

	@Autowired
	private IServiceProducto service;
	
	@GetMapping("/productos/novisibles")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public List<Producto> getListaVisible(){
		return service.findAllByModoVisibleFalse();
	}
	
	@GetMapping("/productos/visibles")
	public List<Producto> getLista(){
		return service.findAllByModoVisibleTrue();
	}
	
	@GetMapping("/productos/{nombre}")
	public List<Producto> getListaLike(@PathVariable String nombre){
		return service.findAllByNombreLikeAndModoVisibleTrue(nombre);
	}
	
	@GetMapping({"/productos/filtrado"})
	public List<Producto> getListaBetween(@RequestParam(name = "desde") LocalDate desde,@RequestParam(name = "hasta") LocalDate hasta){
		System.out.println("//////////////=== desde "+desde +" hasta "+hasta);
		return service.findAllByFechaRegistroBetween(desde, hasta);
	}
	
	@GetMapping("/producto/{id}")
	public ResponseEntity<?> getProducto(@PathVariable Long id) {
		Producto producto =null;
		Map<String,Object>response=new HashMap<>();
		try {
			producto=service.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Ocurrio un erro");
			response.put("error",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(producto==null) {
			response.put("mensaje", "No se encontraron datos en base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Producto>(producto,HttpStatus.OK);
	}
	@PostMapping("/producto")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<?> saveProducto(@Valid @RequestBody Producto producto,BindingResult result){
		Producto newProducto =null;
		Map<String,Object>response=new HashMap<>();
		
		if(result.hasErrors()) {
			List<String>errors=result.getFieldErrors().stream()
					.map(err->"El campo '"+err.getField()+"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			newProducto=service.save(producto);
		}catch(DataAccessException e) {
			response.put("mensaje", "Ocurrio un error en el insert");
			response.put("error",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("producto", newProducto);
		response.put("mensaje","Se registro correctamente");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/producto/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> setProducto(@Valid @RequestBody Producto producto,BindingResult result,@PathVariable Long id){
	
		Producto updateProducto=service.findById(id);
		Map<String,Object>response=new HashMap<>();
		if(result.hasErrors()) {
			List<String>errors=result.getFieldErrors().stream()
					.map(err->"El campo '"+err.getField()+"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		try {
			updateProducto.setNombre(producto.getNombre());
			updateProducto.setCantidad(producto.getCantidad());
			updateProducto.setPrecio(producto.getPrecio());
			updateProducto.setCategoria(producto.getCategoria());
		}catch(DataAccessException e) {
			response.put("mensaje", "Ocurrio un error en el update");
			response.put("error",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		updateProducto=service.save(updateProducto);
		response.put("producto", updateProducto);
		response.put("mensaje","Se actualizó correctamente");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/producto/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteProducto(@PathVariable Long  id){
		Map<String,Object>response=new HashMap<>();
		try {
			service.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Ocurrio un erro");
			response.put("error",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","Se elimino correctamente");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PutMapping("/modovisible/{id}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<?> setProductoModoVisible(@PathVariable Long id){
		
		Producto updateProducto=service.findById(id);
		Map<String,Object>response=new HashMap<>();
		
		try {
			if(updateProducto.getModoVisible()) {
				updateProducto.setModoVisible(false);
			}else {
				updateProducto.setModoVisible(true);
			}
		}catch(DataAccessException e) {
			response.put("mensaje", "Ocurrio un error en el update");
			response.put("error",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		updateProducto=service.save(updateProducto);
		response.put("producto", updateProducto);
		response.put("mensaje","Se actualizó correctamente");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}

}
