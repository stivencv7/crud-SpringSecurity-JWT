 package com.evelasco.crud.rapido.security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.evelasco.crud.rapido.models.service.UserDetailsServiceImpl;
import com.evelasco.crud.rapido.security.jwt.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//OncePerRequestFilter: significa una ves por peticion
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)
			throws ServletException, IOException {
		String tokenHeader=request.getHeader("Authorization");
		
		if(tokenHeader!=null && tokenHeader.startsWith("Bearer ")) {
			System.out.println("===========holar");
			String token=tokenHeader.substring(7);
			
			if(jwtUtils.isTokenValied(token)) {
				String username=jwtUtils.getUsername(token);
				UserDetails userDetails=userDetailsServiceImpl.loadUserByUsername(username);
				
				//userDetails.getAuthorities()= para obtener los roles del usuario
				UsernamePasswordAuthenticationToken authenticationToken=
						new UsernamePasswordAuthenticationToken(username, null,userDetails.getAuthorities());
				
				//SecurityContextHolder es el que contiene la autenticacion propia de la aplicacion
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
