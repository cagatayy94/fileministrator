package com.fileministrator.web.business;

import com.fileministrator.web.entity.User;

public interface UserManager {
    User getFirstByEmail(String email);
}
