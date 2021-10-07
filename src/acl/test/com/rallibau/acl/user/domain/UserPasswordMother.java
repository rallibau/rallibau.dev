package com.rallibau.acl.user.domain;

import com.rallibau.shared.domain.WordMother;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserPasswordMother {
    public static final String PASSWORD_ENCRYPTED = "password_encrypted";

    public static UserPassword random() {
        return UserPasswordMother.create(WordMother.random());
    }

    private static UserPassword create(String passsword) {
        BCryptPasswordEncoder bCryptPasswordEncoder =  mock(BCryptPasswordEncoder.class);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(PASSWORD_ENCRYPTED);
        return UserPassword.create(bCryptPasswordEncoder,passsword);
    }
}

