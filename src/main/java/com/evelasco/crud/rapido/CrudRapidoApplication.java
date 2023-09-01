package com.evelasco.crud.rapido;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.evelasco.crud.rapido.models.entity.ERole;
import com.evelasco.crud.rapido.models.entity.Role;
import com.evelasco.crud.rapido.models.entity.Usuario;
import com.evelasco.crud.rapido.models.service.IServiceUsuario;

@SpringBootApplication
public class CrudRapidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudRapidoApplication.class, args);
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private IServiceUsuario usuarioService;
	
	@Bean
	CommandLineRunner init() {
		return args->{
			Usuario usuario=Usuario.builder()
					.email("santiago@gmail.com")
					.username("santiago")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(Role.builder()
							.name(ERole.valueOf(ERole.ADMIN.name()))
							.build()))
					.build();
			
			Usuario usuario2=Usuario.builder()
					.email("anyi@gmail.com")
					.username("anyi")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(Role.builder()
							.name(ERole.valueOf(ERole.USER.name()))
							.build()))
					.build();
			
			Usuario usuario3=Usuario.builder()
					.email("andrea@gmail.com")
					.username("andrea")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(Role.builder()
							.name(ERole.valueOf(ERole.INVITED.name()))
							.build()))
					.build();
			
			usuarioService.save(usuario);	
			usuarioService.save(usuario2);	
			usuarioService.save(usuario3);	
		};
	
	}

}
