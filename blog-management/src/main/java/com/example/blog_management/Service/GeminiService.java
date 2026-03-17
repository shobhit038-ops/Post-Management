package com.example.blog_management.Service;

import java.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apikey;

    // text model
    @Value("${gemini.model}")
    private String model;

    private final RestTemplate restTemplate = new RestTemplate();
    public String generateText(String prompt) {

        String url = "https://generativelanguage.googleapis.com/v1/models/"
                + model + ":generateContent?key=" + apikey;

        Map<String, Object> textPart = Map.of("text", prompt);
        Map<String, Object> partWrapper = Map.of("parts", List.of(textPart));
        Map<String, Object> requestBody = Map.of("contents", List.of(partWrapper));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<Map> response =
                    restTemplate.postForEntity(url, entity, Map.class);
            List<Map> candidates =
                    (List<Map>) response.getBody().get("candidates");
            Map content =
                    (Map) candidates.get(0).get("content");
            List<Map> parts =
                    (List<Map>) content.get("parts");
            return parts.get(0).get("text").toString();

        } catch (Exception e) {
            return "Error calling Gemini API: " + e.getMessage();
        }
    }
}