package com.rallibau.acl.user.domain;

import com.rallibau.shared.domain.WordMother;
import com.rallibau.shared.domain.spring.security.PasswordEncoderFactory;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserPasswordMother {
    public static final String PASSWORD_ENCRYPTED = "password_encrypted";

    public static UserPassword random() {
        return UserPasswordMother.create(WordMother.random());
    }

    private static UserPassword create(String password) {
        PasswordEncoderFactory passwordEncoderFactory = mock(PasswordEncoderFactory.class);
        when(passwordEncoderFactory.encode(anyString())).thenReturn(PASSWORD_ENCRYPTED);
        return UserPassword.create(passwordEncoderFactory, password);
    }
}

