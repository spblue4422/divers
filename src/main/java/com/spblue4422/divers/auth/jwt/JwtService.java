package com.spblue4422.divers.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtService {
	public static String decodeJwt(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().get("loginId", String.class);
	}

	public static Boolean checkTokenExpired(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().getExpiration().before(new Date());
	}

	public static String createJwt(String loginId, String secretKey, Long expiredTime) {
		Claims claims = Jwts.claims();
		claims.put("loginId", loginId);

		return Jwts.builder()
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
				   .signWith(SignatureAlgorithm.HS256, secretKey)
				   .compact();
	}
}
