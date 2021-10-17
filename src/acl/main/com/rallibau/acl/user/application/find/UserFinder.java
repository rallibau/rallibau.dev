package com.rallibau.acl.user.application.find;

import com.rallibau.acl.user.domain.User;
import com.rallibau.acl.user.domain.UserId;
import com.rallibau.acl.user.domain.UserNotExist;
import com.rallibau.acl.user.domain.UserRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

@Service
public class UserFinder {
    private final UserRepository userRepository;

    public UserFinder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User find(String id) {
        return userRepository.get(new UserId(id)).orElseThrow(() -> new UserNotExist(new UserId(id)));
    }

    public List<User> findByCriteria(Criteria criteria) {
        return userRepository.matching(criteria);
    }

    public Optional<User> findByName(String userName) {
        return userRepository.findByName(userName);
    }
}
