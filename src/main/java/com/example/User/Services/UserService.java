package com.example.User.Services;

import com.example.User.Entity.User;
import com.example.User.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> viewAll() {
        return userRepo.findAll();
    }

    public User viewById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User update(Long id, User user) {
        User existingUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setMobile(user.getMobile());

        return userRepo.save(existingUser);
    }

    public String delete(Long id) {
        User user = viewById(id);
        userRepo.delete(user);
        return "User: " + id + " deleted";
    }
}
