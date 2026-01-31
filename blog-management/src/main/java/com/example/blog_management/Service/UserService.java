package com.example.blog_management.Service;

import com.example.blog_management.DTO.userDTO;
import com.example.blog_management.Model.User;
import com.example.blog_management.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public ResponseEntity<userDTO> getuserbyid(Integer id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        User user = userRepo.getReferenceById(id);
        userDTO userDTO = new userDTO(user.getId(),user.getUsername(),user.getEmail());
        return ResponseEntity.ok(userDTO);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public void deleteuser(Integer id) {
        userRepo.deleteById(id);
    }

    public ResponseEntity<userDTO> getUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        userDTO userDTO = new userDTO(user.getId(), user.getUsername(),user.getEmail());
        return ResponseEntity.ok(userDTO);
    }
}
