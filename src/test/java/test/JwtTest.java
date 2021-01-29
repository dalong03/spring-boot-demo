package test;

import java.util.Date;

import org.junit.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTest {

	@Test
	public void generate() {
		long now = System.currentTimeMillis();
		long exp = now + 1000 * 600;// 300秒过期
		JwtBuilder jwtBuilder = Jwts.builder().setId("888").setSubject("小白").setIssuedAt(new Date())// 签发时间
				.setExpiration(new Date(exp))// 过期时间
				.signWith(SignatureAlgorithm.HS256, "hahaha");
		String token = jwtBuilder.compact();
		System.out.println(token);
	}

	@Test
	public void parse() {
		String compactJwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE2MTA0MjIzODEsImV4cCI6MTYxMDQyMjk4MX0.x7GuCaLv1zdFJaeqMsDkFsQ8aaBBf0__7gs67Vi-y4g";
		Claims claims = Jwts.parser().setSigningKey("hahaha").parseClaimsJws(compactJwt).getBody();
		System.out.println(claims);
	}
}
