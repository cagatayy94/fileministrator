package com.fileministrator.web.repository;

import com.fileministrator.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getFirstByEmail(String email);
}
