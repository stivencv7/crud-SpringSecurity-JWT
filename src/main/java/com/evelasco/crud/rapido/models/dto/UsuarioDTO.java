package com.evelasco.crud.rapido.models.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
	private String email;
	private String username;
	private String password;
	private Set<String>roles;
	
	
	
}
