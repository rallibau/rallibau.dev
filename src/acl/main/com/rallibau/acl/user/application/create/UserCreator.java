package com.rallibau.acl.user.application.create;

import com.rallibau.acl.user.domain.User;
import com.rallibau.acl.user.domain.UserRepository;
import com.rallibau.shared.domain.Service;

@Service
public class UserCreator {
    private final UserRepository userRepository;

    public UserCreator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user) {
        userRepository.save(user);
    }
}
