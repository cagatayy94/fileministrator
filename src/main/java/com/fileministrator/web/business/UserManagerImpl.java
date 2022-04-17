package com.fileministrator.web.business;

import com.fileministrator.web.entity.User;
import com.fileministrator.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagerImpl implements UserManager{

    private final UserRepository userRepository;

    @Autowired
    public UserManagerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getFirstByEmail(String email) {
        return this.userRepository.getFirstByEmail(email);
    }
}
