package com.example.blog_management;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Post management REST API Documentation",
				description = "post management REST API Documentation",
				version = "v1"
		)
)
public class BlogManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogManagementApplication.class, args);
	}
}
