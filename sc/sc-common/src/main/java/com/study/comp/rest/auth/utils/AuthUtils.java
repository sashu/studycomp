package com.study.comp.rest.auth.utils;

import java.security.Key;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.util.Base64;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthUtils {
	public static final String SECRET_KEY = "study comp compstudy secret";

	public static String createJWTToken(Map<String, Object> authMap, int expParam, int expValue) {

		Calendar exp = Calendar.getInstance();
		exp.add(expParam, expValue);

		Map<String, Object> headerParams = new HashMap<String, Object>();
		headerParams.put("typ", "JWT");
		headerParams.put("alg", "HS256");

		Map<String, Object> claimsMap = new HashMap<String, Object>();
		claimsMap.put("userId", authMap.get("userId"));
		claimsMap.put("appId", authMap.get("clientId"));
		claimsMap.put("logUserId", authMap.get("logUserId"));
		claimsMap.put("user", authMap.get("user"));
		claimsMap.put("exp", exp.getTimeInMillis());

		String base64SecretKey = Base64.encodeBase64String(SECRET_KEY.getBytes());
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64SecretKey);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		String token = Jwts.builder().setHeaderParams(headerParams).setClaims(claimsMap)
				.signWith(SignatureAlgorithm.HS256, signingKey).compact();
		System.out.println(token);
		return token;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> getJWTPayLoad(String token, String secret) {
		if (StringUtils.isNotBlank(token)) {
			String secretKey = SECRET_KEY;
			if (StringUtils.isNotBlank(secret)) {
				secretKey = secret;
			}
			String base64SecretKey = Base64.encodeBase64String(secretKey.getBytes());
			Jwt jwt = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64SecretKey)).parse(token);
			Map<String, Object> payLoad = (Map<String, Object>) jwt.getBody();
			return payLoad;
		} else {
			return null;
		}
	}
}
