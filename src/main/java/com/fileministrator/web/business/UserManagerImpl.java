package com.fileministrator.web.business;

import com.fileministrator.web.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagerImpl implements UserManager{

    private final UserManager userManager;

    @Autowired
    public UserManagerImpl(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public User getFirstByEmail(String email) {
        return this.userManager.getFirstByEmail(email);
    }
}
