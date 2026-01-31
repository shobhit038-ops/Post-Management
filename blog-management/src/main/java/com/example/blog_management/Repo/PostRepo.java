package com.example.blog_management.Repo;

import com.example.blog_management.Model.Post;
import com.example.blog_management.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByTitleContainingIgnoreCaseAndCategoryIgnoreCase(String title, String category);
    List<Post> findByTitleContainingIgnoreCase(String title);
    List<Post> findByCategoryIgnoreCase(String category);
    List<Post> findByUserId(int userId);
}
