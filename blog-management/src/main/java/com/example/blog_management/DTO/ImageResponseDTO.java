package com.example.blog_management.DTO;

public class ImageResponseDTO {
    private String imageBase64;

    public ImageResponseDTO(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getImageBase64() {
        return imageBase64;
    }
}
