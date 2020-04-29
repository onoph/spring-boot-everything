package com.jwttoken.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwttoken.service.UserDetailsServiceImpl;


public class AuthTokenFilter extends OncePerRequestFilter {

	private static final String AUTH_HEADER = "Authorization";
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	
	@Override	
	protected void doFilterInternal(HttpServletRequest arg0, HttpServletResponse arg1, FilterChain filterChain)
			throws ServletException, IOException {
		
		try{
			// retreive authenticatedUser from request
			String jwt = parseJwt(arg0);
			if(null != jwt && jwtUtils.validateJwtToken(jwt)){
				String username = jwtUtils.getUserNameFromJwtToken(jwt);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(arg0));
				
				// set authenticated user from JWT token
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}

		filterChain.doFilter(arg0, arg1);
		
	}

	
	private String parseJwt(HttpServletRequest request){
		String headerAuth = request.getHeader(AUTH_HEADER);
		
		if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}
	
	
}
