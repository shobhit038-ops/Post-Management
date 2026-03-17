package com.example.blog_management.Service;
import com.example.blog_management.Model.UserPrincipal;
import com.example.blog_management.Model.User;
import com.example.blog_management.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repo.findByUsername(username);
        if(user==null){
            System.out.println("user 404");
            throw new UsernameNotFoundException("user 404");
        }
        return new UserPrincipal(user);
    }
}