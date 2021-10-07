package com.rallibau.shared.domain.spring.security;

public interface PasswordEncoder {

    String encode(String target);
}
