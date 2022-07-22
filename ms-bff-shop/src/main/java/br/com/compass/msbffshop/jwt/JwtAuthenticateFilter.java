package br.com.compass.msbffshop.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.compass.msbffshop.client.entity.Customer;

public class JwtAuthenticateFilter extends UsernamePasswordAuthenticationFilter{

	private final AuthenticationManager authenticationManager;
	
	private static final int TOKEN_EXPIRATION = 600000;
	public static final String TOKEN_PASSWORD = "189a4ee2-0924-11ed-861d-0242ac120002";
	
	public JwtAuthenticateFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl("/v1/login");
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Customer customer = new ObjectMapper().readValue(request.getInputStream(), Customer.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					customer.getEmail(), customer.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException("User authentication failed.");
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		CustomerDetailsData customerData = (CustomerDetailsData) authResult.getPrincipal();
		
		String token = JWT.create()
				.withSubject(customerData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
				.sign(Algorithm.HMAC512(TOKEN_PASSWORD));
		response.getWriter().write(token);
		response.getWriter().flush();
	}
}
