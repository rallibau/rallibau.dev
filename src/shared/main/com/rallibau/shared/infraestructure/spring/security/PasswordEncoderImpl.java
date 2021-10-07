package com.rallibau.shared.infraestructure.spring.security;

import com.rallibau.shared.domain.spring.security.PasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder {
    private final org.springframework.security.crypto.password.PasswordEncoder encoder;

    public PasswordEncoderImpl(org.springframework.security.crypto.password.PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public org.springframework.security.crypto.password.PasswordEncoder instance() {
        return encoder;
    }

    public String encode(String target) {
        return encoder.encode(target);
    }
}
