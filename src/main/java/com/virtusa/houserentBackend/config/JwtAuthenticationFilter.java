package com.virtusa.houserentBackend.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.virtusa.houserentBackend.service.IMPL.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	// get user details from user
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	// this class use to check the token (isValid, isExpired....)
	@Autowired
	private JwtUtils jwtutils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println(requestTokenHeader);

		String username = null;
		String jwtToken = null;

		// if you want to start token with something
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = this.jwtutils.extractUsername(jwtToken);
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("jwt token has expired!!!");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error Occur in token match");
			}
		}

		else {
			System.out.println("Invalid token not start with Bearer string");
		}

		// validate token
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			final UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(username);
			if(this.jwtutils.validateToken(jwtToken, userDetails)) {
				//token isvalid
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}else {
			System.out.println("Token is not Valid !!");
		}
		filterChain.doFilter(request, response);
	}
	
}
