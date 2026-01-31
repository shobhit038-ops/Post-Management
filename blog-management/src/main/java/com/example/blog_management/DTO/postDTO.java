package com.example.blog_management.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;
@Schema(name = "POST",
        description = "Schema to hold post information"
)
public class postDTO {
    @Schema(
            description = "post id",example = "123"
    )
    private int id;
    @Schema(
            description = "text related content in post"
    )
    private String text;
    @Schema(
            description = "Title of Post",example = "the great person"
    )
    private String title;
    @Schema(
            description = "Category of Post",example = "TECH"
    )
    private String Category;
    @Schema(
            description = "Likes on Post",example = "12"
    )
    private Boolean Likes;
    @Schema(
            description = "Comments on post",example = "its nice post"
    )
    private List<String> comments;
    @Schema(
            description = "Creation date of Post"
    )
    private LocalDateTime Createdat;
    @Schema(
            description = "Updation date of Post"
    )
    private LocalDateTime Updatedat;

    public postDTO(int id, String text, String title, String category, Boolean likes, List<String> comments, LocalDateTime createdat, LocalDateTime updatedat) {
        this.id = id;
        this.text = text;
        this.title = title;
        Category = category;
        Likes = likes;
        this.comments = comments;
        Createdat = createdat;
        Updatedat = updatedat;
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
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public Boolean getLikes() {
        return Likes;
    }

    public void setLikes(Boolean likes) {
        Likes = likes;
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
