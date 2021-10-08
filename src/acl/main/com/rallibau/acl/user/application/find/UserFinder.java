package com.rallibau.acl.user.application.find;

import com.rallibau.acl.user.domain.*;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;

import java.util.Arrays;
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
        Criteria criteria = new Criteria(new Filters(Arrays.asList(Filter.create("userName", "=", userName))), Order.asc("userName"));
        List<User> users=  userRepository.matching(criteria);

        if(users.isEmpty()){
            return Optional.empty();
        }

        if(users.size() > 1){
            throw new UserDuplicated(new UserName(userName));
        }

        return Optional.of(users.get(0));
    }
}
