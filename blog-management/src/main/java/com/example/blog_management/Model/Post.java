package com.example.blog_management.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    private int id;
    private String text;
    private String title;
    private String category;
    private Boolean Likes;
    private List<String> comments;
    private LocalDateTime Createdat;
    private LocalDateTime Updatedat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // foreign key in Post table
    private User user;

    public Post(){}
    public  Post(int id, String text, String title, String category, Boolean like, List<String> comments, LocalDateTime createdat, LocalDateTime updatedat,User user) {
        this.id = id;
        this.text = text;
        this.title = title;
        this.category = category;
        Likes = like;
        this.comments = comments;
        Createdat = createdat;
        Updatedat = updatedat;
        this.user = user;
    }

    public Boolean getLikes() {
        return Likes;
    }

    public void setLikes(Boolean likes) {
        Likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        category = category;
    }

    public Boolean getLike() {
        return Likes;
    }

    public void setLike(Boolean like) {
        Likes = like;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreatedat() {
        return Createdat;
    }

    public void setCreatedat(LocalDateTime createdat) {
        Createdat = createdat;
    }

    public LocalDateTime getUpdatedat() {
        return Updatedat;
    }

    public void setUpdatedat(LocalDateTime updatedat) {
        Updatedat = updatedat;
    }
}
