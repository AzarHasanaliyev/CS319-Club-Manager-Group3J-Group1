package com.club1.studentclubmanager.service;

import com.club1.studentclubmanager.exception.CustomNotFoundException;
import com.club1.studentclubmanager.model.Club;
import com.club1.studentclubmanager.model.User;
import com.club1.studentclubmanager.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User user, Long id){
        // Check if the user exists
        User existingUser = userRepository.findById(id).
                orElseThrow(() -> new CustomNotFoundException("User by id "+ id + " was not found"));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).
                orElseThrow(() -> new CustomNotFoundException("User by id "+ id + " was not found"));
    }
    public void deleteUserById(Long id){
        boolean exists = userRepository.existsById(id);
        if (!exists){
            throw new CustomNotFoundException("User by id " + id + " was not found");
        }
        userRepository.deleteById(id);
    }
}
