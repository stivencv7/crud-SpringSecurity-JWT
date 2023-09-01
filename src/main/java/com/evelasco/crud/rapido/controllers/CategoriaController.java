package com.evelasco.crud.rapido.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import com.evelasco.crud.rapido.models.entity.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import com.evelasco.crud.rapido.models.service.IServiceCategoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CategoriaController {

	@Autowired
	private IServiceCategoria service;
	
	@GetMapping("/categorias")
	public List<Categoria> getLista(){
		return service.findAll();
	}
	
	/*@GetMapping("/producto/{id}")
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
	}*/

}
