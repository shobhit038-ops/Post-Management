package com.example.blog_management.Controller;
import com.example.blog_management.DTO.ImageResponseDTO;
import com.example.blog_management.DTO.postDTO;
import com.example.blog_management.Model.Post;
import com.example.blog_management.Service.GeminiService;
import com.example.blog_management.Service.Postservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Tag(
        name ="CRUD REST API FOR POST",
        description = "crud rest api for post delete,fetch,update,read"
)
@RestController
@RequestMapping("post")
@CrossOrigin(origins = "*")        
public class PostController {
    @Autowired
    Postservice postservice;

    private final GeminiService geminiService;

     public PostController(GeminiService geminiService){
         this.geminiService = geminiService;
     }

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
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token missing");
        }
        String token = authHeader.substring(7); // Bearer hata diya
          return postservice.putpost(posts,token);
    }

    @Operation(
            summary = "FIND ALL POST REST API",
            description = "REST API to FIND ALL POST"
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
    @GetMapping("findall")
    public ResponseEntity<List<postDTO>> findall(){
        return postservice.findallPost();
    }

    @Operation(
            summary = "FIND MYPOST REST API",
            description = "REST API to FIND MY POST"
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
    @GetMapping("owned")
    public ResponseEntity<List<postDTO>> mypost(HttpServletRequest request){
        String authheader = request.getHeader("Authorization");
        if (authheader == null || !authheader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        String token = authheader.substring(7);
        return postservice.findmyPost(token);
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
            summary = "ADD COMMENT IN POST REST API",
            description = "REST API to ADD COMMENT"
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
    @PostMapping("{id}/comments")
    public ResponseEntity<String> AddComment(@PathVariable int id,@RequestBody String Comment){
        return postservice.Addcomment(id,Comment);
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
            summary = "SEARCH POST  REST API",
            description = "REST API to FIND POST BY USERNAME"
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
    @GetMapping("find/{username}")
    public ResponseEntity<List<postDTO>> viewPost(@PathVariable String username){
        return postservice.ViewPost(username);
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
    @GetMapping("search/{id}")
    public ResponseEntity<postDTO> viewPost(@PathVariable Integer id){
        return postservice.ViewPost1(id);
    }

    @Operation(
            summary = "GET REST API",
            description = "REST API to generate TEXT IN BLOG"
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
    @GetMapping("ai/generate")
    public ResponseEntity<String> generate(@RequestParam String prompt) {
        return ResponseEntity.status(HttpStatus.OK).body(geminiService.generateText(prompt));
    }
}
