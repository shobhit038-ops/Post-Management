package com.example.blog_management.Controller;
import com.example.blog_management.DTO.postDTO;
import com.example.blog_management.Model.Post;
import com.example.blog_management.Service.Postservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Tag(
        name ="CRUD REST API FOR POST",
        description = "crud rest api for post delete,fetch,update,read"
)
@Controller
@RequestMapping("post")
public class PostController {
    @Autowired
    Postservice postservice;

    @Operation(
            summary = "Create POST REST API",
            description = "REST API to create new POST"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @PostMapping("create")
    public ResponseEntity<String> putpost(@RequestBody Post posts, HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7); // Bearer hata diya
          return postservice.putpost(posts);
    }

    @Operation(
            summary = "UPDATE POST REST API",
            description = "REST API to UPDATE POST"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @PostMapping("update")
    public ResponseEntity<String> updatePost(@RequestBody Post post){
        return postservice.Updatepost(post);
    }
    @Operation(
            summary = "DELETE POST REST API",
            description = "REST API to DELETE POST"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @GetMapping("delete/{id}")
    public ResponseEntity<String> dletepost(@PathVariable Integer id){
        return postservice.DeletePostbyId(id);
    }
    @Operation(
            summary = "FIND POST  REST API",
            description = "REST API to FIND POST BY POSTID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @GetMapping("find/{id}")
    public ResponseEntity<List<postDTO>> viewPost(@PathVariable Integer id){
        return postservice.ViewPost(id);
    }

    @Operation(
            summary = "FIND POST  REST API",
            description = "REST API to FIND POST BY TITLE OR CATEGORY"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @GetMapping("search")
    public ResponseEntity<List<postDTO>> SearchPosts(@RequestParam String title,@RequestParam String category){
        return postservice.SearchPosts(title,category);
    }
}
