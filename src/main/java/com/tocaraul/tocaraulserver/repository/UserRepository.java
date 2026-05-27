package com.tocaraul.tocaraulserver.repository;

import com.tocaraul.tocaraulserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(int id);
    User findByEmail(String email);
}
