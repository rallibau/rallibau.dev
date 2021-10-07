package com.rallibau.shared.infraestructure.spring.security;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.spring.security.PasswordEncoderFactory;

public class PasswordEncoderImpl implements PasswordEncoderFactory {
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
