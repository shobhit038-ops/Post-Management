package com.example.blog_management.Repo;

import com.example.blog_management.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer>{
    User findByUsername(String username);
    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);
    void deleteByEmail(String email);
}
