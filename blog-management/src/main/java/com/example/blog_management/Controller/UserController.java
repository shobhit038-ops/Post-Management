package com.example.blog_management.Controller;

import com.example.blog_management.DTO.userDTO;
import com.example.blog_management.Model.User;
import com.example.blog_management.Service.JWTservice;
import com.example.blog_management.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
@Tag(
        name = "USER CRUD REST API",
        description = "crud rest api for USER delete,fetch,update,read"
)
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTservice jwtservice;
    @Operation(
            summary = "READ USER REST API",
            description = "REST API to FIND USER BY USERID"
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
    public ResponseEntity<userDTO> getUserById(@PathVariable Integer id){
        return userService.getuserbyid(id);
    }
    @Operation(
            summary = "READ USER REST API",
            description = "REST API to find user by username"
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
    @GetMapping("findbyUsername/{username}")
    public ResponseEntity<userDTO> getuserbyUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }
    @Operation(
            summary = " REST API to register new user",
            description = "REST API to register new user"
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
    @PostMapping("register")
    public User registerUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @Operation(
            summary = "LOGIN USER REST API",
            description = "REST API to LOGIN USER"
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
    @PostMapping("login")
    public String Loginuser(@RequestBody User user){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtservice.generateToken(user.getUsername());
        }
        return "fail";
    }
    @Operation(
            summary = "DELETE USER REST API",
            description = "REST API to DELETE USER BY USERID"
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
    @PostMapping("delete/{id}")
    public String DeleteUser(@PathVariable Integer id){
        userService.deleteuser(id);
        return "deleted";
    }
}