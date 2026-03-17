package com.example.blog_management.Repo;

import com.example.blog_management.Model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenrepo extends JpaRepository<PasswordResetToken,Long> {
    Optional<PasswordResetToken> findByEmail(String email);
    void deleteByEmail(String email);
}
