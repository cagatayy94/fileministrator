package com.fileministrator.web.business;

import com.fileministrator.web.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserManager {
    User getFirstByEmail(String email);
}
