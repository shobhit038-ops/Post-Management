package com.example.blog_management.Service;
import com.example.blog_management.DTO.postDTO;
import com.example.blog_management.Model.Post;
import com.example.blog_management.Model.User;
import com.example.blog_management.Repo.PostRepo;
import com.example.blog_management.Repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
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
    @Autowired
    EmailService emailService;
    @Autowired
    JWTservice jwtservice;

    public ResponseEntity<String> putpost(Post posts,String token) {
        String username = jwtservice.extractUserName(token);

        User user = userRepo.findByUsername(username);
        posts.setUser(user);
        posts.setCreatedat(LocalDateTime.now());
        posts.setUpdatedat(LocalDateTime.now());

        postRepo.save(posts);
        return ResponseEntity.ok("Post successfully created");
    }

    public ResponseEntity<String> Updatepost(Post post) {
        Post existingPost = postRepo.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        if(post.getTitle()!=null && !existingPost.getTitle().equals(post.getTitle())){
            existingPost.setTitle(post.getTitle());
        }
        if(post.getText()!=null && !existingPost.getText().equals(post.getText())){
            existingPost.setText(post.getText());
        }
        if(post.getCategory()!=null && !existingPost.getCategory().equals(post.getCategory())){
            existingPost.setCategory(post.getCategory());
        }
        if(post.getComments()!=null && !existingPost.getComments().equals(post.getComments())){existingPost.setComments(post.getComments());}
        if (post.getLikes() != null && !post.getLikes().equals(existingPost.getLikes())) {existingPost.setLikes(post.getLikes());}
        existingPost.setUpdatedat(LocalDateTime.now());
        postRepo.save(existingPost);
        return ResponseEntity.ok("Post successfully updated");
    }

    public ResponseEntity<String> DeletePostbyId(Integer id) {
        Post existPost = postRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        postRepo.deleteById(id);
        return ResponseEntity.ok("Post succesfully deletd");
    }

    public ResponseEntity<List<postDTO>> ViewPost(String username) {
        if (!userRepo.existsByUsername(username)) {
            throw new RuntimeException("User not found");
        }
        User user = userRepo.findByUsername(username);
        List<Post> posts = user.getPosts();
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
        if (title != null && !title.isEmpty() && category != null && !category.isEmpty()) {
            posts = postRepo.findByTitleContainingIgnoreCaseAndCategoryIgnoreCase(title, category);

        } else if (title != null && !title.isEmpty()) {
            posts = postRepo.findByTitleContainingIgnoreCase(title);
        } else if (category != null && !category.isEmpty()) {
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

    public ResponseEntity<String> Addcomment(int postid, String comment) {
        Post post = postRepo.findById(postid).orElseThrow(()-> new RuntimeException("post was not found"));
        List<String> comments = post.getComments();
        comments.add(comment);
        post.setComments(comments);
        postRepo.save(post);
        emailService.sendEmail(post.getUser().getEmail(),"Comment was added to your blog \uD83D\uDD25\uD83D\uDC4F","Someone has just commented on your blog post titled You can view and reply to the comment by logging into your account.");
        return ResponseEntity.status(HttpStatus.OK).body("Comment was added");
    }

    public ResponseEntity<List<postDTO>> findmyPost(String token) {
        String username = jwtservice.extractUserName(token);
        User user = userRepo.findByUsername(username);
        List<Post> post = user.getPosts();

        List<postDTO> response = post.stream()
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
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<postDTO> ViewPost1(Integer id) {
//        if (!userRepo.existsById(id)) {
//            throw new RuntimeException("User not found");
//        }
        Post posts = postRepo.findById(id).orElseThrow(()->new RuntimeException("user not found"));
        postDTO postDTOList = new postDTO(
                posts.getId(),
                posts.getText(),
                posts.getTitle(),
                posts.getCategory(),
                posts.getLikes(),
                posts.getComments(),
                posts.getCreatedat(),
                posts.getUpdatedat()
        );
        return ResponseEntity.ok(postDTOList);
    }

    public ResponseEntity<List<postDTO>> findallPost() {
        List<Post> post = postRepo.findAll();
        List<postDTO> postDTOList = new ArrayList<>();
        for (Post p : post) {
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
        return ResponseEntity.status(HttpStatus.OK).body(postDTOList);
    }
}
