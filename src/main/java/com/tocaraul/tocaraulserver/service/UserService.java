package com.tocaraul.tocaraulserver.service;

import com.tocaraul.tocaraulserver.entity.User;
import com.tocaraul.tocaraulserver.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user){
        this.userRepository.save(user);
    }

    public User findById(int id){
        return this.userRepository.findById(id);
    }

    public User getByEmail(String email){
        return (User) this.userRepository.findByEmail(email);
    }
}
