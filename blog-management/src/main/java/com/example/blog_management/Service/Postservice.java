package com.example.blog_management.Service;
import com.example.blog_management.DTO.postDTO;
import com.example.blog_management.Model.Post;
import com.example.blog_management.Model.User;
import com.example.blog_management.Repo.PostRepo;
import com.example.blog_management.Repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jose.JwaAlgorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class Postservice {
    @Autowired
    PostRepo postRepo;
    @Autowired
    JWTservice jwTService;
    @Autowired
    UserRepo userRepo;

    public ResponseEntity<String> putpost(Post posts) {
        Post post = new Post();
        post.setId(posts.getId());
        post.setText(posts.getText());
        post.setTitle(posts.getTitle());
        post.setCategory(posts.getCategory());
        post.setLikes(posts.getLikes());
        post.setComments(posts.getComments());
        post.setCreatedat(LocalDateTime.now());
        post.setUpdatedat(LocalDateTime.now());
        User user = userRepo.findById(posts.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        post.setUser(user);  // important!
        postRepo.save(post);
        return ResponseEntity.ok("Post successfully created");
    }

    public ResponseEntity<String> Updatepost(Post post) {
        // Fetch existing post
        Post existingPost = postRepo.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        //  Fetch user
        User user = userRepo.findById(post.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //  Update fields
        if(post.getLike()!=null && !existingPost.getTitle().equals(post.getTitle())){
            existingPost.setTitle(post.getTitle());
        }
        if(post.getText()!=null && !existingPost.getText().equals(post.getText()))existingPost.setText(post.getText());

        if(post.getCategory()!=null && !existingPost.getCategory().equals(post.getCategory()))existingPost.setCategory(post.getCategory());
        if(post.getComments()!=null && !existingPost.getComments().equals(post.getComments()))existingPost.setComments(post.getComments());
        if (post.getLikes() != null && !post.getLikes().equals(existingPost.getLikes())) {
            existingPost.setLikes(post.getLikes());
        }

        //  createdAt should NOT change
        existingPost.setUpdatedat(LocalDateTime.now());

        existingPost.setUser(user);

        //Save updated entity
        postRepo.save(existingPost);
        return ResponseEntity.ok("Post successfully updated");
    }

    public ResponseEntity<String> DeletePostbyId(Integer id) {
        Post existPost = postRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        postRepo.deleteById(id);
        return ResponseEntity.ok("Post succesfully deletd");
    }

    public ResponseEntity<List<postDTO>> ViewPost(Integer id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        List<Post> posts = postRepo.findByUserId(id);
        List<postDTO> postDTOList = new ArrayList<>();
        for (Post p : posts) {
            postDTO dto = new postDTO(
                    p.getId(),
                    p.getText(),
                    p.getTitle(),
                    p.getCategory(),
                    p.getLikes(),
                    p.getComments(),
                    p.getCreatedat(),
                    p.getUpdatedat()
            );
            postDTOList.add(dto);
        }
        return ResponseEntity.ok(postDTOList);
    }

    public ResponseEntity<List<postDTO>> SearchPosts(String title, String category) {
        List<Post> posts;
        if (title != null && category != null) {
            posts = postRepo.findByTitleContainingIgnoreCaseAndCategoryIgnoreCase(title,category);
        } else if (title != null) {
            posts = postRepo.findByTitleContainingIgnoreCase(title);
        } else if (category != null) {
            posts = postRepo.findByCategoryIgnoreCase(category);
        } else {
            posts = postRepo.findAll();
        }

        List<postDTO> response = posts.stream()
                .map(p -> new postDTO(
                        p.getId(),
                        p.getText(),
                        p.getTitle(),
                        p.getCategory(),
                        p.getLikes(),
                        p.getComments(),
                        p.getCreatedat(),
                        p.getUpdatedat()
                ))
                .toList();
        return ResponseEntity.ok(response);
    }
}
