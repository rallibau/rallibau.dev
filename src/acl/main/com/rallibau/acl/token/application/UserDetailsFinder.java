package com.rallibau.acl.token.application;

import com.rallibau.acl.user.application.find.UserFinder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsFinder implements UserDetailsService {

    private final UserFinder userFinder;

    public UserDetailsFinder(UserFinder userFinder) {
        this.userFinder = userFinder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<com.rallibau.acl.user.domain.User> user = userFinder.findByName(username);
        if(!user.isPresent()){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.get().userName().value(), user.get().userPassword().value(), new ArrayList<>());
    }
}
