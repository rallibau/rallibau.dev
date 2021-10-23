package com.rallibau.acl.user.infrastructure.persistence.spring;

import com.rallibau.acl.user.application.find.UserFinder;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Primary
class UserDetailsAclImpl implements UserDetailsService {


    private final UserFinder userFinder;

    public UserDetailsAclImpl(UserFinder userFinder) {
        this.userFinder = userFinder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.rallibau.acl.user.domain.User> user = userFinder.findByName(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.get().userName().value(), user.get().userPassword().value(), new ArrayList<>());
    }
}
