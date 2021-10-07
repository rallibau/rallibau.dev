package com.rallibau.shared.domain.spring.security;

public interface PasswordEncoderFactory {

    org.springframework.security.crypto.password.PasswordEncoder instance();

    String encode(String target);
}
