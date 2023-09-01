package com.evelasco.crud.rapido.models.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.evelasco.crud.rapido.models.entity.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private IServiceUsuario serviceUsuario;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario=serviceUsuario.findByUsername(username).orElseThrow(()->
			new UsernameNotFoundException("El usuario '"+username+"' no existe.")
		);
		//SimpleGrantedAuthority es la athorizacion de 
		Collection<? extends GrantedAuthority>authorities=usuario.getRoles().stream()
				.map(role-> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
				.collect(Collectors.toSet());
		
		
		return new User(usuario.getUsername(),
				usuario.getPassword(),
				true,true,true,true,
				authorities) ;
	}

}
