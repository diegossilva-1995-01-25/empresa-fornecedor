package br.com.diegossilva.empresa_fornecedor.demo.security;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTHelper {

	private static final Key SECRET_KEY = Keys.hmacShaKeyFor("sua-chave-secreta-muito-longa-e-segura-aqui-mude-isso-em-producao".getBytes());
	private static final int MINUTES = 60;

	public static String generateToken(String email) {
		var now = Instant.now();
	    return Jwts.builder()
	        .setSubject(email)
	        .setIssuedAt(Date.from(now))
	        .setExpiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
	        .signWith(SECRET_KEY)
	        .compact();
	}

	public static String extractUsername(String token) {
		return getTokenBody(token).getSubject();
	}

	public static Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private static Claims getTokenBody(String token) {
		try {
	    	return Jwts
	    		.parserBuilder()
	    		.setSigningKey(SECRET_KEY)
	    		.build()
	    		.parseClaimsJws(token)
	    		.getBody();
		} catch (ExpiredJwtException e) {
	    	throw new AccessDeniedException("Access denied: " + e.getMessage());
	    }
	}

	private static boolean isTokenExpired(String token) {
		Claims claims = getTokenBody(token);
		return claims.getExpiration().before(new Date());
	}
	
}