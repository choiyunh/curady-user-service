package com.curady.userservice.config.jwt;

public interface JwtProperties {
	String SECRET = "ro0man5tici0sm9qgajsdb281";
	int EXPIRATION_TIME = 864000000;
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}
