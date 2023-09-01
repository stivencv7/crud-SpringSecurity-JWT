package com.evelasco.crud.rapido.models.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="productos")
public class Producto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 3)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private Categoria categoria;
	
	@NotNull
	@Min(value=1)
	private int precio;
	
	@NotNull
	private int cantidad;
	
	@Column(name = "modo_visible")
	private boolean modoVisible;
	
	@Column(name="fecha_registro")
	private LocalDate fechaRegistro;
	
	@PostPersist
	private void registerFechaRegistro() {
		this.fechaRegistro=LocalDate.now();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public boolean getModoVisible() {
		return modoVisible;
	}

	public void setModoVisible(boolean modoVisible) {
		this.modoVisible = modoVisible;
	}
	
	
	
}
