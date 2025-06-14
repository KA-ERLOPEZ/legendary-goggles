package com.sistema.pizzeria.elpirata.api.core.config.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTProvider {

	private static final Logger logger = LoggerFactory.getLogger(JWTProvider.class);

	@Value("${jwt.access-token-secret}")
	private String accessTokenSecret;

	@Value("${jwt.refresh-token-secret}")
	private String refreshTokenSecret;

	@Value("${jwt.access-token-expiration}")
	private long accessTokenExpiration;

	@Value("${jwt.refresh-token-expiration}")
	private long refreshTokenExpiration;

	private SecretKey getSecretKey(String secret) {
		byte[] keyBytes = Decoders.BASE64URL.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateAccessToken(UserDetails details) {
		return generateToken(details.getUsername(), accessTokenExpiration, accessTokenSecret);
	}

	public String generateRefreshToken(String username) {
		return generateToken(username, refreshTokenExpiration, refreshTokenSecret);
	}

	private String generateToken(String subject, long expiration, String secret) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expiration * 1000);

		return Jwts.builder().subject(subject).issuedAt(now).expiration(expiryDate).signWith(getSecretKey(secret))
				.compact();
	}

	public String getUserFromRefreshToken(String token) {
		return getUserFromToken(token, refreshTokenSecret);
	}

	public String getUserFromAccessToken(String token) {
		return getUserFromToken(token, accessTokenSecret);
	}

	private String getUserFromToken(String token, String secret) {
		try {
			return Jwts.parser().verifyWith(getSecretKey(secret)).build().parseSignedClaims(token).getPayload()
					.getSubject();
		} catch (JwtException ex) {
			logger.error("Error al obtener el usuario del token: " + ex.getMessage());
			return null;
		}
	}

	public boolean validateToken(String token, TokenType type) {
		try {
			SecretKey key = type == TokenType.ACCESS ? getSecretKey(accessTokenSecret)
					: getSecretKey(refreshTokenSecret);

			Jwts.parser().verifyWith(key).build().parseSignedClaims(token);

			return true;
		} catch (MalformedJwtException ex) {
			logger.error("Token mal formado: " + ex.getMessage());
		} catch (UnsupportedJwtException ex) {
			logger.error("Toke no suportado: " + ex.getMessage());
		} catch (ExpiredJwtException ex) {
			logger.error("Token expirado: " + ex.getMessage());
		} catch (IllegalArgumentException ex) {
			logger.error("Token no válido: " + ex.getMessage());
		} catch (io.jsonwebtoken.security.SignatureException ex) {
			logger.error("Error en la firma del token: " + ex.getMessage());
		}
		return false;
	}

}
