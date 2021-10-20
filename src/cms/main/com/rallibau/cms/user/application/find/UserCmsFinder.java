package com.rallibau.cms.user.application.find;

import com.rallibau.cms.user.domain.User;
import com.rallibau.cms.user.domain.UserCmsRepository;
import com.rallibau.cms.user.domain.UserId;
import com.rallibau.cms.user.domain.UserNotExist;
import com.rallibau.shared.domain.Service;

@Service
public class UserCmsFinder {
    private final UserCmsRepository userCmsRepository;

    public UserCmsFinder(UserCmsRepository userCmsRepository) {
        this.userCmsRepository = userCmsRepository;
    }

    public User find(String id) {
        return userCmsRepository.get(new UserId(id)).orElseThrow(() -> new UserNotExist(new UserId(id)));
    }
}
